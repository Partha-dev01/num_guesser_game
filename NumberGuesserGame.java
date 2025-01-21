import java.util.Random;
import java.util.Scanner;

public class NumberGuesserGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int start = 1;
        int end = 100;
        int Diff = 10;
        int max_try = 5;
        int machineNumber = random.nextInt(end - start + 1) + start;
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Guess a number between " + start + " and " + end + " within " + max_try + " tries.");
        System.out.println("Allowed difference: " + Diff);

        for (int tries = 0; tries < max_try; tries++) 
        {
            System.out.print("\nEnter your guess: ");
            int guess;
            try {
                guess = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                tries--;
                continue;
            }

            int difference = Math.abs(machineNumber - guess);
            if (guess == machineNumber) 
            {
                System.out.println("\nCongratulations! You've won in " + (tries + 1) + " tries!");
                return;
            }
            System.out.println(difference > Diff? "Outside allowed range." : "Within allowed range");
            System.out.println(guess < machineNumber? "Try higher." : "Try lower.");
            System.out.println("Tries left: " + (max_try - tries - 1));
        }

        System.out.println("\nGame Over! The number was " + machineNumber);
        System.out.println("The machine wins!");
    }
}

