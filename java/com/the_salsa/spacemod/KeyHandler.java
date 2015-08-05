//package com.the_salsa.spacemod;
//
//import net.minecraft.client.settings.KeyBinding;
//import net.minecraft.entity.player.EntityPlayer;
//
//import org.lwjgl.input.Keyboard;
//
//import com.the_salsa.spacemod.OxygenInventoryPacket.InventoryMessageHandler;
//
//import cpw.mods.fml.client.FMLClientHandler;
//import cpw.mods.fml.client.registry.ClientRegistry;
//import cpw.mods.fml.common.eventhandler.SubscribeEvent;
//import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
//
//public class KeyHandler
//{
//	/** Key bindings index */
//	public static final int CUSTOM_INVENTORY = 0;
//	/** Key descriptions */
//	private static final String[] descriptions = { "Oxygen Inventory" };
//	/** Default key values */
//	public static final int[] keyValues = { Keyboard.KEY_I };
//	/** This stores all of the key bindings */
//	public static KeyBinding[] keys = {};
//	
//	public KeyHandler()
//	{
//		keys = new KeyBinding[descriptions.length];
//		for (int i = 0; i < descriptions.length; ++i)
//		{
//			keys[ i ] = new KeyBinding(descriptions[i], keyValues[i], "Space Mod");
//			ClientRegistry.registerKeyBinding(keys[i]);
//		}
//	}
//	
//	@SubscribeEvent
//	public void onKeyInput(KeyInputEvent event)
//	{
//		if (FMLClientHandler.instance().getClient().inGameHasFocus)
//		{
//			int kb = Keyboard.getEventKey();
//			boolean isDown = Keyboard.getEventKeyState();
//			
//			if (kb == keys[CUSTOM_INVENTORY].getKeyCode())
//			{
//				System.out.println("[KEYHANDLER] registered I key being pressed");
//				EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
//				if (player.openContainer instanceof ContainerOxygenTanks)
//				{
//					// TODO close screen? Not sure how to do this yet.
//				}
//				else
//				{
//					System.out.println("[KEYHANDLER] attempting to send open gui packet to server...");
//					SpaceMod.wrapper.sendToServer(new OxygenInventoryPacket(SpaceMod.GUI_CUSTOM_INV));
//				}
//			}
//		}
//	}
//}
