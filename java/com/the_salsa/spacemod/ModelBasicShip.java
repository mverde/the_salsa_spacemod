package com.the_salsa.spacemod;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBasicShip extends ModelBase
{
	  //fields
    ModelRenderer chassisMain;
    ModelRenderer chassisBody;
    ModelRenderer engineRight;
    ModelRenderer engineTipRight;
    ModelRenderer engineLeft;
    ModelRenderer engineTipLeft;
    ModelRenderer wingLeft;
    ModelRenderer wingRight;
    ModelRenderer chassisDetailTop;
    ModelRenderer cannon;
    ModelRenderer cannonTip;
    ModelRenderer chassisNose1;
    ModelRenderer chassisNose2;
    ModelRenderer chassisNose3;
    ModelRenderer chassisBack;
    ModelRenderer cockpitTop;
    ModelRenderer cockpitRight;
    ModelRenderer cockpitLeft;
    ModelRenderer cockpitFront;
    ModelRenderer cockpitBack;
    ModelRenderer topFin;
  
  public ModelBasicShip()
  {
    textureWidth = 256;
    textureHeight = 256;
    
      chassisMain = new ModelRenderer(this, 0, 13);
      chassisMain.addBox(-8F, 8F, 13F, 16, 8, 9);
      chassisMain.setRotationPoint(0F, 0F, 0F);
      chassisMain.setTextureSize(64, 32);
      chassisMain.mirror = true;
      setRotation(chassisMain, 0F, 0F, 0F);
      chassisBody = new ModelRenderer(this, 0, 32);
      chassisBody.addBox(-10F, 7F, -12F, 20, 10, 26);
      chassisBody.setRotationPoint(0F, 0F, 0F);
      chassisBody.setTextureSize(64, 32);
      chassisBody.mirror = true;
      setRotation(chassisBody, 0F, 0F, 0F);
      engineRight = new ModelRenderer(this, 0, 70);
      engineRight.addBox(10F, 4F, -17F, 8, 8, 19);
      engineRight.setRotationPoint(0F, 0F, 0F);
      engineRight.setTextureSize(64, 32);
      engineRight.mirror = true;
      setRotation(engineRight, 0F, 0F, 0F);
      engineTipRight = new ModelRenderer(this, 26, 209);
      engineTipRight.addBox(9.5F, 3.5F, -18.06667F, 9, 9, 3);
      engineTipRight.setRotationPoint(0F, 0F, 0F);
      engineTipRight.setTextureSize(64, 32);
      engineTipRight.mirror = true;
      setRotation(engineTipRight, 0F, 0F, 0F);
      engineLeft = new ModelRenderer(this, 63, 70);
      engineLeft.addBox(-18F, 4F, -17F, 8, 8, 19);
      engineLeft.setRotationPoint(0F, 0F, 0F);
      engineLeft.setTextureSize(64, 32);
      engineLeft.mirror = true;
      setRotation(engineLeft, 0F, 0F, 0F);
      engineTipLeft = new ModelRenderer(this, 0, 209);
      engineTipLeft.addBox(-18.5F, 3.5F, -19F, 9, 9, 3);
      engineTipLeft.setRotationPoint(0F, 0F, 0F);
      engineTipLeft.setTextureSize(64, 32);
      engineTipLeft.mirror = true;
      setRotation(engineTipLeft, 0F, 0F, 0F);
      wingLeft = new ModelRenderer(this, 0, 102);
      wingLeft.addBox(-37F, 12F, -6F, 28, 1, 16);
      wingLeft.setRotationPoint(0F, 0F, 0F);
      wingLeft.setTextureSize(64, 32);
      wingLeft.mirror = true;
      setRotation(wingLeft, 0F, -0.122173F, 0F);
      wingRight = new ModelRenderer(this, 0, 121);
      wingRight.addBox(-37F, 12F, -10F, 28, 1, 16);
      wingRight.setRotationPoint(0F, 0F, 0F);
      wingRight.setTextureSize(64, 32);
      wingRight.mirror = true;
      setRotation(wingRight, 0F, -3.01942F, 0F);
      chassisDetailTop = new ModelRenderer(this, 98, 17);
      chassisDetailTop.addBox(-4F, 6.5F, 14F, 8, 2, 20);
      chassisDetailTop.setRotationPoint(0F, 0F, 0F);
      chassisDetailTop.setTextureSize(64, 32);
      chassisDetailTop.mirror = true;
      setRotation(chassisDetailTop, 0F, 0F, 0F);
      cannon = new ModelRenderer(this, 0, 140);
      cannon.addBox(-0.5F, 16F, 24F, 1, 1, 23);
      cannon.setRotationPoint(0F, 0F, 0F);
      cannon.setTextureSize(64, 32);
      cannon.mirror = true;
      setRotation(cannon, 0F, 0F, 0F);
      cannonTip = new ModelRenderer(this, 84, 0);
      cannonTip.addBox(-1.5F, 15.5F, 44F, 3, 2, 1);
      cannonTip.setRotationPoint(0F, 0F, 0F);
      cannonTip.setTextureSize(64, 32);
      cannonTip.mirror = true;
      setRotation(cannonTip, 0F, 0F, 0F);
      chassisNose1 = new ModelRenderer(this, 0, 165);
      chassisNose1.addBox(-7F, 8F, 22F, 14, 8, 8);
      chassisNose1.setRotationPoint(0F, 0F, 0F);
      chassisNose1.setTextureSize(64, 32);
      chassisNose1.mirror = true;
      setRotation(chassisNose1, 0F, 0F, 0F);
      chassisNose2 = new ModelRenderer(this, 0, 182);
      chassisNose2.addBox(-6F, 8.5F, 30F, 12, 7, 6);
      chassisNose2.setRotationPoint(0F, 0F, 0F);
      chassisNose2.setTextureSize(64, 32);
      chassisNose2.mirror = true;
      setRotation(chassisNose2, 0F, 0F, 0F);
      chassisNose3 = new ModelRenderer(this, 0, 196);
      chassisNose3.addBox(-5F, 9.5F, 36F, 10, 6, 5);
      chassisNose3.setRotationPoint(0F, 0F, 0F);
      chassisNose3.setTextureSize(64, 32);
      chassisNose3.mirror = true;
      setRotation(chassisNose3, 0F, 0F, 0F);
      chassisBack = new ModelRenderer(this, 100, 41);
      chassisBack.addBox(-9F, 0.5F, -12F, 18, 7, 8);
      chassisBack.setRotationPoint(0F, 0F, 0F);
      chassisBack.setTextureSize(64, 32);
      chassisBack.mirror = true;
      setRotation(chassisBack, 0F, 0F, 0F);
      cockpitTop = new ModelRenderer(this, 155, 0);
      cockpitTop.addBox(-6F, -2F, -4F, 12, 0, 14);
      cockpitTop.setRotationPoint(0F, 0F, 0F);
      cockpitTop.setTextureSize(64, 32);
      cockpitTop.mirror = true;
      setRotation(cockpitTop, 0F, 0F, 0F);
      cockpitRight = new ModelRenderer(this, 156, 16);
      cockpitRight.addBox(6F, -2F, -4F, 0, 9, 14);
      cockpitRight.setRotationPoint(0F, 0F, 0F);
      cockpitRight.setTextureSize(64, 32);
      cockpitRight.mirror = true;
      setRotation(cockpitRight, 0F, 0F, 0F);
      cockpitLeft = new ModelRenderer(this, 186, 16);
      cockpitLeft.addBox(-6F, -2F, -4F, 0, 9, 14);
      cockpitLeft.setRotationPoint(0F, 0F, 0F);
      cockpitLeft.setTextureSize(64, 32);
      cockpitLeft.mirror = true;
      setRotation(cockpitLeft, 0F, 0F, 0F);
      cockpitFront = new ModelRenderer(this, 155, 47);
      cockpitFront.addBox(-6F, -2F, 10F, 12, 9, 0);
      cockpitFront.setRotationPoint(0F, 0F, 0F);
      cockpitFront.setTextureSize(64, 32);
      cockpitFront.mirror = true;
      setRotation(cockpitFront, 0F, 0F, 0F);
      cockpitBack = new ModelRenderer(this, 181, 47);
      cockpitBack.addBox(-6F, -2F, -4F, 12, 9, 0);
      cockpitBack.setRotationPoint(0F, 0F, 0F);
      cockpitBack.setTextureSize(64, 32);
      cockpitBack.mirror = true;
      setRotation(cockpitBack, 0F, 0F, 0F);
      topFin = new ModelRenderer(this, 47, 165);
      topFin.addBox(-0.5F, -13F, -12F, 1, 12, 8);
      topFin.setRotationPoint(0F, 0F, 0F);
      topFin.setTextureSize(64, 32);
      topFin.mirror = true;
      setRotation(topFin, 0.4363323F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    chassisMain.render(f5);
    chassisBody.render(f5);
    engineRight.render(f5);
    engineTipRight.render(f5);
    engineLeft.render(f5);
    engineTipLeft.render(f5);
    wingLeft.render(f5);
    wingRight.render(f5);
    chassisDetailTop.render(f5);
    cannon.render(f5);
    cannonTip.render(f5);
    chassisNose1.render(f5);
    chassisNose2.render(f5);
    chassisNose3.render(f5);
    chassisBack.render(f5);
    cockpitTop.render(f5);
    cockpitRight.render(f5);
    cockpitLeft.render(f5);
    GL11.glEnable(GL11.GL_BLEND);
    cockpitFront.render(f5);
    GL11.glDisable(GL11.GL_BLEND);
    cockpitBack.render(f5);
    topFin.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}
