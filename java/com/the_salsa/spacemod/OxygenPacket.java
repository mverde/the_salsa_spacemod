package com.the_salsa.spacemod;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class OxygenPacket implements IMessage
{
	private String text;
	
	public OxygenPacket()
	{}
	
	public OxygenPacket(String text)
	{
		this.text = text;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		text = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeUTF8String(buf, text);
	}
	
	public static class OxygenMessageHandler implements IMessageHandler<OxygenPacket, IMessage>
	{		
		EntityPlayer player;
		
		@Override
		public IMessage onMessage(OxygenPacket message, MessageContext ctx)
		{
			player = Minecraft.getMinecraft().thePlayer;
			handleOxygenUpdate(message, player);
			
			return null;
		}
		
		public void handleOxygenUpdate(OxygenPacket message, EntityPlayer player)
		{
			//System.out.println("[PACKET] received oxygen update packet");
			ExtendedPropertiesPlayer properties = (ExtendedPropertiesPlayer) player.getExtendedProperties(ExtendedPropertiesPlayer.EX_PROP_NAME);
			
			properties.setMaxOxygen(Integer.parseInt(message.text.substring(0, 5)));
			properties.setCurrentOxygen(Integer.parseInt(message.text.substring(5)));
		}
	}
}
