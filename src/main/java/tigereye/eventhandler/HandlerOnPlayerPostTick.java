package tigereye.eventhandler;

import cpw.mods.fml.common.gameevent.*;
import cpw.mods.fml.relauncher.*;
import tigereye.misc.*;
import cpw.mods.fml.common.eventhandler.*;

public class HandlerOnPlayerPostTick
{
    @SubscribeEvent
    public void onPlayerPostTick(final TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.side != Side.SERVER || event.player.getFoodStats().getFoodLevel() < 18) {
            return;
        }
        final ManaProperties prop = ManaProperties.get(event.player);
        if (prop.getCurrentMana() >= prop.getMaxMana()) {
            return;
        }
        final ManaProperties manaProperties2;
        final ManaProperties manaProperties = manaProperties2 = prop;
        ++manaProperties2.manaTimer;
        if (prop.manaTimer >= 60) {
            prop.replenishMana(1);
            prop.manaTimer = 0;
        }
    }
}
