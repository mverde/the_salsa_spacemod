package com.the_salsa.spacemod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBlasterPistol extends ModelBase
{
  //fields
    ModelRenderer barrel;
    ModelRenderer barrelTip;
    ModelRenderer gasCanister;
    ModelRenderer chamber;
    ModelRenderer grip;
    ModelRenderer hammer;
    ModelRenderer sight;
    ModelRenderer barrelNut;
  
  public ModelBlasterPistol()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      barrel = new ModelRenderer(this, 0, 21);
      barrel.addBox(0F, 0F, 0F, 1, 1, 10);
      barrel.setRotationPoint(0F, 0.1F, 0F);
      barrel.setTextureSize(64, 32);
      barrel.mirror = true;
      setRotation(barrel, 0F, 0F, 0F);
      barrelTip = new ModelRenderer(this, 12, 12);
      barrelTip.addBox(0F, 0F, 0F, 2, 2, 1);
      barrelTip.setRotationPoint(-0.5F, -0.4F, 0F);
      barrelTip.setTextureSize(64, 32);
      barrelTip.mirror = true;
      setRotation(barrelTip, 0F, 0F, 0F);
      gasCanister = new ModelRenderer(this, 0, 15);
      gasCanister.addBox(0F, 0F, 0F, 2, 4, 2);
      gasCanister.setRotationPoint(-0.5F, 0F, 6F);
      gasCanister.setTextureSize(64, 32);
      gasCanister.mirror = true;
      setRotation(gasCanister, 0F, 0F, 0F);
      chamber = new ModelRenderer(this, 8, 15);
      chamber.addBox(0F, 0F, 0F, 2, 3, 3);
      chamber.setRotationPoint(-0.5F, 0F, 8F);
      chamber.setTextureSize(64, 32);
      chamber.mirror = true;
      setRotation(chamber, 0F, 0F, 0F);
      grip = new ModelRenderer(this, 0, 9);
      grip.addBox(0F, 0F, 0F, 2, 4, 2);
      grip.setRotationPoint(-0.5F, 3F, 9.1F);
      grip.setTextureSize(64, 32);
      grip.mirror = true;
      setRotation(grip, 0.3490659F, 0F, 0F);
      hammer = new ModelRenderer(this, 8, 11);
      hammer.addBox(0F, 0F, 0F, 1, 1, 1);
      hammer.setRotationPoint(0F, 0.3F, 11F);
      hammer.setTextureSize(64, 32);
      hammer.mirror = true;
      setRotation(hammer, 0F, 0F, 0F);
      sight = new ModelRenderer(this, 8, 13);
      sight.addBox(0F, 0F, 0F, 1, 1, 1);
      sight.setRotationPoint(0F, -0.5F, 9F);
      sight.setTextureSize(64, 32);
      sight.mirror = true;
      setRotation(sight, 0F, 0F, 0F);
      barrelNut = new ModelRenderer(this, 8, 9);
      barrelNut.addBox(0F, 0F, 0F, 1, 1, 1);
      barrelNut.setRotationPoint(0.5F, -0.1F, 0.7F);
      barrelNut.setTextureSize(64, 32);
      barrelNut.mirror = true;
      setRotation(barrelNut, 0F, 0F, 0.7853982F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    barrel.render(f5);
    barrelTip.render(f5);
    gasCanister.render(f5);
    chamber.render(f5);
    grip.render(f5);
    hammer.render(f5);
    sight.render(f5);
    barrelNut.render(f5);
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