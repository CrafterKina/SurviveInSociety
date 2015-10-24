/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.block.entities;

import jp.crafterkina.SurviveInSociety.item.EnumItem;
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
        if(stack != null){
            compound.setTag("stack", stack.writeToNBT(new NBTTagCompound()));
        }
    }

    public ItemStack updateContent(){
        return stack;
    }

    public void setContent(ItemStack content){
        if(!isValid(content)) return;
        stack = content;
    }

    public boolean isValid(ItemStack target){
        return target != null && target.getItem() == EnumItem.Requisition.getItem();
    }
}
