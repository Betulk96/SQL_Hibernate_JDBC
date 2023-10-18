package MiniBookStore;

import java.sql.SQLException;
import java.util.Scanner;
public class MiniBookStore {
    public static void main(String[] args) throws SQLException {

        start();


    }

    //1-product,book,notebook classlarını oluşturalım
    //2-book,notebook için service classı oluşturma
    private static void start() throws SQLException {
        Scanner inp = new Scanner(System.in);
        int select;
        //service oluşturalım.
        ProductService bookService = new BookService();
        ProductService notebookService = new NoteBookService();

        //10-kategori menüsü
        do {
            System.out.println("--- Mini Book Store ---");
            System.out.println("Ürün Yönetim Paneli");
            System.out.println("1-Kitaplar");
            System.out.println("2-Defterler");
            System.out.println("0-ÇIKIŞ");
            System.out.println("Seçiminiz : ");
            select = inp.nextInt();
            inp.nextLine();

            ProductService service = select == 1 ? bookService : notebookService;

            switch (select) {
                case 1, 2 -> service.processMenu();

                //case 2:
                // bookService.processMenu();
                case 0 -> System.out.println("İyi günler...");
                default -> System.out.println("Hatalı giriş!");
            }


        } while (select != 0);


    }
}