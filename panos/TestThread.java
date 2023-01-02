package panos;

import panos.BlockchainThread;

public class TestThread {
    public static void main(String[] args) {
        System.out.println("test");
//

        BlockchainThread b1 = new BlockchainThread();
        b1.GenesisBlock();
        System.out.println(b1.isChainValid());



//        Create a search method with threads


    }
}
