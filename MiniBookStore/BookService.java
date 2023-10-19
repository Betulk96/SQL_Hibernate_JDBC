package MiniBookStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//2-a:Kitaplarla yapılabilecek işlemler
public class BookService extends DatabaseHandler implements ProductService {

    public BookService() throws SQLException {
    }

    @Override
    public void processMenu() {
        int choice;
        do {
            System.out.println("--------------------------------------------");
            System.out.println("1-Kitapları listele");
            System.out.println("2-Kitap ekle");
            System.out.println("3-Kitabı sil");
            System.out.println("4-Filtrele");
            System.out.println("5-Stok Güncelleme");
            System.out.println("0-Geri Dön");
            System.out.println("Seçiminiz: ");
            choice = inp.nextInt();
            inp.nextLine();//yeni satıra geçmesi için

            switch (choice) {
                case 1 -> listProducts();
                case 2 -> addProduct();
                case 3 -> deleteProduct();
                case 4 -> filterProducts();
                case 5 -> stockUpdate();
                case 0 -> {
                    System.out.println("Ana Menüye Yönlendiriliyorunuz");
                    closeConnection();
                }

                default -> System.out.println("Hatali giriş");
            }

        } while (choice != 0);

    }

    @Override
    public void listProducts() {
        // LİSTEYİ GÖSTERME
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM books");
            System.out.printf("%-3s | %-20s | %-15s | %-10s | %-4s | %-10s | %-3s | \n",
                    "ID",
                    "Kitap Adı",
                    "Yazar Adı",
                    "Yayınevi",
                    "ISBN",
                    "Birim Fiyat",
                    "Stok");
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void filterProducts() {
        boolean select = true;
        while (select) {
            System.out.println("""
                    Filtreleme:
                    1-Yayıncıya Göre
                    2-Kitap İsmine Göre
                    3-Kitap ID'sine Göre
                    4-Yazar İsmine Göre
                    5-ISBN'ye Göre
                    6-Fiyatı ARTARAK
                    7-Fiyatı AZALARAK
                    0-ÇIKIŞ
                    Seçiminiz :""");
            int a = inp.nextInt();
            inp.nextLine();
            switch (a) {
                case 1 -> searchBooksByPublisher();
                case 2 -> searchBooksByBookName();
                case 3 -> searchBooksById();
                case 4 -> searchBooksByPAuthorName();
                case 5 -> searchBooksByISBN();
                case 6 -> searchBooksByIncreasingInPrice();
                case 7 -> searchBooksByDecreaseInPrice();
                case 0 -> {
                    select = false;
                    System.out.println("Filtreleme işleminden Çıkış");
                }
                default -> System.out.println("Geçersiz Giriş");
            }

        }

    }

    @Override
    public void addProduct() {
        Scanner inp = new Scanner(System.in);
        String sql2 = "INSERT INTO books VALUES(?,?,?,?,?,?) ";
        try (PreparedStatement pr = connection.prepareStatement(sql2);) {

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
            int inserted1 = pr.executeUpdate();
            if (inserted1 > 0) System.out.println("Eklem işleminiz Başarılı");
            else System.out.println("Ekleme işlemi başarısız.");
            listProducts();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct() {
        Scanner inp = new Scanner(System.in);
        String sql3 = "DELETE FROM books WHERE id=? ";
        try (PreparedStatement deleted = connection.prepareStatement(sql3)) {
            listProducts();
            System.out.println("Silmek İstediğiniz Kitap id : ");
            int d1 = inp.nextInt();
            deleted.setInt(1, d1);
            int deleted1 = deleted.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stockUpdate() {

        String sql3 = "UPDATE books SET stock=stock+ ? WHERE id=?";
        try(PreparedStatement pr=connection.prepareStatement(sql3)) {
            listProducts();
            System.out.println("Stok Bilgisini Güncellemek İstediğiniz Ürünün ID No: ");
            int idno=inp.nextInt();
            System.out.println("Miktar Giriniz.Stok azaltması için başına(-) ekleyiniz" );
            int stok=inp.nextInt();
            inp.nextLine();
            pr.setInt(1,stok);
            pr.setInt(2,idno);
            int updated= pr.executeUpdate();

            if (updated>0){
                listProducts();
            }else System.out.println("ürün bulunamadıı");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Filtreleme methodları
    public void searchBooksByPublisher() {
        System.out.println("Yayıncı ismi Giriniz:");
        String publisherName = inp.nextLine();

        String sql = "SELECT * FROM books WHERE publisher ILIKE ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + publisherName + "%");
            ResultSet rs1 = preparedStatement.executeQuery();
            printNextWithDo(rs1);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchBooksById() {
        System.out.println("ID.NO Giriniz:");
        int idNo = inp.nextInt();
        inp.next();
        String sql1 = "SELECT * FROM books WHERE id= ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {
            preparedStatement.setInt(1, idNo);
            ResultSet rs1 = preparedStatement.executeQuery();
            printNextWithDo(rs1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchBooksByBookName() {
        System.out.println("Kitap ismi Giriniz:");
        String bookName = inp.nextLine();
        String sql = "SELECT * FROM books WHERE name ILIKE ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + bookName + "%");
            ResultSet rs1 = ps.executeQuery();
            printNextWithDo(rs1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchBooksByPAuthorName() {
        System.out.println("Yazar ismi Giriniz:");
        String authorName = inp.nextLine();
        String sql = "SELECT * FROM books WHERE authorname ILIKE ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + authorName + "%");
            ResultSet rs1 = preparedStatement.executeQuery();
            printNextWithDo(rs1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchBooksByISBN() {
        System.out.println("ISBN Giriniz:");
        String isbnNo = inp.nextLine();
        String sql = "SELECT * FROM books WHERE isbn ILIKE ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + isbnNo + "%");
            ResultSet rs1 = preparedStatement.executeQuery();
            printNextWithDo(rs1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchBooksByIncreasingInPrice() {
        String sql = "SELECT * FROM books ORDER BY price";

        try (Statement st = connection.createStatement()) {
            ResultSet rs1 = st.executeQuery(sql);
            printNextWithWhile(rs1);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchBooksByDecreaseInPrice() {
        String sql = "SELECT * FROM books ORDER BY price DESC";

        try (Statement st = connection.createStatement()) {
            ResultSet rs1 = st.executeQuery(sql);
            printNextWithWhile(rs1);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void printNextWithWhile(ResultSet rs1) throws SQLException {
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

    }

    public void printNextWithDo(ResultSet rs1) throws SQLException {
        if (rs1.next()) {//Toblo boş değilse
            System.out.printf("%-3s | %-20s | %-15s | %-10s | %-4s | %-10s | %-3s | \n",
                    "ID", "Kitap Adı", "Yazar Adı", "Yayınevi", "ISBN", "Birim Fiyat", "Stok");
            System.out.println("-----------------------------------------------------------------------------------");
            do {
                System.out.printf("%-3s | %-20s | %-15s | %-10s | %-4s | %-10s | %-3s | \n",
                        rs1.getInt("id"),
                        rs1.getString("name"),
                        rs1.getString("authorname"),
                        rs1.getString("publisher"),
                        rs1.getString("isbn"),
                        rs1.getDouble("price") + "TL",
                        rs1.getInt("stock"));
            } while (rs1.next());
        } else System.out.println("Sonuç Bulunamadı!");

    }


}
