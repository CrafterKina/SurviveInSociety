/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.item;

import jp.crafterkina.SurviveInSociety.SurviveInSociety;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public enum EnumItem{
    ;

    public static final EnumItem[] values = values();
    private final Item item;

    EnumItem(Item item){
        this.item = item;
    }

    EnumItem(){
        this(new Item());
    }

    public static void registerItems(){
        for(EnumItem enumItem : values){
            GameRegistry.registerItem(enumItem.item, enumItem.name());
        }
    }

    public static void registerModels(){
        for(EnumItem enumItem : values){
            enumItem.registerModel();
        }
    }

    protected void registerModel(){
        ItemMeshDefinition definition = new ItemMeshDefinition(){
            public ModelResourceLocation getModelLocation(ItemStack stack){
                return new ModelResourceLocation(new ResourceLocation(SurviveInSociety.PARENT_PACKAGE, item.getUnlocalizedName().substring(5).replaceFirst("item\\.", "").replaceFirst("kina\\.", "")), "inventory");
            }
        };
        ModelLoader.setCustomMeshDefinition(item, definition);
    }
}
