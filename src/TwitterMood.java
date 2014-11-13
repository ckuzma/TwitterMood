/*
 * TwitterMood - by Chris Kuzma
 * 
 * This is a Java program designed to observe Twitter's "mood" in live time and
 * plot this on a chart / physically represent this through a local Arduino. In
 * order to communicate directly with the Arduino, you need to install the
 * rxtx libraries for your computer. Otherwise this will crash.
 * 
 * Additionally, you will need to supply your own Twitter development keys in
 * order to successfully access the Twitter API- which this requires in order
 * to work. Modify the values found in [Credentials.java] with you own.
 * 
 * If you are attempting to run this application on a Windows computer, then
 * you will need to modify the serial port settings found in
 * [ArduinoConnection.java]. See that file for instructions.
 * 
 */

import java.util.Scanner;

public class TwitterMood {
	public static void main(String[] args) throws Exception{
		Scanner input = new Scanner(System.in);
		System.out.print("\n\nDo you want to connect to an Arduino? Enter Y or N: ");
		String arduinoOrNot = input.nextLine();
		if(arduinoOrNot.equals("y") || arduinoOrNot.equals("Y")){
			WithArduino moody = new WithArduino();
			System.out.println("30 = Top Happiness\t0 = Lowest Happiness");
			moody.moodStart();
		}
		if(arduinoOrNot.equals("n") || arduinoOrNot.equals("N")){
			System.out.println("30 = Top Happiness\t0 = Lowest Happiness");
			WithoutArduino moody = new WithoutArduino();
			moody.moodStart();
		}
		System.out.println();
	}
}

