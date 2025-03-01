package tigereye.itemblock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tigereye.spell.SpellData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import java.util.List;
import java.util.ArrayList;

import tigereye.ArcanaSpells;
import tigereye.gui.GuiHandler;
import tigereye.gui.InventorySpellBook;

public class ItemSpellBook extends Item {

    public ItemSpellBook() {
        this.setMaxStackSize(1);
        this.setUnlocalizedName("spell_book2");
        this.setTextureName("tigereye:spell_book2");
        this.setCreativeTab(ModItemsBlocks.tabSpellBooks);
    }

    // Добавление заклинания в книгу
    public void addSpell(ItemStack book, SpellData spell) {
        NBTTagCompound compound = book.getTagCompound();
        if (compound == null) {
            compound = new NBTTagCompound();
        }

        NBTTagList spellList = compound.getTagList("Spells", 10); // 10 - тип compound
        if (spellList == null) {
            spellList = new NBTTagList();
        }

        NBTTagCompound spellTag = new NBTTagCompound();
        spell.writeToNBT(spellTag);
        spellList.appendTag(spellTag);

        compound.setTag("Spells", spellList);
        book.setTagCompound(compound);
    }

    // Получение списка заклинаний из книги
    public List<SpellData> getSpells(ItemStack book) {
        List<SpellData> spells = new ArrayList<>();
        if (book.stackTagCompound != null && book.stackTagCompound.hasKey("Spells")) {
            NBTTagList spellList = book.stackTagCompound.getTagList("Spells", 10);
            for (int i = 0; i < spellList.tagCount(); i++) {
                NBTTagCompound spellTag = spellList.getCompoundTagAt(i);
                spells.add(SpellData.readFromNBTTagCompound(spellTag));
            }
        }
        return spells;
    }

    // Сохранение инвентаря заклинаний в книгу
    public void saveInventoryToBook(ItemStack book, InventorySpellBook inventory) {
        if (book.stackTagCompound == null) {
            book.stackTagCompound = new NBTTagCompound();
        }
        NBTTagList spellList = new NBTTagList();
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack != null && stack.getItem() instanceof ItemSpellScroll) {
                SpellData spellData = ItemSpellScroll.getSpellData(stack);
                if (spellData != null) {
                    NBTTagCompound spellTag = SpellData.writeToNBTTagCompound(spellData);
                    spellList.appendTag(spellTag);
                }
            }
        }
        book.stackTagCompound.setTag("Spells", spellList);
    }

    // Загрузка инвентаря заклинаний из книги
    public void loadInventoryFromBook(ItemStack book, InventorySpellBook inventory) {
        if (book.stackTagCompound != null && book.stackTagCompound.hasKey("Spells")) {
            NBTTagList spellList = book.stackTagCompound.getTagList("Spells", 10);
            for (int i = 0; i < spellList.tagCount(); i++) {
                NBTTagCompound spellTag = spellList.getCompoundTagAt(i);
                SpellData spellData = SpellData.readFromNBTTagCompound(spellTag);
                if (spellData != null) {
                    ItemStack spellStack = new ItemStack(ModItemsBlocks.spell_scroll); // Используем ItemSpellScroll
                    spellStack.stackTagCompound = new NBTTagCompound();
                    spellStack.stackTagCompound.setTag("spell", spellTag);
                    inventory.setInventorySlotContents(i, spellStack);
                }
            }
        }
    }

    // Получение активного слота из книги
    public int getActiveSlot(ItemStack book) {
        if (book.stackTagCompound != null && book.stackTagCompound.hasKey("ActiveSlot")) {
            return book.stackTagCompound.getInteger("ActiveSlot");
        }
        return 0; // Возвращаем 0, если активный слот не задан
    }

    // Установка активного слота в книге
    public void setActiveSlot(ItemStack book, int slot) {
        if (book.stackTagCompound == null) {
            book.stackTagCompound = new NBTTagCompound();
        }
        book.stackTagCompound.setInteger("ActiveSlot", slot);
    }

    public static SpellData getSpellData(ItemStack stack) {
        if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey("spell")) {
            return SpellData.readFromNBTTagCompound(stack.stackTagCompound.getCompoundTag("spell"));
        }
        return null;
    }
    
    // Использование книги (открытие GUI)
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            // Открыть GUI книги заклинаний
            player.openGui(ArcanaSpells.instance, GuiHandler.SPELL_BOOK_GUI, world, (int) player.posX, (int) player.posY, (int) player.posZ);
        }
        return stack;
    }
}