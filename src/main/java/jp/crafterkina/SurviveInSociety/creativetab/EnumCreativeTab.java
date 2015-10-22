/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.creativetab;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public enum EnumCreativeTab{
    ;

    private final CreativeTabs tab;

    EnumCreativeTab(CreativeTabs tab){
        this.tab = tab;
    }

    public Item appendTab(Item target){
        return target.setCreativeTab(tab);
    }

    public Block appendTab(Block target){
        return target.setCreativeTab(tab);
    }
}
