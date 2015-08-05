package com.martin.firstmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

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
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    boltOuter.render(f5);
    boltInner.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}
