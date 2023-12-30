package Project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Combining Main.java and Uygulama.java...");

        // Run the application logic
        runApplication();
    }

    public static void runApplication() throws IOException {
        // Get the file path from the user
        String filePath = getUserInput("Enter the file name to be read:");

        // Initialize the process queue
        ProcessQueue processQueue = new ProcessQueue();

        // Get the simulation duration in seconds
        int simulationDuration = processQueue.calculateSimulationDuration(filePath);

        // Initialize the active process
        Process activeProcess = new Process(new int[]{-1, -1, -1, -1, 0});

        // Create a list of processes based on the file content
        ArrayList<Process> processList = processQueue.createProcessList(filePath);

        // Simulate the process execution over time
        for (int second = 0; second < simulationDuration; second++) {
            // Check for arriving processes at the current second
            ArrayList<Process> arrivingProcesses = processQueue.checkArrivingProcesses(processList, second);

            // Update the process queues based on the arriving processes
            processQueue.updateQueues(arrivingProcesses);

            // Update the active process based on the current queues
            activeProcess = processQueue.updateActiveProcess(activeProcess, second);
        }
    }

    public static String getUserInput(String prompt) {
        // Prompt the user for input and return the entered value
        System.out.print(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}