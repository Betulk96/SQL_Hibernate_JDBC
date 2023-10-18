package MiniBookStore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//2-a:Kitaplarla yapılabilecek işlemler
public class BookService extends DatabaseHandler  {

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
            System.out.println("0-Geri Dön");
            System.out.println("Seçiminiz: ");
            choice = inp.nextInt();
            inp.nextLine();//yeni satıra geçmesi için

            switch (choice) {
                case 1 -> listProducts();
                case 2 -> addProduct();
                case 3 -> deleteProduct();
                case 4 -> filterProducts();
                case 0 -> System.out.println("Ana Menüye Yönlendiriliyorunuz");
                default -> System.out.println("Hatali giriş");
            }

        } while (choice != 0);

    }

    //6kitapları formatla listeleme

  //verride
  //blic void stockUpdate(String code) {
  //  System.out.println("Eklemek istediğiniz ürün miktarını giriniz.");
  //  int a = inp.nextInt();
  //  inp.nextLine();
  //  if (a > 0) {


  //      for (Book nb : this.bookList) {
  //          int b = nb.getStock();
  //          nb.setStock(a + b);
  //          break;


  //      }

  //  }



}
