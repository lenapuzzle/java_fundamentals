import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CashRegister {
  private final static String filename = "outstandingReceipts.txt";

  public static void main(String [] args) throws IOException {
//    final String filename = "outstandingReceipts.txt";
//    Scanner scan = new Scanner(System.in);

//    String userInput = "";
//    do {
//      System.out.println("How much money do you have?");
//      userInput = scan.nextLine();
//    } while(userInput.isBlank());
    double userInput = getMoneyValue("How much money do you have?");

    System.out.println("I have $" + userInput);
//    if (userInput.equals("")) {
//    if(userInput.isBlank()) {
//      System.out.println("Error! You didn't specify a starting value");
//    } else {
//      System.out.println("I have $" + userInput);
//    }

    File receipts = new File(filename);
    Scanner fileScanner = new Scanner(receipts);
    double lastNight = 0.0;
//    while(fileScanner.hasNextDouble()) {
//      lastNight += fileScanner.nextDouble();
//    }
    while(fileScanner.hasNext()) {
      lastNight += Double.parseDouble(fileScanner.nextLine());
//      try {
//       lastNight += Double.parseDouble(fileScanner.nextLine());
//      } catch(NumberFormatException nfe) {
//        nfe.printStackTrace();
////        System.out.println("The file had a line that wasn't a number!");
//      }
    }
    double registerTotal = userInput + lastNight;
    System.out.println("Your current balance is: $"+ registerTotal);
    fileScanner.close();

    String prompt = "How much does the customer's order cost?";
    double costOfOrder = getMoneyValue(prompt);
    System.out.println("The customer's cost of order is $" + costOfOrder);

    double cashProvided = getMoneyValue("How much cash did the customer provide?");
    System.out.println("The customer gave you $" + cashProvided);

    // while the cash provided is lower than the cost of the order
    while(cashProvided < costOfOrder) {
      // keep prompting them to give more cash
      cashProvided = getMoneyValue("That's not enough money, please provide more funds.");
    }

    // now that we have a sufficient amount of money
    // figure out the change due to the customer
    double changeDue = cashProvided - costOfOrder;
    // while the amount of change due is greater than the amount I have in my cash register
    while(changeDue > registerTotal) {
      // re-prompt them for a different payment method
      cashProvided = getMoneyValue("We don't have sufficient change, please give us less money");
      changeDue = cashProvided - costOfOrder;
    }

    if(changeDue > 0) {
      System.out.println("You owe the customer change! Provide them with $" + changeDue);
    } else {
      System.out.println("The customer gave you exact change! Thanks for your order.");
    }
    // and tell the cashier what it is
  }

  public static double getMoneyValue (String prompt) {
    Scanner scan = new Scanner(System.in);
    // prompt = "How much does the customer's order cost?"
    // prompt for the customer's order cost
    System.out.println(prompt);
    // take the user input and store it in a costOfOrder variable?
    String userInput = scan.nextLine();
    while (userInput.isBlank()) {
      System.out.println("Error! You didn't specify a number. Please try again.");
      userInput = scan.nextLine();
    }
    double moneyValue = Double.parseDouble(userInput);
    return moneyValue;
  }
}