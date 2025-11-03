package app;

public class Task {
    private int Number;
    private String description;
    private Status status;
    private int daysInProcessing;

    public Task(int number, String description, Status status, int daysInProcessing) {
        Number = number;
        this.description = description;
        this.status = status;
        this.daysInProcessing = daysInProcessing;
    }

    public int getDaysInProcessing() {
        return daysInProcessing;
    }

    public Status getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public int getNumber() {
        return Number;
    }

    @Override
    public String toString() {
        return "Task №" + Number +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", daysInProcessing=" + daysInProcessing;
    }
}
