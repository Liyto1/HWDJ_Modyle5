package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasePopulateService {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();

        try {
            insertWorkerData(connection);

            insertClientData(connection);

            insertProjectData(connection);

            insertProjectWorkerData(connection);

            System.out.println("Базу даних успішно заповнено.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertWorkerData(Connection connection) throws SQLException {
        String insertWorkerSQL = "INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertWorkerSQL)) {
            insertWorkerRow(preparedStatement, "Forrest Gump", "1990-05-15", "Trainee", 800);
        }
    }

    private static void insertClientData(Connection connection) throws SQLException {
        String insertClientSQL = "INSERT INTO client (NAME) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertClientSQL)) {
            insertClientRow(preparedStatement, "Client A");
        }
    }

    private static void insertProjectData(Connection connection) throws SQLException {
        String insertProjectSQL = "INSERT INTO project (CLIENT_ID, START_DATE, FINISH_DATE) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertProjectSQL)) {
            insertProjectRow(preparedStatement, 1, "2023-01-01", "2023-03-31");
        }
    }

    private static void insertProjectWorkerData(Connection connection) throws SQLException {
        String insertProjectWorkerSQL = "INSERT INTO project_worker (PROJECT_ID, WORKER_ID) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertProjectWorkerSQL)) {
            insertProjectWorkerRow(preparedStatement, 1, 1);
        }
    }

    private static void insertWorkerRow(PreparedStatement preparedStatement, String name, String birthday, String level, int salary) throws SQLException {
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, birthday);
        preparedStatement.setString(3, level);
        preparedStatement.setInt(4, salary);
        preparedStatement.executeUpdate();
    }

    private static void insertClientRow(PreparedStatement preparedStatement, String name) throws SQLException {
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
    }

    private static void insertProjectRow(PreparedStatement preparedStatement, int clientId, String startDate, String finishDate) throws SQLException {
        preparedStatement.setInt(1, clientId);
        preparedStatement.setString(2, startDate);
        preparedStatement.setString(3, finishDate);
        preparedStatement.executeUpdate();
    }

    private static void insertProjectWorkerRow(PreparedStatement preparedStatement, int projectId, int workerId) throws SQLException {
        preparedStatement.setInt(1, projectId);
        preparedStatement.setInt(2, workerId);
        preparedStatement.executeUpdate();
    }
}
