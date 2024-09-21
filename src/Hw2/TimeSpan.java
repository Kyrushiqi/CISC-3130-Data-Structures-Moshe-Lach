/*
Static Factory Method: A static method that return the same type of object as its class/interface.
Professor's Static Factory Method def: a static method (whether in a class or in an interface) that returns an object of
the type of the class/interface. Some interfaces and classes in the Java library use static factory
methods heavily; e.g., java.time.LocalDate.
 */
package Hw2;
import java.util.Objects;

/*
TimeSpan objects: Represent positive amount of time measured in hours and minutes -- 2h30m

Feedback from Professor (Approval Granted):
Great! (I think the constructor could be rewritten to take only one parameter, the total minutes.)
YAYYY!! and I agree :D
 */
public class TimeSpan implements Comparable<TimeSpan>{
    private int totalMins;

    private TimeSpan(int hours, int minutes) {
        this.totalMins = (hours*60) + minutes;
    }

    /** Static Factory Method:
     * Throws an IllegalArgumentException if hours is negative.
     * @param hours
     * @return TimeSpan object representing the specified # of hours.
     */
    public static TimeSpan ofHours(int hours) {
        if(hours < 0){
            throw new IllegalArgumentException();
        }
        return new TimeSpan(hours, 0);
    }

    /** Static Factory Method:
     * Throws an IllegalArgumentException if minutes is negative.
     * @param totalMinutes
     * @return TimeSpan object representing the specified # of minutes.
     */
    public static TimeSpan ofMinutes(int totalMinutes){
        if(totalMinutes < 0){
            throw new IllegalArgumentException();
        }
        return new TimeSpan(0, totalMinutes);
    }

    /** Static Factory Method:
     * Throws an IllegalArgumentException if hours or minutes is negative.
     * @param hours, minutes
     * @return TimeSpan object representing the specified # of hours and minutes.
     */
    public static TimeSpan ofHoursAndMinutes(int hours, int minutes){
        if(hours < 0 || minutes < 0){
            throw new IllegalArgumentException();
        }
        return new TimeSpan(hours, minutes);
    }

    public int getHours(){
        return totalMins / 60;
    }

    /**
     * Should always return a value between 0 and 59 (inclusive).
     */
    public int getMinutes(){
        return totalMins % 60;
    }

    public int getTotalMinutes(){
        return totalMins;
    }

    @Override // Object Class
    public String toString(){
        return getHours() + "h" + getMinutes() + "m";
    }

    /**
     * If time = time then return true.
     * EX: 2h30m = 2h30m -> True
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof TimeSpan){ // if o is a TimeSpan object...
            TimeSpan other = (TimeSpan) o; // downcasts o to TimeSpan
            return this.totalMins == other.totalMins;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(totalMins);
    }

    /**
     * Compared by length.
     * Shorter TimeSpan < Longer TimeSpan
     */
    @Override
    public int compareTo(TimeSpan other){
        return Integer.compare(this.totalMins, other.totalMins);
    }

    /**
     * Adds two TimeSpan objects together and returns a new TimeSpan object with the sum.
     * EX: 2h30m + 1h45m = 4h15m
     */
    public TimeSpan plus(TimeSpan other){
        return new TimeSpan(0, this.totalMins + other.totalMins); // might be incorrect
    }

    /**
     * Adds hours to 'this' TimeSpan
     * EX: 'this' = 2h30m
     * Add 5 hours to 'this' = 7h30m
     */
    public TimeSpan plusHours(int hours){
        if(hours < 0){
            throw new IllegalArgumentException();
        }
        return new TimeSpan(0, this.totalMins + (hours*60));
    }

    /**
     * Adds minutes to 'this' TimeSpan
     * EX: 'this' = 2h30m
     * Add 90 minutes to 'this' = 4h0m
     */
    public TimeSpan plusMinutes(int minutes){
        if(minutes < 0){
            throw new IllegalArgumentException();
        }
        return new TimeSpan(0, this.totalMins + minutes);
    }

    /**
     * Adds hours and minutes to 'this' TimeSpan
     * EX: 'this' = 2h30m
     * Add 1h45m to 'this' = 4h15m
     */
    public TimeSpan plusHoursAndMinutes(int hours, int minutes){
        if(hours < 0 || minutes < 0){
            throw new IllegalArgumentException();
        }
        return new TimeSpan(0, this.totalMins + (hours*60) + minutes);
    }
}
