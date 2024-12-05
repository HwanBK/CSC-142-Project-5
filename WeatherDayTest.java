import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class WeatherDayTest.
 *
 * @author  Hwansu Kim (Billy)
 * @version 02/18/2022
 */
public class WeatherDayTest
{
    /**
     * Default constructor for test class WeatherDayTest
     */
    public WeatherDayTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    
    // HAPPY PATH TEST //
    @Test
    public void testConstructorAndGets() {
        WeatherDay testWeatherDay = new WeatherDay(new Date(2018, 2, 28), 78, 45.5, 10, 7.0, 2.0, 0.3);
        assertEquals(20180228, testWeatherDay.getDate().toInt());
        assertEquals(78, testWeatherDay.getTempHigh());
        assertEquals(45.5, testWeatherDay.getTempAvg(), 0.001);
        assertEquals(10, testWeatherDay.getTempLow());
        assertEquals(7.0, testWeatherDay.getHumidityAvg(), 0.001);
        assertEquals(2.0, testWeatherDay.getWindAvg(), 0.001);
        assertEquals(0.3, testWeatherDay.getPrecipitation(), 0.001);
    }
    
    
    @Test
    public void testCalcHeatIndex() {
        WeatherDay testWeatherDay = new WeatherDay(new Date(2018, 2, 28), 78, 45.5, 10, 7.0, 2.0, 0.3);
        assertEquals(76.43946024, testWeatherDay.calcHeatIndex(), 0.00000001);
    }
    
    
    // EXCEPTION THROWING TEST //
    @Test (expected = IllegalArgumentException.class)
    public void testDateNull() {
        WeatherDay testWeatherDay = new WeatherDay(null, 78, 45.5, 10, 7.0, 2.0, 0.3);
    }
}
