package com.martin.firstmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelMartMob extends ModelBase
{
  //fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer righteye;
    ModelRenderer lefteye;
  
  public ModelMartMob()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-4F, -8F, -4F, 8, 8, 8);
      head.setRotationPoint(0F, -7F, 0F);
      head.setTextureSize(64, 64);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      body = new ModelRenderer(this, 16, 16);
      body.addBox(-4F, 0F, -2F, 8, 12, 4);
      body.setRotationPoint(0F, -4F, 0F);
      body.setTextureSize(64, 64);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      rightarm = new ModelRenderer(this, 40, 16);
      rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
      rightarm.setRotationPoint(-8F, 2F, 0F);
      rightarm.setTextureSize(64, 64);
      rightarm.mirror = true;
      setRotation(rightarm, 0F, 0F, 0F);
      leftarm = new ModelRenderer(this, 40, 16);
      leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
      leftarm.setRotationPoint(-1F, 10F, 2F);
      leftarm.setTextureSize(64, 64);
      leftarm.mirror = true;
      setRotation(leftarm, 0.7853982F, 0F, 0F);
      rightleg = new ModelRenderer(this, 0, 16);
      rightleg.addBox(-4F, 0F, -2F, 4, 12, 4);
      rightleg.setRotationPoint(-2F, 12F, 0F);
      rightleg.setTextureSize(64, 64);
      rightleg.mirror = true;
      setRotation(rightleg, 0F, 0F, 0F);
      leftleg = new ModelRenderer(this, 0, 16);
      leftleg.addBox(-3F, 0F, -2F, 4, 12, 4);
      leftleg.setRotationPoint(5F, 12F, 0F);
      leftleg.setTextureSize(64, 64);
      leftleg.mirror = true;
      setRotation(leftleg, 0F, 0F, 0F);
      righteye = new ModelRenderer(this, 0, 0);
      righteye.addBox(0F, 0F, 0F, 2, 1, 3);
      righteye.setRotationPoint(1F, -11F, -7F);
      righteye.setTextureSize(64, 64);
      righteye.mirror = true;
      setRotation(righteye, 0F, 0F, 0F);
      lefteye = new ModelRenderer(this, 0, 0);
      lefteye.addBox(0F, 0F, 0F, 2, 1, 3);
      lefteye.setRotationPoint(-3F, -11F, -7F);
      lefteye.setTextureSize(64, 64);
      lefteye.mirror = true;
      setRotation(lefteye, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    head.render(f5);
    body.render(f5);
    rightarm.render(f5);
    leftarm.render(f5);
    rightleg.render(f5);
    leftleg.render(f5);
    righteye.render(f5);
    lefteye.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
	  this.head.rotateAngleY = f3 / (180f / (float) Math.PI);
	  this.head.rotateAngleX = f4 / (180f / (float) Math.PI);
	  
	  this.righteye.rotateAngleX = this.head.rotateAngleX;
	  this.righteye.rotateAngleY = this.head.rotateAngleY;
	  this.lefteye.rotateAngleX = this.head.rotateAngleX;
	  this.lefteye.rotateAngleY = this.head.rotateAngleY;
	  
	  this.rightarm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
	  this.leftarm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
	  this.rightarm.rotateAngleZ = 0.0F;
	  this.leftarm.rotateAngleZ = 0.0F;
	  this.rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
	  this.leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
	  this.rightleg.rotateAngleY = 0.0F;
	  this.leftleg.rotateAngleY = 0.0F;
	  
	  this.rightarm.rotateAngleY = 0.0F;
	  this.leftarm.rotateAngleY = 0.0F;
	  float f6;
	  float f7;
	  
	  if (this.onGround > -9990.0F)
	  {
		  f6 = this.onGround;
		  this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * (float)Math.PI * 2.0F) * 2.0F;
		  this.rightarm.rotationPointZ = MathHelper.sin(this.body.rotateAngleY) * 5.0F;
		  this.rightarm.rotationPointX = -MathHelper.cos(this.body.rotateAngleY) * 5.0F;
		  this.leftarm.rotationPointZ = -MathHelper.sin(this.body.rotateAngleY) * 5.0F;
		  this.leftarm.rotationPointX = MathHelper.cos(this.body.rotateAngleY) * 5.0F;
		  this.rightarm.rotateAngleY += this.body.rotateAngleY;
		  this.leftarm.rotateAngleY += this.body.rotateAngleX;
		  this.leftarm.rotateAngleX += this.body.rotateAngleY;
		  f6 = 1.0F - this.onGround;
		  f6 *= f6;
		  f6 *= f6;
		  f6 = 1.0F - f6;
		  f7 = MathHelper.sin(f6 * (float)Math.PI);
		  float f8 = MathHelper.sin(this.onGround * (float)Math.PI) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
		  this.rightarm.rotateAngleX = (float)((double)this.rightarm.rotateAngleX - ((double)f7 * 1.2D + (double)f8));
		  this.rightarm.rotateAngleY = this.body.rotateAngleY * 2.0F;
		  this.rightarm.rotateAngleZ = MathHelper.sin(this.onGround * (float)Math.PI) * -0.4F;
	  }
	  this.rightarm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
	  this.leftarm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
	  this.rightarm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
	  this.leftarm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
	  
	  super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
}
