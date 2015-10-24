/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.block.blocks;

import jp.crafterkina.SurviveInSociety.block.entities.TileEntityBulletinBoard;
import jp.crafterkina.SurviveInSociety.client.model.SISModelLoaderRegistrar;
import jp.crafterkina.SurviveInSociety.client.model.models.ModelBulletinBoard;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

@SISModelLoaderRegistrar.HasCustomModel(ModelBulletinBoard.class)
public class BlockBulletinBoard extends BlockContainer{
    public BlockBulletinBoard(){
        super(Material.rock);
        GameRegistry.registerTileEntity(TileEntityBulletinBoard.class, BlockBulletinBoard.class.getCanonicalName());
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta){
        return new TileEntityBulletinBoard();
    }

    @Override
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass){
        return renderPass == 0 ? 0x004800 : 0xFFFFFF;
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
