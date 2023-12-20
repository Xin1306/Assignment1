import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

        public class Main {

            public static void writeUserData(String filePath, List<UserProfile> userData) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                    // Write header
                    writer.write("Email, Username, UserPassword, RegistrationDate, CurrentPoints");
                    writer.newLine();

                    // Write user data
                    for (UserProfile user : userData) {
                        writer.write(user.toCsvString());
                        writer.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public static List<UserProfile> readUserData(String filePath) {
                List<UserProfile> userData = new ArrayList<>();

                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                    // Read header (skip it)
                    reader.readLine();

                    // Read user data
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        UserProfile user = new UserProfile(parts[0], parts[1], parts[2], parts[3], parts[4]);
                        userData.add(user);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return userData;
            }

            public static void main(String[] args) {
                // Example usage
                List<UserProfile> user_data = new ArrayList<>();
                user_data.add(new UserProfile("johndoe@email.com", "JohnDoe", "1234tt*", "2023-06-17", "23"));
                user_data.add(new UserProfile("janesmith@email.com", "JaneSmith", "iikyd6", "2023-06-17", "15"));

                String csvFilePath = "user_data.csv";

                // Write user data to CSV
                writeUserData(csvFilePath, user_data);

                // Read user data from CSV
                List<UserProfile> readData = readUserData(csvFilePath);
                for (UserProfile user : readData) {
                    System.out.println(user);
                }
            }
        }

        class UserProfile {
            private String email;
            private String username;
            private String userPassword;
            private String registrationDate;
            private String currentPoints;

            public UserProfile(String email, String username, String userPassword, String registrationDate,String currentPoints) {
                this.email = email;
                this.username = username;
                this.userPassword = userPassword;
                this.registrationDate = registrationDate;
                this.currentPoints = currentPoints;
            }

            public String toCsvString() {
                return email + "," + username + "," + userPassword + "," + registrationDate + "," + currentPoints;
            }

            @Override
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


