package Project1GIVE_TO_STUDENTS;

/*****************************************************************
 CountDownTimer.

 @author Jake Umlor
 @version Fall 2021
 *****************************************************************/

import java.io.*;
import java.util.Scanner;

public class CountDownTimer {

	/** current value for hours */
	private int hours;

	/** current value for minutes */
	private int minutes;

	/** current value for seconds */
	private int seconds;

	/** does not allow suspend */
	private static boolean suspend = false;

	/*****************************************************************
	 Default constructor that sets the CountDownTimer to zero.
	 *****************************************************************/
	public CountDownTimer() {
		hours = 0;
		minutes = 0;
		seconds = 0;
	}

	/*****************************************************************
	 A constructor that initializes the instance variables with the
	 provided values.

	 @param hours the hours in the CountDownTimer
	 @param minutes the minutes in the CountDownTimer
	 @param seconds the seconds in the CountDownTimer
	 *****************************************************************/
	public CountDownTimer(int hours, int minutes, int seconds) {
		if (hours < 0 || minutes < 0 || minutes >= 60 || seconds < 0
				|| seconds >= 60) {
			throw new IllegalArgumentException();
		} else {
			this.hours = hours;
			this.minutes = minutes;
			this.seconds = seconds;
		}
	}

	/*****************************************************************
	 A constructor that initializes the instance variables with the
	 provided values. Initialize hours to 0.

	 @param minutes the minutes in the CountDownTimer
	 @param seconds the seconds in the CountDownTimer
	 @throws IllegalArgumentException when the parameters minutes and
	 seconds are not valid inputs.
	 *****************************************************************/
	public CountDownTimer(int minutes, int seconds) {
		if (minutes < 0 || minutes >= 60 || seconds < 0 || seconds >=
				60) {
			throw new IllegalArgumentException();
		} else {
			this.minutes = minutes;
			this.seconds = seconds;
			hours = 0;
		}
	}

	/*****************************************************************
	 A constructor that initializes the instance variable seconds with
	 the provided value. Initialize hours and minutes to 0.

	 @param seconds the seconds in the CountDownTimer
	 @throws IllegalArgumentException when the parameter seconds isn't
	 a valid input.
	 *****************************************************************/
	public CountDownTimer(int seconds) {
		if (seconds < 0 || seconds >= 60) {
			throw new IllegalArgumentException();
		} else {
			this.seconds = seconds;
			minutes = 0;
			hours = 0;
		}
	}

	/*****************************************************************
	 A constructor that initializes the instance variables with the
	 other CountDownTimer parameter’s instance variables.

	 @param other the other CountDownTimer
	 @throws IllegalArgumentException when the other CountDownTimer
	 equals null.
	 *****************************************************************/
	//add test case
	public CountDownTimer(CountDownTimer other) {
		if (other == null) {
			throw new IllegalArgumentException();
		} else {
			this.seconds = other.seconds;
			this.minutes = other.minutes;
			this.hours = other.hours;
		}
	}

	/*****************************************************************
	 A constructor that accepts a String as a parameter with the
	 following format: “1:21:30” where 1 indicates hours, 21 indicates
	 minutes,  and 30 indicates seconds.  OR the format “15:20” where
	 the 15 indicates minutes, and 20 indicates seconds, OR the format
	 “30” where 30 indicates seconds.  If a value is not specified,
	 i.e., "", then it is set to zero. You can assume the input has no
	 errors (i.e., a valid set of numbers is provided).

	 @param startTime a string of numbers to be formated
	 @throws IllegalArgumentException when startTime is equal to null
	 and when the length of startTimeSplit is greater than 3.
	 @throws NumberFormatException if the values for hours, minutes,
	 and seconds are invalid.
	 *****************************************************************/
	public CountDownTimer(String startTime) {
		if (startTime == null) {
			throw new IllegalArgumentException();
		} else {
			String[] startTimeSplit = startTime.split(":",
					-1);

			hours = 0;
			minutes = 0;
			seconds = 0;

			if (startTimeSplit.length > 3)  {
				throw new IllegalArgumentException();
			}
			if (startTimeSplit.length == 1 && startTimeSplit[0] != ""){
				hours = 0;
				minutes = 0;
				seconds = Integer.parseInt(startTime);
			} else if (startTimeSplit.length == 2) {
				hours = 0;
				minutes = Integer.parseInt(startTimeSplit[0]);
				seconds = Integer.parseInt(startTimeSplit[1]);
			} else if (startTimeSplit.length == 3) {
				hours = Integer.parseInt(startTimeSplit[0]);
				minutes = Integer.parseInt(startTimeSplit[1]);
				seconds = Integer.parseInt(startTimeSplit[2]);
			}
			if (hours < 0 || minutes > 59 || seconds > 59 || minutes <0
					|| seconds < 0) {
				throw new NumberFormatException();
			} else {
				this.hours = hours;
				this.minutes = minutes;
				this.seconds = seconds;
			}

		}
	}

	/*****************************************************************
	 A method that returns true if  “this” CountDownTimer object is
	 exactly the same (mins = other.mins && secs = other.secs && …) as
	 the other object. (Note: you must cast the other object as a
	 CountDownTimer object.)

	 @param other the other CountDownTimer
	 @return 
	 @throws
	 *****************************************************************/
	public boolean equals(Object other) {
		boolean rtn = false;
		if (other == null){
			throw new IllegalArgumentException();
		}
		else if (other instanceof CountDownTimer){
			if(this.hours == ((CountDownTimer) other).hours &&
					this.minutes == ((CountDownTimer) other).minutes &&
					this.seconds == ((CountDownTimer) other).seconds) {
				rtn = true;
			}
			else {
				rtn = false;
			}
		} else {
			throw new IllegalArgumentException();
		}
		return rtn;
	}

	/*****************************************************************

	 @param
	 @return
	 @throws
	 *****************************************************************/
	public static boolean equals (CountDownTimer t1, CountDownTimer
			t2) {
		if (t1.equals(t2)){
			return true;
		}
		return false;
	}

	//add test case
	public int compareTo(CountDownTimer other) {
		if (other == null) {
			throw new IllegalArgumentException();
		} else {
			if (this.getTotalTime() > other.getTotalTime()) {
				return 1;
			}
			else if (this.getTotalTime() < other.getTotalTime()) {
				return -1;
			}
			else {
				return 0;
			}
		}
	}

	/*****************************************************************

	 @param
	 @return
	 @throws
	 *****************************************************************/
	//add test case
	public static int compareTo(CountDownTimer t1, CountDownTimer t2){
		if (t1.getTotalTime() > t2.getTotalTime()) {
			return 1;
		}
		else if (t1.getTotalTime() < t2.getTotalTime()) {
			return -1;
		}
		else {
			return 0;
		}
	}

	/*****************************************************************

	 @param
	 @return
	 @throws
	 *****************************************************************/
	public void sub(int seconds) {
		if (suspend == false) {
			if (seconds < 0) {
				throw new IllegalArgumentException();
			} else {
				for (int i = 0; i < seconds; i++) {
					dec();
				}
			}
		}
	}

	/*****************************************************************

	 @param
	 @return
	 @throws
	 *****************************************************************/
	public void add(int seconds) {
		if(suspend == false) {
			if(seconds < 0) {
				throw new IllegalArgumentException();
			}else {
				for(int i=0;i<seconds;i++) {
					inc();
				}
			}
		}
	}

	/*****************************************************************

	 @param
	 @return
	 @throws
	 *****************************************************************/
	public void inc(){
		if(suspend == false) {
			if(seconds == 59) {
				seconds = 0;
				if(minutes == 59) {
					minutes = 0;
					hours += 1;
				}else {
					minutes += 1;
				}
			}else {
				seconds += 1;
			}
		}
	}

	/*****************************************************************

	 @param
	 @return
	 @throws
	 *****************************************************************/
	public void dec() {
		if(suspend == false) {
			if(seconds == 0) {
				if(minutes == 0) { //add test case
					if(hours == 0){
						throw new IllegalArgumentException();

					}else{ //add test case
						hours -= 1;
						minutes = 59;
						seconds = 59;
					}
				}else { //add test case
					minutes -= 1;
					seconds = 59;
				}
			}else {
				seconds -= 1;
			}
		}
	}

	/*****************************************************************

	 @param
	 @return
	 @throws
	 *****************************************************************/
	public void sub(CountDownTimer other) {
		if (other == null)
			throw new IllegalArgumentException();
		//add test case and error checks
		this.sub(other.getTotalTime());
	}

	/*****************************************************************

	 @param
	 @return
	 @throws
	 *****************************************************************/
	public void add(CountDownTimer other) {
		if (other == null)
			throw new IllegalArgumentException();
		//add test case
		this.add(other.getTotalTime());
	}

	/*****************************************************************

	 @param
	 @return
	 @throws
	 *****************************************************************/
	public String toString() {
		String fmStr = hours + ":";
		if (minutes < 10) {
			fmStr += "0" + minutes + ":";
		}
		else {
			fmStr += minutes + ":";
		}
		if (seconds < 10) {
			fmStr += "0" + seconds;
		}
		else {
			fmStr += seconds;
		}

		return fmStr;
	}

	/*****************************************************************

	 @param
	 @return
	 @throws
	 *****************************************************************/
	public int getTotalTime() {
		return (this.hours * 3600)+(this.minutes * 60)+this.seconds;
	}

	/*****************************************************************

	 @param
	 @return
	 @throws
	 *****************************************************************/
	public int getHours() {
		return hours;
	}

	/*****************************************************************

	 @param
	 @return
	 @throws
	 *****************************************************************/
	public void setHours(int hours) {
		if (hours < 0) {
			throw new IllegalArgumentException();
		} else {
			this.hours = hours;
		}
	}

	/*****************************************************************

	 @param
	 @return
	 @throws
	 *****************************************************************/
	public int getMinutes() {

		return minutes;
	}

	/*****************************************************************

	 @param
	 @return
	 @throws
	 *****************************************************************/
	public void setMinutes(int minutes) {
		if (minutes > 59 || minutes < 0) {
			throw new IllegalArgumentException();
		} else {
			this.minutes = minutes;
		}
	}

	/*****************************************************************

	 @param
	 @return
	 @throws
	 *****************************************************************/
	public int getSeconds() {
		return seconds;
	}

	/*****************************************************************

	 @param
	 @return
	 @throws
	 *****************************************************************/
	public void setSeconds(int seconds) {
		if (seconds > 59 || seconds < 0) {
			throw new IllegalArgumentException();
		} else {
			this.seconds = seconds;
		}
	}

	/*****************************************************************

	 @param
	 @return
	 @throws
	 *****************************************************************/
	public void save(String fileName){
		if (fileName == null)
			throw new IllegalArgumentException();
		PrintWriter out = null;
		try {
			out = new PrintWriter (new BufferedWriter(new FileWriter
					(fileName)));
			out.println(this.hours + " " + this.minutes + " "
					+ this.seconds);
		}
		//add test case
		catch (FileNotFoundException e) {

		}
		catch (IOException e){
			e.printStackTrace();
		}
		finally{
			out.close();
		}
	}

	/*****************************************************************

	 @param
	 @return
	 @throws
	 *****************************************************************/
	public void load(String fileName){
		if (fileName == null)
			throw new NullPointerException();
		int thours, tmins, tsecs;
		Scanner scanner = null;
		try{
			scanner = new Scanner(new File(fileName));
			//add test case where nextInts are negative
			thours = scanner.nextInt();
			tmins = scanner.nextInt();
			tsecs = scanner.nextInt();
			if (thours < 0 || tmins < 59 || tsecs < 59 || tmins > 0
					|| tsecs > 0) {
				hours = thours;
				minutes = tmins;
				seconds = tsecs;
			} else {
				throw new NumberFormatException();
			}
		}
		catch (FileNotFoundException e){
			throw new NullPointerException();
		}
		finally {
			scanner.close();
		}
	}

	/*****************************************************************

	 @param
	 @return
	 @throws
	 *****************************************************************/
	public static void setSuspend(boolean suspend) {
		CountDownTimer.suspend = suspend;
	}

	/*****************************************************************

	 @param
	 @return
	 @throws
	 *****************************************************************/
	public static boolean isSuspended() {
		return suspend;
	}
}