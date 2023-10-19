package MiniBookStore;

import java.sql.*;
import java.util.Scanner;

public class DatabaseHandler {
    protected Connection connection;
    Scanner inp = new Scanner(System.in);

    public DatabaseHandler() {
        //DatabaseHandler classı çağrıldığında otomatik veri bağlantısı kurmak için

        // Veritabanı bağlantısını kurmak için gerekli ayarlar
        String url = "jdbc:postgresql://localhost:5432/PROJECT"; // Veritabanı URL'si-project adında bir database açtık
        String username = "techpront"; // Veritabanı kullanıcı adı
        String password = "password"; // Veritabanı şifresi

        try {
            // Veritabanına bağlan
            connection = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            System.out.println("Database bağlanırken yanlış bilgiler girdiniz.");
            e.printStackTrace();
        }

    }


    public void closeConnection() {
        try {
            if (connection != null) {//eğer bağlantı açıksa kapat
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
