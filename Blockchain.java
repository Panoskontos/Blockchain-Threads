import java.util.Date;
import java.util.List;
import java.util.LinkedList;
import java.util.Objects;

public class Blockchain {
    private List<Block> blocks;
    private int prefix;

    public Blockchain() {
        this.blocks = new LinkedList<>();
        this.prefix = 3;
    }

    public void GenesisBlock() {

        if (this.blocks.isEmpty()) {
            ProductData p1 = new ProductData(1,"1222","pizza",new Date().getTime(),20,"the best food","fast food");

            Block genesisBlock = new Block("0", p1, new Date().getTime());

            if (genesisBlock != null) {
                genesisBlock.mineBlock(this.prefix);
                blocks.add(genesisBlock);
            }
            }
            System.out.println("Node "+this.blocks.size()+" created!");
        }


    public Block searchIfProductExists(String code){
        Block previousBlock;
        for (int i = this.blocks.size()-1; i >= 0; i--) {
            previousBlock = this.blocks.get(i);

            if(previousBlock.getData().getCode().equals(code)){
                System.out.println(previousBlock);
                return previousBlock;
            }
        }
        return null;
    }

    public void addBlock(Block block) {
        // Get Last Block of the product
        Block searchProduct = searchIfProductExists(block.getData().getCode());

        if(searchProduct==null) {
            System.out.println("Product doesn't exist");
            if (block != null) {
                block.mineBlock(this.prefix);
                blocks.add(block);
                System.out.println("Node "+this.blocks.size()+" created!");
            }


        } else {
            System.out.println("Product exists");
            ProductData p2 = new ProductData(
                    searchProduct.getData().getAA()+1,
                    searchProduct.getData().getCode(),
                    searchProduct.getData().getTitle(),
                    new Date().getTime(),
                    block.getData().getPrice(),
                    searchProduct.getData().getDescription(),
                    searchProduct.getData().getCategory()
            );
            Block newBlock = new Block(block.getPreviousHash(), p2, new Date().getTime());
            newBlock.mineBlock(this.prefix);
            blocks.add(newBlock);
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
