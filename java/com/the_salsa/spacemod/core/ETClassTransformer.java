package com.the_salsa.spacemod.core;

import java.util.Iterator;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ETClassTransformer implements IClassTransformer
{
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass)
	{
		if (name.equals("sa"))
		{
			System.out.println("********* INSIDE OBFUSCATED ENTITY TRANSFORMER ABOUT TO PATCH: " + name);
			return patchClassASM(name, basicClass, true);
		}

		if (name.equals("net.minecraft.entity.Entity"))
		{
			System.out.println("********* INSIDE ENTITY TRANSFORMER ABOUT TO PATCH: " + name);
			return patchClassASM(name, basicClass, false);
		}

		return basicClass;
	}
	
	public byte[] patchClassASM(String name, byte[] bytes, boolean obfuscated)
	{
		String targetMethodName;
		
		//target method
		if (obfuscated)
		{
			targetMethodName = "func_70071_h_";
		}
		else
		{
			targetMethodName = "onUpdate";
		}
		
		//set up ASM class manipulation stuff. Consult the ASM docs for details
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNode, 0);
		
		//loop over methods in Entity class until we get to onUpdate
		Iterator<MethodNode> methods = classNode.methods.iterator();
		while (methods.hasNext())
		{
			MethodNode m = methods.next();
			int invokeIndex = -1;
			
			if (m.name.equals(targetMethodName) && m.desc.equals("()V"))
			{
				System.out.println("********* Inside target method!");

				AbstractInsnNode currentNode = null;
				AbstractInsnNode targetNode = null;
				int index = -1;
				
				//loop over instructions in onUpdate method until we get to the end
				@SuppressWarnings("unchecked")
				Iterator<AbstractInsnNode> iter = m.instructions.iterator();
				while (iter.hasNext())
				{
					index++;
					currentNode = iter.next();
					
					if (currentNode.getOpcode() == Opcodes.INVOKEVIRTUAL)
					{
						targetNode = currentNode;
						invokeIndex = index;
						
						/*
						 * Example:
						 * mv.visitFieldInsn(GETSTATIC, "net/minecraftforge/common/MinecraftForge", "EVENT_BUS", "Lcpw/mods/fml/common/eventhandler/EventBus;");
						 * mv.visitTypeInsn(NEW, "net/minecraftforge/event/entity/EntityEvent$EntityConstructing");
						 * mv.visitInsn(DUP);
						 * mv.visitVarInsn(ALOAD, 0);
						 * mv.visitMethodInsn(INVOKESPECIAL, "net/minecraftforge/event/entity/EntityEvent$EntityConstructing", "<init>", "(Lnet/minecraft/entity/Entity;)V", false);
						 * mv.visitMethodInsn(INVOKEVIRTUAL, "cpw/mods/fml/common/eventhandler/EventBus", "post", "(Lcpw/mods/fml/common/eventhandler/Event;)Z", false);
						 * mv.visitInsn(POP);
						 */
						
						InsnList toInject = new InsnList();
						toInject.add(new FieldInsnNode(Opcodes.GETSTATIC, "net/minecraftforge/common/MinecraftForge", "EVENT_BUS", "Lcpw/mods/fml/common/eventhandler/EventBus;"));
						toInject.add(new TypeInsnNode(Opcodes.NEW, "com/the_salsa/spacemod/core/EntityTick"));
						toInject.add(new InsnNode(Opcodes.DUP));
						toInject.add(new VarInsnNode(Opcodes.ALOAD, 0));
						toInject.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "com/the_salsa/spacemod/core/EntityTick", "<init>", "(Lnet/minecraft/entity/Entity;)V", false));
						toInject.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "cpw/mods/fml/common/eventhandler/EventBus", "post", "(Lcpw/mods/fml/common/eventhandler/Event;)Z", false));
						toInject.add(new InsnNode(Opcodes.POP));
						
						m.instructions.insert(targetNode, toInject);

						System.out.println("Patching Complete!");
						break;
					}
				}
			}
		}
		
		//ASM specific for cleaning up and returning the final bytes for JVM processing.
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		classNode.accept(writer);
		return writer.toByteArray();
	}
}
