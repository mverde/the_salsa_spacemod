package com.the_salsa.spacemod;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRocketLauncher extends ModelBase
{
	//fields
    ModelRenderer grip;
    ModelRenderer scopeMount1;
    ModelRenderer scope;
    ModelRenderer scopeLens;
    ModelRenderer triggerRing1;
    ModelRenderer triggerRing2;
    ModelRenderer gasCanister;
    ModelRenderer scopeLens2;
    ModelRenderer barrel1;
    ModelRenderer barrel2;
    ModelRenderer barrel3;
    ModelRenderer barrel4;
    ModelRenderer barrelBack;
  
    public ModelRocketLauncher()
    {
    	textureWidth = 64;
    	textureHeight = 32;
    
    	grip = new ModelRenderer(this, 0, 16);
    	grip.addBox(0F, 0F, 0F, 2, 4, 1);
    	grip.setRotationPoint(-0.5F, 2F, 7F);
    	grip.setTextureSize(64, 32);
    	grip.mirror = true;
    	setRotation(grip, 0.5235988F, 0F, 0F);
    	
    	scopeMount1 = new ModelRenderer(this, 6, 17);
    	scopeMount1.addBox(0F, 0F, 0F, 1, 1, 1);
    	scopeMount1.setRotationPoint(2.5F, 0F, 5F);
    	scopeMount1.setTextureSize(64, 32);
    	scopeMount1.mirror = true;
    	setRotation(scopeMount1, 0F, 0F, 0F);
    	
    	scope = new ModelRenderer(this, 0, 21);
    	scope.addBox(0F, 0F, 0F, 1, 1, 3);
    	scope.setRotationPoint(3.5F, 0F, 4F);
    	scope.setTextureSize(64, 32);
    	scope.mirror = true;
    	setRotation(scope, 0F, 0F, 0F);
    	
    	scopeLens = new ModelRenderer(this, 10, 17);
    	scopeLens.addBox(0F, 0F, 0F, 1, 1, 0);
    	scopeLens.setRotationPoint(3.5F, 0F, 7F);
    	scopeLens.setTextureSize(64, 32);
    	scopeLens.mirror = true;
    	setRotation(scopeLens, 0F, 0F, 0F);
    	
    	triggerRing1 = new ModelRenderer(this, 10, 18);
    	triggerRing1.addBox(0F, 0F, 0F, 1, 1, 0);
    	triggerRing1.setRotationPoint(0F, 2.5F, 6F);
    	triggerRing1.setTextureSize(64, 32);
    	triggerRing1.mirror = true;
    	setRotation(triggerRing1, 0F, 0F, 0F);
    	
    	triggerRing2 = new ModelRenderer(this, 14, 17);
    	triggerRing2.addBox(0F, 0F, 0F, 1, 0, 2);
    	triggerRing2.setRotationPoint(0F, 3.5F, 6F);
    	triggerRing2.setTextureSize(64, 32);
    	triggerRing2.mirror = true;
    	setRotation(triggerRing2, 0F, 0F, 0F);
    	
    	gasCanister = new ModelRenderer(this, 0, 25);
    	gasCanister.addBox(0F, 0F, 0F, 2, 1, 1);
    	gasCanister.setRotationPoint(1F, 2.5F, 4F);
    	gasCanister.setTextureSize(64, 32);
    	gasCanister.mirror = true;
    	setRotation(gasCanister, 0F, 0F, 1.570796F);
    	
    	scopeLens2 = new ModelRenderer(this, 12, 17);
    	scopeLens2.addBox(0F, 0F, 0F, 1, 1, 0);
    	scopeLens2.setRotationPoint(3.5F, 0F, 4F);
    	scopeLens2.setTextureSize(64, 32);
    	scopeLens2.mirror = true;
    	setRotation(scopeLens2, 0F, 0F, 0F);
    	
    	barrel1 = new ModelRenderer(this, 34, 0);
    	barrel1.addBox(0F, 0F, 0F, 2, 1, 13);
    	barrel1.setRotationPoint(-0.5F, -1.5F, -1F);
    	barrel1.setTextureSize(64, 32);
    	barrel1.mirror = true;
    	setRotation(barrel1, 0F, 0F, 0F);
    	
    	barrel2 = new ModelRenderer(this, 0, 0);
    	barrel2.addBox(0F, 0F, 0F, 1, 2, 13);
    	barrel2.setRotationPoint(1.5F, -0.5F, -1F);
    	barrel2.setTextureSize(64, 32);
    	barrel2.mirror = true;
    	setRotation(barrel2, 0F, 0F, 0F);
    	
    	barrel3 = new ModelRenderer(this, 34, 0);
    	barrel3.addBox(0F, 0F, 0F, 2, 1, 13);
    	barrel3.setRotationPoint(-0.5F, 1.5F, -1F);
    	barrel3.setTextureSize(64, 32);
    	barrel3.mirror = true;
    	setRotation(barrel3, 0F, 0F, 0F);
    	
    	barrel4 = new ModelRenderer(this, 0, 0);
    	barrel4.addBox(0F, 0F, 0F, 1, 2, 13);
    	barrel4.setRotationPoint(-1.5F, -0.5F, -1F);
    	barrel4.setTextureSize(64, 32);
    	barrel4.mirror = true;
    	setRotation(barrel4, 0F, 0F, 0F);
    	
    	barrelBack = new ModelRenderer(this, 0, 0);
    	barrelBack.addBox(0F, 0F, 0F, 2, 2, 1);
    	barrelBack.setRotationPoint(-0.5F, -0.5F, 11F);
    	barrelBack.setTextureSize(64, 32);
    	barrelBack.mirror = true;
    	setRotation(barrelBack, 0F, 0F, 0F);
    }	
  
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
    	setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    	grip.render(f5);
    	scopeMount1.render(f5);
    	scope.render(f5);
    	//GL11.glEnable(GL11.GL_BLEND);
    	scopeLens.render(f5);
    	scopeLens2.render(f5);
    	//GL11.glDisable(GL11.GL_BLEND);
    	triggerRing1.render(f5);
    	triggerRing2.render(f5);
    	gasCanister.render(f5);
    	barrel1.render(f5);
    	barrel2.render(f5);
    	barrel3.render(f5);
    	barrel4.render(f5);
    	barrelBack.render(f5);
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
