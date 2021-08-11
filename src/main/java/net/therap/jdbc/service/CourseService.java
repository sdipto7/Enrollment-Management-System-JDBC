package net.therap.jdbc.service;

import net.therap.jdbc.domain.Course;
import net.therap.jdbc.util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rumi.dipto
 * @since 8/11/21
 */
public class CourseService {

    public List<Course> getAll() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        String query = "SELECT * FROM course";
        ResultSet resultSet = executeQuery(connection, query);
        List<Course> courseList = extractCourseData(resultSet);

        return courseList;
    }

    public ResultSet executeQuery(Connection connection, String query) throws SQLException {
        ResultSet resultSet = null;
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

        return resultSet;
    }

    public List<Course> extractCourseData(ResultSet resultSet) throws SQLException {
        List<Course> courseList = new ArrayList<>();
        while (resultSet.next()) {
            Course course = new Course(resultSet.getString("course_code"), resultSet.getString("course_title"));
            courseList.add(course);
        }

        return courseList;
    }
}
