package tracker;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputCases {

    public static ArrayList<Course> coursesList = new ArrayList<>();
    public static ArrayList<Notification> notifications = new ArrayList<>();

    public static void emptyInput() {
        System.out.println("No input");
    }

    public static void backInput() {
        System.out.println("Enter 'exit' to exit the program.");
    }

    public static void unknownInput() {
        System.out.println("Unknown command!");
    }

    public static void addStudents() {

        int studentCounter = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student credentials or 'back' to return:");
        String addStudentsInput = scanner.nextLine();

        while (!addStudentsInput.equals("back")) {
            if (InputsFormatter.validStudentData(addStudentsInput)) {
                studentCounter++;
            }

            addStudentsInput = scanner.nextLine();
        }

        System.out.println("Total " + studentCounter + " students have been added.");
    }

    public static void listInput() {

        if (InputsFormatter.studentsList.isEmpty()) {
            System.out.println("No students found");
        } else {
            System.out.println("Students: ");

            for (Student student : InputsFormatter.studentsList) {
                System.out.println(student.getId());
            }
        }
    }

    public static void findInput() {

        System.out.println("Enter an id or 'back' to return:");
        Scanner scanner = new Scanner(System.in);

        String inputId = scanner.nextLine();

        while (!inputId.equals("back")) {

            if (isNumeric(inputId)) {
                int intInputId = Integer.parseInt(inputId);

                if (InputsFormatter.validId(intInputId)) {
                    Student student = InputsFormatter.studentById(intInputId);

                    String output = String.format("%d points: Java=%d; DSA=%d; Databases=%d; Spring=%d", student.getId(),
                            student.getJavaPoints(), student.getDsaPoints(), student.getDbPoints(), student.getSpringPoints());

                    System.out.println(output);
                } else {
                    System.out.printf("No student is found for id=%s \n", inputId);
                }
            } else {
                System.out.printf("No student is found for id=%s \n", inputId);
            }
            inputId = scanner.nextLine();
        }
    }

    public static void addPoints() {
        System.out.println("Enter an id and points or 'back' to return");
        Scanner scanner = new Scanner(System.in);
        String inputBlock = scanner.nextLine();

        while (!inputBlock.equals("back")) {

            String[] splitInputBlock = inputBlock.split(" ", 5);

            if (splitInputBlock.length == 5) {

                String inputId = splitInputBlock[0];
                String javaPointsInput = splitInputBlock[1];
                String dsaPointsInput = splitInputBlock[2];
                String dbPointsInput = splitInputBlock[3];
                String springPointsInput = splitInputBlock[4];

                if (isNumeric(inputId)) {

                    int intInputId = Integer.parseInt(inputId);

                    if (InputsFormatter.validId(intInputId)) {
                        if (isNumeric(javaPointsInput) && isNumeric(dsaPointsInput) && isNumeric(dbPointsInput) && isNumeric(springPointsInput)) {

                            Student student = InputsFormatter.studentById(intInputId);

                            int intJavaPoints = Integer.parseInt(javaPointsInput);
                            int intDsaPoints = Integer.parseInt(dsaPointsInput);
                            int intDbPoints = Integer.parseInt(dbPointsInput);
                            int intSpringPoints = Integer.parseInt(springPointsInput);

                            CourseStatistics.courseUpdate(coursesList.get(0), intJavaPoints, student);
                            CourseStatistics.courseUpdate(coursesList.get(1), intDsaPoints, student);
                            CourseStatistics.courseUpdate(coursesList.get(2), intDbPoints, student);
                            CourseStatistics.courseUpdate(coursesList.get(3), intSpringPoints, student);

                            InputsFormatter.addPoints(student, intJavaPoints, intDsaPoints, intDbPoints, intSpringPoints);

                            CourseStatistics.updateNotification(student, notifications);

                            System.out.println("Points updated.");
                        } else {
                            System.out.println("Incorrect points format.");
                        }
                    } else {
                        System.out.printf("No student is found for id=%s \n", inputId);
                    }
                } else {
                    System.out.printf("No student is found for id=%s \n", inputId);
                }
            } else {
                System.out.println("Incorrect points format.");
            }
            inputBlock = scanner.nextLine();
        }
    }

    public static void statisticsInput() {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Most popular: %s \n", CourseStatistics.mostPopular(coursesList));
        System.out.printf("Least popular: %s \n", CourseStatistics.leastPopular(coursesList));
        System.out.printf("Highest activity: %s \n", CourseStatistics.highestActivity(coursesList));
        System.out.printf("Lowest activity: %s \n", CourseStatistics.leastActivity(coursesList));
        System.out.printf("Easiest course: %s \n", CourseStatistics.easiest(coursesList));
        System.out.printf("Hardest course: %s \n", CourseStatistics.hardest(coursesList));

        String input = scanner.nextLine();

        while (!input.equals("back")) {

            if (CourseStatistics.validCourseName(input, coursesList)) {

                Course course = CourseStatistics.getCourseByName(input, coursesList);

                System.out.println(course.getName());
                System.out.printf("%s %s %s \n", "id", "points", "completed");

                CourseStatistics.getStudentsByCourse(InputsFormatter.studentsList, course);
            } else {
                System.out.println("Unknown course");
            }
            input = scanner.nextLine();
        }

    }

    public static void notifyInput() {
        CourseStatistics.sendNotification(notifications);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }

        if (strNum.startsWith("-")) {
            return false;
        }

        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        return pattern.matcher(strNum).matches();
    }
}
