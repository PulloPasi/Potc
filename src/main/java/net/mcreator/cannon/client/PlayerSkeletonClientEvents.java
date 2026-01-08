package net.mcreator.cannon.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.client.renderer.entity.player.PlayerRenderer;

import net.mcreator.cannon.CannonMod;
import net.mcreator.cannon.client.renderer.layer.PlayerSkeletonLayer;
import net.mcreator.cannon.init.CannonModItems;

import java.util.WeakHashMap;

@Mod.EventBusSubscriber(modid = CannonMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class PlayerSkeletonClientEvents {
    private PlayerSkeletonClientEvents() {
    }

    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event) {
        addLayer(event, "default", false);
        addLayer(event, "slim", true);
    }

    private static void addLayer(EntityRenderersEvent.AddLayers event, String skin, boolean slim) {
        PlayerRenderer renderer = event.getSkin(skin);
        if (renderer == null) {
            return;
        }
        renderer.addLayer(new PlayerSkeletonLayer(renderer, event.getEntityModels(), slim));
    }

}

@Mod.EventBusSubscriber(modid = CannonMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
final class PlayerSkeletonRenderEvents {
    private static final WeakHashMap<PlayerRenderer, Boolean> HIDDEN_RENDERERS = new WeakHashMap<>();

    @SubscribeEvent
    public static void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
        if (!hasAztecCoin(event.getEntity())) {
            return;
        }

        PlayerRenderer renderer = event.getRenderer();
        renderer.getModel().setAllVisible(false);
        HIDDEN_RENDERERS.put(renderer, Boolean.TRUE);
    }

    @SubscribeEvent
    public static void onRenderPlayerPost(RenderPlayerEvent.Post event) {
        PlayerRenderer renderer = event.getRenderer();
        if (HIDDEN_RENDERERS.remove(renderer) != null) {
            renderer.getModel().setAllVisible(true);
        }
    }

    private static boolean hasAztecCoin(net.minecraft.world.entity.player.Player player) {
        return player.getInventory().items.stream().anyMatch(stack -> stack.is(CannonModItems.AZTEC_COIN.get()));
    }
}
