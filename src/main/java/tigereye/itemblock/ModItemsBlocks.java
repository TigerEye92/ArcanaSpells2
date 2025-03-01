package tigereye.itemblock;

import net.minecraft.creativetab.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import cpw.mods.fml.common.registry.*;
import tigereye.brewingapi.*;

import cpw.mods.fml.relauncher.*;

public class ModItemsBlocks
{
    public static final CreativeTabs tabSpellBooks;
    public static final Item spell_scroll;
    public static final Item spell_book;
    public static final Item apple_mana;
    public static final Item apple_mana2;
    public static final Item apple_mana3;
    public static final Item apple_mana4;
    public static final Item apple_mana5;
    public static final Item mana_crystal;
    public static final Item itemPotionBase;
    public static final Block mana_crystal_ore;
    public static final Block mana_crystal_block;
    

    
    
    public static void registerModItemsAndBlocks() {
        registerItem(ModItemsBlocks.spell_scroll);
        registerItem(ModItemsBlocks.apple_mana);
        registerItem(ModItemsBlocks.spell_book);

        registerItem(ModItemsBlocks.apple_mana2);
        registerItem(ModItemsBlocks.apple_mana3);
        registerItem(ModItemsBlocks.apple_mana4);
        registerItem(ModItemsBlocks.apple_mana5);
        registerItem(ModItemsBlocks.mana_crystal);
        registerItem(ModItemsBlocks.itemPotionBase);
        registerBlock(ModItemsBlocks.mana_crystal_ore);
        registerBlock(ModItemsBlocks.mana_crystal_block);
        GameRegistry.addRecipe(new ItemStack(ModItemsBlocks.apple_mana), new Object[] { "xxx", "xyx", "xxx", 'x', ModItemsBlocks.mana_crystal, 'y', Items.apple });
        GameRegistry.addRecipe(new ItemStack(ModItemsBlocks.mana_crystal_block), new Object[] { "xxx", "xxx", "xxx", 'x', ModItemsBlocks.mana_crystal });
        GameRegistry.addRecipe(new ItemStack(ModItemsBlocks.mana_crystal, 9), new Object[] { "x", 'x', ModItemsBlocks.mana_crystal_block });
        final ItemStack awkward = new ItemStack((Item)Items.potionitem, 1, 16);
        final ItemStack gunpowder = new ItemStack(Items.gunpowder);
        final ItemStack glowstone = new ItemStack(Items.glowstone_dust);
        final ItemStack redstone = new ItemStack(Items.redstone);
        final ItemStack mana = ItemPotionBase.getManaPotionStack(false, 0);
        final ItemStack manaAmpl = ItemPotionBase.getManaPotionStack(false, 1);
        final ItemStack manaSplash = ItemPotionBase.getManaPotionStack(true, 0);
        final ItemStack manaSplashAmpl = ItemPotionBase.getManaPotionStack(true, 1);
        final ItemStack regen = ItemPotionBase.getManaRegenPotionStack(false, 0, false);
        final ItemStack regenAmpl = ItemPotionBase.getManaRegenPotionStack(false, 1, false);
        final ItemStack regenExt = ItemPotionBase.getManaRegenPotionStack(false, 0, true);
        final ItemStack regenSplash = ItemPotionBase.getManaRegenPotionStack(true, 0, false);
        final ItemStack regenSplashAmpl = ItemPotionBase.getManaRegenPotionStack(true, 1, false);
        final ItemStack regenSplashExt = ItemPotionBase.getManaRegenPotionStack(true, 0, true);
        BrewingRecipes.brewing().addBrewing(awkward, new ItemStack(ModItemsBlocks.mana_crystal), mana);
        BrewingRecipes.brewing().addBrewing(mana, glowstone, manaAmpl);
        BrewingRecipes.brewing().addBrewing(mana, gunpowder, manaSplash);
        BrewingRecipes.brewing().addBrewing(manaAmpl, gunpowder, manaSplashAmpl);
        BrewingRecipes.brewing().addBrewing(manaSplash, glowstone, manaSplashAmpl);
        BrewingRecipes.brewing().addBrewing(awkward, new ItemStack(ModItemsBlocks.apple_mana), regen);
        BrewingRecipes.brewing().addBrewing(regen, glowstone, regenAmpl);
        BrewingRecipes.brewing().addBrewing(regenExt, glowstone, regenAmpl);
        BrewingRecipes.brewing().addBrewing(regen, redstone, regenExt);
        BrewingRecipes.brewing().addBrewing(regenAmpl, redstone, regenExt);
        BrewingRecipes.brewing().addBrewing(regen, gunpowder, regenSplash);
        BrewingRecipes.brewing().addBrewing(regenAmpl, gunpowder, regenSplashAmpl);
        BrewingRecipes.brewing().addBrewing(regenSplash, glowstone, regenSplashAmpl);
        BrewingRecipes.brewing().addBrewing(regenSplashExt, glowstone, regenSplashAmpl);
        BrewingRecipes.brewing().addBrewing(regenExt, gunpowder, regenSplashExt);
        BrewingRecipes.brewing().addBrewing(regenSplash, redstone, regenSplashExt);
        BrewingRecipes.brewing().addBrewing(regenSplashAmpl, redstone, regenSplashExt);
    }
    
    private static void registerBlock(final Block block) {
        GameRegistry.registerBlock(block, "tigereye_" + block.getUnlocalizedName().substring(5));
    }
    
    private static void registerItem(final Item item) {
        GameRegistry.registerItem(item, "tigereye_" + item.getUnlocalizedName().substring(5));
    }
    
    static {
        tabSpellBooks = new CreativeTabs("tabSpellBooks") {
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem() {
                return ModItemsBlocks.spell_scroll;
            }
        };
        spell_scroll = new ItemSpellScroll();
        spell_book = new ItemSpellBook();
        apple_mana = (Item)new ItemAppleMana();
        apple_mana2 = (Item)new ItemAppleMana2();
        apple_mana3 = (Item)new ItemAppleMana3();
        apple_mana4 = (Item)new ItemAppleMana4();
        apple_mana5 = (Item)new ItemAppleMana5();
        mana_crystal = new Item().setUnlocalizedName("mana_crystal").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("tigereye:mana_crystal");
        itemPotionBase = (Item)new ItemPotionBase();
        mana_crystal_ore = new BlockManaCrystalOre();
        mana_crystal_block = new BlockManaCrystal();
    }
}
