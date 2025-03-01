package tigereye.itemblock;

import net.minecraft.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.creativetab.*;
import net.minecraft.item.*;
import java.util.*;
import net.minecraft.potion.*;
import tigereye.potion.*;
import tigereye.brewingapi.*;
import net.minecraft.client.renderer.texture.*;

public class ItemPotionBase extends ItemPotion
{
    @SideOnly(Side.CLIENT)
    private IIcon bottleDrinkable;
    @SideOnly(Side.CLIENT)
    private IIcon bottleSplash;
    @SideOnly(Side.CLIENT)
    private IIcon manaOverlayIcon;
    @SideOnly(Side.CLIENT)
    private IIcon manaRegenOverlayIcon;
    
    public ItemPotionBase() {
        this.setUnlocalizedName("potion_base");
        this.setTextureName("potion");
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(final Item par1Item, final CreativeTabs par2CreativeTab, final List par3List) {
        par3List.add(getManaPotionStack(false, 0));
        par3List.add(getManaPotionStack(false, 1));
        par3List.add(getManaPotionStack(true, 0));
        par3List.add(getManaPotionStack(true, 1));
        par3List.add(getManaRegenPotionStack(false, 0, false));
        par3List.add(getManaRegenPotionStack(false, 1, false));
        par3List.add(getManaRegenPotionStack(false, 0, true));
        par3List.add(getManaRegenPotionStack(true, 0, false));
        par3List.add(getManaRegenPotionStack(true, 1, false));
        par3List.add(getManaRegenPotionStack(true, 0, true));
    }
    
    public static ItemStack getManaPotionStack(final boolean splash, final int amplification) {
        final ItemStack itemStack = new ItemStack(ModItemsBlocks.itemPotionBase, 1, splash ? 16384 : 1);
        final List<PotionEffect> effects = new ArrayList<PotionEffect>();
        effects.add(new PotionEffect(ModPotions.mana.id, 1, amplification));
        BrewingRecipes.brewing().setEffects(itemStack, (List)effects);
        return itemStack;
    }
    
    public static ItemStack getManaRegenPotionStack(final boolean splash, final int amplification, final boolean extended) {
        final ItemStack itemStack = new ItemStack(ModItemsBlocks.itemPotionBase, 1, splash ? 16384 : 1);
        final List<PotionEffect> effects = new ArrayList<PotionEffect>();
        effects.add(new PotionEffect(ModPotions.manaRegen.id, (int)(900.0f * getDurationModifier(splash, amplification, extended)), amplification));
        BrewingRecipes.brewing().setEffects(itemStack, (List)effects);
        return itemStack;
    }
    
    private static float getDurationModifier(final boolean splash, final int amplification, final boolean extended) {
        float modifier = splash ? 0.75f : 1.0f;
        for (int i = 0; i < amplification; ++i) {
            modifier /= 2.0f;
        }
        return extended ? (modifier * 8.0f / 3.0f) : modifier;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(final int p_77617_1_) {
        return isSplash(p_77617_1_) ? this.bottleSplash : this.bottleDrinkable;
    }
    
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(final ItemStack par1ItemStack, final int par2) {
        return 16777215;
    }
    
    public IIcon getIcon(final ItemStack par1ItemStack, final int par2Pass) {
        if (par2Pass == 0) {
            final List<PotionEffect> effects = (List<PotionEffect>)this.getEffects(par1ItemStack);
            if (effects.size() > 0) {
                final int potionId = effects.get(0).getPotionID();
                if (potionId == ModPotions.mana.id) {
                    return this.manaOverlayIcon;
                }
                if (potionId == ModPotions.manaRegen.id) {
                    return this.manaRegenOverlayIcon;
                }
            }
        }
        return super.getIcon(par1ItemStack, par2Pass);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister par1IconRegister) {
        this.bottleDrinkable = par1IconRegister.registerIcon("tigereye:bottle_drinkable");
        this.bottleSplash = par1IconRegister.registerIcon("tigereye:bottle_splash");
        this.manaOverlayIcon = par1IconRegister.registerIcon("tigereye:mana_potion_overlay");
        this.manaRegenOverlayIcon = par1IconRegister.registerIcon("tigereye:mana_regen_potion_overlay");
    }
}
