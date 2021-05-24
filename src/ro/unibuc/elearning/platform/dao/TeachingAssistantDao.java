package ro.unibuc.elearning.platform.dao;

import java.sql.SQLException;
import java.sql.Statement;

public final class TeachingAssistantDao extends Dao {
    private static TeachingAssistantDao teachingAssistantDao;

    private TeachingAssistantDao() {
        super();
        createTable();
    }

    private void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS TeachingAssistant (\n" +
                "id INT PRIMARY KEY,\n" +
                "supervisorTeacherId INT NOT NULL,\n" +
                "FOREIGN KEY (supervisorTeacherId) REFERENCES Teacher (id),\n" +
                "FOREIGN KEY (id) REFERENCES User(id))";

        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static TeachingAssistantDao getTeachingAssistantDao() {
        if (teachingAssistantDao == null)
            teachingAssistantDao = new TeachingAssistantDao();
        return teachingAssistantDao;
    }

}
