package com.the_salsa.spacemod;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class RenderPlasmaSaber implements IItemRenderer
{
	private ModelPlasmaSaber model;
	
	public RenderPlasmaSaber()
	{
		model = new ModelPlasmaSaber();
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
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		
		GL11.glScalef(2F, 2F, 2F);
		
		switch (type)
		{
		case INVENTORY:
			GL11.glScalef(0.8F, 0.8F, 0.8F);
			GL11.glTranslatef(0.25F, 0.55F, 0F);
			GL11.glRotatef(-230F, 1F, 0F, 0F);
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(SpaceMod.MODID, "models/plasmasaberoff.png"));
			break;
		case EQUIPPED:
			GL11.glTranslatef(0.1F, 0.75F, 0.25F);
			GL11.glRotatef(145F, 1F, 0F, 0F);
			GL11.glRotatef(-35F, 0F, 0F, 1F);
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(SpaceMod.MODID, "models/plasmasaber.png"));
			break;
		case EQUIPPED_FIRST_PERSON:
			GL11.glTranslatef(0F, 0.6F, 0.3F);
			GL11.glRotatef(180F, 1F, 0F, 0.1F);
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(SpaceMod.MODID, "models/plasmasaber.png"));
			break;
		default:
			GL11.glScalef(0.66F, 0.66F, 0.66F);
			GL11.glTranslatef(0F, 0.5F, 0F);
			GL11.glRotatef(180F, 1F, 0F, 0F);
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(SpaceMod.MODID, "models/plasmasaberoff.png"));
			break;
		}
		
		model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		
		GL11.glPopMatrix();
	}
	
}
