package com.the_salsa.spacemod;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBlasterRifle extends ModelBase
{
	//fields
    ModelRenderer barrel;
    ModelRenderer barrelCover;
    ModelRenderer reciever;
    ModelRenderer grip;
    ModelRenderer scopeMount1;
    ModelRenderer scopeMount2;
    ModelRenderer scope;
    ModelRenderer scopeLens;
    ModelRenderer stock1;
    ModelRenderer stock2;
    ModelRenderer triggerRing1;
    ModelRenderer triggerRing2;
    ModelRenderer stock3;
    ModelRenderer scopeLens2;
  
    public ModelBlasterRifle()
    {
    	textureWidth = 64;
    	textureHeight = 32;
    
    	barrel = new ModelRenderer(this, 0, 18);
    	barrel.addBox(0F, 0F, 0F, 1, 1, 13);
    	barrel.setRotationPoint(0F, 0.1F, -1F);
    	barrel.setTextureSize(64, 32);
    	barrel.mirror = true;
    	setRotation(barrel, 0F, 0F, 0F);
    	
    	barrelCover = new ModelRenderer(this, 28, 24);
    	barrelCover.addBox(0F, 0F, 0F, 2, 2, 6);
    	barrelCover.setRotationPoint(-0.5F, -0.4F, 0F);
    	barrelCover.setTextureSize(64, 32);
    	barrelCover.mirror = true;
    	setRotation(barrelCover, 0F, 0F, 0F);
    	
    	reciever = new ModelRenderer(this, 28, 19);
    	reciever.addBox(0F, 0F, 0F, 2, 2, 3);
    	reciever.setRotationPoint(-0.5F, 0F, 6F);
    	reciever.setTextureSize(64, 32);
    	reciever.mirror = true;
    	setRotation(reciever, 0F, 0F, 0F);
    	
    	grip = new ModelRenderer(this, 28, 15);
    	grip.addBox(0F, 0F, 0F, 2, 3, 1);
    	grip.setRotationPoint(-0.5F, 2F, 8F);
    	grip.setTextureSize(64, 32);
    	grip.mirror = true;
    	setRotation(grip, 0.5235988F, 0F, 0F);
    	
    	scopeMount1 = new ModelRenderer(this, 26, 0);
    	scopeMount1.addBox(0F, 0F, 0F, 1, 1, 1);
    	scopeMount1.setRotationPoint(0F, -1F, 0F);
    	scopeMount1.setTextureSize(64, 32);
    	scopeMount1.mirror = true;
    	setRotation(scopeMount1, 0F, 0F, 0F);
    	
    	scopeMount2 = new ModelRenderer(this, 22, 0);
    	scopeMount2.addBox(0F, 0F, 0F, 1, 1, 1);
    	scopeMount2.setRotationPoint(0F, -1F, 7F);
    	scopeMount2.setTextureSize(64, 32);
    	scopeMount2.mirror = true;
    	setRotation(scopeMount2, 0F, 0F, 0F);
    	
    	scope = new ModelRenderer(this, 0, 7);
    	scope.addBox(0F, 0F, 0F, 1, 1, 3);
    	scope.setRotationPoint(0F, -2F, 6F);
    	scope.setTextureSize(64, 32);
    	scope.mirror = true;
    	setRotation(scope, 0F, 0F, 0F);
    	
    	scopeLens = new ModelRenderer(this, 18, 0);
    	scopeLens.addBox(0F, 0F, 0F, 1, 1, 0);
    	scopeLens.setRotationPoint(0F, -2F, 9F);
    	scopeLens.setTextureSize(64, 32);
    	scopeLens.mirror = true;
    	setRotation(scopeLens, 0F, 0F, 0F);
    	
    	stock1 = new ModelRenderer(this, 8, 7);
      	stock1.addBox(0F, 0F, 0F, 1, 3, 1);
      	stock1.setRotationPoint(0F, 0.1F, 12F);
      	stock1.setTextureSize(64, 32);
      	stock1.mirror = true;
      	setRotation(stock1, 0F, 0F, 0F);
      	
      	stock2 = new ModelRenderer(this, 7, 0);
      	stock2.addBox(0F, 0F, 0F, 1, 4, 1);
      	stock2.setRotationPoint(0F, 1F, 8.9F);
      	stock2.setTextureSize(64, 32);
      	stock2.mirror = true;
      	setRotation(stock2, 1.047198F, 0F, 0F);
      	
      	triggerRing1 = new ModelRenderer(this, 30, 0);
      	triggerRing1.addBox(0F, 0F, 0F, 1, 1, 0);
      	triggerRing1.setRotationPoint(0F, 2F, 7F);
      	triggerRing1.setTextureSize(64, 32);
      	triggerRing1.mirror = true;
      	setRotation(triggerRing1, 0F, 0F, 0F);
      	
      	triggerRing2 = new ModelRenderer(this, 32, 0);
      	triggerRing2.addBox(0F, 0F, 0F, 1, 0, 2);
      	triggerRing2.setRotationPoint(0F, 3F, 7F);
      	triggerRing2.setTextureSize(64, 32);
      	triggerRing2.mirror = true;
      	setRotation(triggerRing2, 0F, 0F, 0F);
      	
      	stock3 = new ModelRenderer(this, 11, 0);
      	stock3.addBox(0F, 0F, 0F, 2, 1, 1);
      	stock3.setRotationPoint(1F, 0.5F, 7F);
      	stock3.setTextureSize(64, 32);
      	stock3.mirror = true;
      	setRotation(stock3, 0F, 0F, 0F);
      	
      	scopeLens2 = new ModelRenderer(this, 18, 0);
      	scopeLens2.addBox(0F, 0F, 0F, 1, 1, 0);
      	scopeLens2.setRotationPoint(0F, -2F, 6F);
      	scopeLens2.setTextureSize(64, 32);
      	scopeLens2.mirror = true;
      	setRotation(scopeLens2, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
	  super.render(entity, f, f1, f2, f3, f4, f5);
	  setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	  barrel.render(f5);
	  barrelCover.render(f5);
	  reciever.render(f5);
	  grip.render(f5);
	  scopeMount1.render(f5);
	  GL11.glEnable(GL11.GL_BLEND);
	  scope.render(f5);
	  scopeLens.render(f5);
	  scopeLens2.render(f5);
	  GL11.glDisable(GL11.GL_BLEND);
	  stock1.render(f5);
	  stock2.render(f5);
	  triggerRing1.render(f5);
	  triggerRing2.render(f5);
	  stock3.render(f5);
	  GL11.glScalef(0.6F, 1F, 0.9F);
	  scopeMount2.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
	  model.rotateAngleX = x;
	  model.rotateAngleY = y;
	  model.rotateAngleZ = z;
  }
}
