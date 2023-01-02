import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;

public class MineThreadTask implements Runnable {

    private final int prefix;
    private String hash;
    private String previousHash;
    private ProductData data;
    private long timeStamp;
    private int nonce;
    private ExecutorService executor;


    public MineThreadTask(ExecutorService executor, String previousHash, ProductData data, long timeStamp, int prefix) {
        this.executor = executor;
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = timeStamp;
        this.hash = calculateBlockHash();
        this.prefix = prefix;
    }
    @Override
    public void run() {
        mineBlock(this.prefix);
        executor.shutdown();
    }

    public String mineBlock(int prefix){
        String prefixString =
                new String(new char[prefix]).replace('\0','0');
        while (!hash.substring(0,prefix).equals(prefixString)){
            nonce++;
            hash = calculateBlockHash();
        }
        return hash;
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

    public String getHash() {
        return hash;
    }
}