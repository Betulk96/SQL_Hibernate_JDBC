package MiniBookStore;

import java.sql.*;
import java.util.Scanner;

public class BookSql {
    public static void main(String[] args) throws SQLException {
        //1.ADIM
        Connection connection = DriverManager.
                getConnection("jdbc:postgresql://localhost:5432/PROJECT", "techpront", "password");
        //2-ADIM
        Statement st = connection.createStatement();

        Scanner inp = new Scanner(System.in);
        //tablo oluşturma
        boolean sql1 = st.execute("CREATE TABLE IF NOT EXISTS books" +
                "(name VARCHAR(50),price REAL,stock INT,authorNAME VARCHAR(50),publisher VARCHAR(50),isbn VARCHAR(10))");

//data ekleme
        String sql2 = "INSERT INTO books VALUES(?,?,?,?,?,?) ";
        PreparedStatement pr = connection.prepareStatement(sql2);

        pr.setString(1, "Fareler ve İnsanlar");
        pr.setDouble(2, 150);
        pr.setInt(3, 15);
        pr.setString(4, "J.Steinbeck");
        pr.setString(5, "Penguin");
        pr.setString(6, "A111");

        int inserted = pr.executeUpdate();


        pr.setString(1, "Sefiller");
        pr.setDouble(2, 170.99);
        pr.setInt(3, 60);
        pr.setString(4, "V.Hugo");
        pr.setString(5, "Dream");
        pr.setString(6, "A333");
        int inserted2 = pr.executeUpdate();


        pr.setString(1, "Suç ve Ceza");
        pr.setDouble(2, 250.99);
        pr.setInt(3, 25);
        pr.setString(4, "Dostoyevski");
        pr.setString(5, "Penguin");
        pr.setString(6, "A222");

        int inserted3 = pr.executeUpdate();


//LİSTEYİ GÖSTERME

        ResultSet rs = st.executeQuery("SELECT * FROM books");
        System.out.printf("%-3s | %-20s | %-15s | %-10s | %-4s | %-10s | %-3s | \n",
                "ID", "Kitap Adı", "Yazar Adı", "Yayınevi", "ISBN", "Birim Fiyat", "Stok");
        System.out.println("-----------------------------------------------------------------------------------");

        while (rs.next()) {

            System.out.printf("%-3s | %-20s | %-15s | %-10s | %-4s | %-10s | %-3s | \n",
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("authorname"),
                    rs.getString("publisher"),
                    rs.getString("isbn"),
                    rs.getDouble("price") + "TL",
                    rs.getInt("stock"));

        }

//ÜRÜN EKLEME
        System.out.println("ISBN : ");
        String isbn = inp.nextLine();
        System.out.println("Kitap Adı :");
        String name = inp.nextLine();
        System.out.println("Yazar Adı :");
        String author = inp.nextLine();
        System.out.println("Yayınevi:");
        String publisher = inp.nextLine();
        System.out.println("Birim Fiyat :");
        double price = inp.nextDouble();
        System.out.println("Stok : ");
        int stock = inp.nextInt();
        inp.nextLine();

        pr.setString(1, name);
        pr.setDouble(2, price);
        pr.setInt(3, stock);
        pr.setString(4, author);
        pr.setString(5, publisher);
        pr.setString(6, isbn);
        int inserted4 = pr.executeUpdate();

//Delete

        String sql3 = "DELETE FROM books WHERE id=? ";
        PreparedStatement deleted = connection.prepareStatement(sql3);

        System.out.println("Kitap id : ");
        int d1 = inp.nextInt();
        deleted.setInt(1, d1);
        int deleted1 = deleted.executeUpdate();

        //filtreme
        String sql4 = "SELECT * FROM books WHERE publisher ILIKE ? ";
        PreparedStatement filter = connection.prepareStatement(sql4);

        System.out.println("Yayınevi ismi: ");
        String f = inp.nextLine();

        filter.setString(1, f);
        //filter.setString(1, "%" + f + "%");böyle yazılabilir.
        ResultSet rs1 = filter.executeQuery();

        System.out.printf("%-3s | %-20s | %-15s | %-10s | %-4s | %-10s | %-3s | \n",
                "ID", "Kitap Adı", "Yazar Adı", "Yayınevi", "ISBN", "Birim Fiyat", "Stok");
        System.out.println("-----------------------------------------------------------------------------------");

        while (rs1.next()) {

            System.out.printf("%-3s | %-20s | %-15s | %-10s | %-4s | %-10s | %-3s | \n",
                    rs1.getInt("id"),
                    rs1.getString("name"),
                    rs1.getString("authorname"),
                    rs1.getString("publisher"),
                    rs1.getString("isbn"),
                    rs1.getDouble("price") + "TL",
                    rs1.getInt("stock"));

        }


        st.close();
        connection.close();
    }


}



