import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetVillainsNames {
    public static void main (String [] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter username default:");
        String user = sc.nextLine();
        user = user.equals("") ? "root" : user;

        System.out.println("Enter password default:");
        String password = sc.nextLine().trim();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement stmt = connection.prepareStatement(
                "SELECT v.name, COUNT(DISTINCT mv.minion_id) AS count_minions\n" +
                        "FROM villains v\n" +
                        "JOIN minions_villains mv ON v.id = mv.villain_id\n" +
                        "GROUP BY mv.villain_id\n" +
                        "HAVING count_minions > 15\n" +
                        "ORDER BY count_minions DESC;");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            System.out.printf("%s %d",
                    rs.getString("name"),
                    rs.getInt("count_minions"));
        }

    }
}
