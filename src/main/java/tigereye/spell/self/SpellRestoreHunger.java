package tigereye.spell.self;

import tigereye.spell.Spell;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

public class SpellRestoreHunger extends Spell {

    public SpellRestoreHunger(final int par1) {
        super(par1);
        this.setName("restorehunger"); // Устанавливаем имя заклинания
    }

    @Override
    public short getMaxLevel() {
        return 3; // Максимальный уровень заклинания
    }

    @Override
    public int getManaCost() {
        return 4; // Стоимость маны для применения заклинания
    }

    @Override
    public short getCooldown() {
        return 80; // Время перезарядки заклинания (в тиках)
    }

    @Override
    public String getParticleName() {
        return "happyVillager"; // Частицы, которые будут появляться при применении заклинания
    }

    @Override
    public double getParticleAmount() {
        return 1.0; // Количество частиц
    }

    @Override
    public String getSoundName() {
        return "random.eat"; // Звук, который будет воспроизводиться при применении заклинания
    }

    @Override
    public void castSpell(final short par1Level, final World par2World, final EntityPlayer par3EntityPlayer) {
        // Воспроизводим звук
        par2World.playSoundAtEntity(par3EntityPlayer, this.getSoundName(), 1.0f, 1.0f);

        // Восстанавливаем уровень голода игрока
        int hungerToRestore = 2 * par1Level; // Количество единиц голода, которое будет восстановлено (зависит от уровня заклинания)
        int saturationToRestore = 1 * par1Level; // Количество насыщения, которое будет восстановлено

        // Увеличиваем уровень голода и насыщения
        par3EntityPlayer.getFoodStats().addStats(hungerToRestore, saturationToRestore);

        // Создаем частицы для визуального эффекта
        for (int i = 0; i < 10; i++) {
            par2World.spawnParticle(this.getParticleName(),
                    par3EntityPlayer.posX + (Spell.random.nextDouble() - 0.5),
                    par3EntityPlayer.posY + Spell.random.nextDouble() * 2,
                    par3EntityPlayer.posZ + (Spell.random.nextDouble() - 0.5),
                    0.0, 0.0, 0.0);
        }
    }
}
