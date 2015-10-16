/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.block.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;

public class BlockBulletinBoard extends BlockContainer{
    public BlockBulletinBoard(){
        super(Material.rock);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta){
        return null;
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType(){
        return 3;
    }

    @Override
    public EnumWorldBlockLayer getBlockLayer(){
        return EnumWorldBlockLayer.CUTOUT_MIPPED;
    }
}
