/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.block;

import com.google.common.base.CaseFormat;
import jp.crafterkina.SurviveInSociety.SurviveInSociety;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
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
    private final Block block;
    private final Item item;
    private final String name;

    EnumBlock(Block block){
        this.block = block;
        this.item = new ItemBlock(block);
        this.name = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name());
    }

    EnumBlock(Block block, String name){
        this(block, new ItemBlock(block), name);
    }

    EnumBlock(Block block, Item item, String name){
        this.block = block;
        this.item = item;
        this.name = name;
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
        for(EnumBlock value : values){
            registerBlock(value.block, value.item, value.name);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels(){
        for(EnumBlock value : values){
            ItemMeshDefinition definition = value.registerModel();
            if(definition == null) continue;
            ModelLoader.setCustomMeshDefinition(value.item, definition);
        }
    }

    public ItemMeshDefinition registerModel(){
        return new ItemMeshDefinition(){
            public ModelResourceLocation getModelLocation(ItemStack stack){
                return new ModelResourceLocation(new ResourceLocation(SurviveInSociety.PARENT_PACKAGE, name), "inventory");
            }
        };
    }
}
