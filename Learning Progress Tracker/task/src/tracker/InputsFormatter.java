package tracker;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputsFormatter {

    public static ArrayList<Student> studentsList = new ArrayList<>();

    static int initialId = 10000;

    public static boolean validFirstName(String studentName) {

        if (studentName.length() < 2 || studentName.startsWith("'") || studentName.startsWith("-")) {
            return false;
        }

        final Pattern pattern = Pattern.compile("(?:[a-zA-Z]|['-][a-zA-Z])+");
        final Matcher matcher = pattern.matcher(studentName);

        return matcher.matches();
    }

    public static boolean validLastName(String studentLastName) {

        if (studentLastName.length() < 2 || studentLastName.startsWith("'") || studentLastName.startsWith("-")) {
            return false;
        }

        final Pattern pattern = Pattern.compile("(?:\\s*[a-zA-Z]|['-][a-zA-Z])+");
        final Matcher matcher = pattern.matcher(studentLastName);

        return matcher.matches();
    }

    public static boolean validEmail(String studentEmail) {

        final Pattern pattern = Pattern.compile("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        final Matcher matcher = pattern.matcher(studentEmail);

        return matcher.matches();
    }

    public static boolean validStudentData(String studentData) {

        String[] splittedStudentData = studentData.split(" ");

        if (splittedStudentData.length < 3) {
            System.out.println("Incorrect credentials");
            return false;
        }

        StringBuilder lastNameConcat = new StringBuilder();

        for (int i = 1; i < splittedStudentData.length - 1; i++) {
            lastNameConcat.append(splittedStudentData[i]).append(" ");
        }

        lastNameConcat.deleteCharAt(lastNameConcat.length() - 1);

        String firstName = splittedStudentData[0];
        String lastName = lastNameConcat.toString();
        String email = splittedStudentData[splittedStudentData.length - 1];

        if (!validFirstName(firstName)) {
            System.out.println("Incorrect first name");
            return false;
        }

        if (!validLastName(lastName)) {
            System.out.println("Incorrect last name");
            return false;
        }

        if(!validEmail(email)) {
            System.out.println("Incorrect email");
            return false;
        }

        if (isMailTaken(email)) {
            System.out.println("This email is already taken.");
            return false;
        }

        Student student = new Student(initialId++, firstName, lastName, email, 0, 0, 0,
                0, false);
        studentsList.add(student);
        System.out.println("The student has been added.");
        return true;

    }

    public static boolean isMailTaken (String email) {
        return studentsList.stream().anyMatch(e -> e.getEmail().equals(email));
    }

    public static boolean validId(int id) {
        return studentsList.stream().anyMatch(student -> student.getId() == id);
    }

    public static Student studentById (int id) {
        return studentsList.stream()
                .filter(student -> student.getId() == id)
                .findFirst().orElse(null);
    }
    public static void addPoints (Student student, int javaPoints, int dsaPoints, int dbPoints, int springPoints) {

        student.setJavaPoints(student.getJavaPoints() + javaPoints);
        student.setDsaPoints(student.getDsaPoints() + dsaPoints);
        student.setDbPoints(student.getDbPoints() + dbPoints);
        student.setSpringPoints(student.getSpringPoints() + springPoints);
    }
}
