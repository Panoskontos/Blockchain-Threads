import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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


    }
    @Override
    public void run() {
        this.answer = isChainValid();
        executor.shutdown();
    }

    public boolean isChainValid() {
        BlockThread currentBlock;
        BlockThread previousBlock;
        String hashTarget = new String(new char[this.prefix]).replace('\0','0');
        for (int i = 1; i < this.blocks.size(); i++) {
            currentBlock = this.blocks.get(i);
            previousBlock = this.blocks.get(i - 1);

            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
                return false;
            }
            if (!currentBlock.getHash().substring(0,this.prefix).equals(hashTarget))
                return false;
        }
        return true;
    }

    public boolean isAnswer() {
        return answer;
    }
}