package unit_0_review;
import java.util.Scanner;

/**
 * Bank/Tax calculator which allows the user to see the effects of credit card interest in relation to other factors
 * @author Aniket Arbatti
 * @version Completed Sept 29, 2023
 */

public class JavaLoopAssignment {
	public static void main(String[] args) {

		// Setting up the variables
		final int LIMIT = 100000, MONTHS = 12;
		String yearPrint = "years", monthPrint = "months";

		// Made these doubles because they take on floating points later on the in the program
		double counter = 0, purchaseOwed = 0,interestOwed = 0, balanceOwing = 1;
		double initBalance, rate, purchase, payment, monthlyRate, interest;

		// The scope of the Scanner inside the do-while loop is limited, so I pulled it outside to make sure it can be seen by everything.
		Scanner input;


		// Do-while Loop for the Initial Balance
		do {
			System.out.print("Please enter an Initial Balance: ");
			input = new Scanner(System.in);
			initBalance = input.nextDouble();
			// Checks to see if the values are valid
			if (initBalance < 0 || initBalance >= 100000) {
				System.out.println("Please make sure your Initial Balance is greater than $0 but less than $100,000");
			}
			// Constraints for the repeat
		} while (initBalance < 0 || initBalance >= LIMIT);

		// Do-while Loop for the Interest Rate
		do {
			System.out.print("Please enter an Interest Rate: ");
			rate = input.nextDouble();
			monthlyRate = (rate / 100) /MONTHS;
			// Checks to see if the values are valid
			if (rate < 0) {
				System.out.println("Please make sure your Tax Rate is greater than or equal to $0");
			}
			// Constraints for the repeat
		} while (rate < 0);

		// Do-while Loop for the Additional Monthly Purchase
		do {
			System.out.print("Please enter an Additional Monthly Purchase: ");
			purchase = input.nextDouble();
			// Checks to see if the values are valid
			if (purchase < 0) {
				System.out.println("Please make sure your Monthly Purchase Amount is greater than or equal to $0");
			}
			// Constraints for the repeat
		} while (purchase < 0);

		// Do-while Loop for the Payment Amount
		do {
			System.out.print("Please enter a Payment Amount: ");
			payment = input.nextDouble();
			// Checks to see if the values are valid
			if (payment < 10) {
				System.out.println("Please make sure your Payment Amount is greater than or equal to $10");
			}
			// Constraints for the repeat
		} while (payment < 10);



		// Checks to see if there is actually any money that is owed to the bank
		if (initBalance != 0 ||  purchase != 0) {
			// The loop to print all the information. Runs as long as the balance owing is greater than $0 but less than or equal to $100,000
			while (balanceOwing > 0 && balanceOwing <= LIMIT) {
				// Prints the headers for the chart when the counter is 0...so only once
				if (counter == 0) {
					System.out.println("Month\t\t\tInitial Balance\t\t\tInterest\t\t\tPurchases\t\t\tPayment\t\t\t\tBalance Owing");
				} 
				else {
					// Sets the initial balance for the next month equal to the balance owing for the previous month to update the values
					initBalance = balanceOwing;
				}

				// The general values each variable takes on at the end of each month
				interest = initBalance * monthlyRate;
				balanceOwing = initBalance + purchase - payment + interest;
				purchaseOwed += purchase;
				interestOwed += interest;
				counter++;

				// Checks to see if the balance owing is less than 0 and defaults that value to 0 to avoid printing a negative number.
				if (balanceOwing <= 0) {
					balanceOwing = 0;
				}
				// Final print statement for the chart of values
				System.out.format("%3.0f\t\t\t$%9.2f\t\t\t$%8.2f\t\t\t$%8.2f\t\t\t$%7.2f\t\t\t$%9.2f\n", counter, initBalance, interest, purchase, payment, balanceOwing);
			}

			// Just some grammar checks to make sure that the correct tense is being used respective to the number amount - 1 month vs 1 months...
			if ((int) (counter / MONTHS) == 1) {
				yearPrint = "year";
			}
			if ((int) (counter % MONTHS) == 1) {
				monthPrint = "month";
			}

			// The print message if the credit debt goes beyond $100,000, prints the according information as well
			if (balanceOwing >= LIMIT) {
				System.out.println();
				System.out.format("You will be in debt by $100000.00 or more after %d %s and %d %s.\n", (int) (counter / MONTHS), yearPrint, (int) (counter % MONTHS), monthPrint);
			}
			// The print message for if they pay off their credit debt and the according information.
			else if (balanceOwing <= 0) {
				System.out.println();
				System.out.format("It will take %d %s and %d %s to pay off your credit card.\n", (int) (counter / MONTHS), yearPrint, (int) (counter % MONTHS), monthPrint);
			}
			// This part was common between the previous two print statements, so it can be yanked out of the if-loop and be made general between them
			System.out.format("You spent a total of $%.2f in purchases and $%.2f was charged in interest.", purchaseOwed, interestOwed);
		}
		else {
			System.out.println("You don't owe any money.");
		}
		input.close();
	}
}
