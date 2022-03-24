import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure {
    public static void main (String [] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        int id = sc.nextInt();
        PreparedStatement updateMinions = connection.prepareStatement("CALL usp_get_older(?)");
        updateMinions.setInt(1, id);

        PreparedStatement selectMinions = connection.prepareStatement("select name, age from minions where id = ?");
        selectMinions.setInt(1, id);
        ResultSet minions = selectMinions.executeQuery();

        while (minions.next()){
            System.out.printf("%s %d%n",minions.getString("name"), minions.getInt("age"));
        }


    }
}
