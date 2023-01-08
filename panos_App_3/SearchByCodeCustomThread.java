package panos_App_3;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;


public class SearchByCodeCustomThread extends Thread{
    private static volatile boolean finished = false;
    private final ReentrantLock lock = new ReentrantLock();
    private int id;
    private List<BlockThread> blocks;

    private String code;
    private BlockThread BlockWereCodewasFound;

    private int count;

    public SearchByCodeCustomThread(int id, List<BlockThread> blocks, String code, int count) {
        this.id = id;
        this.blocks = blocks;
        this.code = code;
        this.count = count;


    }

    @Override
    public void run() {
        lock.lock();
        try {
            if(this.count==2){
                BlockThread previousBlock;
                for (int i = this.blocks.size()-1; i >= 0; i--) {
                    previousBlock = this.blocks.get(i);

                    if(previousBlock.getData().getCode().equals(code)){
                        System.out.println(previousBlock);
                        finished = true;
                        interruptAll();
                        System.out.println(previousBlock.getData().toString());
                    }
                }
                finished = true;
                interruptAll();


            }
            if(this.count==1) {
                BlockThread nextBlock;
                for (int i =0 ; i < this.blocks.size(); i++) {
                    nextBlock = this.blocks.get(i);

                    if(nextBlock.getData().getCode().equals(code)){
                        System.out.println(nextBlock);
                        finished = true;
                        interruptAll();
                        System.out.println(nextBlock.getData().toString());

                    }
                }
                finished = true;
                interruptAll();



            }

            finished = true;
            interruptAll();
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