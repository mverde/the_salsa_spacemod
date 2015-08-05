package com.the_salsa.spacemod;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelOxygenHelmet extends ModelBiped
{
  //fields
  ModelRenderer helmet;
  
  public ModelOxygenHelmet(float scale)
  {
	  super(scale, 0, 64, 64);
    
      helmet = new ModelRenderer(this, 0, 32);
      helmet.addBox(-8F, -12F, -8F, 16, 16, 16);
      helmet.setRotationPoint(0F, 0F, 0F);
      helmet.setTextureSize(64, 64);
      helmet.mirror = true;
      setRotation(helmet, 0F, 0F, 0F);
      
      bipedHead.addChild(helmet);
  }
  
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		GL11.glPushMatrix();
		GL11.glScalef(0.65F, 0.625F, 0.65F);
		GL11.glTranslatef(0F, -0.2F, 0F);
		bipedHead.render(f5);
		GL11.glPopMatrix();
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
