package net.therap.jdbc.service;

import net.therap.jdbc.domain.Course;
import net.therap.jdbc.domain.EnrollmentDetailsOutput;
import net.therap.jdbc.domain.Trainee;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
public class JdbcViewService {

    public static void printCourseInformation(List<Course> courseList) {
        System.out.println("Courses: ");
        for (Course c : courseList) {
            System.out.println(c.getCourseCode() + " - " + c.getCourseTitle());
        }
    }

    public static void printTraineeInformation(List<Trainee> traineeList) {
        System.out.println("Trainees: ");
        for (Trainee t : traineeList) {
            System.out.println(t.getTraineeId() + " - " + t.getTraineeName());
        }
    }

    public static void print(EnrollmentDetailsOutput enrollmentDetailsOutput) {
        for (Map.Entry m : enrollmentDetailsOutput.getTraineeInfo().entrySet()) {
            System.out.println("Trainee ID: " + m.getKey());
            System.out.println("Trainee Name: " + m.getValue());
            System.out.println("Courses: ");

            HashSet<String> courses = enrollmentDetailsOutput.getEnrollmentInfo().get(m.getKey());
            for (String s : courses) {
                System.out.println(s + " - " + enrollmentDetailsOutput.getCourseInfo().get(s));
            }
            System.out.println("====================");
        }
    }
}