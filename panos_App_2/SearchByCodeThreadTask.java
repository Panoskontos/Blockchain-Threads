package panos_App_2;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class SearchByCodeThreadTask implements Runnable {

    private List<BlockThread> blocks;
    private ExecutorService executor;
    private String code;
    private BlockThread BlockWereCodewasFound;

    private int count;



    public SearchByCodeThreadTask(ExecutorService executor,List<BlockThread> blocks,String code,int count) {
        this.executor = executor;
        this.blocks = blocks;
        this.code = code;
        this.count = count;


    }
    @Override
    public void run() {
        this.BlockWereCodewasFound = searchByCode(this.code);
        System.out.println("Blocks found:");
        System.out.println(this.BlockWereCodewasFound.getData().toString());
        executor.shutdown();
    }




    public BlockThread searchByCode(String code){

        if(this.count==2){
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
        if(this.count==1) {
            BlockThread nextBlock;
            for (int i =0 ; i < this.blocks.size(); i++) {
                nextBlock = this.blocks.get(i);

                if(nextBlock.getData().getCode().equals(code)){
                    System.out.println(nextBlock);
                    return nextBlock;
                }
            }
            return null;
        }

        return null;
    }


    public BlockThread getBlockWereCodewasFound() {
        return BlockWereCodewasFound;
    }
}


