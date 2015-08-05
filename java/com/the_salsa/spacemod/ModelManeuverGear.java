package com.the_salsa.spacemod;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelManeuverGear extends ModelBiped
{
	//fields
	ModelRenderer tankLeft;
	ModelRenderer tankRight;
	ModelRenderer foilLeft;
	ModelRenderer foilRight;
	ModelRenderer thrusterLeft;
	ModelRenderer thrusterRight;
	ModelRenderer nozzleLeftUp;
	ModelRenderer nozzleLeftDown;
	ModelRenderer nozzleLeftLeft;
	ModelRenderer nozzleLeftForward;
	ModelRenderer nozzleLeftBack;
	ModelRenderer nozzleRightRight;
	ModelRenderer nozzleRightUp;
	ModelRenderer nozzleRightBack;
	ModelRenderer nozzleRightDown;
	ModelRenderer nozzleRightForward;
	ModelRenderer tankTopLeft;
	ModelRenderer tankTopRioht;
	ModelRenderer tankConnectorLeft;
	ModelRenderer tankHose;
	ModelRenderer tankConnectorRight;
	ModelRenderer foilConnectorLeft;
	ModelRenderer foilConnectorRight;
	ModelRenderer foilBoxShapeLeft;
	ModelRenderer foilBoxShapeRight;

	public ModelManeuverGear(float scale)
	{
		super(scale, 0, 64, 64);
		
	    tankLeft = new ModelRenderer(this, 35, 50);
	    tankLeft.addBox(0.5F, 2F, 2F, 3, 6, 4);
	    tankLeft.setRotationPoint(0F, 0F, 0F);
	    tankLeft.setTextureSize(64, 64);
	    tankLeft.mirror = true;
	    setRotation(tankLeft, 0F, 0F, 0F);
	    
	    tankRight = new ModelRenderer(this, 50, 50);
	    tankRight.addBox(-3.5F, 2F, 2F, 3, 6, 4);
	    tankRight.setRotationPoint(0F, 0F, 0F);
	    tankRight.setTextureSize(64, 64);
	    tankRight.mirror = true;
	    setRotation(tankRight, 0F, 0F, 0F);
	    
	    foilLeft = new ModelRenderer(this, 35, 46);
	    foilLeft.addBox(3.5F, 2F, 4F, 6, 2, 1);
	    foilLeft.setRotationPoint(0F, 0F, 0F);
	    foilLeft.setTextureSize(64, 64);
	    foilLeft.mirror = true;
	    setRotation(foilLeft, 0F, 0F, 0F);
	    
	    foilRight = new ModelRenderer(this, 50, 46);
	    foilRight.addBox(-9.5F, 2F, 4F, 6, 2, 1);
	    foilRight.setRotationPoint(0F, 0F, 0F);
	    foilRight.setTextureSize(64, 64);
	    foilRight.mirror = true;
	    setRotation(foilRight, 0F, 0F, 0F);
	    
	    thrusterLeft = new ModelRenderer(this, 56, 21);
	    thrusterLeft.addBox(9.5F, 1.5F, 3.5F, 2, 3, 2);
	    thrusterLeft.setRotationPoint(0F, 0F, 0F);
	    thrusterLeft.setTextureSize(64, 64);
	    thrusterLeft.mirror = true;
	    setRotation(thrusterLeft, 0F, 0F, 0F);
	    
	    thrusterRight = new ModelRenderer(this, 50, 15);
	    thrusterRight.addBox(-11.5F, 1.5F, 3.5F, 2, 3, 2);
	    thrusterRight.setRotationPoint(0F, 0F, 0F);
	    thrusterRight.setTextureSize(64, 64);
	    thrusterRight.mirror = true;
	    setRotation(thrusterRight, 0F, 0F, 0F);
	    
	    nozzleLeftUp = new ModelRenderer(this, 44, 15);
	    nozzleLeftUp.addBox(10F, 1F, 4F, 1, 1, 1);
	    nozzleLeftUp.setRotationPoint(0F, 0F, 0F);
	    nozzleLeftUp.setTextureSize(64, 64);
	    nozzleLeftUp.mirror = true;
	    setRotation(nozzleLeftUp, 0F, 0F, 0F);
	    
	    nozzleLeftDown = new ModelRenderer(this, 44, 18);
	    nozzleLeftDown.addBox(10F, 4F, 4F, 1, 1, 1);
	    nozzleLeftDown.setRotationPoint(0F, 0F, 0F);
	    nozzleLeftDown.setTextureSize(64, 64);
	    nozzleLeftDown.mirror = true;
	    setRotation(nozzleLeftDown, 0F, 0F, 0F);
	    
	    nozzleLeftLeft = new ModelRenderer(this, 35, 12);
	    nozzleLeftLeft.addBox(11F, 2.5F, 4F, 1, 1, 1);
	    nozzleLeftLeft.setRotationPoint(0F, 0F, 0F);
	    nozzleLeftLeft.setTextureSize(64, 64);
	    nozzleLeftLeft.mirror = true;
	    setRotation(nozzleLeftLeft, 0F, 0F, 0F);
	    
	    nozzleLeftForward = new ModelRenderer(this, 40, 12);
	    nozzleLeftForward.addBox(10F, 2.5F, 3F, 1, 1, 1);
	    nozzleLeftForward.setRotationPoint(0F, 0F, 0F);
	    nozzleLeftForward.setTextureSize(64, 64);
	    nozzleLeftForward.mirror = true;
	    setRotation(nozzleLeftForward, 0F, 0F, 0F);
	    
	    nozzleLeftBack = new ModelRenderer(this, 45, 12);
	    nozzleLeftBack.addBox(10F, 2.5F, 5F, 1, 1, 1);
	    nozzleLeftBack.setRotationPoint(0F, 0F, 0F);
	    nozzleLeftBack.setTextureSize(64, 64);
	    nozzleLeftBack.mirror = true;
	    setRotation(nozzleLeftBack, 0F, 0F, 0F);
	    
	    nozzleRightRight = new ModelRenderer(this, 50, 12);
	    nozzleRightRight.addBox(-12F, 2.5F, 4F, 1, 1, 1);
	    nozzleRightRight.setRotationPoint(0F, 0F, 0F);
	    nozzleRightRight.setTextureSize(64, 64);
	    nozzleRightRight.mirror = true;
	    setRotation(nozzleRightRight, 0F, 0F, 0F);
	    
	    nozzleRightUp = new ModelRenderer(this, 59, 15);
	    nozzleRightUp.addBox(-11F, 1F, 4F, 1, 1, 1);
	    nozzleRightUp.setRotationPoint(0F, 0F, 0F);
	    nozzleRightUp.setTextureSize(64, 64);
	    nozzleRightUp.mirror = true;
	    setRotation(nozzleRightUp, 0F, 0F, 0F);
	    
	    nozzleRightBack = new ModelRenderer(this, 60, 12);
	    nozzleRightBack.addBox(-11F, 2.5F, 5F, 1, 1, 1);
	    nozzleRightBack.setRotationPoint(0F, 0F, 0F);
	    nozzleRightBack.setTextureSize(64, 64);
	    nozzleRightBack.mirror = true;
	    setRotation(nozzleRightBack, 0F, 0F, 0F);
	    
	    nozzleRightDown = new ModelRenderer(this, 59, 18);
	    nozzleRightDown.addBox(-11F, 4F, 4F, 1, 1, 1);
	    nozzleRightDown.setRotationPoint(0F, 0F, 0F);
	    nozzleRightDown.setTextureSize(64, 64);
	    nozzleRightDown.mirror = true;
	    setRotation(nozzleRightDown, 0F, 0F, 0F);
	    
	    nozzleRightForward = new ModelRenderer(this, 55, 12);
	    nozzleRightForward.addBox(-11F, 2.5F, 3F, 1, 1, 1);
	    nozzleRightForward.setRotationPoint(0F, 0F, 0F);
	    nozzleRightForward.setTextureSize(64, 64);
	    nozzleRightForward.mirror = true;
	    setRotation(nozzleRightForward, 0F, 0F, 0F);
	    
	    tankTopLeft = new ModelRenderer(this, 35, 61);
	    tankTopLeft.addBox(1F, 1.5F, 3F, 2, 1, 2);
	    tankTopLeft.setRotationPoint(0F, 0F, 0F);
	    tankTopLeft.setTextureSize(64, 64);
	    tankTopLeft.mirror = true;
	    setRotation(tankTopLeft, 0F, 0F, 0F);
	    
	    tankTopRioht = new ModelRenderer(this, 50, 61);
	    tankTopRioht.addBox(-3F, 1.5F, 3F, 2, 1, 2);
	    tankTopRioht.setRotationPoint(0F, 0F, 0F);
	    tankTopRioht.setTextureSize(64, 64);
	    tankTopRioht.mirror = true;
	    setRotation(tankTopRioht, 0F, 0F, 0F);
	    
	    tankConnectorLeft = new ModelRenderer(this, 50, 9);
	    tankConnectorLeft.addBox(1.5F, 0.5F, 4F, 1, 1, 1);
	    tankConnectorLeft.setRotationPoint(0F, 0F, 0F);
	    tankConnectorLeft.setTextureSize(64, 64);
	    tankConnectorLeft.mirror = true;
	    setRotation(tankConnectorLeft, 0F, 0F, 0F);
	    
	    tankHose = new ModelRenderer(this, 35, 6);
	    tankHose.addBox(-5F, 0F, 4F, 10, 1, 1);
	    tankHose.setRotationPoint(0F, 0F, 0F);
	    tankHose.setTextureSize(64, 64);
	    tankHose.mirror = true;
	    setRotation(tankHose, 0F, 0F, 0F);
	    
	    tankConnectorRight = new ModelRenderer(this, 45, 9);
	    tankConnectorRight.addBox(-2.5F, 0.5F, 4F, 1, 1, 1);
	    tankConnectorRight.setRotationPoint(0F, 0F, 0F);
	    tankConnectorRight.setTextureSize(64, 64);
	    tankConnectorRight.mirror = true;
	    setRotation(tankConnectorRight, 0F, 0F, 0F);
	    
	    foilConnectorLeft = new ModelRenderer(this, 35, 9);
	    foilConnectorLeft.addBox(4.3F, 1F, 4F, 1, 1, 1);
	    foilConnectorLeft.setRotationPoint(0F, 0F, 0F);
	    foilConnectorLeft.setTextureSize(64, 64);
	    foilConnectorLeft.mirror = true;
	    setRotation(foilConnectorLeft, 0F, 0F, 0F);
	    
	    foilConnectorRight = new ModelRenderer(this, 60, 9);
	    foilConnectorRight.addBox(-5.3F, 1F, 4F, 1, 1, 1);
	    foilConnectorRight.setRotationPoint(0F, 0F, 0F);
	    foilConnectorRight.setTextureSize(64, 64);
	    foilConnectorRight.mirror = true;
	    setRotation(foilConnectorRight, 0F, 0F, 0F);
	    
	    foilBoxShapeLeft = new ModelRenderer(this, 35, 0);
	    foilBoxShapeLeft.addBox(3.5F, 1.5F, 3.5F, 2, 3, 2);
	    foilBoxShapeLeft.setRotationPoint(0F, 0F, 0F);
	    foilBoxShapeLeft.setTextureSize(64, 64);
	    foilBoxShapeLeft.mirror = true;
	    setRotation(foilBoxShapeLeft, 0F, 0F, 0F);
	    
	    foilBoxShapeRight = new ModelRenderer(this, 44, 0);
	    foilBoxShapeRight.addBox(-5.5F, 1.5F, 3.5F, 2, 3, 2);
	    foilBoxShapeRight.setRotationPoint(0F, 0F, 0F);
	    foilBoxShapeRight.setTextureSize(64, 64);
	    foilBoxShapeRight.mirror = true;
	    setRotation(foilBoxShapeRight, 0F, 0F, 0F);
   		
   		bipedBody.addChild(tankLeft);
   		bipedBody.addChild(tankRight);
   		bipedBody.addChild(foilLeft);
   		bipedBody.addChild(foilRight);
   		bipedBody.addChild(thrusterLeft);
   		bipedBody.addChild(thrusterRight);
   		bipedBody.addChild(nozzleLeftUp);
   		bipedBody.addChild(nozzleLeftDown);
   		bipedBody.addChild(nozzleLeftLeft);
   		bipedBody.addChild(nozzleLeftForward);
   		bipedBody.addChild(nozzleLeftBack);
   		bipedBody.addChild(nozzleRightRight);
   		bipedBody.addChild(nozzleRightUp);
   		bipedBody.addChild(nozzleRightBack);
   		bipedBody.addChild(nozzleRightDown);
   		bipedBody.addChild(nozzleRightForward);
   		bipedBody.addChild(tankTopLeft);
   		bipedBody.addChild(tankTopRioht);
   		bipedBody.addChild(tankConnectorLeft);
   		bipedBody.addChild(tankHose);
   		bipedBody.addChild(tankConnectorRight);
   		bipedBody.addChild(foilConnectorLeft);
   		bipedBody.addChild(foilConnectorRight);
   		bipedBody.addChild(foilBoxShapeLeft);
   		bipedBody.addChild(foilBoxShapeRight);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
 		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
 		bipedBody.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
 		model.rotateAngleY = y;
 		model.rotateAngleZ = z;
	}
}