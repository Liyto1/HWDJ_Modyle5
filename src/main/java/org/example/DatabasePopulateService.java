package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabasePopulateService {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();

        String sqlFilePath = "sql/populate_db.sql";

        try (FileReader fileReader = new FileReader(sqlFilePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            StringBuilder sqlQuery = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                sqlQuery.append(line).append(" ");
                if (line.trim().endsWith(";")) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.toString())) {
                        preparedStatement.execute();
                    }
                    // Очищаємо буфер для наступного запиту
                    sqlQuery.setLength(0);
                }
            }

            System.out.println("Базу даних успішно заповнено.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
