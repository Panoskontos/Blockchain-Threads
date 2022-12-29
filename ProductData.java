import java.util.Date;

public class ProductData {
    private int AA;
    private String code;
    private String title;
    private long timeStamp;
    private int price;
    private String description;
    private String category;
    private int previousAA;

    public ProductData(int AA,
                            String code,
                            String title,
                            long timeStamp,
                            int price,
                            String description,
                            String category
                            ){
        this.AA = AA;
        this.code = code;
        this.title = title;
        this.timeStamp = timeStamp;
        this.price = price;
        this.description = description;
        this.category = category;

        if(this.AA>0){
            this.previousAA = this.AA-1;
        } else {
            this.previousAA = 0;
        }

    }

    public int getAA() {
        return AA;
    }

    public String getCode() {
        return code;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public int getPreviousAA() {
        return previousAA;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void incrementAA(){
        this.AA++;
    }

}

