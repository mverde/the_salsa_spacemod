package com.the_salsa.spacemod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBasicShip extends ModelBase
{
  //fields
    ModelRenderer chassisMain;
    ModelRenderer chassisBody;
    ModelRenderer engineRight;
    ModelRenderer engineLeft;
    ModelRenderer wingLeft;
    ModelRenderer wingRight;
    ModelRenderer chassisDetailTop;
    ModelRenderer cannonLeft;
    ModelRenderer cannonRight;
    ModelRenderer cannonTipLeft;
    ModelRenderer cannonTipRight;
    ModelRenderer chassisNose1;
    ModelRenderer chassisNose2;
    ModelRenderer chassisNose3;
    ModelRenderer chassisBack;
    ModelRenderer cockpitTop;
    ModelRenderer cockpitRight;
    ModelRenderer cockpitLeft;
    ModelRenderer cockpitFront;
    ModelRenderer topFin;
    ModelRenderer engineTipLeft;
    ModelRenderer engineTipRight;
  
  public ModelBasicShip()
  {
    textureWidth = 256;
    textureHeight = 256;
    
      chassisMain = new ModelRenderer(this, 0, 13);
      chassisMain.addBox(-8F, -4F, 13F, 16, 8, 9);
      chassisMain.setRotationPoint(0F, 12F, 0F);
      chassisMain.setTextureSize(64, 32);
      chassisMain.mirror = true;
      setRotation(chassisMain, 0F, 3.141593F, 0F);
      chassisBody = new ModelRenderer(this, 0, 32);
      chassisBody.addBox(-10F, -5F, -12F, 20, 10, 26);
      chassisBody.setRotationPoint(0F, 12F, 0F);
      chassisBody.setTextureSize(64, 32);
      chassisBody.mirror = true;
      setRotation(chassisBody, 0F, 3.141593F, 0F);
      engineRight = new ModelRenderer(this, 0, 70);
      engineRight.addBox(10F, -8F, -17F, 8, 8, 19);
      engineRight.setRotationPoint(0F, 12F, 0F);
      engineRight.setTextureSize(64, 32);
      engineRight.mirror = true;
      setRotation(engineRight, 0F, 3.141593F, 0F);
      engineLeft = new ModelRenderer(this, 63, 70);
      engineLeft.addBox(-18F, -8F, -17F, 8, 8, 19);
      engineLeft.setRotationPoint(0F, 12F, 0F);
      engineLeft.setTextureSize(64, 32);
      engineLeft.mirror = true;
      setRotation(engineLeft, 0F, 3.141593F, 0F);
      wingLeft = new ModelRenderer(this, 0, 102);
      wingLeft.addBox(-37F, 0F, -6F, 28, 1, 16);
      wingLeft.setRotationPoint(0F, 12F, 0F);
      wingLeft.setTextureSize(64, 32);
      wingLeft.mirror = true;
      setRotation(wingLeft, 0F, 3.01942F, 0F);
      wingRight = new ModelRenderer(this, 0, 121);
      wingRight.addBox(-37F, 0F, -10F, 28, 1, 16);
      wingRight.setRotationPoint(0F, 12F, 0F);
      wingRight.setTextureSize(64, 32);
      wingRight.mirror = true;
      setRotation(wingRight, 0F, 0.122173F, 0F);
      chassisDetailTop = new ModelRenderer(this, 98, 17);
      chassisDetailTop.addBox(-4F, -5.5F, 14F, 8, 2, 20);
      chassisDetailTop.setRotationPoint(0F, 12F, 0F);
      chassisDetailTop.setTextureSize(64, 32);
      chassisDetailTop.mirror = true;
      setRotation(chassisDetailTop, 0F, 3.141593F, 0F);
      cannonLeft = new ModelRenderer(this, 0, 140);
      cannonLeft.addBox(22F, 1F, -20F, 1, 1, 23);
      cannonLeft.setRotationPoint(0F, 12F, 0F);
      cannonLeft.setTextureSize(64, 32);
      cannonLeft.mirror = true;
      setRotation(cannonLeft, 0F, 0F, 0F);
      cannonRight = new ModelRenderer(this, 49, 140);
      cannonRight.addBox(-22F, 1F, -20F, 1, 1, 23);
      cannonRight.setRotationPoint(0F, 12F, 0F);
      cannonRight.setTextureSize(64, 32);
      cannonRight.mirror = true;
      setRotation(cannonRight, 0F, 0F, 0F);
      cannonTipLeft = new ModelRenderer(this, 84, 0);
      cannonTipLeft.addBox(21F, 0.5F, -18F, 3, 2, 1);
      cannonTipLeft.setRotationPoint(0F, 12F, 0F);
      cannonTipLeft.setTextureSize(64, 32);
      cannonTipLeft.mirror = true;
      setRotation(cannonTipLeft, 0F, 0F, 0F);
      cannonTipRight = new ModelRenderer(this, 95, 0);
      cannonTipRight.addBox(-23F, 0.5F, -18F, 3, 2, 1);
      cannonTipRight.setRotationPoint(0F, 12F, 0F);
      cannonTipRight.setTextureSize(64, 32);
      cannonTipRight.mirror = true;
      setRotation(cannonTipRight, 0F, 0F, 0F);
      chassisNose1 = new ModelRenderer(this, 0, 165);
      chassisNose1.addBox(-7F, -4F, 22F, 14, 8, 8);
      chassisNose1.setRotationPoint(0F, 12F, 0F);
      chassisNose1.setTextureSize(256, 256);
      chassisNose1.mirror = true;
      setRotation(chassisNose1, 0F, 3.141593F, 0F);
      chassisNose2 = new ModelRenderer(this, 0, 182);
      chassisNose2.addBox(-6F, -3.5F, 30F, 12, 7, 6);
      chassisNose2.setRotationPoint(0F, 12F, 0F);
      chassisNose2.setTextureSize(256, 256);
      chassisNose2.mirror = true;
      setRotation(chassisNose2, 0F, 3.141593F, 0F);
      chassisNose3 = new ModelRenderer(this, 0, 196);
      chassisNose3.addBox(-5F, -2.5F, 36F, 10, 6, 5);
      chassisNose3.setRotationPoint(0F, 12F, 0F);
      chassisNose3.setTextureSize(256, 256);
      chassisNose3.mirror = true;
      setRotation(chassisNose3, 0F, 3.141593F, 0F);
      chassisBack = new ModelRenderer(this, 100, 41);
      chassisBack.addBox(-9F, -11.5F, -12F, 18, 7, 8);
      chassisBack.setRotationPoint(0F, 12F, 0F);
      chassisBack.setTextureSize(256, 256);
      chassisBack.mirror = true;
      setRotation(chassisBack, 0F, 3.141593F, 0F);
      cockpitTop = new ModelRenderer(this, 155, 0);
      cockpitTop.addBox(-4F, -12F, -4F, 8, 0, 14);
      cockpitTop.setRotationPoint(0F, 12F, 0F);
      cockpitTop.setTextureSize(256, 256);
      cockpitTop.mirror = true;
      setRotation(cockpitTop, 0F, 3.141593F, 0F);
      cockpitRight = new ModelRenderer(this, 155, 14);
      cockpitRight.addBox(4F, -12F, -4F, 0, 7, 14);
      cockpitRight.setRotationPoint(0F, 12F, 0F);
      cockpitRight.setTextureSize(256, 256);
      cockpitRight.mirror = true;
      setRotation(cockpitRight, 0F, 3.141593F, 0F);
      cockpitLeft = new ModelRenderer(this, 181, 14);
      cockpitLeft.addBox(-4F, -12F, -4F, 0, 7, 14);
      cockpitLeft.setRotationPoint(0F, 12F, 0F);
      cockpitLeft.setTextureSize(256, 256);
      cockpitLeft.mirror = true;
      setRotation(cockpitLeft, 0F, 3.141593F, 0F);
      cockpitFront = new ModelRenderer(this, 155, 34);
      cockpitFront.addBox(-4F, -12F, 10F, 8, 7, 0);
      cockpitFront.setRotationPoint(0F, 12F, 0F);
      cockpitFront.setTextureSize(256, 256);
      cockpitFront.mirror = true;
      setRotation(cockpitFront, 0F, 3.141593F, 0F);
      topFin = new ModelRenderer(this, 47, 165);
      topFin.addBox(-0.5F, -24F, -7F, 1, 12, 8);
      topFin.setRotationPoint(0F, 12F, 0F);
      topFin.setTextureSize(256, 256);
      topFin.mirror = true;
      setRotation(topFin, 0.4363323F, 3.141593F, 0F);
      engineTipLeft = new ModelRenderer(this, 0, 209);
      engineTipLeft.addBox(-18.5F, -8.5F, -19F, 9, 9, 3);
      engineTipLeft.setRotationPoint(0F, 12F, 0F);
      engineTipLeft.setTextureSize(256, 256);
      engineTipLeft.mirror = true;
      setRotation(engineTipLeft, 0F, 3.141593F, 0F);
      engineTipRight = new ModelRenderer(this, 26, 209);
      engineTipRight.addBox(9.5F, -8.5F, -18.06667F, 9, 9, 3);
      engineTipRight.setRotationPoint(0F, 12F, 0F);
      engineTipRight.setTextureSize(256, 256);
      engineTipRight.mirror = true;
      setRotation(engineTipRight, 0F, 3.141593F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    chassisMain.render(f5);
    chassisBody.render(f5);
    engineRight.render(f5);
    engineLeft.render(f5);
    wingLeft.render(f5);
    wingRight.render(f5);
    chassisDetailTop.render(f5);
    cannonLeft.render(f5);
    cannonRight.render(f5);
    cannonTipLeft.render(f5);
    cannonTipRight.render(f5);
    chassisNose1.render(f5);
    chassisNose2.render(f5);
    chassisNose3.render(f5);
    chassisBack.render(f5);
    cockpitTop.render(f5);
    cockpitRight.render(f5);
    cockpitLeft.render(f5);
    cockpitFront.render(f5);
    topFin.render(f5);
    engineTipLeft.render(f5);
    engineTipRight.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}

