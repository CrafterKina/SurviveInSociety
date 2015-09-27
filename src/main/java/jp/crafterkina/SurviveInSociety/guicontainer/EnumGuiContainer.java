/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.guicontainer;

import jp.crafterkina.SurviveInSociety.SurviveInSociety;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum EnumGuiContainer{
    ;

    public static final EnumGuiContainer[] values = values();
    private final Class<? extends GuiContainer> gui;
    private final Class<? extends Container> container;

    EnumGuiContainer(Class<? extends GuiContainer> gui, Class<? extends Container> container){
        this.gui = gui;
        this.container = container;
    }

    public static void register(){
        NetworkRegistry.INSTANCE.registerGuiHandler(SurviveInSociety.getInstance(), new IGuiHandler(){
            @Override
            public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
                return values[ID].getContainer(player, world, x, y, z);
            }

            @Override
            public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
                return values[ID].getGui(player, world, x, y, z);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private <T> T createInstance(Class<? extends T> clazz, EntityPlayer player, World world, int x, int y, int z){
        for(Constructor<?> constructor : clazz.getConstructors()){
            Class<?>[] types = constructor.getParameterTypes();
            Object[] param = new Object[types.length];
            for(int i = 0; i < types.length; i++){
                Class<?> paramClass = types[i];
                if(player != null && paramClass.isInstance(player)){
                    param[i] = player;
                }else if(world != null && paramClass.isInstance(world)){
                    param[i] = world;
                }else if(paramClass.isInstance(x)){
                    param[i] = x;
                    if(++i != types.length && types[i].isInstance(y)){
                        param[i] = y;
                        if(++i != types.length && types[i].isInstance(z)){
                            param[i] = z;
                        }
                    }
                }else if(paramClass.isInstance(BlockPos.ORIGIN)){
                    param[i] = new BlockPos(x, y, z);
                }else{
                    param = null;
                    break;
                }
            }

            if(param == null){
                continue;
            }

            try{
                return (T) constructor.newInstance(param);
            }catch(InstantiationException e){
                e.printStackTrace();
            }catch(IllegalAccessException e){
                e.printStackTrace();
            }catch(InvocationTargetException e){
                e.printStackTrace();
            }
        }
        throw new NotImplementedException();
    }

    protected GuiContainer getGui(EntityPlayer player, World world, int x, int y, int z){
        return createInstance(gui, player, world, x, y, z);
    }

    protected Container getContainer(EntityPlayer player, World world, int x, int y, int z){
        return createInstance(container, player, world, x, y, z);
    }

    public void openGui(EntityPlayer player, World world, Vec3i pos){
        player.openGui(SurviveInSociety.getInstance(), ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
    }

    public void openGui(EntityPlayer player, World world, int x, int y, int z){
        openGui(player, world, new Vec3i(x, y, z));
    }
}
