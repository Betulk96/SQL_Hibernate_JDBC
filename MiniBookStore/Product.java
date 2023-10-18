package MiniBookStore;

//1-a Ürünlerin ortak özellikleri: İd,isim,birim fiyat,stok
public class Product {

    private String name;

    private Double price;

    private int stock;

    //parametre const.


    public Product(String name, Double price, int stock) {

        this.name = name;
        this.price = price;
        this.stock = stock;

    }
//parent child için gerekli ,
 //   public Product() {
 //   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }




}
