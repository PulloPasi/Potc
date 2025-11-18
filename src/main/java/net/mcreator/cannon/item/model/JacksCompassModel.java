package net.mcreator.cannon.item.model;

import net.mcreator.cannon.item.JacksCompassItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class JacksCompassModel extends GeoModel<JacksCompassItem> {
    @Override
    public ResourceLocation getModelResource(JacksCompassItem animatable) {
        return new ResourceLocation("cannon", "geo/compass_-_converted.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(JacksCompassItem animatable) {
        return new ResourceLocation("cannon", "textures/item/compass.png");
    }

    @Override
    public ResourceLocation getAnimationResource(JacksCompassItem animatable) {
        return new ResourceLocation("cannon", "animations/compass_-_converted.animation.json");
    }
}
