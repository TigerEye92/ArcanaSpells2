package tigereye.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ContainerSpellBook extends Container {

    private final InventorySpellBook spellBookInventory;
    private final ItemStack bookStack;

    public ContainerSpellBook(InventoryPlayer inventoryPlayer, ItemStack bookStack) {
        this.bookStack = bookStack;
        this.spellBookInventory = new InventorySpellBook();

        // Проверяем, есть ли у предмета NBT-тег, и если нет, создаем его
        if (!bookStack.hasTagCompound()) {
            bookStack.setTagCompound(new NBTTagCompound());
        }

        // Читаем данные из NBT-тега в инвентарь книги заклинаний
        this.spellBookInventory.readFromNBT(bookStack.getTagCompound());

        // Добавляем слоты для активных заклинаний (используем SlotSpellBook)
        for (int i = 0; i < 8; ++i) {
            this.addSlotToContainer(new SlotSpellBook(this.spellBookInventory, i, 18, 5 + i * 18));
        }

        // Добавляем слоты для инвентаря игрока
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 48 + j * 18, 171 + i * 18));
            }
        }

        // Добавляем слоты для горячей панели игрока
        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 48 + i * 18, 229));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        // Игрок может взаимодействовать с контейнером всегда
        return true;
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);

        // Сохраняем инвентарь книги заклинаний в NBT-тег при закрытии контейнера
        if (!bookStack.hasTagCompound()) {
            bookStack.setTagCompound(new NBTTagCompound());
        }

        this.spellBookInventory.writeToNBT(bookStack.getTagCompound());
    }

    @Override
    public ItemStack slotClick(int slotId, int clickedButton, int mode, EntityPlayer player) {
        // Проверяем, что slotId находится в допустимых пределах
        if (slotId < 0 || slotId >= this.inventorySlots.size()) {
            return null; // Возвращаем null, если слот недействителен
        }

        // Получаем слот и проверяем, что он не null
        Slot slot = this.getSlot(slotId);
        if (slot == null) {
            return null; // Возвращаем null, если слот не найден
        }

        // Получаем ItemStack в слоте и проверяем, что он не null
        ItemStack stack = slot.getStack();
        if (stack == null && mode != 0) {
            return null; // Возвращаем null, если ItemStack отсутствует и режим не "стандартный"
        }

        // Вызываем оригинальный метод slotClick, если все проверки пройдены
        return super.slotClick(slotId, clickedButton, mode, player);
    }

    public InventorySpellBook getSpellBookInventory() {
        return spellBookInventory;
    }
}