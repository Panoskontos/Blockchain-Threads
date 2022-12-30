import java.util.Date;
import java.util.List;
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



//        Searching by name or code
        System.out.println("Are any products you want to search?\nSearch product\n 1. by name\n 2. by code\n 3. don't want to search");
        int search = myObj.nextInt();  // Read user input
        if(search==1){
//            search by name
            System.out.println("Enter name");
            String search_name = myObj.next();
            List<Block> result = b1.searchByName(search_name);
            if (result!=null){
                result.stream().forEach(j->{System.out.println(j.getData().toString());});
            }
        }
        if(search==2){
//            search by code
            System.out.println("Enter code");
            String search_code = myObj.next();
            Block result = b1.searchByCode(search_code);
            if (result!=null){
                System.out.println(result.getData().toString());
            }

        }



        // Table
        System.out.println("Do you want to see data about a product?\n1. Yes\n2. No");
        int table_desicion = myObj.nextInt();
        if(table_desicion==1){
            System.out.println("Enter product code");
            String table_product_code = myObj.next();
            b1.CreateTableByCode(table_product_code);
        }

//        Block myblock = new Block(b1.getBlocks().get(b1.getBlocks().size()-1).getHash(),"Data for second",new Date().getTime());
//        b1.addBlock(myblock);



//        test for fake blocks
//        Block fakeblock = new Block("dsfssdfsd","Data for second",new Date().getTime());
//        b1.addBlock(fakeblock);


        System.out.println("\n\ntests");
        System.out.println(b1.getBlocks());
        System.out.println(b1.isChainValid());
    }
}