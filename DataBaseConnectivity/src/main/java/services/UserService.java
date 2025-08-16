package main.java.services;

import main.java.dao.UserDao;
import main.java.models.UserModel;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserDao userDao = new UserDao();

    public void createUser(UserModel userModel) {
        try {
            userDao.insertUser(userModel);
        } catch (SQLException e) {
            System.out.println("Error creating user: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<UserModel> getAllUsers() {
        try {
            return userDao.readAllUsers();
        } catch (SQLException e) {
            System.out.println("Error during ");
            return List.of();
        }
    }

    public UserModel getUser(int userId) {
        try {
            return userDao.readUser(userId);
        } catch (SQLException e) {
            System.out.println("No user returned!");
            return null;
        }
    }

    public void deleteUser(int id) {
        try {
            if (userDao.deleteUSer(id)) {
                System.out.println("User: '" + id + "' deleted successfully!");
            } else {
                System.out.println("We could not delete the user check it later!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
