
public class Main
{
    public static void main(String[] args) {

        DataBase db = new DataBase();
        db.createNewTable("block");
        db.insert("block","Panos", 20000);
        db.selectAll("block");


    }
}