package net.mcreator.cannon.entity;

import net.mcreator.cannon.init.CannonModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class HeavyLeadEntity extends LeadEntity {
    public HeavyLeadEntity(PlayMessages.SpawnEntity packet, Level level) {
        this(CannonModEntities.HEAVY_LEAD_ENTITY.get(), level);
    }

    public HeavyLeadEntity(net.minecraft.world.entity.EntityType<? extends LeadEntity> type, Level level) {
        super(type, level);
    }

    public static HeavyLeadEntity create(Level level, BlockPos from, BlockPos to) {
        HeavyLeadEntity entity = CannonModEntities.HEAVY_LEAD_ENTITY.get().create(level);
        if (entity != null) {
            entity.setFromPos(from);
            entity.setToPos(to);
            entity.updatePositionAndBox();
        }
        return entity;
    }

    @Override
    protected void updatePositionAndBox() {
        super.updatePositionAndBox();
        this.setBoundingBox(this.getBoundingBox().inflate(0.05));
    }
}
