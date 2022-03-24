import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class IncreaseMinionsAge {
    public static void main (String [] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        String[] ids = sc.nextLine().split(" ");
        String query = "UPDATE minions\n" +
                "SET age = age + 1, \n" +
                "name = lower(name)\n" +
                "WHERE id = ?";
        PreparedStatement updateMinions = connection.prepareStatement(query);


        for (int i = 0; i < ids.length; i++)
        {
            updateMinions.setInt(1,Integer.parseInt(ids[i]));
            updateMinions.executeUpdate();
        }

        PreparedStatement selectMinions = connection.prepareStatement("select name, age from minions");
        ResultSet minions = selectMinions.executeQuery();
        while (minions.next()){
            System.out.printf("%s %d%n",minions.getString("name"), minions.getInt("age"));
        }
    }
}
