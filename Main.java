import java.util.Date;

public class Main
{
    public static void main(String[] args) {


//        connect and interact with sqlitte
        DataBase db = new DataBase();
        db.createNewTable("block");
//        db.insert("block","Panos", 20000);
        db.selectAll("block");



//        create the blockchain
        Blockchain b1 = new Blockchain();
        b1.GenesisBlock();

//        Block myblock = new Block(b1.getBlocks().get(b1.getBlocks().size()-1).getHash(),"Data for second",new Date().getTime());
//        b1.addBlock(myblock);
        ProductData p1 = new ProductData(1,"1222","pizza",new Date().getTime(),30,"the best food","fast food");
        Block nextBlock = new Block(b1.getBlocks().get(b1.getBlocks().size()-1).getHash(), p1, new Date().getTime());
        b1.addBlock(nextBlock);


//        test for fake blocks
//        Block fakeblock = new Block("dsfssdfsd","Data for second",new Date().getTime());
//        b1.addBlock(fakeblock);


        System.out.println(b1.getBlocks().get(0).getData().getAA());
        System.out.println(b1.getBlocks().get(1).getData().getAA());
        System.out.println(b1.getBlocks().get(0).getData().getPrice());
        System.out.println(b1.getBlocks().get(1).getData().getPrice());

        System.out.println(b1.getBlocks());
        System.out.println(b1.isChainValid());
    }
}