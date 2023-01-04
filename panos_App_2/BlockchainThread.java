package panos_App_2;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BlockchainThread {
    private List<BlockThread> blocks;
    private int prefix;

    private DataBase db;

    public BlockchainThread() {
        this.blocks = new LinkedList<>();
        this.prefix = 3;
//       connect and interact with sqlitte
        this.db = new DataBase();
        db.createNewTable("block");
        this.blocks = db.readDataBase("block");
        this.blocks.stream().forEach(j->{
            System.out.println(j.getData().toString());
        });
//        db.selectAll("block");
//        db.countAll("block");
    }

    public void GenesisBlock() {
        DataBase db = this.db;
//        int rows = db.countAll("block");
        if (this.blocks.isEmpty()) {
            ProductData p1 = new ProductData(1,"0","genesis",new Date().getTime(),20,"none","none");
            BlockThread genesisBlock = new BlockThread("0", p1, new Date().getTime());
            if (genesisBlock != null) {
                genesisBlock.mineBlock(this.prefix);
                blocks.add(genesisBlock);
//                db.insert("block",
//                        "0",
//                        1,p1.getCode(),
//                        p1.getTitle(),
//                        p1.getTimeStamp(),
//                        p1.getPrice(),
//                        p1.getDescription(),
//                        p1.getCategory(),
//                        p1.getPreviousAA(),
//                        genesisBlock.getHash()
//                );
            }
        }
//        don't add to the db
//        if (this.blocks.isEmpty() && rows>0) {
//            panos_App_1.ProductData p1 = new panos_App_1.ProductData(1,"0","genesis",new Date().getTime(),20,"none","none");
//            panos_App_1.Block genesisBlock = new panos_App_1.Block("0", p1, new Date().getTime());
//            if (genesisBlock != null) {
//                genesisBlock.mineBlock(this.prefix);
//                blocks.add(genesisBlock);
//            }
//        }
        System.out.println("Node "+this.blocks.size()+" created!");
    }


    public BlockThread searchIfProductExists(String code){
        BlockThread previousBlock;
        for (int i = this.blocks.size()-1; i >= 0; i--) {
            previousBlock = this.blocks.get(i);

            if(previousBlock.getData().getCode().equals(code)){
                System.out.println(previousBlock);
                return previousBlock;
            }
        }
        return null;
    }

    public BlockThread searchByCode(String code){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Do you want to search for\n 1. Oldest panos_App_1.Block\n 2. Newest panos_App_1.Block ");
        int count = myObj.nextInt();  // Read user


        ExecutorService executor = Executors.newFixedThreadPool(30);
        SearchByCodeThreadTask m1 = new SearchByCodeThreadTask(executor,this.blocks,code,count);
        executor.execute(m1);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("");;
        }
        return m1.getBlockWereCodewasFound();
    }


    public List<BlockThread> searchByName(String name){

        ExecutorService executor = Executors.newFixedThreadPool(30);
        SearchByNameThreadTask m1 = new SearchByNameThreadTask(executor,this.blocks,name);
        executor.execute(m1);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("");;
        }
        return m1.getBlockWereNamewasFound();

    }



    // method for formatting printed output
    public static String formatAsTable(List<List<String>> rows)
    {
        int[] maxLengths = new int[rows.get(0).size()];
        for (List<String> row : rows)
        {
            for (int i = 0; i < row.size(); i++)
            {
                maxLengths[i] = Math.max(maxLengths[i], row.get(i).length());
            }
        }

        StringBuilder formatBuilder = new StringBuilder();
        for (int maxLength : maxLengths)
        {
            formatBuilder.append("%-").append(maxLength + 2).append("s");
        }
        String format = formatBuilder.toString();

        StringBuilder result = new StringBuilder();
        for (List<String> row : rows)
        {
            result.append(String.format(format, row.toArray(new String[0]))).append("\n");
        }
        return result.toString();
    }


    public void CreateTableByCode(String code){
        List<BlockThread> myblocks = new LinkedList<>();
        BlockThread nextBlock;
        for (int i = 0; i < this.blocks.size(); i++) {
            nextBlock = this.blocks.get(i);
            if(nextBlock.getData().getCode().equals(code)){
//                System.out.println(nextBlock);
                myblocks.add(nextBlock);
            }
        }
        System.out.println("\n");
        List<List<String>> rows = new ArrayList<>();
        List<String> headers = Arrays.asList("Product","Date","Price");
        rows.add(headers);
        int n = 0;
        while (n<myblocks.size()){
            String price = Integer.toString(myblocks.get(n).getData().getPrice())+"$";
//            String timestamp = Long.toString(myblocks.get(n).getData().getTimeStamp());
            Date timestamp = new Date(myblocks.get(n).getData().getTimeStamp());
            String string_timestamp = ""+timestamp+"";
            rows.add(Arrays.asList(
                    myblocks.get(n).getData().getTitle()
                    ,string_timestamp, price));
            n++;
        }
        System.out.println(formatAsTable(rows));
    }

    public void addBlock(BlockThread block) {
        // Get Last panos_App_1.Block of the product
        BlockThread searchProduct = searchIfProductExists(block.getData().getCode());
        DataBase db = this.db;

        if(searchProduct==null) {
            System.out.println("Product doesn't exist");
            if (block != null) {
                block.mineBlock(this.prefix);
                blocks.add(block);
                db.insert("block",
                        block.getPreviousHash(),
                        block.getData().getAA(),
                        block.getData().getCode(),
                        block.getData().getTitle(),
                        block.getData().getTimeStamp(),
                        block.getData().getPrice(),
                        block.getData().getDescription(),
                        block.getData().getCategory(),
                        block.getData().getPreviousAA(),
                        block.getHash()
                );
                System.out.println("Node "+this.blocks.size()+" created!");
            }


        } else {
            System.out.println("Product exists");
            ProductData p2 = new ProductData(
                    searchProduct.getData().getAA()+1,
                    searchProduct.getData().getCode(),
                    searchProduct.getData().getTitle(),
                    new Date().getTime(),
                    block.getData().getPrice(),
                    searchProduct.getData().getDescription(),
                    searchProduct.getData().getCategory()
            );
            BlockThread newBlock = new BlockThread(block.getPreviousHash(), p2, new Date().getTime());
            newBlock.mineBlock(this.prefix);
            blocks.add(newBlock);
            db.insert("block",
                    newBlock.getPreviousHash(),
                    newBlock.getData().getAA(),
                    newBlock.getData().getCode(),
                    newBlock.getData().getTitle(),
                    newBlock.getData().getTimeStamp(),
                    newBlock.getData().getPrice(),
                    newBlock.getData().getDescription(),
                    newBlock.getData().getCategory(),
                    newBlock.getData().getPreviousAA(),
                    newBlock.getHash()
            );
            System.out.println("Node "+this.blocks.size()+" created!");

        }




    }

    public boolean isChainValid() {
        ExecutorService executor = Executors.newFixedThreadPool(30);
        ChainValidThreadTask m1 = new ChainValidThreadTask(executor,this.blocks,this.prefix);
        executor.execute(m1);
        return m1.isAnswer();
    }


    public List<BlockThread> getBlocks() {
        return blocks;
    }

    public int getPrefix() {
        return prefix;
    }
}
