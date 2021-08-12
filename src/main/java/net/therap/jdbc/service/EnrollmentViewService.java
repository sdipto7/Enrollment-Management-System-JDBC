package net.therap.jdbc.service;

import net.therap.jdbc.domain.Course;
import net.therap.jdbc.domain.Enrollment;
import net.therap.jdbc.domain.Trainee;

import java.util.Collections;
import java.util.List;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
public class EnrollmentViewService {

    public static void printCourseInformation(List<Course> courseList) {
        System.out.println("Courses: ");
        for (Course course : courseList) {
            System.out.println(course.getCourseCode() + " - " + course.getCourseTitle());
        }
    }

    public static void printTraineeInformation(List<Trainee> traineeList) {
        System.out.println("Trainees: ");
        for (Trainee trainee : traineeList) {
            System.out.println(trainee.getTraineeId() + " - " + trainee.getTraineeName());
        }
    }

    public static void printEnrollmentInformation(List<Enrollment> enrollmentList) {
        Collections.sort(enrollmentList);
        Trainee trainee = null;
        
        for (Enrollment enrollment : enrollmentList) {
            Course course = enrollment.getCourse();
            if (trainee == null || (enrollment.getTrainee().hashCode() != trainee.hashCode())) {
                trainee = enrollment.getTrainee();
                System.out.println("Trainee:\n" + trainee.getTraineeId() + " - " + trainee.getTraineeName());
                System.out.println("Courses:\n" + course.getCourseCode() + " - " + course.getCourseTitle());
            } else if (enrollment.getTrainee().equals(trainee)) {
                System.out.println(course.getCourseCode() + " - " + course.getCourseTitle());
            }
        }
    }
}