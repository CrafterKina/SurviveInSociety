/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.block.blocks;

import jp.crafterkina.SurviveInSociety.block.entities.TileEntityBulletinBoard;
import jp.crafterkina.SurviveInSociety.client.model.SISModelLoaderRegistrar;
import jp.crafterkina.SurviveInSociety.client.model.models.ModelBulletinBoard;
import jp.crafterkina.SurviveInSociety.client.model.state.PropertyGeneral;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.common.registry.GameRegistry;

@SISModelLoaderRegistrar.HasCustomModel(ModelBulletinBoard.class)
public class BlockBulletinBoard extends BlockContainer{
    public static final PropertyGeneral<BlockPos> POS = new PropertyGeneral<BlockPos>("pos", BlockPos.class);

    public BlockBulletinBoard(){
        super(Material.rock);
        GameRegistry.registerTileEntity(TileEntityBulletinBoard.class, BlockBulletinBoard.class.getCanonicalName());
        setDefaultState(((IExtendedBlockState) blockState.getBaseState()).withProperty(POS, BlockPos.ORIGIN));
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta){
        return new TileEntityBulletinBoard();
    }

    @Override
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass){
        return renderPass == 0 ? 0x004800 : 0xFFFFFF;
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos){
        return ((IExtendedBlockState) state).withProperty(POS, pos);
    }

    @Override
    public boolean isOpaqueCube(){
        return false;
    }

    @Override
    public boolean isFullCube(){
        return false;
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

    @Override
    protected BlockState createBlockState(){
        return new ExtendedBlockState(this, new IProperty[]{}, new IUnlistedProperty[]{POS});
    }
}
