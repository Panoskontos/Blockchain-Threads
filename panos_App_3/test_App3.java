package panos_App_3;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class test_App3 {
    public static void test(){

//      Initialize Blockchain
        BlockchainThread b1 = new BlockchainThread();
        b1.GenesisBlock();
        System.out.println(b1.isChainValid());




//        Handle inputs
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("\nEnter how many products you want to add?");
        int count = 1;  // Read user input
        System.out.println("Adding " + count+" products");  // Output user input
        int i = 0;
        while(i<count){
            System.out.println("\nPLease add details for the "+(i+1)+" product");
            String code = "001";
            int price = 500;
            String title = "protein milk";
            String cat = "diery";
            String desc = "product that helps with protein";


            ProductData p1 = new ProductData(1,code,title,new Date().getTime(),price,desc,cat);
            BlockThread nextBlock = new BlockThread(b1.getBlocks().get(b1.getBlocks().size()-1).getHash(), p1, new Date().getTime());
            b1.addBlock(nextBlock);


            i++;

        }



//        Handle Search
        System.out.println("\nAre any products you want to search?\nSearch product\n 1. by name\n 2. by code\n 3. don't want to search");
        int search = 1;  // Read user input
        if(search==1){
//            search by name
            System.out.println("Enter name");
            String search_name = "protein";
            List<BlockThread> result = b1.searchByName(search_name);
        }
        if(search==2){
//            search by code
            System.out.println("Enter code");
            String search_code = myObj.next();
            BlockThread result = b1.searchByCode(search_code);

        }




        // Table
        System.out.println("\nDo you want to see data about a product?\n1. Yes\n2. No");
        int table_desicion = 1;
        if(table_desicion==1){
            System.out.println("Enter product code");
            String table_product_code = "001";
            b1.CreateTableByCode(table_product_code);
        }



//        Is the blockchain valid
//        System.out.println("\nPrinting all blocks");
//        System.out.println(b1.getBlocks());
        System.out.println("\n");
        System.out.println("Is blockchain valid?");
        System.out.println(b1.isChainValid());

    }
}
