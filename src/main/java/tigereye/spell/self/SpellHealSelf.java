package tigereye.spell.self;

import tigereye.spell.Spell;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

public class SpellHealSelf extends Spell {

    public SpellHealSelf(final int par1) {
        super(par1);
        this.setName("healself"); // Устанавливаем имя заклинания
    }

    @Override
    public short getMaxLevel() {
        return 5; // Максимальный уровень заклинания
    }

    @Override
    public int getManaCost() {
        return 5; // Стоимость маны для применения заклинания
    }

    @Override
    public short getCooldown() {
        return 100; // Время перезарядки заклинания (в тиках)
    }

    @Override
    public String getParticleName() {
        return "heart"; // Частицы, которые будут появляться при применении заклинания
    }

    @Override
    public double getParticleAmount() {
        return 1.0; // Количество частиц
    }

    @Override
    public String getSoundName() {
        return "random.drink"; // Звук, который будет воспроизводиться при применении заклинания
    }

    @Override
    public void castSpell(final short par1Level, final World par2World, final EntityPlayer par3EntityPlayer) {
        // Воспроизводим звук
        par2World.playSoundAtEntity(par3EntityPlayer, this.getSoundName(), 1.0f, 1.0f);

        // Восстанавливаем здоровье игрока
        float healAmount = 2.0f * par1Level; // Количество здоровья, которое будет восстановлено (зависит от уровня заклинания)
        par3EntityPlayer.heal(healAmount);

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
