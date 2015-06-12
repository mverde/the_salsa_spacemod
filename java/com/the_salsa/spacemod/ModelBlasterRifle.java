package com.the_salsa.spacemod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

public class ModelBlasterRifle extends ModelBase
{
//fields
 ModelRenderer barrel;
 ModelRenderer barrelCover;
 ModelRenderer gasChamber;
 ModelRenderer reciever;
 ModelRenderer grip;
 ModelRenderer scopeMount1;
 ModelRenderer scopeMount2;
 ModelRenderer scope;
 ModelRenderer scopeRim;
 ModelRenderer scopeLens;
 ModelRenderer stock1;
 ModelRenderer stock2;

public ModelBlasterRifle()
{
 textureWidth = 64;
 textureHeight = 32;
 
   barrel = new ModelRenderer(this, 0, 17);
   barrel.addBox(0F, 0F, 0F, 1, 1, 14);
   barrel.setRotationPoint(0F, 0.1F, -2F);
   barrel.setTextureSize(64, 32);
   barrel.mirror = true;
   setRotation(barrel, 0F, 0F, 0F);
   barrelCover = new ModelRenderer(this, 0, 11);
   barrelCover.addBox(0F, 0F, 0F, 2, 2, 4);
   barrelCover.setRotationPoint(-0.5F, -0.4F, 0F);
   barrelCover.setTextureSize(64, 32);
   barrelCover.mirror = true;
   setRotation(barrelCover, 0F, 0F, 0F);
   gasChamber = new ModelRenderer(this, 12, 12);
   gasChamber.addBox(0F, 0F, 0F, 2, 3, 2);
   gasChamber.setRotationPoint(0F, 0F, 4F);
   gasChamber.setTextureSize(64, 32);
   gasChamber.mirror = true;
   setRotation(gasChamber, 0F, 0F, 0F);
   reciever = new ModelRenderer(this, 20, 12);
   reciever.addBox(0F, 0F, 0F, 2, 2, 3);
   reciever.setRotationPoint(-0.5F, 0F, 6F);
   reciever.setTextureSize(64, 32);
   reciever.mirror = true;
   setRotation(reciever, 0F, 0F, 0F);
   grip = new ModelRenderer(this, 22, 7);
   grip.addBox(0F, 0F, 0F, 2, 3, 2);
   grip.setRotationPoint(-0.5F, 2F, 7F);
   grip.setTextureSize(64, 32);
   grip.mirror = true;
   setRotation(grip, 0.5235988F, 0F, 0F);
   scopeMount1 = new ModelRenderer(this, 26, 0);
   scopeMount1.addBox(0F, 0F, 0F, 1, 1, 1);
   scopeMount1.setRotationPoint(0F, -1F, 6F);
   scopeMount1.setTextureSize(64, 32);
   scopeMount1.mirror = true;
   setRotation(scopeMount1, 0F, 0F, 0F);
   scopeMount2 = new ModelRenderer(this, 22, 0);
   scopeMount2.addBox(0F, 0F, 0F, 1, 1, 1);
   scopeMount2.setRotationPoint(0F, -1F, 8F);
   scopeMount2.setTextureSize(64, 32);
   scopeMount2.mirror = true;
   setRotation(scopeMount2, 0F, 0F, 0F);
   scope = new ModelRenderer(this, 0, 0);
   scope.addBox(0F, 0F, 0F, 1, 1, 5);
   scope.setRotationPoint(0F, -2F, 4F);
   scope.setTextureSize(64, 32);
   scope.mirror = true;
   setRotation(scope, 0F, 0F, 0F);
   scopeRim = new ModelRenderer(this, 16, 9);
   scopeRim.addBox(0F, 0F, 0F, 2, 2, 1);
   scopeRim.setRotationPoint(-0.5F, -2.5F, 9F);
   scopeRim.setTextureSize(64, 32);
   scopeRim.mirror = true;
   setRotation(scopeRim, 0F, 0F, 0F);
   scopeLens = new ModelRenderer(this, 17, 0);
   scopeLens.addBox(0F, 0F, 0F, 1, 1, 1);
   scopeLens.setRotationPoint(0F, -2F, 9.1F);
   scopeLens.setTextureSize(64, 32);
   scopeLens.mirror = true;
   setRotation(scopeLens, 0F, 0F, 0F);
   stock1 = new ModelRenderer(this, 12, 8);
   stock1.addBox(0F, 0F, 0F, 1, 3, 1);
   stock1.setRotationPoint(0F, 0.1F, 12F);
   stock1.setTextureSize(64, 32);
   stock1.mirror = true;
   setRotation(stock1, 0F, 0F, 0F);
   stock2 = new ModelRenderer(this, 12, 0);
   stock2.addBox(0F, 0F, 0F, 1, 1, 1);
   stock2.setRotationPoint(0F, 1.1F, 11F);
   stock2.setTextureSize(64, 32);
   stock2.mirror = true;
   setRotation(stock2, 0F, 0F, 0F);
}

public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
{
 super.render(entity, f, f1, f2, f3, f4, f5);
 setRotationAngles(f, f1, f2, f3, f4, f5, entity);
 barrel.render(f5);
 barrelCover.render(f5);
 gasChamber.render(f5);
 reciever.render(f5);
 grip.render(f5);
 scopeMount1.render(f5);
 scopeMount2.render(f5);
 GL11.glEnable(GL11.GL_BLEND);
 scope.render(f5);
 scopeRim.render(f5);
 scopeLens.render(f5);
 GL11.glDisable(GL11.GL_BLEND);
 stock1.render(f5);
 stock2.render(f5);
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
