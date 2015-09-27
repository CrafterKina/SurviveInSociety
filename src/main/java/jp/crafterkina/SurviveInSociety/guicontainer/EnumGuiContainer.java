/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.guicontainer;

import jp.crafterkina.SurviveInSociety.SurviveInSociety;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public enum EnumGuiContainer{
    ;

    public static final EnumGuiContainer[] values = values();


    public static void register(){
        NetworkRegistry.INSTANCE.registerGuiHandler(SurviveInSociety.getInstance(), new IGuiHandler(){
            @Override
            public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
                return null;
            }

            @Override
            public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
                return null;
            }
        });
    }
}
