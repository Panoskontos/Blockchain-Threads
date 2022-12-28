
public class Main
{
    public static void main(String[] args) {

        DataBase db = new DataBase();
        db.createNewTable("block");
//        db.insert("block","Panos", 20000);
        db.selectAll("block");

        Blockchain b1 = new Blockchain();
        b1.GenesisBlock();

    }
}