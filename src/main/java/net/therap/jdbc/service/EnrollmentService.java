package net.therap.jdbc.service;

import net.therap.jdbc.domain.Course;
import net.therap.jdbc.domain.Enrollment;
import net.therap.jdbc.domain.Trainee;
import net.therap.jdbc.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

        ResultSet resultSet = queryExecute(connection, query);
        return extractEnrollmentData(resultSet);
    }

    public void updateAll(String traineeId, String courseCode) {
        Connection connection = ConnectionManager.getConnection();
        String query = "INSERT INTO enrollment VALUES (?, ?)";
        updateExecute(connection, query, traineeId, courseCode);
    }

    public ResultSet queryExecute(Connection connection, String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public void updateExecute(Connection connection, String query, String traineeId, String courseCode) {
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

    public List<Enrollment> extractEnrollmentData(ResultSet resultSet) {
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
}