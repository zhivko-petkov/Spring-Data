import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class PrintAllMinionNames {
    public static void main (String [] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement selectAllMinions = connection.prepareStatement(
                "select name from minions\n");

        ResultSet minions = selectAllMinions.executeQuery();
        List<String> minionsList = new ArrayList<>();

        while (minions.next()){
            minionsList.add(minions.getString("name"));
        }
        for (int i = 0; i < minionsList.size()/2; i++) {
            System.out.printf("%s\n%s\n", minionsList.get(i),minionsList.get((minionsList.size() - i) - 1));
        }

    }
}
