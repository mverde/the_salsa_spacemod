package com.the_salsa.spacemod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderBlasterRifle extends RenderGunGeneric
{
	private static ModelBlasterRifle model = new ModelBlasterRifle();
	
	public RenderBlasterRifle()
	{
		super(model);
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		GL11.glPushMatrix();
		GL11.glScalef(3F, 3F, 3F);
		
		switch (type)
		{
		case INVENTORY:
			GL11.glScalef(0.333F, 0.333F, 0.333F);
			FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
			IIcon icon = item.getIconIndex();
			renderItem.renderIcon(0, 0, icon, 16, 16);
			
			String text;
			if (item.stackTagCompound == null)
			{
				text = "0";
			}
			else
			{
				text = "" + item.stackTagCompound.getInteger("currentAmmo");
			}
			
			fontRenderer.drawStringWithShadow(text, 1, 1, 0xFFFFFF);
			break;
		case EQUIPPED:
			GL11.glTranslatef(0.025F, -0.25F, 0.025F);
			GL11.glRotatef(180F, 1F, 0.6F, 1F);
			GL11.glRotatef(45F, 2.2F, 4F, -1.7F);
			break;
		case EQUIPPED_FIRST_PERSON:
			GL11.glScalef(0.9F, 0.9F, 0.9F);
			GL11.glTranslatef(0.2F, 0.4975F, -0.2F);
			GL11.glRotatef(90F, 1F, 0.5F, -4F);
			GL11.glRotatef(90F, 0.75F, 0.25F, -2.95F);
			GL11.glRotatef(10F, 0.2F, 0.9F, 0.5F);
			break;
		default:
			GL11.glScalef(0.66F, 0.66F, 0.66F);
			GL11.glTranslatef(0F, 0.25F, 0.375F);
			GL11.glRotatef(180F, 1F, 0F, 0F);
			break;
		}

		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(SpaceMod.MODID, "models/blasterrifle.png"));
		model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		
		GL11.glPopMatrix();
	}
	
}
