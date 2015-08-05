package com.the_salsa.spacemod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBasicShip extends ModelBase
{
  //fields
    ModelRenderer chassisMain;
    ModelRenderer chassisBack;
    ModelRenderer engineRight;
    ModelRenderer engineLeft;
    ModelRenderer wingLeft;
    ModelRenderer wingRight;
    ModelRenderer chassisDetailTop;
    ModelRenderer cannonLeft;
    ModelRenderer cannonRight;
    ModelRenderer cannonTipLeft;
    ModelRenderer cannonTipRight;
  
  public ModelBasicShip()
  {
    textureWidth = 256;
    textureHeight = 256;
    
      chassisMain = new ModelRenderer(this, 0, 0);
      chassisMain.addBox(-8F, -4F, 13F, 16, 8, 25);
      chassisMain.setRotationPoint(0F, 12F, 0F);
      chassisMain.setTextureSize(256, 256);
      chassisMain.mirror = true;
      setRotation(chassisMain, 0F, 3.141593F, 0F);
      chassisBack = new ModelRenderer(this, 0, 32);
      chassisBack.addBox(-10F, -5F, -10F, 20, 10, 24);
      chassisBack.setRotationPoint(0F, 12F, 0F);
      chassisBack.setTextureSize(256, 256);
      chassisBack.mirror = true;
      setRotation(chassisBack, 0F, 3.141593F, 0F);
      engineRight = new ModelRenderer(this, 0, 67);
      engineRight.addBox(10F, -7F, -15F, 8, 8, 23);
      engineRight.setRotationPoint(0F, 12F, 0F);
      engineRight.setTextureSize(256, 256);
      engineRight.mirror = true;
      setRotation(engineRight, 0F, 3.141593F, 0F);
      engineLeft = new ModelRenderer(this, 63, 67);
      engineLeft.addBox(-18F, -7F, -15F, 8, 8, 23);
      engineLeft.setRotationPoint(0F, 12F, 0F);
      engineLeft.setTextureSize(256, 256);
      engineLeft.mirror = true;
      setRotation(engineLeft, 0F, 3.141593F, 0F);
      wingLeft = new ModelRenderer(this, 0, 99);
      wingLeft.addBox(-37F, 0F, -6F, 28, 1, 16);
      wingLeft.setRotationPoint(0F, 12F, 0F);
      wingLeft.setTextureSize(256, 256);
      wingLeft.mirror = true;
      setRotation(wingLeft, 0F, 3.01942F, 0F);
      wingRight = new ModelRenderer(this, 0, 117);
      wingRight.addBox(-37F, 0F, -10F, 28, 1, 16);
      wingRight.setRotationPoint(0F, 12F, 0F);
      wingRight.setTextureSize(256, 256);
      wingRight.mirror = true;
      setRotation(wingRight, 0F, 0.122173F, 0F);
      chassisDetailTop = new ModelRenderer(this, 98, 17);
      chassisDetailTop.addBox(-4F, -6F, 14F, 8, 2, 20);
      chassisDetailTop.setRotationPoint(0F, 12F, 0F);
      chassisDetailTop.setTextureSize(256, 256);
      chassisDetailTop.mirror = true;
      setRotation(chassisDetailTop, 0F, 3.141593F, 0F);
      cannonLeft = new ModelRenderer(this, 0, 135);
      cannonLeft.addBox(22F, 1F, -20F, 1, 1, 23);
      cannonLeft.setRotationPoint(0F, 12F, 0F);
      cannonLeft.setTextureSize(256, 256);
      cannonLeft.mirror = true;
      setRotation(cannonLeft, 0F, 0F, 0F);
      cannonRight = new ModelRenderer(this, 49, 135);
      cannonRight.addBox(-22F, 1F, -20F, 1, 1, 23);
      cannonRight.setRotationPoint(0F, 12F, 0F);
      cannonRight.setTextureSize(256, 256);
      cannonRight.mirror = true;
      setRotation(cannonRight, 0F, 0F, 0F);
      cannonTipLeft = new ModelRenderer(this, 84, 0);
      cannonTipLeft.addBox(21.5F, 0.5F, -18F, 2, 2, 1);
      cannonTipLeft.setRotationPoint(0F, 12F, 0F);
      cannonTipLeft.setTextureSize(256, 256);
      cannonTipLeft.mirror = true;
      setRotation(cannonTipLeft, 0F, 0F, 0F);
      cannonTipRight = new ModelRenderer(this, 95, 0);
      cannonTipRight.addBox(-22.5F, 0.5F, -18F, 2, 2, 1);
      cannonTipRight.setRotationPoint(0F, 12F, 0F);
      cannonTipRight.setTextureSize(256, 256);
      cannonTipRight.mirror = true;
      setRotation(cannonTipRight, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    chassisMain.render(f5);
    chassisBack.render(f5);
    engineRight.render(f5);
    engineLeft.render(f5);
    wingLeft.render(f5);
    wingRight.render(f5);
    chassisDetailTop.render(f5);
    cannonLeft.render(f5);
    cannonRight.render(f5);
    cannonTipLeft.render(f5);
    cannonTipRight.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}

