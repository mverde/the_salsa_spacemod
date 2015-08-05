package com.the_salsa.spacemod;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class OxygenInventoryPacket implements IMessage
{
	private int guiId;
	
	public OxygenInventoryPacket()
	{}
	
	public OxygenInventoryPacket(int id)
	{
		guiId = id;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		guiId = ByteBufUtils.readVarInt(buf, 4);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeVarInt(buf, guiId, 4);
	}
	
	public static class InventoryMessageHandler implements IMessageHandler<OxygenInventoryPacket, IMessage>
	{		
		EntityPlayer player;
		
		@Override
		public IMessage onMessage(OxygenInventoryPacket message, MessageContext ctx)
		{
			player = Minecraft.getMinecraft().thePlayer;
			handleOpenGui(message, player);
			
			return null;
		}
		
		public void handleOpenGui(OxygenInventoryPacket message, EntityPlayer player)
		{
			System.out.println("[PACKET] received open gui packet");
			player.openGui(SpaceMod.instance, message.guiId, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
		}
	}
}
