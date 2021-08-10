package net.therap.jdbc.service;

import net.therap.jdbc.domain.Course;
import net.therap.jdbc.domain.Enrollment;
import net.therap.jdbc.domain.Trainee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
public class EnrollmentService {

    public static ResultSet queryExecute(Connection connection, String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public static void updateExecute(Connection connection, String query, String traineeId, String courseCode) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, traineeId);
            preparedStatement.setString(2, courseCode);
            int row = preparedStatement.executeUpdate();
            System.out.println(row + " rows affected!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Enrollment> extractEnrollmentData(ResultSet resultSet) {
        List<Enrollment> enrollmentList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                boolean newEnrollment = true;

                Course course = new Course(resultSet.getString("course.course_code"), resultSet.getString("course.course_title"));
                Trainee trainee = new Trainee(resultSet.getString("trainee.trainee_id"), resultSet.getString("trainee.trainee_name"));

                if (enrollmentList.size() > 0) {
                    for (Enrollment enrollment : enrollmentList) {
                        if (enrollment.getTrainee().getTraineeId().equals(resultSet.getString("trainee.trainee_id"))) {
                            enrollment.getCourseList().add(course);
                            newEnrollment = false;
                            break;
                        }
                    }
                }
                if (newEnrollment) {
                    List<Course> courseList = new ArrayList<>();
                    courseList.add(course);
                    enrollmentList.add(new Enrollment(trainee, courseList));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enrollmentList;
    }

    public static List<Trainee> extractTraineeData(ResultSet resultSet) {
        List<Trainee> traineeList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Trainee trainee = new Trainee(resultSet.getString("trainee_id"), resultSet.getString("trainee_name"));
                traineeList.add(trainee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return traineeList;
    }

    public static List<Course> extractCourseData(ResultSet resultSet) {
        List<Course> courseList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Course course = new Course(resultSet.getString("course_code"), resultSet.getString("course_title"));
                courseList.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courseList;
    }
}