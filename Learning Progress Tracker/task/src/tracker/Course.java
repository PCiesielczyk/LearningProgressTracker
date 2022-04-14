package tracker;

public class Course {

    private String name;
    private int pointsToCompletion;
    private int completedTasks;
    private int enrolls;
    private double averageScore;

    public Course(String name, int pointsToCompletion, int completedTasks, int enrolls, double averageScore) {
        this.name = name;
        this.pointsToCompletion = pointsToCompletion;
        this.completedTasks = completedTasks;
        this.enrolls = enrolls;
        this.averageScore = averageScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPointsToCompletion() {
        return pointsToCompletion;
    }

    public void setPointsToCompletion(int pointsToCompletion) {
        this.pointsToCompletion = pointsToCompletion;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(int completedTasks) {
        this.completedTasks = completedTasks;
    }

    public int getEnrolls() {
        return enrolls;
    }

    public void setEnrolls(int enrolls) {
        this.enrolls = enrolls;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }
}
