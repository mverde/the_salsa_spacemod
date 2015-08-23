package com.the_salsa.spacemod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class RenderEntityGeneric extends Render
{
	protected ResourceLocation texture;
	protected ModelBase model;
	
	public RenderEntityGeneric(ModelBase model, ResourceLocation resource)
	{
		this.texture = resource;
		this.model = model;
	}
	
    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(EntityThrowable entity, double x, double y, double z, float yaw, float pitch, float scale)
    {
    	Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(180F - entity.prevRotationPitch - (entity.rotationPitch - entity.prevRotationPitch) * pitch, 1.0F, 0.0F, 0.0F);
		GL11.glScalef(scale, scale, scale != 1 ? 3.0F * scale : 1.0F);
		model.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
    }

	@Override
	public abstract void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_);

	@Override
	protected abstract ResourceLocation getEntityTexture(Entity p_110775_1_);
}
