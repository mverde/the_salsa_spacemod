package com.the_salsa.spacemod;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

public class RenderBasicShip extends RenderEntityGeneric
{
	public RenderBasicShip(ModelBase model, ResourceLocation resource)
	{
		super(model, resource);
	}

	protected ResourceLocation getEntityTexture(EntityBasicShip entity)
	{
		return this.texture;
	}
	
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityBasicShip) entity);
    }
    
    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
    	Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y + 2.0F, (float) z);
		GL11.glScalef(2.0F, 2.0F, 2.0F);
		GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(180F - entity.prevRotationPitch - (entity.rotationPitch - entity.prevRotationPitch) * pitch, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(180f, 0.0F, 1.0F, 0.0F);
		
		model.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
    }
}
