package com.the_salsa.spacemod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public abstract class RenderGunGeneric implements IItemRenderer
{
	protected static RenderItem renderItem = new RenderItem();
	protected ModelBase model;
	
	public RenderGunGeneric(ModelBase model) //FINISH CONVERTING OTHER GUNS
	{
		this.model = model;
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper)
	{
		return type != ItemRenderType.INVENTORY;
	}

	@Override
	public abstract void renderItem(ItemRenderType type, ItemStack item, Object... data);
}
