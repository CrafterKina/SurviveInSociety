/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.entity.entities;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.INpc;
import net.minecraft.world.World;

public class EntityReceptionist extends EntityLiving implements INpc{
    public EntityReceptionist(World worldIn){
        super(worldIn);
    }
}
