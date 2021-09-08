package Project1GIVE_TO_STUDENTS;

import java.io.*;
import java.util.Scanner;

public class CountDownTimer {

	private int hours;
	private int minutes;
	private int seconds;


	public CountDownTimer() {
		hours = 0;
		minutes = 0;
		seconds = 0;
	}

	public CountDownTimer(int hours, int minutes, int seconds) {
		if (hours > -1) {
			this.hours = hours;
		} else {
			throw new IllegalArgumentException("Hours cannot be negative");
		}

		if (minutes > -1) {
			if (minutes < 60) {
				this.minutes = minutes;
			}
			else {
				throw new IllegalArgumentException("Minutes cannot be greater than 59");
			}
		} else {
			throw new IllegalArgumentException("Minutes cannot be negative");
		}

		if (seconds > -1) {
			if (seconds < 60) {
				this.seconds = seconds;
			}
			else {
				throw new IllegalArgumentException("Seconds cannot be greater than 59");
			}
		} else {
			throw new IllegalArgumentException("Seconds cannot be negative");
		}
	}

	public CountDownTimer(int minutes, int seconds) {
		this.minutes = minutes;
		this.seconds = seconds;
		hours = 0;
	}

	public CountDownTimer(int seconds) {
		this.seconds = seconds;
		hours = 0;
		minutes = 0;
	}

	public CountDownTimer(CountDownTimer other) {
		other.seconds = this.seconds;
		other.minutes = this.minutes;
		other.hours = this.hours;
	}

	public CountDownTimer(String startTime) {
		int startTimeLen = startTime.length();
		if (startTimeLen <= 2) {
			this.seconds = Integer.parseInt(startTime);
		}

		else if (startTimeLen > 3 && startTimeLen <= 5) {
			String[] startTimeSplit = startTime.split(":");
			this.minutes = Integer.parseInt(startTimeSplit[0]);
			this.seconds = Integer.parseInt(startTimeSplit[1]);
		}
		else if (startTimeLen > 6 && startTimeLen <= 8) {
			String[] startTimeSplit = startTime.split(":");
			this.hours = Integer.parseInt(startTimeSplit[0]);
			this.minutes = Integer.parseInt(startTimeSplit[1]);
			this.seconds = Integer.parseInt(startTimeSplit[2]);
		}
		else {
			this.hours = 0;
			this.minutes = 0;
			this.seconds = 0;
		}
	}

	public boolean equals(CountDownTimer other) {
		boolean rtn = false;
		if (other == null){
			rtn = false;
		}
		else if (other instanceof CountDownTimer){
			if(this.hours == other.hours &&
				this.minutes == other.minutes &&
				this.seconds == other.seconds) {
				rtn = true;
			}
			else {
				rtn = false;
			}
		}
		return rtn;
	}

	public static boolean equals (CountDownTimer t1, CountDownTimer t2) {
		if (t1.equals(t2)){
			return true;
		}
		return false;
	}

	public int compareTo(CountDownTimer other) {
		if (this.hours > other.hours && this.minutes > other.minutes && this.seconds > other.seconds) {
			return 1;
		}
		else if (this.hours < other.hours && this.minutes < other.minutes && this.seconds < other.seconds) {
			return -1;
		}
		else {
			return 0;
		}
	}

	public static int compareTo(CountDownTimer t1, CountDownTimer t2) {
		if (t1.hours > t2.hours && t1.minutes > t2.minutes && t1.seconds > t2.seconds) {
			return 1;
		}
		else if (t1.hours < t2.hours && t1.minutes < t2.minutes && t1.seconds < t2.seconds) {
			return -1;
		}
		else {
			return 0;
		}
	}

	public void dec() {
		this.seconds--;
	}

	public void sub(int seconds) {
		this.seconds -= seconds;
	}

	public void add(int seconds) {
		this.seconds += seconds;
	}

	public void add(CountDownTimer other) {
		this.hours += other.hours;
		this.minutes += other.minutes;
		this.seconds += other.seconds;
	}

	public String toString() {
		String fmStr = this.hours + ":";
		if (this.minutes < 10) {
			fmStr += "0" + this.minutes + ":";
		}
		else {
			fmStr += this.minutes + ":";
		}
		if (this.seconds < 10) {
			fmStr += "0" + this.seconds;
		}
		else {
			fmStr += this.seconds;
		}

		return fmStr;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public void save(String fileName){
		if (fileName == null)
			throw new IllegalArgumentException();
		PrintWriter out = null;
		try {
			out = new PrintWriter (new BufferedWriter(new FileWriter(fileName)));
			out.println(this.hours + "" + this.minutes + "" + this.seconds);
		}
		catch (IOException e){
			e.printStackTrace();
		}
		finally{
			out.close();
		}
	}

	public void load(String fileName){
		if (fileName == null)
			throw new NullPointerException();

		Scanner scanner = null;
		try{
			scanner = new Scanner(new File(fileName));

			this.hours = scanner.nextInt();
			this.minutes = scanner.nextInt();
			this.seconds = scanner.nextInt();
		}
		catch (FileNotFoundException e){
			throw new NullPointerException();
		}
		finally {
			scanner.close();
		}
	}
}
