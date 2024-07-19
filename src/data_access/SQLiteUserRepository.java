package data_access;

import entity.*;
import java.sql.*;

public class SQLiteUserRepository {
    private static final String URL = "jdbc:sqlite:users.db";

    public SQLiteUserRepository() {
        createTable();
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "username TEXT PRIMARY KEY,"
                + "password TEXT NOT NULL,"
                + "email TEXT NOT NULL,)";
        //HANDLER: TRY CATCH
    }
}
