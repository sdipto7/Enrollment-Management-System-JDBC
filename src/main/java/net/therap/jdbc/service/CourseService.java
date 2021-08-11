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

    public List<Course> getAll() {
        Connection connection = ConnectionManager.getConnection();
        String query = "SELECT * FROM course";
        ResultSet resultSet = executeQuery(connection, query);

        return extractCourseData(resultSet);
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

    public List<Course> extractCourseData(ResultSet resultSet) {
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
