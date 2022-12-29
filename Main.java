import java.util.Date;
import java.util.Scanner;  // Import the Scanner class

public class Main
{
    public static void main(String[] args) {

//        create the blockchain
        Blockchain b1 = new Blockchain();
        b1.GenesisBlock();



//        Handle inputs
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter how many products you want to add?");
        int count = myObj.nextInt();  // Read user input
        System.out.println("Adding " + count+" products");  // Output user input
        int i = 0;
        while(i<count){
            System.out.println("PLease add details for the "+(i+1)+" product");
            System.out.println("\nEnter product code:");
            String code = myObj.next();
            System.out.println("\nEnter product price:");
            int price = myObj.nextInt();
            System.out.println("\nEnter product title:");
            String title = myObj.next();
            System.out.println("\nEnter product category:");
            String cat = myObj.next();
            System.out.println("\nEnter product description:");
            String desc = myObj.next();


            ProductData p1 = new ProductData(1,code,title,new Date().getTime(),price,desc,cat);
            Block nextBlock = new Block(b1.getBlocks().get(b1.getBlocks().size()-1).getHash(), p1, new Date().getTime());
            b1.addBlock(nextBlock);


            i++;

        }


        System.out.println("Are any products you want to search?\nSearch product\n 1. by name\n 2. by code\n 3. don't want to search");
        int search = myObj.nextInt();  // Read user input
        if(search==1){
//            search by name
        }
        if(search==2){
//            search by code
        }


//        Block myblock = new Block(b1.getBlocks().get(b1.getBlocks().size()-1).getHash(),"Data for second",new Date().getTime());
//        b1.addBlock(myblock);



//        test for fake blocks
//        Block fakeblock = new Block("dsfssdfsd","Data for second",new Date().getTime());
//        b1.addBlock(fakeblock);



        System.out.println(b1.getBlocks());
        System.out.println(b1.isChainValid());
    }
}