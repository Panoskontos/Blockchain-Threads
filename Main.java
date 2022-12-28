import java.util.Date;

public class Main
{
    public static void main(String[] args) {

        DataBase db = new DataBase();
        db.createNewTable("block");
//        db.insert("block","Panos", 20000);
        db.selectAll("block");

        Blockchain b1 = new Blockchain();
        b1.GenesisBlock();
        Block myblock = new Block(b1.getBlocks().get(b1.getBlocks().size()-1).getHash(),"Data for second",new Date().getTime());
        b1.addBlock(myblock);

//        Block fakeblock = new Block("dsfssdfsd","Data for second",new Date().getTime());
//        b1.addBlock(fakeblock);

        System.out.println(b1.getBlocks());
        System.out.println(b1.isChainValid());
    }
}