package panos_App_2;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockThread {
    private String hash;
    private String previousHash;
    private ProductData data;
    private long timeStamp;
    private int nonce;




    public BlockThread(String previousHash, ProductData data, long timeStamp) {
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = timeStamp;
        this.hash = calculateBlockHash();

    }

//    mining using threadpools
    public String mineBlock(int prefix){

        ExecutorService executor = Executors.newFixedThreadPool(4);
        MineThreadTask m1 = new MineThreadTask(executor,this.previousHash,this.data, this.timeStamp,prefix);
        executor.execute(m1);
        while (m1.getResult()==null){
//            so it won't give null
            System.out.println(".");
        }
        hash = m1.getResult();
        return hash;
//        String prefixString =
//                new String(new char[prefix]).replace('\0','0');
//        while (!hash.substring(0,prefix).equals(prefixString)){
//            nonce++;
//            hash = calculateBlockHash();
//        }
//
//        return hash;
    }

    public String calculateBlockHash(){
        String dataToHash = previousHash + Long.toString(timeStamp)
                +data+Integer.toString(nonce);
        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes){
            builder.append(String.format("%02x",b));
        }
        return builder.toString();
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getHash() {
        return hash;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public int getNonce() {
        return nonce;
    }

    public ProductData getData() {
        return data;
    }

    public void setHash(String newhash){
        this.hash = newhash;
    }
}


//        testing on differenet threads
//        int[] threadCounts = {1, 2, 4, 8, 16,20,30,40,50};
//        for (int threadCount : threadCounts) {
//            ExecutorService executor = Executors.newFixedThreadPool(threadCount);
//            long startTime = System.nanoTime();
//            executor.execute(new panos.MineThreadTask(executor,this.previousHash,this.data, this.timeStamp,prefix));
//            long endTime = System.nanoTime();
//            long elapsedTime = endTime - startTime;
//            System.out.println("Thread count: " + threadCount + ", elapsed time: " + elapsedTime + " ns");
//        }

