package tracker;

public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int javaPoints;
    private int dsaPoints;
    private int dbPoints;
    private int springPoints;
    private boolean needsNotification;

    public Student(int id, String firstName, String lastName, String email, int javaPoints,
                   int dsaPoints, int dbPoints, int springPoints, boolean needsNotification) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.javaPoints = javaPoints;
        this.dsaPoints = dsaPoints;
        this.dbPoints = dbPoints;
        this.springPoints = springPoints;
        this.needsNotification = needsNotification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getJavaPoints() {
        return javaPoints;
    }

    public void setJavaPoints(int javaPoints) {
        this.javaPoints = javaPoints;
    }

    public int getDsaPoints() {
        return dsaPoints;
    }

    public void setDsaPoints(int dsaPoints) {
        this.dsaPoints = dsaPoints;
    }

    public int getDbPoints() {
        return dbPoints;
    }

    public void setDbPoints(int dbPoints) {
        this.dbPoints = dbPoints;
    }

    public int getSpringPoints() {
        return springPoints;
    }

    public void setSpringPoints(int springPoints) {
        this.springPoints = springPoints;
    }

    public boolean isNeedsNotification() {
        return needsNotification;
    }

    public void setNeedsNotification(boolean needsNotification) {
        this.needsNotification = needsNotification;
    }
}
