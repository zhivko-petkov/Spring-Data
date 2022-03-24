import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetMinionNames {
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
                "SELECT v.name, m.name, m.age\n" +
                        "FROM villains v\n" +
                        "JOIN minions_villains mv ON v.id = mv.villain_id\n" +
                        "JOIN minions m ON m.id = mv.minion_id\n" +
                        "WHERE v.id = ?");

        int id = sc.nextInt();
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();


        int count = 1;
        while (rs.next()){
            if(count == 1){
                System.out.printf("Villain: %s\n", rs.getString("v.name"));
            }
            System.out.printf("%d. %s %d\n", count,
                    rs.getString("m.name"),
                    rs.getInt("m.age"));
            count++;
        }
        if(!rs.next() && count == 1){
            System.out.printf("No villain with ID %d exists in the database.", id);
        }

    }
}
