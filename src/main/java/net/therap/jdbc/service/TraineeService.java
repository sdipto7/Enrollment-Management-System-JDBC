package net.therap.jdbc.service;

import net.therap.jdbc.domain.Trainee;
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
public class TraineeService {

    public List<Trainee> getAll() {
        Connection connection = ConnectionManager.getConnection();
        String query = "SELECT * FROM trainee";
        ResultSet resultSet = queryExecute(connection, query);
        return extractTraineeData(resultSet);
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

    public List<Trainee> extractTraineeData(ResultSet resultSet) {
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
}