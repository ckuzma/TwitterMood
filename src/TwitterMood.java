import java.util.Scanner;

public class TwitterMood {
	public static void main(String[] args) throws Exception{
		Scanner input = new Scanner(System.in);
		System.out.print("\n\nDo you want to connect to an Arduino? Enter Y or N: ");
		String arduinoOrNot = input.nextLine();
		if(arduinoOrNot.equals("y") || arduinoOrNot.equals("Y")){
			MoodRunnableArduino moody = new MoodRunnableArduino();
			System.out.println("30 = Top Happiness\t0 = Lowest Happiness");
			moody.moodStart();
		}
		if(arduinoOrNot.equals("n") || arduinoOrNot.equals("N")){
			System.out.println("30 = Top Happiness\t0 = Lowest Happiness");
			MoodRunnableNoDuino moody = new MoodRunnableNoDuino();
			moody.moodStart();
		}
		System.out.println();
	}
}

