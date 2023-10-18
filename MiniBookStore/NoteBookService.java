package MiniBookStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//2-b:defterlerle yapılabilecek işlemler
public class NoteBookService implements ProductService {
    public Scanner inp = new Scanner(System.in);
    //defterleri saklamak için list oluşturma
    public List<NoteBook> notebookList = new ArrayList<>();

    public NoteBookService() {
//        NoteBook notebook1 = new NoteBook("Çizgili Defter", "150", 15, "ALA", "50", "A111");
//        NoteBook notebook2 = new NoteBook("Kareli Defter", "250", 25, "AZY", "80", "A222");
//        NoteBook notebook3 = new NoteBook("Resim Defteri", "170", 15, "ASD", "100", "A333");
//
//        this.notebookList.add(notebook1);
//        this.notebookList.add(notebook2);
//        this.notebookList.add(notebook3);

    }

    @Override
    public void processMenu() {
        int choice;
        do {
            System.out.println("--------------------------------------------");
            System.out.println("1-Defterleri listele");
            System.out.println("2-Defter ekle");
            System.out.println("3-Defter sil");
            System.out.println("4-Markaya göre filtrele");
            System.out.println("0-Geri Dön");
            System.out.println("Seçiminiz: ");
            choice = inp.nextInt();
            inp.nextLine();//yeni satıra geçmesi için

            switch (choice) {
                case 1 -> listProducts();
                case 2 -> addProduct();
                case 3 -> deleteProduct();
                case 4 -> {
                    System.out.println("Yayınevi : ");
                    String publisher = inp.nextLine();

                }
                case 0 -> System.out.println("Ana Menüye Yönlendiriliyorunuz");
                default -> System.out.println("Hatali giriş");
            }

        } while (choice != 0);

    }

    @Override
    public void listProducts() {

    }

    @Override
    public void addProduct() {
        System.out.println("Ürün Kodu : ");
        String code = inp.nextLine();

        boolean isExists = false;
        for (NoteBook nB : this.notebookList) {//ürün kodu var mı diye kontrol ediyoruz
            if (nB.getCode().equals(code)) {
                System.out.println("Bu kitap sistemde kayıtlı,lütfen ürün güncelleme yapınız.");//stok güncelleme :Ödev
                stockUpdate(code);

                isExists = true;
                break;

            }

        }
        if (!isExists) {
            System.out.println("Defter Adı :");
            String name = inp.nextLine();
            System.out.println("Marka :");
            String brand = inp.nextLine();
            System.out.println("Yaprak Sayısı :");
            String sheet = inp.nextLine();
            System.out.println("Birim Fiyat :");
            String price = inp.nextLine();
            System.out.println("Stok : ");
            int stock = inp.nextInt();
            inp.nextLine();

         //   NoteBook newnoteBook = new NoteBook(name, price, stock, brand, sheet, code);
         //   this.notebookList.add(newnoteBook);
        }
        listProducts();
    }

    @Override
    public void deleteProduct() {



    }

    @Override
    public void filterProducts() {

    }


    @Override
    public void stockUpdate(String code) {
        System.out.println("Eklemek istediğiniz ürün miktarını giriniz.");
        int a = inp.nextInt();
        inp.nextLine();
        if (a > 0) {


            for (NoteBook nb : this.notebookList) {
                int b = nb.getStock();
                nb.setStock(a + b);
                break;


            }

        }

    }
}
