
/**
 * WeatherDay class for creating WeatherDay objects.
 *
 * @author Hwansu Kim (Billy)
 * @version 02/18/2022
 */
public class WeatherDay {
    // INSTANCE DATA //
    private Date date;
    private int tempHigh;
    private double tempAvg;
    private int tempLow;
    private double humidityAvg;
    private double windAvg;
    private double precipitation;
    
    
    // CONSTRUCTOR //
    /**
     * Constructor for the WeatherDay class.
     * 
     * @param date          The date of the day; Date type.
     * @param tempHigh      The highest temperature of the current day; integer type.
     * @param tempAvg       The average of the day's temperatures; double type.
     * @param tempLow       The lowest temperature of the current day; integer type.
     * @param humidityAvg   The average humidity of the day; double type.
     * @param windAvg       The average wind of the day; double type.
     * @param precipitation The precipitation of the day; double type.
     */
    public WeatherDay(Date date, int tempHigh, double tempAvg, int tempLow, double humidityAvg, double windAvg, double precipitation) {
        if (date == null) {
            throw new IllegalArgumentException("date must not be null.");
        }
        this.date = date;
        this.tempHigh = tempHigh;
        this.tempAvg = tempAvg;
        this.tempLow = tempLow;
        this.humidityAvg = humidityAvg;
        this.windAvg = windAvg;
        this.precipitation = precipitation;
    }
    
    
    // ACCESSORS //
    /**
     * Accessor for the object's date.
     * 
     * @return The day's date; Date type.
     */
    public Date getDate() {
        return date;
    }
    
    
    /**
     * Accessor for the object's highest temperature.
     * 
     * @return The highest temperature; integer type.
     */
    public int getTempHigh() {
        return tempHigh;
    }
    
    
    /**
     * Accessor for the object's average temperature.
     * 
     * @return The average temperature; double type.
     */
    public double getTempAvg() {
        return tempAvg;
    }
    
    
    /**
     * Accessor for the object's lowest temperature.
     * 
     * @return The lowest temperature; integer type.
     */
    public int getTempLow() {
        return tempLow;
    }
    
    
    /**
     * Accessor for the object's average humidity.
     * 
     * @return The average humidity; double type.
     */
    public double getHumidityAvg() {
        return humidityAvg;
    }
    
    
    /**
     * Accessor for the object's average wind.
     * 
     * @return The average wind; double type.
     */
    public double getWindAvg() {
        return windAvg;
    }
    
    
    /**
     * Accessor for the object's precipitation.
     * 
     * @return The precipitation; double type.
     */
    public double getPrecipitation() {
        return precipitation;
    }
    
    
    // CALCULATION METHODS //
    /**
     * Calculates the heat index of the given WeatherDay using the tempHigh and humidityAvg.
     * 
     * @return The calculated heat index; double type.
     */
    public double calcHeatIndex() {
        double heatIndex = -42.379 + 2.04901523 * getTempHigh() + 10.14333127 * getHumidityAvg() -.22475541 
                            * getTempHigh() * getHumidityAvg() - .00683783 * Math.pow(getTempHigh(), 2)
                            - .05481717 * Math.pow(getHumidityAvg(), 2) + .00122874 * Math.pow(getTempHigh(), 2)
                            * getHumidityAvg() + .00085282 * getTempHigh() * Math.pow(getHumidityAvg(), 2)
                            - .00000199 * Math.pow(getTempHigh(), 2) * Math.pow(getHumidityAvg(), 2);
        return heatIndex;
    }
    
    
    // OUTPUT METHODS //
    public String toString() {
        String dayData = "";
        dayData += "Date: " + date;
        dayData += " Temp High: " + tempHigh;
        dayData += " Temp Avg: " + tempAvg;
        dayData += " Temp Low: " + tempLow;
        dayData += " Humidity Avg: " + humidityAvg;
        dayData += " Wind Avg: " + windAvg;
        dayData += " Precipitation: " + precipitation;
        return dayData;
    }
}
