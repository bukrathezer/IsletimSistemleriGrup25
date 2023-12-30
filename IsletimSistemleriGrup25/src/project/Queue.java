package Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Queue {

    // Declare three ArrayLists to store processes with different priorities
    private ArrayList<Process> universalPio1;
    private ArrayList<Process> universalPio2;
    private ArrayList<Process> universalPio3;

    // Constructor to initialize the ArrayLists
    public Queue() {
        universalPio1 = new ArrayList<>();
        universalPio2 = new ArrayList<>();
        universalPio3 = new ArrayList<>();
    }

    // A method to count the number of lines in a given file
    public static int countLines(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int lines = 0;

        // Loop through the file and increment the line count
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            lines++;
        }
        scanner.close();

        return lines;
    }

    // A method to calculate the number of loops needed to process the file
    public int calculateLoops(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        int lineCount = countLines(file);
        int loopCount = 0;
        // Create a 2D array to store the data from the file
        int[][] array = new int[lineCount][3];

        Scanner fileScanner = new Scanner(file);

        // Loop through the file and split each line by comma
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] splitArray = line.split(", ");
            int arrayRow = 0;

            // Parse the values and store them in the array
            for (String value : splitArray) {
                array[loopCount][arrayRow] = Integer.parseInt(value);
                arrayRow++;
            }
            // Add the third value to the loop count
            loopCount += array[loopCount][2];
        }
        fileScanner.close();

        return loopCount;
    }

    // A method to create Process objects from the file data and store them in an ArrayList
    public ArrayList<Process> createObjects(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        int loopCount = 0;

        Scanner fileScanner = new Scanner(file);
        int lineCount = countLines(file);
        // Create a 2D array to store the data from the file
        int[][] array = new int[lineCount][6];

        // Create an array to store the Process objects
        Process[] tempProcesses = new Process[lineCount];

        // Loop through the file and split each line by comma
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] splitArray = line.split(", ");
            int arrayRow = 1;

            // Parse the values and store them in the array
            for (String value : splitArray) {
                array[loopCount][arrayRow] = Integer.parseInt(value);
                arrayRow++;
            }
            // Set the first value to the loop count
            array[loopCount][0] = loopCount;
            // Set the fifth value to 0
            array[loopCount][4] = 0;
            // Set the sixth value to -1
            array[loopCount][5] = -1;

            // Create a Process object with the array values and store it in the array
            Process tempProcess = new Process(array[loopCount]);
            tempProcesses[loopCount] = tempProcess;

            loopCount++;
        }

        // Convert the array to an ArrayList
        ArrayList<Process> processes = new ArrayList<>(Arrays.asList(tempProcesses));
        fileScanner.close();

        return processes;
    }

    // A method to determine the arrival times of the processes in the queue
    public ArrayList<Process> determineArrivalTimes(ArrayList<Process> queue, int second) {
        // Create a temporary ArrayList to store the processes that arrive at the given second
        ArrayList<Process> tempQueue = new ArrayList<>();

        // Loop through the queue and check the arrival time of each process
        for (Process element : queue) {
            if (element.arrivalTime == second) {
                // Add the process to the temporary queue
                tempQueue.add(element);
            }
        }
        return tempQueue;
    }

    // A method to check the priority of the processes in the queue and sort them into two lists
    public ArrayList<ArrayList<Process>> queueCheck(ArrayList<Process> queueAtSecond,
                                                    ArrayList<Process> realTimeLastList,
                                                    ArrayList<Process> userJobLastList) {
        // Loop through the queue at the given second
        for (Process element : queueAtSecond) {
            // Use a switch statement to check the priority of each process
            switch (element.priority) {
                case 0:
                    // If the priority is 0, add the process to the real time list
                    realTimeLastList.add(element);
                    break;
                default:
                    // Otherwise, add the process to the user job list
                    userJobLastList.add(element);
                    break;
            }
        }

        // Create an ArrayList to store the two lists
        ArrayList<ArrayList<Process>> fullList = new ArrayList<>();
        fullList.add(realTimeLastList);
        fullList.add(userJobLastList);

        // Set the first element to the real time list and the second element to the user job list
        fullList.set(0, realTimeLastList);
        fullList.set(1, userJobLastList);

        return fullList;
    }
}