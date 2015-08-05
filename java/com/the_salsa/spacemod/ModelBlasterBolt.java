package com.the_salsa.spacemod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

public class ModelBlasterBolt extends ModelBase
{
	//fields
	ModelRenderer boltOuter;
	ModelRenderer boltInner;
  
	public ModelBlasterBolt()
	{
		textureWidth = 64;
		textureHeight = 32;
		
		boltOuter = new ModelRenderer(this, 0, 14);
		boltOuter.addBox(0F, 0F, -8F, 2, 2, 16);
		boltOuter.setRotationPoint(0F, 0F, 0F);
		boltOuter.setTextureSize(64, 32);
		boltOuter.mirror = true;
		setRotation(boltOuter, 0F, 0F, 0F);
		
		boltInner = new ModelRenderer(this, 20, 0);
		boltInner.addBox(0.5F, 0.5F, -7F, 1, 1, 14);
		boltInner.setRotationPoint(0F, 0F, 0F);
		boltInner.setTextureSize(64, 32);
		boltInner.mirror = true;
		setRotation(boltInner, 0F, 0F, 0F);
	}	
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glPushMatrix();
		GL11.glScalef(0.3F, 0.3F, 0.3F);
		GL11.glEnable(GL11.GL_BLEND);
		boltInner.render(f5);
		boltOuter.render(f5);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
  
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		this.boltOuter.rotateAngleX = f4;
		this.boltInner.rotateAngleY = f3 / (180F / (float)Math.PI);
		this.boltOuter.rotateAngleY = f3 / (180F / (float)Math.PI);
		this.boltInner.rotateAngleX = f4;
	}
}
