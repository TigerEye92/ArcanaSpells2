package tigereye.itemblock;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.creativetab.*;

public class BlockManaCrystal extends Block
{
    protected BlockManaCrystal() {
        super(Material.iron);
        this.setHardness(5.0f);
        this.setResistance(10.0f);
        this.setStepSound(BlockManaCrystal.soundTypePiston);
        this.setBlockName("mana_crystal_block");
        this.setBlockTextureName("tigereye:" + this.getUnlocalizedName().substring(5));
        this.setLightLevel(0.625f);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
}
