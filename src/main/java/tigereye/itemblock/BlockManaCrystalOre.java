package tigereye.itemblock;

import net.minecraft.block.*;
import java.util.*;
import net.minecraft.block.material.*;
import net.minecraft.creativetab.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.util.*;

public class BlockManaCrystalOre extends Block
{
    private Random rand;
    
    protected BlockManaCrystalOre() {
        super(Material.rock);
        this.rand = new Random();
        this.setHardness(3.0f);
        this.setResistance(5.0f);
        this.setStepSound(BlockManaCrystalOre.soundTypePiston);
        this.setBlockName("mana_crystal_ore");
        this.setBlockTextureName("tigereye:" + this.getUnlocalizedName().substring(5));
        this.setLightLevel(0.625f);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
    
    public Item getItemDropped(final int p_149650_1_, final Random p_149650_2_, final int p_149650_3_) {
        return ModItemsBlocks.mana_crystal;
    }
    
    public int quantityDroppedWithBonus(final int p_149679_1_, final Random p_149679_2_) {
        if (p_149679_1_ > 0 && Item.getItemFromBlock((Block)this) != ModItemsBlocks.mana_crystal) {
            int j = p_149679_2_.nextInt(p_149679_1_ + 2) - 1;
            if (j < 0) {
                j = 0;
            }
            return j + 1;
        }
        return 1;
    }
    
    public int getExpDrop(final IBlockAccess p_149690_1_, final int p_149690_5_, final int p_149690_7_) {
        if (ModItemsBlocks.mana_crystal != Item.getItemFromBlock((Block)this)) {
            return MathHelper.getRandomIntegerInRange(this.rand, 2, 5);
        }
        return 0;
    }
}
