/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.block.entities;

import jp.crafterkina.SurviveInSociety.item.EnumItem;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.ITransformation;

public class TileEntityBulletinBoard extends TileEntity{
    private ItemStack stack = null;
    private ModelRotation transformation = ModelRotation.X0_Y0;

    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        ItemStack.loadItemStackFromNBT(compound.getCompoundTag("stack"));
        transformation = ModelRotation.values()[compound.getByte("rotation")];
    }

    @Override
    public void writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        if(stack != null){
            compound.setTag("stack", stack.writeToNBT(new NBTTagCompound()));
        }
        compound.setByte("rotation", (byte) transformation.ordinal());
    }

    public ItemStack updateContent(){
        return stack;
    }

    public boolean hasContent(){
        return stack != null;
    }

    public ItemStack getContent(){
        return stack.copy();
    }

    public void setContent(ItemStack content){
        if(!isValid(content)) return;
        stack = content;
    }

    public ITransformation getTransformation(){
        return transformation;
    }

    @Override
    public Packet getDescriptionPacket(){
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        writeToNBT(nbtTagCompound);
        return new S35PacketUpdateTileEntity(getPos(), 1, nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
        readFromNBT(pkt.getNbtCompound());
    }

    public boolean isValid(ItemStack target){
        return target != null && target.getItem() == EnumItem.Requisition.getItem();
    }
}
