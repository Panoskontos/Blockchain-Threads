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
            Block genesisBlock = new Block("0", "Data for the first block", new Date().getTime());

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
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public int getPrefix() {
        return prefix;
    }
}
