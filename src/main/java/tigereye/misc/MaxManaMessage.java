package tigereye.misc;

import io.netty.buffer.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.common.network.simpleimpl.*;
import tigereye.*;
import net.minecraft.entity.player.*;

public class MaxManaMessage implements IMessage
{
    private int maxMana;
    
    public MaxManaMessage() {
    }
    
    public MaxManaMessage(final int maxMana) {
        this.maxMana = maxMana;
    }
    
    public void fromBytes(final ByteBuf buf) {
        this.maxMana = ByteBufUtils.readVarInt(buf, 1);
    }
    
    public void toBytes(final ByteBuf buf) {
        ByteBufUtils.writeVarInt(buf, this.maxMana, 1);
    }
    
    public static class Handler implements IMessageHandler<MaxManaMessage, IMessage>
    {
        public IMessage onMessage(final MaxManaMessage message, final MessageContext ctx) {
            final EntityPlayer player = ArcanaSpells.proxy.getPlayerFromMessageContext(ctx);
            final ManaProperties prop = ManaProperties.get(player);
            prop.setMaxMana(message.maxMana);
            return null;
        }
    }
}
