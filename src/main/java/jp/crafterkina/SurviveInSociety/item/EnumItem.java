/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public enum EnumItem{
    ;

    public static final EnumItem[] values = values();
    private Item item;

    EnumItem(Item item){
        this.item = item;
    }

    EnumItem(){

    }

    public static void registerItems(){
        for(EnumItem enumItem : values){
            GameRegistry.registerItem(enumItem.item, enumItem.name());
        }
    }

    public static void registerModels(){

    }
}
