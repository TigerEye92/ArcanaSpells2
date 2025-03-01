package tigereye.itemblock;

import cpw.mods.fml.relauncher.*;
import net.minecraft.entity.player.*;
import java.util.*;
import tigereye.spell.targeted.*;
import tigereye.spell.summon.*;
import tigereye.spell.shield.*;
import tigereye.spell.aoe.*;
import net.minecraft.nbt.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import tigereye.misc.*;
import net.minecraft.entity.*;
import net.minecraft.creativetab.*;
import tigereye.spell.*;
import net.minecraft.util.*;
import net.minecraft.client.renderer.texture.*;

public class ItemSpellScroll extends Item
{
    @SideOnly(Side.CLIENT)
    protected IIcon[] icons;
    
    public ItemSpellScroll() {
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
        this.setUnlocalizedName("spell_book");
        this.setTextureName("tigereye:" + this.getUnlocalizedName().substring(5));
        this.setCreativeTab(ModItemsBlocks.tabSpellBooks);
    }
    
    public NBTTagCompound getSpellTag(final ItemStack par1ItemStack) {
        if (par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("spell")) {
            return (NBTTagCompound)par1ItemStack.stackTagCompound.getTag("spell");
        }
        return null;
    }

    // Добавляем метод getSpellData
    public static SpellData getSpellData(ItemStack stack) {
        if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey("spell")) {
            return SpellData.readFromNBTTagCompound(stack.stackTagCompound.getCompoundTag("spell"));
        }
        return null;
    }

//    // Метод для получения активного слота
//    public int getActiveSlot(ItemStack stack) {
//        if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey("activeSlot")) {
//            return stack.stackTagCompound.getInteger("activeSlot");
//        }
//        return 0; // По умолчанию активный слот — 0
//    }
//
//    // Метод для установки активного слота
//    public void setActiveSlot(ItemStack stack, int slot) {
//        if (stack.stackTagCompound == null) {
//            stack.stackTagCompound = new NBTTagCompound();
//        }
//        stack.stackTagCompound.setInteger("activeSlot", slot);
//    }
    
    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack par1ItemStack, final EntityPlayer par2EntityPlayer, final List par3List, final boolean par4) {
        final SpellData spellData = SpellData.readFromNBTTagCompound(this.getSpellTag(par1ItemStack));
        if (spellData.spellObj != null) {
            if (spellData.spellObj instanceof ISpellTargeted) {
                par3List.add("Targeted");
            }
            else if (spellData.spellObj instanceof SpellSummon) {
                par3List.add("Summoning");
            }
            else if (spellData.spellObj instanceof SpellShield) {
                par3List.add("Enhancement");
            }
            else if (spellData.spellObj instanceof SpellAoE) {
                par3List.add("Area of effect");
            }
            else {
                par3List.add("Miscellaneous");
            }
            if (spellData.spellCooldown != 0) {
                par3List.add(EnumChatFormatting.RED + "Cooldown: " + spellData.spellCooldown / 20 + " seconds");
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(final ItemStack par1ItemStack) {
        final SpellData spellData = SpellData.readFromNBTTagCompound(this.getSpellTag(par1ItemStack));
        if (spellData.spellObj != null) {
            return spellData.spellObj.getTranslatedName(spellData.spellLevel);
        }
        return super.getItemStackDisplayName(par1ItemStack);
    }
    
    public ItemStack tryCombine(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
        if (par1ItemStack.getItem() == ModItemsBlocks.spell_scroll && par2ItemStack.getItem() == ModItemsBlocks.spell_scroll && par1ItemStack.stackTagCompound != null && par2ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("spell") && par2ItemStack.stackTagCompound.hasKey("spell")) {
            final NBTTagCompound spellTag1 = par1ItemStack.stackTagCompound.getCompoundTag("spell");
            final NBTTagCompound spellTag2 = par2ItemStack.stackTagCompound.getCompoundTag("spell");
            final SpellData spellData1 = SpellData.readFromNBTTagCompound(spellTag1);
            final SpellData spellData2 = SpellData.readFromNBTTagCompound(spellTag2);
            final SpellData spellData3 = SpellData.tryCombine(spellData1, spellData2);
            if (spellData3 != null) {
                return this.getSpellItemStack(spellData3);
            }
        }
        return null;
    }
    
    public ItemStack getSpellItemStack(final SpellData par1SpellData) {
        final ItemStack itemstack = new ItemStack((Item)this);
        itemstack.stackTagCompound = new NBTTagCompound();
        final NBTTagCompound spellTag = SpellData.writeToNBTTagCompound(par1SpellData);
        itemstack.stackTagCompound.setTag("spell", (NBTBase)spellTag);
        itemstack.setItemDamage(par1SpellData.spellObj.effectId);
        return itemstack;
    }
    
    public EnumRarity getRarity(final ItemStack par1ItemStack) {
        return EnumRarity.rare;
    }
    
    public ItemStack onItemRightClick(final ItemStack par1ItemStack, final World par2World, final EntityPlayer par3EntityPlayer) {
        if (!par2World.isRemote) {
            final NBTTagCompound spellTag = this.getSpellTag(par1ItemStack);
            final SpellData spellData = SpellData.readFromNBTTagCompound(spellTag);
            final boolean inCreative = par3EntityPlayer.capabilities.isCreativeMode;
            if (!inCreative && spellData.spellCooldown > 0) {
                return par1ItemStack;
            }
            final ManaProperties props = ManaProperties.get(par3EntityPlayer);
            if (inCreative || props.consumeMana(spellData.spellObj.getManaCost())) {
                spellData.castSpell(par2World, par3EntityPlayer);
                SpellData.startCooldown(spellTag);
            }
        }
        return par1ItemStack;
    }
    
    public void onUpdate(final ItemStack par1ItemStack, final World par2World, final Entity par3Entity, final int par4, final boolean par5) {
        if (!par2World.isRemote) {
            SpellData.tickCooldown(this.getSpellTag(par1ItemStack));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(final Item par1Item, final CreativeTabs par2CreativeTab, final List par3List) {
        for (int i1 = 0; i1 < Spells.spellList.length; ++i1) {
            final Spell spell = Spells.spellList[i1];
            if (spell != null) {
                for (short i2 = spell.getMinLevel(); i2 <= spell.getMaxLevel(); ++i2) {
                    par3List.add(((ItemSpellScroll)ModItemsBlocks.spell_scroll).getSpellItemStack(new SpellData(spell, i2)));
                }
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(final int par1) {
        final int j = MathHelper.clamp_int(par1, 0, this.icons.length - 1);
        if (this.icons[j] != null) {
            return this.icons[j];
        }
        return super.getIconFromDamage(par1);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister par1IconRegister) {
        super.registerIcons(par1IconRegister);
        this.icons = new IIcon[256];
        final IIcon blazefireIcon = par1IconRegister.registerIcon("tigereye:blazefire_book");
        final IIcon teleportIcon = par1IconRegister.registerIcon("tigereye:teleport_book");
        final IIcon witherIcon = par1IconRegister.registerIcon("tigereye:wither_book");
        final IIcon skeletonIcon = par1IconRegister.registerIcon("tigereye:skeleton_book");
        this.icons[Spells.blazefire.effectId] = blazefireIcon;
        this.icons[Spells.ghastfire.effectId] = par1IconRegister.registerIcon("tigereye:ghast_book");
        this.icons[Spells.witherblast.effectId] = witherIcon;
        this.icons[Spells.summon_wolf.effectId] = par1IconRegister.registerIcon("tigereye:wolf_book");
        this.icons[Spells.raise_skeleton.effectId] = skeletonIcon;
        this.icons[Spells.raise_wither_skeleton.effectId] = witherIcon;
        this.icons[Spells.risen_ice_giant.effectId] = par1IconRegister.registerIcon("tigereye:giant_book");
        this.icons[Spells.summon_eldrith_golem.effectId] = par1IconRegister.registerIcon("tigereye:eldgolem_book");
        this.icons[Spells.summon_taintacle.effectId] = par1IconRegister.registerIcon("tigereye:taintacle_book");
        this.icons[Spells.raise_zombie.effectId] = par1IconRegister.registerIcon("tigereye:zombie_book");
        this.icons[Spells.raise_zombie_pigman.effectId] = par1IconRegister.registerIcon("tigereye:zombie_pigman_book");
        this.icons[Spells.summon_witch.effectId] = par1IconRegister.registerIcon("tigereye:witch_book");
        this.icons[Spells.summon_spider.effectId] = par1IconRegister.registerIcon("tigereye:spider_book");
        this.icons[Spells.summon_cave_spider.effectId] = par1IconRegister.registerIcon("tigereye:cave_spider_book");
        this.icons[Spells.teleport.effectId] = teleportIcon;
        this.icons[Spells.respawn.effectId] = teleportIcon;
        this.icons[Spells.fireShield.effectId] = par1IconRegister.registerIcon("tigereye:fire_shield_book");
        this.icons[Spells.earthShield.effectId] = par1IconRegister.registerIcon("tigereye:earth_shield_book");
        this.icons[Spells.waterShield.effectId] = par1IconRegister.registerIcon("tigereye:water_shield_book");
        this.icons[Spells.stormShield.effectId] = par1IconRegister.registerIcon("tigereye:storm_shield_book");
        this.icons[Spells.frostShield.effectId] = par1IconRegister.registerIcon("tigereye:frost_shield_book");
        this.icons[Spells.blazestorm.effectId] = blazefireIcon;
        this.icons[Spells.lightningstorm.effectId] = par1IconRegister.registerIcon("tigereye:lightning_book");
        this.icons[Spells.healSelf.effectId] = par1IconRegister.registerIcon("tigereye:lightning_book");
        this.icons[Spells.restoreHunger.effectId] = par1IconRegister.registerIcon("tigereye:lightning_book");
        this.icons[Spells.invisibility.effectId] = par1IconRegister.registerIcon("tigereye:lightning_book");
    }
}