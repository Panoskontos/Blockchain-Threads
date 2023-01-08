package panos_App_3;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

public class SearchByNameCustomThread extends Thread{
    private static volatile boolean finished = false;
    private final ReentrantLock lock = new ReentrantLock();

    private int id;
    private List<BlockThread> blocks;
    private ExecutorService executor;
    private String name;

    private List<BlockThread> BlockWereNamewasFound;

    private int count;

    public SearchByNameCustomThread(int id, List<BlockThread> blocks, String name) {
        this.id = id;
        this.blocks = blocks;
        this.name = name;

    }

    @Override
    public void run() {

        lock.lock();
        try {


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
            finished = true;
            interruptAll();
        }
        finished = true;
        interruptAll();
        System.out.println("\n---------\n");
        myblocks.stream().forEach(j->{System.out.println(j.getData().toString());});
        System.out.println("\n---------\n");

        } finally {
            lock.unlock();
        }

    }

    private void interruptAll() {
        // Interrupt all other threads
        for (int i = 1; i <= 4; i++) {
            if (i != id) {
                Thread.currentThread().getThreadGroup().interrupt();
            }
        }
    }
}