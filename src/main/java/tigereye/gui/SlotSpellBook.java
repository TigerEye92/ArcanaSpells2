package tigereye.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import tigereye.itemblock.ItemSpellScroll;

public class SlotSpellBook extends Slot {

    public SlotSpellBook(IInventory inventory, int slotIndex, int x, int y) {
        super(inventory, slotIndex, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        // Проверяем, что в слот можно поместить только свитки с заклинаниями
        return stack.getItem() instanceof ItemSpellScroll;
    }
}
