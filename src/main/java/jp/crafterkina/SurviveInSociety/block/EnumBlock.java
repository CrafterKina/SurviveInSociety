/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public enum EnumBlock{
    ;

    public static final EnumBlock[] values = values();

    EnumBlock(){

    }

    @SuppressWarnings("unchecked")
    public static void registerBlock(Block block, Item item, String name){
        Method main = ReflectionHelper.findMethod(GameData.class, null, new String[]{"getMain"});
        Method register = ReflectionHelper.findMethod(GameData.class, null, new String[]{"registerBlock"}, Block.class, String.class);
        try{
            register.invoke(main, block, name);
            if(item != null){
                GameRegistry.registerItem(item, name);
                GameData.getBlockItemMap().put(block, item);
            }
        }catch(IllegalAccessException e){
            throw new AssertionError(e);
        }catch(InvocationTargetException e){
            throw new AssertionError(e);
        }
    }

    public static void registerBlocks(){

    }

    @SideOnly(Side.CLIENT)
    public static void registerModels(){

    }
}
