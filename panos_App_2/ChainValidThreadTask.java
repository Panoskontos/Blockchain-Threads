package panos_App_2;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class ChainValidThreadTask implements Runnable {

    private List<BlockThread> blocks;
    private int prefix;
    private ExecutorService executor;
    private boolean answer;


    public ChainValidThreadTask(ExecutorService executor, List<BlockThread> blocks, int prefix) {
        this.executor = executor;
        this.blocks = blocks;
        this.prefix = prefix;
        this.answer = true;


    }
    @Override
    public void run() {
        BlockThread currentBlock;
        BlockThread previousBlock;

        String hashTarget = new String(new char[this.prefix]).replace('\0','0');

        for (int i = 1; i < this.blocks.size(); i++) {
            currentBlock = this.blocks.get(i);
            previousBlock = this.blocks.get(i - 1);

            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
                System.out.println("false for hashes");
                this.answer = false;
            }
            if (!currentBlock.getHash().substring(0,this.prefix).equals(hashTarget)) {
                System.out.println("false for substring");
                this.answer = false;
            }
        }
        executor.shutdown();
    }

//    public boolean isChainValid() {
//        BlockThread currentBlock;
//        BlockThread previousBlock;
//        String hashTarget = new String(new char[this.prefix]).replace('\0','0');
//        for (int i = 1; i < this.blocks.size(); i++) {
//            currentBlock = this.blocks.get(i);
//            previousBlock = this.blocks.get(i - 1);
//
//            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
//                System.out.println("false for hashes");
//                return false;
//            }
//            if (!currentBlock.getHash().substring(0,this.prefix).equals(hashTarget))
//                System.out.println("false for substring");
//                return false;
//        }
//        return true;
//    }

    public boolean isAnswer() {
        return answer;
    }
}