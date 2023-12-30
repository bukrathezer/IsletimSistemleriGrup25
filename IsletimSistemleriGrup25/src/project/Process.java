package Project;
import java.util.Random;

public class Process {

    private int id;
    private int arrivalTime;
    private int executionTime;
    private int priority;
    private int turnaroundTime;

    // Constructor
    public Process(int id, int arrivalTime, int priority, int executionTime, int turnaroundTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.executionTime = executionTime;
        this.turnaroundTime = turnaroundTime;
    }

    // Getters and setters for each attribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    // toString method to display process information
    @Override
    public String toString() {
        return "Process [Id=" + id + ", Arrival Time=" + arrivalTime + ", Priority=" + priority
                + ", Execution Time=" + executionTime + ", Turnaround Time=" + turnaroundTime + "]";
    }
}