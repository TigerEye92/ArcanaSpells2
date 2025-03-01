package tigereye.itemblock;

import net.minecraft.item.*;
import java.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.world.*;
import tigereye.*;
import tigereye.misc.*;
import net.minecraft.entity.player.*;
import cpw.mods.fml.common.network.simpleimpl.*;

public class ItemAppleMana extends ItemFood
{
    public ItemAppleMana() {
        super(4, 1.2f, false);
        this.setAlwaysEdible();
        this.setUnlocalizedName("apple_mana");
        this.setTextureName("tigereye:" + this.getUnlocalizedName().substring(5));
    }
    
    public EnumRarity getRarity(final ItemStack par1ItemStack) {
        return EnumRarity.rare;
    }
    
    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack par1ItemStack, final EntityPlayer par2EntityPlayer, final List par3List, final boolean par4) {
        par3List.add("\u0414\u0430\u0451\u0442 \u043f\u0440\u0438\u0440\u043e\u0441\u0442 \u043c\u0430\u043d\u044b +2 \u0434\u043e 20 \u0435\u0434\u0438\u043d\u0438\u0446");
    }
    
    protected void onFoodEaten(final ItemStack itemStack, final World world, final EntityPlayer entityPlayer) {
        if (!world.isRemote) {
            final ManaProperties props = ManaProperties.get(entityPlayer);
            int maxMana = props.getMaxMana();
            if (maxMana <= 18) {
                maxMana += 2;
                props.setMaxMana(maxMana);
                ArcanaSpells.networkWrapper.sendTo((IMessage)new MaxManaMessage(maxMana), (EntityPlayerMP)entityPlayer);
            }
        }
    }
}
