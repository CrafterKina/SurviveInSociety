/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.item;

import com.google.common.base.CaseFormat;
import jp.crafterkina.SurviveInSociety.SurviveInSociety;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum EnumItem{
    Requisition,
    ;

    public static final EnumItem[] values = values();
    private final Item item;

    EnumItem(Item item){
        this.item = item.setUnlocalizedName(SurviveInSociety.PARENT_PACKAGE + "." + CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name()));
    }

    EnumItem(){
        this(new Item().setCreativeTab(CreativeTabs.tabMisc));
    }

    /**
     * item registering method.
     */
    public static void registerItems(){
        for(EnumItem enumItem : values){
            GameRegistry.registerItem(enumItem.item, CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, enumItem.name()));
        }
    }

    /**
     * model of item registering method.
     */
    @SideOnly(Side.CLIENT)
    public static void registerModels(){
        for(EnumItem enumItem : values){
            enumItem.registerModel();
        }
    }

    /**
     * on registering model. you can override and do something.
     */
    @SideOnly(Side.CLIENT)
    protected void registerModel(){
        ItemMeshDefinition definition = new ItemMeshDefinition(){
            public ModelResourceLocation getModelLocation(ItemStack stack){
                return new ModelResourceLocation(new ResourceLocation(SurviveInSociety.PARENT_PACKAGE, CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name())), "inventory");
            }
        };
        ModelLoader.setCustomMeshDefinition(item, definition);
    }

    public Item getItem(){
        return item;
    }
}
