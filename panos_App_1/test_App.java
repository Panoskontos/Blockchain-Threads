package panos_App_1;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class test_App {

    public static void test(){
//        create the blockchain
        Blockchain b1 = new Blockchain();
        b1.GenesisBlock();


//        Handle inputs
//        DUMMY DATA for TEST
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("\nEnter how many products you want to add?");
        int count = 1;  // Read user input
        System.out.println("Adding " + count+" products");  // Output user input
        int i = 0;
        while(i<count){
            String code = "001";
            int price = 500;
            String title = "protein milk";
            String cat = "diery";
            String desc = "product that helps with protein";
            ProductData p1 = new ProductData(1,code,title,new Date().getTime(),price,desc,cat);
            Block nextBlock = new Block(b1.getBlocks().get(b1.getBlocks().size()-1).getHash(), p1, new Date().getTime());
            b1.addBlock(nextBlock);
            i++;

        }



//        Searching by name or code
        System.out.println("\nAre any products you want to search?\nSearch product\n 1. by name\n 2. by code\n 3. don't want to search");
        int search = 1;  // Read user input
        if(search==1){
//            search by name
            String search_name = "protein";
            List<Block> result = b1.searchByName(search_name);
            if (result!=null){
                result.stream().forEach(j->{System.out.println(j.getData().toString());});
            }
        }
        if(search==2){
//            search by code
            String search_code = myObj.next();
            Block result = b1.searchByCode(search_code);
            if (result!=null){
                System.out.println(result.getData().toString());
            }

        }





        // Table
        System.out.println("\nDo you want to see data about a product?\n1. Yes\n2. No");
        int table_desicion = 1;
        if(table_desicion==1){
            String table_product_code = "001";
            b1.CreateTableByCode(table_product_code);
        }




        System.out.println("Is blockchain valid?");
        System.out.println(b1.isChainValid());

    };


}
