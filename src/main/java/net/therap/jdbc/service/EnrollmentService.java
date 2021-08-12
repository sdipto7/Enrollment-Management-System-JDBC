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

    public List<Enrollment> getAll() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        String query = "SELECT trainee.trainee_id, trainee.trainee_name, course.course_code, course.course_title" +
                " FROM enrollment INNER JOIN trainee ON enrollment.trainee_id=trainee.trainee_id" +
                " INNER JOIN course ON course.course_code=enrollment.course_code";

        ResultSet resultSet = executeQuery(connection, query);

        return extractEnrollmentData(resultSet);
    }

    public void save(int traineeId, String courseCode) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        String query = "INSERT INTO enrollment VALUES (?, ?)";
        executeUpdate(connection, query, traineeId, courseCode);
    }

    public ResultSet executeQuery(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();

        return statement.executeQuery(query);
    }

    public void executeUpdate(Connection connection, String query, int traineeId, String courseCode) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, traineeId);
        preparedStatement.setString(2, courseCode);
        int row = preparedStatement.executeUpdate();
        System.out.println(row + " rows affected!");
    }

    public List<Enrollment> extractEnrollmentData(ResultSet resultSet) throws SQLException {
        List<Enrollment> enrollmentList = new ArrayList<>();
        while (resultSet.next()) {
            Trainee trainee = new Trainee(resultSet.getInt("trainee.trainee_id"),
                    resultSet.getString("trainee.trainee_name"));

            Course course = new Course(resultSet.getString("course.course_code"),
                    resultSet.getString("course.course_title"));

            Enrollment enrollment = new Enrollment(trainee, course);
            enrollmentList.add(enrollment);
        }

        return enrollmentList;
    }
}