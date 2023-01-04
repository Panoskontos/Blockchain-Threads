package panos_App_2;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class SearchByNameThreadTask implements Runnable {
    private List<BlockThread> blocks;
    private ExecutorService executor;
    private String name;

    private List<BlockThread> BlockWereNamewasFound;


    public SearchByNameThreadTask(ExecutorService executor,List<BlockThread> blocks,String name) {
        this.executor = executor;
        this.blocks = blocks;
        this.name = name;


    }
    @Override
    public void run() {
        this.BlockWereNamewasFound = searchByName(this.name);
        System.out.println("\nProducts found were\n");
        this.BlockWereNamewasFound.stream().forEach(j->{System.out.println(j.getData().toString());});;
        executor.shutdown();
    }

    public List<BlockThread> searchByName(String name){
        List<BlockThread> myblocks = new LinkedList<>();
        BlockThread previousBlock;
        for (int i = this.blocks.size()-1; i >= 0; i--) {
            previousBlock = this.blocks.get(i);
            if(previousBlock.getData().getTitle().contains(name)){
                System.out.println(previousBlock);
                myblocks.add(previousBlock);
            }
        }
        if (myblocks.isEmpty()){
            return null;
        }
        return myblocks;
    }

    public List<BlockThread> getBlockWereNamewasFound() {
        return BlockWereNamewasFound;
    }
}