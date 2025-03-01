package tigereye.spell;

import net.minecraft.item.ItemStack;
import tigereye.itemblock.ItemSpellBook;
import tigereye.itemblock.ItemSpellScroll;

public class SpellHelper {
    public static SpellData getSpellData(ItemStack stack) {
        if (stack.getItem() instanceof ItemSpellScroll) {
            return ItemSpellScroll.getSpellData(stack);
        } else if (stack.getItem() instanceof ItemSpellBook) {
            return ItemSpellBook.getSpellData(stack);
        }
        return null;
    }
}