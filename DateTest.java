import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DateTest.
 *
 * @author  Hwansu Kim (Billy)
 * @version 02/18/2022
 */
public class DateTest
{
    /**
     * Default constructor for test class DateTest
     */
    public DateTest()
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
    
    
    // HAPPY PATH TESTS //
    @Test
    public void testConstructor() {
        Date testDate = new Date(2023, 01, 01);
        assertEquals(2023, testDate.getYear());
        assertEquals(01, testDate.getMonth());
        assertEquals(01, testDate.getDay());
    }
    
    
    @Test
    public void testSetsAndGets() {
        Date testDate = new Date(2023, 01, 01);
        testDate.setYear(1990);
        assertEquals(1990, testDate.getYear());
        testDate.setMonth(12);
        assertEquals(12, testDate.getMonth());
        testDate.setDay(31);
        assertEquals(31, testDate.getDay());
    }
    
    
    // EXCEPTION THROWING TESTS //
    @Test (expected = IllegalArgumentException.class)
    public void testYearNegative() {
        new Date(-0001, 01, 01);
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testMonthAndDayRange() {
        new Date(2000, -01, 01);
        new Date(2000, 13, 01);
        new Date(2000, 01, -01);
        new Date(2000, 01, 32);
    }
}
