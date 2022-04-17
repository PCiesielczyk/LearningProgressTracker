package tracker;

import java.util.ArrayList;
import java.util.Scanner;

import static tracker.InputCases.coursesList;

public class Main {

    public static final int JAVA_POINTS_TO_COMPLETION = 600;
    public static final int DSA_POINTS_TO_COMPLETION = 400;
    public static final int DB_POINTS_TO_COMPLETION = 480;
    public static final int SPRING_POINTS_TO_COMPLETION = 550;

    public static void main(String[] args) {


        Course java = new Course("Java", JAVA_POINTS_TO_COMPLETION, 0, 0, 0);
        Course dsa = new Course("DSA", DSA_POINTS_TO_COMPLETION, 0, 0, 0);
        Course db = new Course("Databases", DB_POINTS_TO_COMPLETION, 0, 0, 0);
        Course spring = new Course("Spring", SPRING_POINTS_TO_COMPLETION, 0, 0, 0);

        coursesList.add(java);
        coursesList.add(dsa);
        coursesList.add(db);
        coursesList.add(spring);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Learning Progress Tracker");

        String input = scanner.nextLine();

        while (!input.equals("exit")) {

            if (input.trim().isEmpty()) {
                InputCases.emptyInput();
            } else {

                switch (input) {

                    case "add students":
                        InputCases.addStudents();
                        break;
                    case "back":
                        InputCases.backInput();
                        break;
                    case "list":
                        InputCases.listInput();
                        break;
                    case "find":
                        InputCases.findInput();
                        break;
                    case "add points":
                        InputCases.addPoints();
                        break;
                    case "statistics":
                        InputCases.statisticsInput();
                        break;
                    case "notify":
                        InputCases.notifyInput();
                        break;
                    default:
                        InputCases.unknownInput();
                        break;
                }
            }

            input = scanner.nextLine();
        }

        System.out.println("Bye!");
    }
}
