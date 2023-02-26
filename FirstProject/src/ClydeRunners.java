import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ClydeRunners {
public static PrintWriter out;
// public static File fileName; //declare global variables i.e. the PrintWriter, File and scanner can be used
public static Scanner in; 

public static void main(String[] args) throws FileNotFoundException {
        // assign password value
        String password = "clyderunners";
        // create scanner object
        in = new Scanner(System.in);
        // initialise fail to 3
        int fail = 3;

        // loop 3 times to try obtain correct password
        do {
            // print welcome message
            System.out.println("Welcome to Glasgow Clyde Runners Club.\n");
            // request user input for password
            System.out.println("Please enter your password to continue: \n");
            // assign input to variable
            String login = in.nextLine();
            // compare strings
            if (password.equals(login)) {
                System.out.println("Password Validated\n");
                Menu();
            }
            else {
                // decrement fail variable
                fail--;
                // output result to user
                System.out.println("Your Password is incorrect");
                // output attempts left to user
                System.out.println("You have: " + fail + " attempts left.");
            }
        // output message to user when 3 failed attempts reached
        }while(fail!=0);
        System.out.println("Number of attempts exceeded. You are now locked out.");
        // exit program
        System.exit(0);
    }


    public static void Menu() {
        // create scanner object
        in = new Scanner(System.in);
        // decalre input file variable
        String fileName = "race-results.txt";
        // create variable to store menu item number
        int item = 0;
        // loop until menu item selected
        do {
            // print menu to user
            System.out.println("1. Read and Display File\n2. Sort and Print Recorded Times\n3. Find and Print Fastest Time\n4. Find and Print Slowest Time\n5. Search\n6. Time Occurrence\n7. Exit Program\n");

            // ask user for menu item input
            System.out.println("Please enter the selected menu item number: ");
            // read input from user
            item = in.nextInt();
            // System.out.println(item);
            if (item == 1) {
                // call method to display race results
                System.out.println("You have selected 1. Read and Display File");
                displayTimes(fileName);
            }
            else if (item == 2) {
                // call method to sort times from slowest to fastest
                System.out.println("You have selected 2. Sort and Print Recorded Times");
                sortTimes(fileName);
            }
            else if (item == 3) {
                // call method to display fastest time
                System.out.println("You have selected 3. Find and Print the Fastest Time");
                displayFastest(fileName);
            }
            else if (item == 4) {
                // call method to display slowest time
                System.out.println("You have selected 4. Find and Print the Slowest Time");
                displaySlowest(fileName);
            }
            else if (item == 5) {
                // call method to search for a time
                System.out.println("You have selected 5. Search");
                searchTime(fileName);
            }
            else if (item == 6) {
                // call method to find occurrence of a time
                System.out.println("You have selected 6. Time Occurrence");
                findOccurrence(fileName);
            }
            else if (item == 7) {
                // print goodbye message and exit program
                System.out.println("You have selected 7. Exit Program");
                System.out.println("Thank you for using the Clyde Runners App. Goodbye.");
                System.exit(0);
            }
            else {
                System.out.println("Please enter a number between 1 and 7");
            }
        } while (item!=0);    
    }
    

        public static void displayTimes(String fileName) {
            try {
                // create a Scanner object to read from the file
                Scanner scanner = new Scanner(new File(fileName));
                // declare 2D array
                String[][] raceData = new String[16][2];
                // initialise row count to count rows
                int i = 0;
                while (scanner.hasNextLine()) {
                    // read data and assign to 2D array
                    String line = scanner.nextLine();
                    String[] data = line.split(" ");
                    raceData[i][0] = data[0] + " " + data[1] + ":";
                    raceData[i][1] = data[2];
                    i++;
                }
                // close scanner
                scanner.close();
                for (int j = 0; j < raceData.length; j++) {
                    // print results to user
                    System.out.println(raceData[j][0] + " " + raceData[j][1] + "s");
                }
            // exception handling
            } catch (Exception e) {
                System.out.println("Error occurred");
                e.printStackTrace();
            }
        }

    public static void sortTimes(String fileName) {
        try {
            // create a Scanner object to read from the file
            Scanner scanner = new Scanner(new File(fileName));
            // declare 2D array
            String[][] raceData = new String[16][2];
            // initialise row count to count rows
            int i = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");
                raceData[i][0] = data[0] + " " + data[1] + ":";
                raceData[i][1] = data[2];
                i++;
            }
            scanner.close();
            // Sort the race data by race time from slowest to fastest using selection sort
            for (int j = 0; j < raceData.length - 1; j++) {
                int minIndex = j;
                for (int k = j + 1; k < raceData.length; k++) {
                    int time1 = Integer.parseInt(raceData[minIndex][1].substring(0, raceData[minIndex][1].length() - 1));
                    int time2 = Integer.parseInt(raceData[k][1].substring(0, raceData[k][1].length() - 1));
                    if (time2 > time1) {
                        minIndex = k;
                    }
                }
                // Swap the current row with the row containing the minimum race time
                String[] temp = raceData[j];
                raceData[j] = raceData[minIndex];
                raceData[minIndex] = temp;
            }
            // assign name for output file
            String userFileName = "SortedTimes.txt";
            // create output file object
            File outputFile = new File(userFileName);
            // create print writer object
            PrintWriter out = new PrintWriter(userFileName);
            // Print the sorted race data to the user
            for (int j = 0; j < raceData.length; j++) {
                // print results to output file
                out.println(raceData[j][0] + " " + raceData[j][1] + "s");
                // print results to user
                System.out.println(raceData[j][0] + " " + raceData[j][1] + "s");
            }
            // close printwriter
            out.close();
        // exception handling
        } catch (Exception e) {
            System.out.println("Exception occurred");
            e.printStackTrace();
        }
    }


    public static void displaySlowest(String fileName) {
        try {
            // create a Scanner object to read from the file
            Scanner scanner = new Scanner(new File(fileName));
            String[][] raceData = new String[16][2];
            int i = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");
                raceData[i][0] = data[0] + " " + data[1] + ":";
                raceData[i][1] = data[2];
                i++;
            }
            scanner.close();
            // assign name for output file
            String userFileName = "SlowestTime.txt";
            // create output file object
            File outputFile = new File(userFileName);
            // create print writer object
            PrintWriter out = new PrintWriter(userFileName);
            // Sort the race data by race time from slowest to fastest using selection sort
            for (int j = 0; j < raceData.length - 1; j++) {
                int minIndex = j;
                for (int k = j + 1; k < raceData.length; k++) {
                    int time1 = Integer.parseInt(raceData[minIndex][1].substring(0, raceData[minIndex][1].length() - 1));
                    int time2 = Integer.parseInt(raceData[k][1].substring(0, raceData[k][1].length() - 1));
                    if (time2 < time1) {
                        minIndex = k;
                    }
                }
                // Swap the current row with the row containing the minimum race time
                String[] temp = raceData[j];
                raceData[j] = raceData[minIndex];
                raceData[minIndex] = temp;
            }
            // print result to output file
            out.println("Racer with slowest time: " + raceData[raceData.length - 1][0] + " " + raceData[raceData.length - 1][1] + "s");
            // print result to user
            System.out.println("Racer with slowest time: " + raceData[raceData.length - 1][0] + " " + raceData[raceData.length - 1][1] + "s");
         // close printwriter
         out.close();
         // exception handling
         } catch (Exception e) {
             System.out.println("Exception occurred");
             e.printStackTrace();
         }
    }

    public static void displayFastest(String fileName) {
        try {
            // create a Scanner object to read from the file
            Scanner scanner = new Scanner(new File(fileName));
            String[][] raceData = new String[16][2];
            int i = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");
                raceData[i][0] = data[0] + " " + data[1] + ":";
                raceData[i][1] = data[2];
                i++;
            }
            scanner.close();
            // assign name for output file
            String userFileName = "FastestTime.txt";
            // create output file object
            File outputFile = new File(userFileName);
            // create print writer object
            PrintWriter out = new PrintWriter(userFileName);
            // Sort the race data by race time from fastest to fastest using selection sort
            for (int j = 0; j < raceData.length - 1; j++) {
                int maxIndex = j;
                for (int k = j + 1; k < raceData.length; k++) {
                    int time1 = Integer.parseInt(raceData[maxIndex][1].substring(0, raceData[maxIndex][1].length() - 1));
                    int time2 = Integer.parseInt(raceData[k][1].substring(0, raceData[k][1].length() - 1));
                    if (time2 > time1) {
                        maxIndex = k;
                    }
                }
                // Swap the current row with the row containing the maximum race time
                String[] temp = raceData[j];
                raceData[j] = raceData[maxIndex];
                raceData[maxIndex] = temp;
            }
            // print result to output file
            out.println("Racer with fastest time: " + raceData[raceData.length - 1][0] + " " + raceData[raceData.length - 1][1] + "s");
            // print result to user
            System.out.println("Racer with fastest time: " + raceData[raceData.length - 1][0] + " " + raceData[raceData.length - 1][1] + "s");
        // close printwriter
        out.close();
        // exception handling
        } catch (Exception e) {
            System.out.println("Exception occurred");
            e.printStackTrace();
        }
    }

    public static void searchTime(String fileName) {
        try {
            // create a Scanner object to read from the file
            Scanner scanner = new Scanner(new File(fileName));
            String[][] raceData = new String[16][2];
            int i = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");
                raceData[i][0] = data[0] + " " + data[1];
                raceData[i][1] = data[2];
                i++;
            }
            scanner.close();
            // assign name for output file
            String userFileName = "SearchedTime.txt";
            // create output file object
            File outputFile = new File(userFileName);
            // create print writer object
            PrintWriter out = new PrintWriter(userFileName);
            // prompt the user to enter a time to search for
            in = new Scanner(System.in);
            System.out.print("Enter a race time to search for: ");
            int searchTime = in.nextInt();
            boolean found = false;
    
            // search for the given time in the race data array
            for (int j = 0; j < raceData.length; j++) {
                String time = raceData[j][1];
                if (time.contains(String.valueOf(searchTime))) {
                    // print result to output file
                    out.println(raceData[j][0] + " matched the searched time of " + raceData[j][1] + "s.");
                    // print result to user
                    System.out.println(raceData[j][0] + " matched the searched time of " + raceData[j][1] + "s.");
                    found = true;
                }
            }
    
            // print a message if no racer achieved the given time
            if (!found) {
                System.out.println("No racer achieved time searched for.");
            }
        // close printwriter
        out.close();
        // exception handling
        } catch (Exception e) {
            System.out.println("Exception occurred");
            e.printStackTrace();
        }
    }
    
    public static void findOccurrence(String fileName) {
        try {
            // create a Scanner object to read from the file
            Scanner scanner = new Scanner(new File(fileName));
            String[][] raceData = new String[16][2];
            int i = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");
                raceData[i][0] = data[0] + " " + data[1];
                raceData[i][1] = data[2];
                i++;
            }
            scanner.close();
            // assign name for output file
            String userFileName = "TimeOccurrences.txt";
            // create output file object
            File outputFile = new File(userFileName);
            // create print writer object
            PrintWriter out = new PrintWriter(userFileName);
            // prompt the user to enter a time to search for
            in = new Scanner(System.in);
            System.out.print("Enter a race time to search occurrences of: ");
            int searchTime = in.nextInt();
            boolean found = false;
            int count = 0;
    
            // search for the given time in the race data array
            for (int j = 0; j < raceData.length; j++) {
                String time = raceData[j][1];
                if (time.contains(String.valueOf(searchTime))) {
                    // System.out.println(raceData[j][0] + " matched the searched time of " + raceData[j][1] + "s.");
                    count++;
                    found = true;
                }
            }
            // print result to output file
            out.println("The time of " + searchTime + "s was achieved " + count + " time(s).");
            // print result to user
            System.out.println("The time of " + searchTime + "s was achieved " + count + " time(s).");
        // close printwriter
        out.close();
        // exception handling
        } catch (Exception e) {
            System.out.println("Exception occurred");
            e.printStackTrace();
        }
    }
}