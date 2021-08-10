package net.therap.jdbc.service;

import net.therap.jdbc.domain.Course;
import net.therap.jdbc.domain.Enrollment;
import net.therap.jdbc.domain.Trainee;

import java.util.List;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
public class EnrollmentViewService {

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

    public static void printEnrollmentInformation(List<Enrollment> enrollmentList) {
        for (Enrollment enrollment : enrollmentList) {
            Trainee trainee = enrollment.getTrainee();
            System.out.println("Trainee:\n" + trainee.getTraineeId() + " - " + trainee.getTraineeName());

            System.out.println("Courses:");
            List<Course> courseList = enrollment.getCourseList();
            for (Course course : courseList) {
                System.out.println(course.getCourseCode() + " - " + course.getCourseTitle());
            }
            System.out.println("============");
        }
    }
}