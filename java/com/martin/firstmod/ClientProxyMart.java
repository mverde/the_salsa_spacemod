package com.martin.firstmod;

import net.minecraft.client.renderer.entity.RenderSnowball;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxyMart extends CommonProxyMart
{
	@Override
	public void registerRendering()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityMartMob.class,  new RenderMart(new ModelMartMob(), 0.5F));
	}
	
	@Override
	public void registerItemRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityMartThrowable.class, new RenderSnowball(FirstMod.martThrow));
	}
}
