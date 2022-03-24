import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class ChangeTownNamesCasing {
    public static void main (String [] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        String country = sc.nextLine().trim();
        PreparedStatement selectTown = connection.prepareStatement(
                "SELECT name FROM towns WHERE country = ?");
        selectTown.setString(1, country);

        ResultSet towns = selectTown.executeQuery();
        if(!towns.next()){
            System.out.println("No town names were affected.");
        } else {
            PreparedStatement updateTown = connection.prepareStatement(
                    "UPDATE towns\n" +
                            "SET name = UPPER(name)\n" +
                            "WHERE country = ?");
            updateTown.setString(1, country);
            int countTowns = updateTown.executeUpdate();
            ResultSet updatedTowns = selectTown.executeQuery();

            int line = 1;
            while (updatedTowns.next()){
                if(line == 1){
                    System.out.printf("%d town names were affected.%n", countTowns);
                    System.out.printf("[%s, ",
                            updatedTowns.getString("name"));
                }
                if(line == countTowns){
                    System.out.printf("%s]",
                            updatedTowns.getString("name"));
                }
                if (line != 1 && line != countTowns){
                    System.out.printf("%s, ",
                            updatedTowns.getString("name"));
                }
                line++;
            }

        }
    }
}
