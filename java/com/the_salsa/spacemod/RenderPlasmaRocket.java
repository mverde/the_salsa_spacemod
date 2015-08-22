package com.the_salsa.spacemod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderPlasmaRocket extends RenderEntityGeneric
{	
	public RenderPlasmaRocket(ModelBase model, ResourceLocation resource)
	{
		super(model, resource);
	}
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityPlasmaRocket entity)
    {
        return this.texture;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityPlasmaRocket) entity);
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
        this.doRender((EntityPlasmaRocket) entity, x, y, z, yaw, pitch, 1F);
    }
}
