package tigereye.eventhandler;

import net.minecraftforge.event.*;
import tigereye.itemblock.*;
import cpw.mods.fml.common.eventhandler.*;

public class HandlerOnAnvilUpdate
{
    @SubscribeEvent
    public void OnAnvilUpdate(final AnvilUpdateEvent event) {
        if (event.left.getItem().equals(ModItemsBlocks.spell_book) && event.right.getItem().equals(ModItemsBlocks.spell_book)) {
            event.output = ((ItemSpellScroll)ModItemsBlocks.spell_book).tryCombine(event.left, event.right);
            event.cost = 10;
        }
    }
}
