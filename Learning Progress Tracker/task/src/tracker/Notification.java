package tracker;

public class Notification {

    private final String courseName;
    private final Student student;
    private boolean isSent;

    public Notification(String courseName, Student student, boolean isSent) {
        this.courseName = courseName;
        this.student = student;
        this.isSent = isSent;
    }

    public void showNotification() {
        System.out.printf("To: %s \n" +
                "Re: Your Learning Progress \n" +
                "Hello, %s %s! You have accomplished our %s course! \n",
                student.getEmail(), student.getFirstName(), student.getLastName(),
                courseName);
    }

    public String getCourseName() {
        return courseName;
    }

    public Student getStudent() {
        return student;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }
}
