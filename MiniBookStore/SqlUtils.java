package MiniBookStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlUtils {
    public static void connection() throws SQLException {
        Connection connection = DriverManager.
                getConnection("jdbc:postgresql://localhost:5432/PROJECT", "techpront", "password");
        //2-ADIM
        Statement st = connection.createStatement();
    }


   // public static void close(){
   //     st.close();
   //     connection.close();
   // }
}
