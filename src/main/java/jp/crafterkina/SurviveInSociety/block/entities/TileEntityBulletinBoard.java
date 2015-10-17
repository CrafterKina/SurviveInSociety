/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.block.entities;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBulletinBoard extends TileEntity{
    private ItemStack stack = null;

    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        ItemStack.loadItemStackFromNBT(compound.getCompoundTag("stack"));
    }

    @Override
    public void writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        NBTTagCompound itemCompound = new NBTTagCompound();
        if(stack != null){
            stack.writeToNBT(itemCompound);
        }
        compound.setTag("stack", itemCompound);
    }
}
