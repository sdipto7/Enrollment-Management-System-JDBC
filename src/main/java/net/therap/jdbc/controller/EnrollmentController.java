package net.therap.jdbc.controller;

import net.therap.jdbc.domain.Course;
import net.therap.jdbc.domain.Enrollment;
import net.therap.jdbc.domain.Trainee;
import net.therap.jdbc.service.CourseService;
import net.therap.jdbc.service.EnrollmentService;
import net.therap.jdbc.service.EnrollmentViewService;
import net.therap.jdbc.service.TraineeService;

import java.util.List;
import java.util.Scanner;

/**
 * @author rumi.dipto
 * @since 8/10/21
 */
public class EnrollmentController {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Press 0 to exit");
            System.out.println("Press 1 to view courses");
            System.out.println("Press 2 to view trainees");
            System.out.println("Press 3 to view course enrollment details");
            System.out.println("Press 4 to assign a course to trainee");

            int operation = input.nextInt();
            if (operation == 0) {
                break;
            }

            executeOperation(operation);
            System.out.println("\n===========\n");
        }

        input.close();
    }

    public static void executeOperation(int operation) {
        EnrollmentService enrollmentService;
        switch (operation) {
            case 1:
                CourseService courseService = new CourseService();
                List<Course> courseList = courseService.getAll();
                EnrollmentViewService.printCourseInformation(courseList);
                break;

            case 2:
                TraineeService traineeService = new TraineeService();
                List<Trainee> traineeList = traineeService.getAll();
                EnrollmentViewService.printTraineeInformation(traineeList);
                break;

            case 3:
                enrollmentService = new EnrollmentService();
                List<Enrollment> enrollmentList = enrollmentService.getAll();
                EnrollmentViewService.printEnrollmentInformation(enrollmentList);
                break;

            case 4:
                Scanner input = new Scanner(System.in);
                System.out.println("Enter trainee id");
                int traineeId = input.nextInt();
                System.out.println("Enter course code");
                String courseCode = input.next();

                enrollmentService = new EnrollmentService();
                enrollmentService.save(traineeId, courseCode);
                break;

            default:
                System.out.println("Wrong input! Please enter a valid input");
                break;
        }
    }
}
