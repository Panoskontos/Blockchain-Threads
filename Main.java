
public class Main
{
    public static void main(String[] args) {

        DataBase db = new DataBase();
        db.createNewTable("malakes");
        db.insert("malakes","Panos", 20000);
        db.selectAll("malakes");


    }
}