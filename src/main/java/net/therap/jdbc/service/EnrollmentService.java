package net.therap.jdbc.service;

import net.therap.jdbc.domain.*;
import net.therap.jdbc.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
public class JdbcService implements Database {

    public static Connection connectDatabase() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

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
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, traineeId);
            preparedStatement.setString(2, courseCode);
            int row = preparedStatement.executeUpdate();
            System.out.println(row + " rows affected!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static List<Enrollment> enrollmentDataExtract(ResultSet resultSet) {
        List<Enrollment> enrollmentDetailsList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Enrollment e = new Enrollment();
                e.setCourseCode(resultSet.getString("course_table.Course_Code"));
                e.setCourseTitle(resultSet.getString("course_table.Course_Title"));
                e.setTraineeName(resultSet.getString("trainee_table.Trainee_Name"));
                e.setTraineeId(resultSet.getString("trainee_table.Trainee_id"));
                enrollmentDetailsList.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollmentDetailsList;
    }

    public static List<Trainee> traineeDataExtract(ResultSet resultSet) {
        List<Trainee> traineeList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Trainee t = new Trainee(resultSet.getString("Trainee_id"), resultSet.getString("Trainee_Name"));
                traineeList.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return traineeList;
    }

    public static List<Course> courseDataExtract(ResultSet resultSet) {
        List<Course> courseList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Course c = new Course(resultSet.getString("Course_Code"), resultSet.getString("Course_Title"));
                courseList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    public static void generateOutput(List<Enrollment> enrollmentDetailsList) {
        HashMap<String, HashSet<String>> enrollmentInfo = new HashMap<>();

        HashMap<String, String> traineeInfo = new HashMap<>();

        HashMap<String, String> courseInfo = new HashMap<>();

        for (Enrollment e : enrollmentDetailsList) {
            String courseCode = e.getCourseCode();
            String courseTitle = e.getCourseTitle();
            String traineeName = e.getTraineeName();
            String traineeId = e.getTraineeId();

            traineeInfo.put(traineeId, traineeName);
            courseInfo.put(courseCode, courseTitle);
            if (enrollmentInfo.containsKey(traineeId)) {
                enrollmentInfo.get(traineeId).add(courseCode);
            } else {
                enrollmentInfo.put(traineeId, new HashSet<>());
                enrollmentInfo.get(traineeId).add(courseCode);
            }
        }
        JdbcViewService.print(enrollmentInfo, traineeInfo, courseInfo);
    }
}