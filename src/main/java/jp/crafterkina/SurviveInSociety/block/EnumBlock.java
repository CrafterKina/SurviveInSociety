/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.block;

import com.google.common.base.CaseFormat;
import jp.crafterkina.SurviveInSociety.SurviveInSociety;
import jp.crafterkina.SurviveInSociety.block.blocks.BlockBulletinBoard;
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
    BulletinBoard(new BlockBulletinBoard(), null, "bulletin_board"),
    ;

    public static final EnumBlock[] values = values();
    private static final Method main = ReflectionHelper.findMethod(GameData.class, null, new String[]{"getMain"});
    private static final Method register = ReflectionHelper.findMethod(GameData.class, null, new String[]{"registerBlock"}, Block.class, String.class);
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

    EnumBlock(Builder builder){
        block = builder.block;
        item = builder.item;
        name = builder.name != null ? builder.name : CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name());
    }

    @SuppressWarnings("unchecked")
    public static void registerBlock(Block block, Item item, String name){
        try{
            register.invoke(main.invoke(null), block, name);
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

    public Block getBlock(){
        return block;
    }

    public Item getItem(){
        return item;
    }

    public String getName(){
        return name;
    }

    private class Builder{
        private Block block;
        private Item item;
        private String name = null;

        private Builder(Block block){
            this.block = block;
            this.item = new ItemBlock(block);
        }

        public Builder setItem(Item item){
            this.item = item;
            return this;
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }
    }
}
