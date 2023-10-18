package MiniBookStore;
//1-b-sadece kitaplara ait olan özellikleri:Product field+,yazar adı,yayınev,isbn no
public class Book extends Product{//product dan field ları alıcaz
    private String authorName;
    private String publisher;
    private String isbn;

    //defaul olmazsa =CTE==> const. oluşturdu.superle çağırdı

    //-parametreli const.
    public Book(String name, Double price, int stock, String authorName, String publisher, String isbn) {
        super(name, price, stock);
        this.authorName=authorName;
        this.publisher=publisher;
        this.isbn=isbn;


    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


}
