
/**
 * Date class for creating Date objects.
 *
 * @author Hwansu Kim (Billy)
 * @version 02/18/2022
 */
public class Date {
    // INSTANCE DATA //
    private int year;
    private int month;
    private int day;
    
    
    // CONSTRUCTOR //
    /**
     * Constructor for the Date class.
     * 
     * @param year          The year of the date; integer type.
     * @param month         The month of the date; integer type.
     * @param day           The day of the date; integer type.
     */
    public Date(int year, int month, int day) {
        setYear(year);
        setMonth(month);
        setDay(day);
    }
    
    
    // ACCESSORS //
    /**
     * Accessor for the object's year.
     * 
     * @return The year of the date; integer type.
     */
    public int getYear() {
        return year;
    }
    
    
    /**
     * Accessor for the object's month.
     * 
     * @return The month of the date; integer type.
     */
    public int getMonth() {
        return month;
    }
    
    
    /**
     * Accessor for the object's day.
     * 
     * @return The day of the date; integer type.
     */
    public int getDay() {
        return day;
    }
    
    
    // MUTATORS //
    /**
     * Mutator for the object's year.
     * 
     * @param year          The year of the date; integer type.
     */
    public void setYear(int year) {
        if (year < 0) {
            throw new IllegalArgumentException("year must not be negative.");
        }
        this.year = year;
    }
    
    
    /**
     * Mutator for the object's month.
     * 
     * @param month         The month of the date; integer type.
     */
    public void setMonth(int month) {
        checkDateRange("month", month, 1, 12);
        this.month = month;
    }
    
    
    /**
     * Mutator for the object's day.
     * 
     * @param day           The day of the date; integer type.
     */
    public void setDay(int day) {
        checkDateRange("day", day, 1, 31);
        this.day = day;
    }
    
    
    // OUTPUT METHODS //
    /**
     * For returning a string output for Date objects.
     * 
     * @return The date in MM/DD/YYYY format.
     */
    public String toString() {
        return String.format("%02d", month) + "/" + String.format("%02d", day) + "/" + year;
    }
    
    
    /**
     * A method for returning the date in a YYYYMMDD format.
     * 
     * @return The date in a YYYYMMDD format; integer type.
     */
    public int toInt() {
        return year * 10000 + month * 100 + day;
    }
    
    
    // MISC. METHODS //
    /**
     * Range validation for methods with integer type parameters.
     * 
     * @param dateType      The category of the date; i.e. the month. String type.
     * @param numToCheck    The current number to pass through the validation check; integer type.
     * @param lowestNum     The lowest number in the range; integer type.
     * @param highestNum    The highest number in the range; integer type.
     */
    private void checkDateRange(String dateType,int numToCheck, int lowestNum, int highestNum) {
        if (numToCheck < lowestNum || numToCheck > highestNum) {
            throw new IllegalArgumentException(dateType + "must be between" + lowestNum + "and" + highestNum + ".");
        }
    }
}
