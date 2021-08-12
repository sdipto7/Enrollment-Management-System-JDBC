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

    public List<Trainee> getAll() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        String query = "SELECT * FROM trainee";
        ResultSet resultSet = executeQuery(connection, query);

        return extractTraineeData(resultSet);
    }

    public ResultSet executeQuery(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();

        return statement.executeQuery(query);
    }

    public List<Trainee> extractTraineeData(ResultSet resultSet) throws SQLException {
        List<Trainee> traineeList = new ArrayList<>();
        while (resultSet.next()) {
            Trainee trainee = new Trainee(resultSet.getInt("trainee_id"), resultSet.getString("trainee_name"));
            traineeList.add(trainee);
        }

        return traineeList;
    }
}
