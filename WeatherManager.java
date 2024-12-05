import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Double;

/**
 * WeatherManager class for constructing WeatherDay array and calculations.
 *
 * @author Hwansu Kim (Billy)
 * @version 02/18/2022
 */
public class WeatherManager {
    // INSTANCE DATA //
    private WeatherDay[] weatherData;

    
    // CONSTRUCTOR //
    /**
     * Constructor for the WeatherManager class.
     * 
     * @param weatherFile       The file, containing weather statistics, to be read; File type.
     */
    public WeatherManager(File weatherFile) throws FileNotFoundException{
        Scanner fileIn = new Scanner(weatherFile);
        int numOfDays = fileIn.nextInt();
        fileIn.nextLine();
        fileIn.nextLine();
        weatherData = new WeatherDay[numOfDays];

        for (int dayNum = 0; dayNum < numOfDays; dayNum++) {
            String[] lineArray = fileIn.nextLine().split(",");

            int year = Integer.parseInt(lineArray[0]);
            int month = Integer.parseInt(lineArray[1]);
            int day = Integer.parseInt(lineArray[2]);
            int tempHigh = Integer.parseInt(lineArray[3]);
            double tempAvg = Double.parseDouble(lineArray[4]);
            int tempLow = Integer.parseInt(lineArray[5]);
            double humidityAvg = Double.parseDouble(lineArray[10]);
            double windAvg = Double.parseDouble(lineArray[13]);
            double precipitation = Double.parseDouble(lineArray[18]);

            Date date = new Date(year, month, day);
            WeatherDay weatherDay = new WeatherDay(date, tempHigh, tempAvg, tempLow, humidityAvg, windAvg, precipitation);
            weatherData[dayNum] = weatherDay;
        }
        sortWeatherDataByDate();
        fileIn.close();
    }

    
    // ACCESSORS //
    /**
     * Returns the length of the instantiated WeatherDay[].
     * 
     * @return      The length of the array; integer type.
     */
    public int getWeatherDayCount() {
        int weatherDayCount = weatherData.length;
        return weatherDayCount;
    }

    
    /**
     * Returns the WeatherDay object in the specified index of the array.
     * 
     * @param       The desired index number; integer type.
     * @return      The WeatherDay object in the instantiated WeatherDay[].
     */
    public WeatherDay getWeatherDay(int index) {
        if (index < 0 || index > getWeatherDayCount()) {
            throw new IllegalArgumentException("Index must not be less than 0 or greater than " + getWeatherDayCount());
        }
        WeatherDay weatherDay = weatherData[index];
        return weatherDay;
    }

    
    /**
     * Returns the index, of the array, of the specified Date object.
     * 
     * @param  date                 The date in a Date object; Date type.
     * @return weatherDayDate       The index holding the specified date; integer type.
     */
    public int findWeatherDay(Date date) {
        int weatherDayDate = -1;
        int index = 0;
        while (weatherDayDate == -1 && index < getWeatherDayCount()) {
            if (weatherData[index].getDate().getYear() == date.getYear()) {
                if (weatherData[index].getDate().getMonth() == date.getMonth()) {
                    if (weatherData[index].getDate().getDay() == date.getDay()) {
                        weatherDayDate = index;
                    }
                }
            }
            index += 1;
        }
        return weatherDayDate;
    }

    
    // CALCULATION METHODS //
    /**
     * Calculates the average of the high temperatures of the specified year and month.
     * 
     * @param year              The desired year to search; integer type.
     * @param month             The desired month to search; integer type.
     * @return avgHighTemp      The average of the high temperatures; double type.
     */
    public double calcAvgHighTemp(int year, int month) {
        double highTempTotal = 0.0;
        int highTempCount = 0;
        int index = 0;
        boolean isPastMonthOfYear = false;
        while (!isPastMonthOfYear && index < getWeatherDayCount()) {
            if (weatherData[index].getDate().getYear() == year) {
                if (weatherData[index].getDate().getMonth() <= month) {
                    if (weatherData[index].getDate().getMonth() == month) {
                        highTempTotal += weatherData[index].getTempHigh();
                        highTempCount += 1;
                    }
                } else {
                    isPastMonthOfYear = true;
                }
            }
            index += 1;
        }

        double avgHighTemp = highTempTotal / highTempCount;
        notANumberSignal(avgHighTemp);
        return avgHighTemp;
    }

    
    /**
     * Calculates the average of the low temperatures of the specified year and month.
     * 
     * @param year              The desired year to search; integer type.
     * @param month             The desired month to search; integer type.
     * @return avgLowTemp       The average of the low temperatures; double type.
     */
    public double calcAvgLowTemp(int year, int month) {
        double lowTempTotal = 0.0;
        int lowTempCount = 0;
        int index = 0;
        boolean isPastMonthOfYear = false;
        while (!isPastMonthOfYear && index < getWeatherDayCount()) {
            if (weatherData[index].getDate().getYear() == year) {
                if (weatherData[index].getDate().getMonth() <= month) {
                    if (weatherData[index].getDate().getMonth() == month) {
                        lowTempTotal += weatherData[index].getTempLow();
                        lowTempCount += 1;
                    }
                } else {
                    isPastMonthOfYear = true;
                }
            }
            index += 1;
        }
        double avgLowTemp = lowTempTotal / lowTempCount;
        notANumberSignal(avgLowTemp);
        return avgLowTemp;
    }

    
    /**
     * Calculates the total amount of rainfall in the specified year and month.
     * 
     * @param year              The desired year to search; integer type.
     * @param month             The desired month to search; integer type.
     * @return rainTotal        The total amount of rainfall; double type.
     */
    public double calcRainTotal(int year, int month) {
        double rainTotal = 0.0;
        int index = 0;
        boolean isPastMonthOfYear = false;
        while (!isPastMonthOfYear && index < getWeatherDayCount()) {
            if (weatherData[index].getDate().getYear() == year) {
                if (weatherData[index].getDate().getMonth() <= month) {
                    if (weatherData[index].getDate().getMonth() == month) {
                        rainTotal += weatherData[index].getPrecipitation();
                    }
                } else {
                    isPastMonthOfYear = true;
                }
            }
            index += 1;
        }
        return rainTotal;
    }

    
    /**
     * Compares the total rainfall of each month in the specified year and returns the month with the most rainfall.
     * 
     * @param year              The desired year to search; integer type.
     * @return rainestMonth     The rainest month of the year; integer Type.
     */
    public int calcRainiestMonth(int year) {
        boolean foundStartMonth = false;
        int index = 0;
        int startMonth = -1;
        while (!foundStartMonth && index < getWeatherDayCount()) {
            if (weatherData[index].getDate().getYear() == year) {
                startMonth = weatherData[index].getDate().getMonth();
                foundStartMonth = true;
            }
            index += 1;
        }
        
        int rainestMonth = -1;
        double totalRainOne = -999.0;
        double totalRainTwo = -999.0;
        int monthToCheckOne = 0;
        int monthToCheckTwo = 0;
        for (int month = startMonth; month <= 12; month++) {
            if (totalRainOne == -999.0) {
                totalRainOne = calcRainTotal(year, month);
                monthToCheckOne = month;
            } else {
                totalRainTwo = calcRainTotal(year, month);
                monthToCheckTwo = month;
            }
            if (totalRainOne >= totalRainTwo) {
                rainestMonth = monthToCheckOne;
            } else {
                rainestMonth = monthToCheckTwo;
            }
        }
        return rainestMonth;
    }

    
    /**
     * Finds the longest warming trend of the specified year and returns the first date of the longest warming trend.
     * 
     * @param year                      The desired year to search; integer type.
     * @return longestWarmingTrend      The date of the first day of the longest warming trend; Date type.
     */
    public Date calcLongestWarmingTrend(int year) {
        Date trendStartDate = null;
        Date longestWarmingTrend = null;
        int consecutiveDays = 0;
        int longestConsecDays = 0;
        for (int index = 1; index < getWeatherDayCount(); index++) {
            if (weatherData[index].getDate().getYear() == year) {
                if (consecutiveDays == 0 && weatherData[index].getTempHigh() > weatherData[index - 1].getTempHigh()) {
                    trendStartDate = weatherData[index - 1].getDate();
                    consecutiveDays += 1;
                } else if (consecutiveDays > 0 && weatherData[index].getTempHigh() > weatherData[index - 1].getTempHigh()){
                    consecutiveDays += 1;
                } else {
                    if (longestConsecDays < consecutiveDays) {
                        longestWarmingTrend = trendStartDate;
                        longestConsecDays = consecutiveDays;
                    }
                    consecutiveDays = 0;
                }
            }
        }
        return longestWarmingTrend;
    }

    
    // INSERTION SORT METHOD //
    /**
     * Sorts the dates, in YYYYMMDD format, in ascending order.
     */
    private void sortWeatherDataByDate() {
        for (int pass = 1; pass < getWeatherDayCount(); pass++) {
            int intTemp = weatherData[pass].getDate().toInt();
            WeatherDay temp = weatherData[pass];
            int checkPos = pass - 1;
            while (checkPos >= 0 && intTemp < weatherData[checkPos].getDate().toInt()) {
                weatherData[checkPos + 1] = weatherData[checkPos];
                checkPos--;
            }
            weatherData[checkPos + 1] = temp;
        }
    }
    
    
    // OUTPUT METHOD //
    public String toString() {
        return "There are " + getWeatherDayCount() + " days accounted for.";
    }

    
    // EXTRA CREDIT //
    /**
     * Stores the average high temps of each month, of the specified year, into an array.
     * 
     * @param year          The desired year to search; integer type.
     * @return tempArray    The array of average monthly high temperatures.
     */
    public double[] calcAvgMonthlyHighTemps(int year) {
        double[] tempArray = new double[13];
        tempArray[0] = -1.0;
        for (int month = 1; month <= 12; month++) {
            if (Double.isNaN(calcAvgHighTemp(year, month))) {
                tempArray[month] = -1.0;
            } else {
                tempArray[month] = calcAvgHighTemp(year, month);
            }
        }
        return tempArray;
    }
    
    
    // MISC METHODS //
    private void notANumberSignal(double temp) {
        if (Double.isNaN(temp)) {
            temp = -9999999.0;
        }
    }
}
