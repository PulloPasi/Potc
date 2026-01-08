package net.mcreator.cannon.client.gui;

import org.valkyrienskies.core.impl.shadow.z;
import org.valkyrienskies.core.impl.shadow.y;
import org.valkyrienskies.core.impl.shadow.x;
import org.valkyrienskies.core.impl.shadow.gy;
import org.valkyrienskies.core.impl.shadow.gx;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.cannon.world.inventory.CompassSelectMenu;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class CompassSelectScreen extends AbstractContainerScreen<CompassSelectMenu> {
	private final static HashMap<String, Object> guistate = CompassSelectMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_the_dutchman;
	Button button_buried_treasure;

	public CompassSelectScreen(CompassSelectMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("cannon:textures/screens/compass_select.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.cannon.compass_select.label_what_do_you_really_want_most"), 14, 20, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_the_dutchman = Button.builder(Component.translatable("gui.cannon.compass_select.button_the_dutchman"), e -> {
		}).bounds(this.leftPos + 46, this.topPos + 56, 87, 20).build();
		guistate.put("button:button_the_dutchman", button_the_dutchman);
		this.addRenderableWidget(button_the_dutchman);
		button_buried_treasure = Button.builder(Component.translatable("gui.cannon.compass_select.button_buried_treasure"), e -> {
		}).bounds(this.leftPos + 39, this.topPos + 84, 103, 20).build();
		guistate.put("button:button_buried_treasure", button_buried_treasure);
		this.addRenderableWidget(button_buried_treasure);
	}
}
