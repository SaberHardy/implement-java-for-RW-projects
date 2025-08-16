package main.java;

import main.java.models.UserModel;
import main.java.services.UserService;

public class mainDB {
    public static void main(String[] args) {
        UserService userService = new UserService();

        userService.createUser(new UserModel("Saber", "Email.com"));
        userService.createUser(new UserModel("Khiro", "Email2.com"));

        System.out.println("All users --------");
        userService.getAllUsers();
    }
}
