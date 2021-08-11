package net.therap.jdbc.service;

import net.therap.jdbc.domain.Course;
import net.therap.jdbc.domain.Enrollment;
import net.therap.jdbc.domain.Trainee;
import net.therap.jdbc.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
public class EnrollmentService {

    public List<Enrollment> getAll() {
        Connection connection = ConnectionManager.getConnection();

        String query = "SELECT trainee.trainee_id, trainee.trainee_name, course.course_code, course.course_title FROM " +
                "((enrollment INNER JOIN trainee ON enrollment.trainee_id=trainee.trainee_id) " +
                "INNER JOIN course ON course.course_code=enrollment.course_code)";

        ResultSet resultSet = executeQuery(connection, query);

        return extractEnrollmentData(resultSet);
    }

    public void updateAll() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter trainee id");
        int traineeId = input.nextInt();
        System.out.println("Enter course code");
        String courseCode = input.next();

        Connection connection = ConnectionManager.getConnection();
        String query = "INSERT INTO enrollment VALUES (?, ?)";
        executeUpdate(connection, query, traineeId, courseCode);
    }

    public ResultSet executeQuery(Connection connection, String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public void executeUpdate(Connection connection, String query, int traineeId, String courseCode) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, traineeId);
            preparedStatement.setString(2, courseCode);
            int row = preparedStatement.executeUpdate();
            System.out.println(row + " rows affected!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Enrollment> extractEnrollmentData(ResultSet resultSet) {
        List<Enrollment> enrollmentList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                boolean newEnrollment = true;

                Course course = new Course(resultSet.getString("course.course_code"), resultSet.getString("course.course_title"));
                Trainee trainee = new Trainee(resultSet.getInt("trainee.trainee_id"), resultSet.getString("trainee.trainee_name"));

                if (enrollmentList.size() > 0) {
                    for (Enrollment enrollment : enrollmentList) {
                        if ((enrollment.getTrainee().getTraineeId()) == (resultSet.getInt("trainee.trainee_id"))) {
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
}