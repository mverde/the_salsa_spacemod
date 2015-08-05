package com.the_salsa.spacemod.core;

import net.minecraft.entity.Entity;
import net.minecraftforge.event.entity.EntityEvent;
import cpw.mods.fml.common.eventhandler.Event.HasResult;

/**
 * EntityConstructing is fired when an Entity is ticking. <br>
 * This event is fired within the onUpdate method of the Entity.<br>
 * <br>
 * This event is not {@link Cancelable}.<br>
 * <br>
 * This event does not have a result. {@link HasResult}<br>
 * <br>
 * This event is fired on the {@link MinecraftForge#EVENT_BUS}.<br>
 **/
public class EntityTick extends EntityEvent
{
    public EntityTick(Entity entity)
    {
        super(entity);
    }
}