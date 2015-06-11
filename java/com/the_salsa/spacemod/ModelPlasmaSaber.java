package com.the_salsa.spacemod;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPlasmaSaber extends ModelBase
{
  //fields
    ModelRenderer hilt1;
    ModelRenderer hilt2;
    ModelRenderer hiltThin;
    ModelRenderer hiltSquare1;
    ModelRenderer crossGuard;
    ModelRenderer bladeInner;
    ModelRenderer bladeOuter;
    ModelRenderer hiltSquare2;
  
  public ModelPlasmaSaber()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      hilt1 = new ModelRenderer(this, 16, 21);
      hilt1.addBox(0F, 0F, 0F, 2, 9, 2);
      hilt1.setRotationPoint(0F, 5F, 0.2F);
      hilt1.setTextureSize(64, 32);
      hilt1.mirror = true;
      setRotation(hilt1, 0F, 0F, 0F);
      hilt2 = new ModelRenderer(this, 8, 21);
      hilt2.addBox(0F, 0F, 0F, 2, 9, 2);
      hilt2.setRotationPoint(1F, 5F, -0.2F);
      hilt2.setTextureSize(64, 32);
      hilt2.mirror = true;
      setRotation(hilt2, 0F, -0.7853982F, 0F);
      hiltThin = new ModelRenderer(this, 12, 3);
      hiltThin.addBox(0F, 0F, 0F, 1, 3, 1);
      hiltThin.setRotationPoint(0.5F, 2F, 0.7F);
      hiltThin.setTextureSize(64, 32);
      hiltThin.mirror = true;
      setRotation(hiltThin, 0F, 0F, 0F);
      hiltSquare1 = new ModelRenderer(this, 0, 0);
      hiltSquare1.addBox(0F, 0F, 0F, 2, 1, 2);
      hiltSquare1.setRotationPoint(0F, 3.5F, 0.2F);
      hiltSquare1.setTextureSize(64, 32);
      hiltSquare1.mirror = true;
      setRotation(hiltSquare1, 0F, 0F, 0F);
      crossGuard = new ModelRenderer(this, 16, 0);
      crossGuard.addBox(0F, 0F, 0F, 3, 1, 3);
      crossGuard.setRotationPoint(1F, 1F, -0.9F);
      crossGuard.setTextureSize(64, 32);
      crossGuard.mirror = true;
      setRotation(crossGuard, 0F, -0.7853982F, 0F);
      bladeInner = new ModelRenderer(this, 24, 7);
      bladeInner.addBox(0F, 0F, 0F, 1, 24, 1);
      bladeInner.setRotationPoint(0.5F, -23F, 0.7F);
      bladeInner.setTextureSize(64, 32);
      bladeInner.mirror = true;
      setRotation(bladeInner, 0F, 0F, 0F);
      bladeOuter = new ModelRenderer(this, 0, 5);
      bladeOuter.addBox(0F, 0F, 0F, 2, 25, 2);
      bladeOuter.setRotationPoint(0.05F, -24F, 0.2F);
      bladeOuter.setTextureSize(64, 32);
      bladeOuter.mirror = true;
      setRotation(bladeOuter, 0F, 0F, 0F);
      hiltSquare2 = new ModelRenderer(this, 8, 0);
      hiltSquare2.addBox(0F, 0F, 0F, 2, 1, 2);
      hiltSquare2.setRotationPoint(0F, 2F, 0.2F);
      hiltSquare2.setTextureSize(64, 32);
      hiltSquare2.mirror = true;
      setRotation(hiltSquare2, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    hilt1.render(f5);
    hilt2.render(f5);
    hiltThin.render(f5);
    hiltSquare1.render(f5);
    crossGuard.render(f5);
    GL11.glEnable(GL11.GL_BLEND);
    bladeInner.render(f5);
    bladeOuter.render(f5);
    GL11.glDisable(GL11.GL_BLEND);
    hiltSquare2.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
