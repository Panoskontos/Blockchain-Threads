import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThread {
    public static void main(String[] args) {
        System.out.println("test");
//

        BlockchainThread b1 = new BlockchainThread();
        b1.GenesisBlock();
        b1.isChainValid();
    }
}
