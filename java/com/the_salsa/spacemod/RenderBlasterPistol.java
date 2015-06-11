package com.the_salsa.spacemod;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class RenderBlasterPistol implements IItemRenderer
{
	private ModelBlasterPistol model;
	
	public RenderBlasterPistol()
	{
		model = new ModelBlasterPistol();
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
			GL11.glScalef(0.87F, 0.87F, 0.87F);
			GL11.glTranslatef(0F, 0.525F, 0.2F);
			GL11.glRotatef(120F, 1F, 0F, 0.35F);
			break;
		case EQUIPPED:
			GL11.glTranslatef(-0.11F, 0.3F, -0.04F);
			GL11.glRotatef(180F, 1F, 0F, 1F);
			GL11.glRotatef(45F, 2.2F, 4F, -1.7F);
			break;
		case EQUIPPED_FIRST_PERSON:
			GL11.glScalef(0.9F, 0.9F, 0.9F);
			GL11.glTranslatef(0.2F, 0.85F, -0.245F);
			GL11.glRotatef(90F, 1F, 0.5F, -4F);
			GL11.glRotatef(90F, 0.75F, 0.25F, -2.95F);
			break;
		default:
			GL11.glScalef(0.66F, 0.66F, 0.66F);
			GL11.glTranslatef(0F, 0.25F, 0.375F);
			GL11.glRotatef(180F, 1F, 0F, 0F);
			break;
		}

		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(SpaceMod.MODID, "models/blasterpistol.png"));
		model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		
		GL11.glPopMatrix();
	}
	
}
