import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * Client code testing for WeatherManager, WeatherDay, and Date.
 *
 * @author Hwansu Kim (Billy)
 * @version 02/18/2022
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.print('\u000c');
        File weatherFile = new File("SeattleWeather.txt");
        WeatherManager newFile = new WeatherManager(weatherFile);
        
        for (int index = 0; index < newFile.getWeatherDayCount(); index++) {
            System.out.println(newFile.getWeatherDay(index));
            System.out.println(newFile.getWeatherDay(index).calcHeatIndex());
        }
        
        
        System.out.println("Longest warming trend start: " + newFile.calcLongestWarmingTrend(2018));
        System.out.println("Weather day count: " + newFile.getWeatherDayCount());
        System.out.println("Get weather day: " + newFile.getWeatherDay(0));
        System.out.println("Find weather day: " + newFile.findWeatherDay(new Date(2019, 2, 27)));
        
        Date newDate = new Date(2022, 12, 27);
        System.out.println("Date toInt: " + newDate.toInt());
        
        System.out.println("Average high temp: " + newFile.calcAvgHighTemp(2018, 2));
        System.out.println("Average low temp: " + newFile.calcAvgLowTemp(2018, 2));
        System.out.println("Rain total: " + newFile.calcRainTotal(2018, 2));
        System.out.println("Rainest month: " + newFile.calcRainiestMonth(2018));
        System.out.println("Calc average monthly high temps: " + Arrays.toString(newFile.calcAvgMonthlyHighTemps(2018)));
    }
}
