import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {


    static final String JDBC_URL = "jdbc:mysql://localhost:3306/userprofile";
    static final String USER = "root";
    static final String PASSWORD = "Helloworld1306@@";

    public static void insertUserProfile(UserProfile userProfile) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO userprofile (Email, Username, UserPassword, RegistrationDate, CurrentPoints) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, userProfile.getEmail());
            preparedStatement.setString(2, userProfile.getUsername());
            preparedStatement.setString(3, userProfile.getUserPassword());
            preparedStatement.setString(4, userProfile.getRegistrationDate());
            preparedStatement.setString(5, userProfile.getCurrentPoints());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<UserProfile> retrieveUserProfile() {
        List<UserProfile> userProfileList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            String selectSQL = "SELECT * FROM userprofile";
            ResultSet resultSet = statement.executeQuery(selectSQL);

            while (resultSet.next()) {
                String email = resultSet.getString("Email");
                String username = resultSet.getString("Username");
                String userPassword = resultSet.getString("UserPassword");
                String registrationDate = resultSet.getString("RegistrationDate");
                String currentPoints = resultSet.getString("CurrentPoints");

                userProfileList.add(new UserProfile(email, username, userPassword,registrationDate, currentPoints));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userProfileList;
    }

    public static void main(String[] args) {
        // Example usage
        UserProfile userProfile1 = new UserProfile("johndoe@email.com", "JohnDoe", "1234tt@","2023-06-17", "23");
        UserProfile userProfile2 = new UserProfile("janesmith@email.com", "JaneSmith", "iiyre7","2023-08-23", "15");

        // Insert user profile into MySQL
        insertUserProfile(userProfile1);
        insertUserProfile(userProfile2);

        // Retrieve user profile from MySQL
        List<UserProfile> readData = retrieveUserProfile();
        for (UserProfile userProfile : readData) {
            System.out.println(userProfile);
        }
    }
}
class UserProfile {
    private String email;
    private String username;
    private String userPassword;
    private String registrationDate;
    private String currentPoints;

    public UserProfile(String email, String username, String userPassword, String registrationDate, String currentPoints) {
        this.email = email;
        this.username = username;
        this.userPassword = userPassword;
        this.registrationDate = registrationDate;
        this.currentPoints = currentPoints;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getCurrentPoints() {
        return currentPoints;
    }

    public String toString() {
        return "UserProfile{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", currentPoints='" + currentPoints + '\'' +
                '}';
    }
}





