package tracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static tracker.Main.*;

public class CourseStatistics {

    public static String mostPopular(ArrayList<Course> courses) {

        boolean isCoursePresent = courses.stream()
                .anyMatch(course -> course.getEnrolls() > 0);

        if (isCoursePresent) {
            int max = courses.stream()
                    .max(Comparator.comparingInt(Course::getEnrolls))
                    .get()
                    .getEnrolls();

            return getByPopularity(courses, max);

        } else {
            return "n/a";
        }
    }

    public static String leastPopular(ArrayList<Course> courses) {

        boolean isCoursePresent = courses.stream()
                .anyMatch(course -> course.getEnrolls() > 0);

        if (isCoursePresent) {
            int min = courses.stream()
                    .min(Comparator.comparingInt(Course::getEnrolls))
                    .get()
                    .getEnrolls();

            if (getByPopularity(courses, min).equals(mostPopular(courses))) {
                return "n/a";
            }
            return getByPopularity(courses, min);

        } else {
            return "n/a";
        }
    }

    public static String highestActivity(ArrayList<Course> courses) {

        boolean isCoursePresent = courses.stream()
                .anyMatch(course -> course.getCompletedTasks() > 0);

        if (isCoursePresent) {
            int max = courses.stream()
                    .max(Comparator.comparingInt(Course::getCompletedTasks))
                    .get()
                    .getCompletedTasks();

            return getByCompletedTasks(courses, max);

        } else {
            return "n/a";
        }
    }

    public static String leastActivity(ArrayList<Course> courses) {

        boolean isCoursePresent = courses.stream()
                .anyMatch(course -> course.getCompletedTasks() > 0);

        if (isCoursePresent) {
            int min = courses.stream()
                    .min(Comparator.comparingInt(Course::getCompletedTasks))
                    .get()
                    .getCompletedTasks();

            if (getByCompletedTasks(courses, min).equals(highestActivity(courses))) {
                return "n/a";
            }
            return getByCompletedTasks(courses, min);

        } else {
            return "n/a";
        }
    }

    public static String hardest(ArrayList<Course> courses) {

        long aboveZeroCounter = courses.stream()
                .filter(course -> course.getAverageScore() > 0)
                .count();

        boolean isCoursesPresent = aboveZeroCounter > 1;

        if (isCoursesPresent) {
            double min = courses.stream()
                    .filter(course -> course.getAverageScore() > 0)
                    .min(Comparator.comparingDouble(Course::getAverageScore))
                    .get()
                    .getAverageScore();

            if (getByAverageScore(courses, min).equals(easiest(courses))) {
                return "n/a";
            }
            return getByAverageScore(courses, min);

        } else {
            return "n/a";
        }
    }

    public static String easiest(ArrayList<Course> courses) {

        boolean isCoursePresent = courses.stream()
                .anyMatch(course -> course.getAverageScore() > 0);

        if (isCoursePresent) {
            double max = courses.stream()
                    .max(Comparator.comparingDouble(Course::getAverageScore))
                    .get()
                    .getAverageScore();

            return getByAverageScore(courses, max);

        } else {
            return "n/a";
        }
    }

    public static void courseUpdate(Course course, int points, Student student) {

        if (points > 0) {
            course.setCompletedTasks(course.getCompletedTasks() + 1);

            switch (course.getName()) {
                case "Java":
                    if (student.getJavaPoints() == 0) {
                        course.setEnrolls(course.getEnrolls() + 1);
                    }
                    break;
                case "DSA":
                    if (student.getDsaPoints() == 0) {
                        course.setEnrolls(course.getEnrolls() + 1);
                    }
                    break;
                case "Databases":
                    if (student.getDbPoints() == 0) {
                        course.setEnrolls(course.getEnrolls() + 1);
                    }
                    break;
                case "Spring":
                    if (student.getSpringPoints() == 0) {
                        course.setEnrolls(course.getEnrolls() + 1);
                    }
                    break;
            }

            course.setAverageScore((course.getAverageScore() * (course.getCompletedTasks() - 1) +
                    points) / course.getCompletedTasks());
        }
    }

    public static void updateNotification(Student student, ArrayList<Notification> notifications) {

        if (student.getJavaPoints() >= JAVA_POINTS_TO_COMPLETION) {

            Notification notification = new Notification("Java", student, false);

            if (isNotificationUnique(notifications, notification)) {
                notifications.add(notification);
            }
        }

        if (student.getDsaPoints() >= DSA_POINTS_TO_COMPLETION) {

            Notification notification = new Notification("DSA", student, false);


            if (isNotificationUnique(notifications, notification)) {
                notifications.add(notification);
            }
        }

        if (student.getDbPoints() >= DB_POINTS_TO_COMPLETION) {

            Notification notification = new Notification("Databases", student, false);

            if (isNotificationUnique(notifications, notification)) {
                notifications.add(notification);
            }
        }

        if (student.getSpringPoints() >= SPRING_POINTS_TO_COMPLETION) {

            Notification notification = new Notification("Spring", student, false);

            if (isNotificationUnique(notifications, notification)) {
                notifications.add(notification);
            }
        }
    }

    public static void sendNotification(ArrayList<Notification> notifications) {

        long studentsNotifiedCounter = uniqueStudentToNotify(notifications);

        for (Notification notification : notifications) {
            if (!notification.isSent()) {
                notification.showNotification();
                notification.setSent(true);
            }
        }
        System.out.printf("Total %d students have been notified. \n", studentsNotifiedCounter);
    }

    private static boolean isNotificationUnique(ArrayList<Notification> notifications, Notification notification) {
        return notifications.stream()
                .noneMatch(notif -> notif.getCourseName().equals(notification.getCourseName())
                        && notif.getStudent().getId() == notification.getStudent().getId());
    }

    private static long uniqueStudentToNotify(ArrayList<Notification> notifications) {
        return notifications.stream()
                .filter(notification -> !notification.isSent())
                .map(Notification::getStudent)
                .distinct()
                .count();
    }

    private static boolean notificationsToSend(ArrayList<Notification> notifications) {
        return notifications.stream()
                .anyMatch(notification -> !notification.isSent());
    }

    private static String getByPopularity(ArrayList<Course> courses, int minOrMax) {
        List<String> coursesArray = courses.stream()
                .filter(course -> course.getEnrolls() == minOrMax)
                .map(Course::getName)
                .collect(Collectors.toList());

        return stringFormatter(coursesArray);
    }

    private static String getByAverageScore(ArrayList<Course> courses, double minOrMax) {
        List<String> coursesArray = courses.stream()
                .filter(course -> course.getAverageScore() == minOrMax)
                .map(Course::getName)
                .collect(Collectors.toList());

        return stringFormatter(coursesArray);
    }

    private static String getByCompletedTasks(ArrayList<Course> courses, int minOrMax) {
        List<String> coursesArray = courses.stream()
                .filter(course -> course.getCompletedTasks() == minOrMax)
                .map(Course::getName)
                .collect(Collectors.toList());

        return stringFormatter(coursesArray);
    }

    public static void getStudentsByCourse(ArrayList<Student> students, Course course) {

        switch (course.getName()) {
            case "Java":
                students.sort(Comparator.comparing(Student::getJavaPoints).reversed().thenComparing(Student::getId));

                for (Student student : students) {
                    if (student.getJavaPoints() > 0) {
                        System.out.printf("%s %s %s \n", student.getId(), student.getJavaPoints(),
                                percentageFormatter(student.getJavaPoints(), course.getPointsToCompletion()));
                    }
                }
                break;
            case "DSA":
                students.sort(Comparator.comparing(Student::getDsaPoints).reversed().thenComparing(Student::getId));

                for (Student student : students) {
                    if (student.getDsaPoints() > 0) {
                        System.out.printf("%s %s %s \n", student.getId(), student.getDsaPoints(),
                                percentageFormatter(student.getDsaPoints(), course.getPointsToCompletion()));
                    }
                }
                break;
            case "Databases":
                students.sort(Comparator.comparing(Student::getDbPoints).reversed().thenComparing(Student::getId));

                for (Student student : students) {
                    if (student.getDbPoints() > 0) {
                        System.out.printf("%s %s %s \n", student.getId(), student.getDbPoints(),
                                percentageFormatter(student.getDbPoints(), course.getPointsToCompletion()));
                    }
                }

                break;
            case "Spring":
                students.sort(Comparator.comparing(Student::getSpringPoints).reversed().thenComparing(Student::getId));

                for (Student student : students) {
                    if (student.getSpringPoints() > 0) {
                        System.out.printf("%s %s %s \n", student.getId(), student.getSpringPoints(),
                                percentageFormatter(student.getSpringPoints(), course.getPointsToCompletion()));
                    }
                }
                break;
        }

    }

    private static String percentageFormatter(int studentsPoints, int pointsToCompletion) {

        double percentage = ((double) studentsPoints / pointsToCompletion) * 100;

        BigDecimal bigDecimal = new BigDecimal(percentage);
        BigDecimal bdRounded = bigDecimal.setScale(1, RoundingMode.HALF_UP);

        return bdRounded + "%";

    }

    private static String stringFormatter(List<String> strings) {

        StringBuilder sb = new StringBuilder();

        for (String elem : strings) {
            sb.append(elem).append(", ");
        }
        return sb.delete(sb.length() - 2, sb.length() - 1).toString();
    }

    public static boolean validCourseName(String name, ArrayList<Course> courses) {

        return courses.stream()
                .anyMatch(course -> course.getName().equalsIgnoreCase(name));
    }

    public static Course getCourseByName(String name, ArrayList<Course> courses) {

        if (validCourseName(name, courses)) {
            return courses.stream()
                    .filter(course -> course.getName().equalsIgnoreCase(name))
                    .findFirst()
                    .get();
        } else {
            return null;
        }
    }


}
