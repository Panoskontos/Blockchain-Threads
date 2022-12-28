import java.util.Date;
import java.util.List;
import java.util.LinkedList;

public class Blockchain {
    private List<Block> blocks;
    private int prefix;

    public Blockchain() {
        this.blocks = new LinkedList<>();
        this.prefix = 3;
    }

    public void GenesisBlock() {

        if (this.blocks.isEmpty()) {
            ProductData p1 = new ProductData(1,"1222","pizza");

            Block genesisBlock = new Block("0", p1, new Date().getTime());

            if (genesisBlock != null) {
                genesisBlock.mineBlock(this.prefix);
                blocks.add(genesisBlock);

            }
            System.out.println("Node "+this.blocks.size()+" created!");
        }
    }

    public void addBlock(Block block) {
        if (block != null) {
            block.mineBlock(this.prefix);
            blocks.add(block);
            System.out.println("Node "+this.blocks.size()+" created!");
        }
    }

    public boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        for (int i = 1; i < this.blocks.size(); i++) {
            currentBlock = this.blocks.get(i);
            previousBlock = this.blocks.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateBlockHash())) {
                return false;
            }

            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
                return false;
            }
        }
        return true;
    }


    public List<Block> getBlocks() {
        return blocks;
    }

    public int getPrefix() {
        return prefix;
    }
}
