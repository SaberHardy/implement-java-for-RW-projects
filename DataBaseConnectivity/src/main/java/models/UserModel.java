package main.java.models;

public class UserModel {
    private int id;
    private String name;
    private String email;

    public void setId(int id) {this.id = id;}
    public int getId() {return id;}

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserModel() {}

    public UserModel(String name, String email) {
        this.email = email;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
    }
}
