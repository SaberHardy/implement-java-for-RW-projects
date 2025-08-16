package main.java.dao;

import main.java.models.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String INSERT_QUERY = "INSERT INTO users(name, email) VALUES (?,?)";
    private static final String SELECT_ALL_USERS_QUERY = "SELECT * FROM users";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM users where id=?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users where id=?";
    private static final String UPDATE_USER_QUERY = "Update users set name=?, email=? where id=?";

    // Insert user
    public void insertUser(UserModel user) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    user.setId(resultSet.getInt(1));
                }
            }
        }
    }

    // Read single user by id
    public UserModel readUser(int id) throws SQLException {
        UserModel userModel = null;
        // Opens a database connection using a try-with-resources statement for automatic resource management.
        try (Connection connection = DatabaseConnection.getConnection();
             // Prepares a SQL statement to select a user by ID.
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            // Sets the first parameter in the SQL query to the provided user ID.
            statement.setInt(1, id);
            // Executes the query and obtains the result set.
            try (ResultSet resultSet = statement.executeQuery()) {
                // Checks if a user was found.
                if (resultSet.next()) {
                    userModel = new UserModel();
                    userModel.setId(resultSet.getInt("id"));
                    userModel.setName(resultSet.getString("name"));
                    userModel.setEmail(resultSet.getString("email"));
                    userModel.setId(id);
                }
            }
        }
        return userModel;
    }

    // Read all users
    public List<UserModel> readAllUsers() throws SQLException {
        List<UserModel> users = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             // Prepares a SQL statement to select a user by ID.
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                UserModel userModel = new UserModel();
                userModel.setId(resultSet.getInt("id"));
                userModel.setName(resultSet.getString("name"));
                userModel.setEmail(resultSet.getString("email"));

                users.add(userModel);
            }
        }
        return users;
    }


    // Delete user
    public boolean deleteUSer(int id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             // Prepares a SQL statement to select a user by ID.
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        }
    }
    // Update
    public boolean updateUser(UserModel user) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             // Prepares an SQL statement for updating a user.
             PreparedStatement stmt = conn.prepareStatement(UPDATE_USER_QUERY)) {

            // Sets the first parameter (name) in the SQL statement.
            stmt.setString(1, user.getName());

            // Sets the second parameter (email) in the SQL statement.
            stmt.setString(2, user.getEmail());

            // Sets the third parameter (user ID) in the SQL statement.
            stmt.setInt(3, user.getId());

            // Executes the update and returns true if at least one row was affected
            return stmt.executeUpdate() > 0;
        }
    }
}
