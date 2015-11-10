/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.block.blocks;

import jp.crafterkina.SurviveInSociety.block.EnumBlock;
import jp.crafterkina.SurviveInSociety.block.entities.TileEntityBulletinBoard;
import jp.crafterkina.SurviveInSociety.client.model.SISModelLoaderRegistrar;
import jp.crafterkina.SurviveInSociety.client.model.models.ModelBulletinBoard;
import jp.crafterkina.SurviveInSociety.client.model.state.PropertyGeneral;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ITransformation;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;

@SISModelLoaderRegistrar.HasCustomModel(ModelBulletinBoard.class)
public class BlockBulletinBoard extends BlockContainer{
    public static final PropertyGeneral<BlockPos> POS = new PropertyGeneral<BlockPos>("pos", BlockPos.class);
    private static final ITransformation[] transformations = ModelRotation.values();

    public BlockBulletinBoard(){
        super(Material.wood);
        setHardness(3.0F);
        setLightLevel(0.125F);
        setStepSound(soundTypeWood);
        GameRegistry.registerTileEntity(TileEntityBulletinBoard.class, BlockBulletinBoard.class.getCanonicalName());
        setDefaultState(((IExtendedBlockState) blockState.getBaseState()).withProperty(POS, BlockPos.ORIGIN));
    }

    public static boolean setBulletinBord(World world, BlockPos pos, EnumFacing facing){
        if(!canStay(world, pos)) return false;
        ITransformation rotation;
        byte i;
        for(i = 0; i < transformations.length; i++){
            rotation = transformations[i];
            if(rotation.rotate(EnumFacing.NORTH) == facing){
                break;
            }
        }
        world.setBlockState(pos, ((IExtendedBlockState) EnumBlock.BulletinBoard.getBlock().getDefaultState()).withProperty(POS, pos));
        TileEntityBulletinBoard te = (TileEntityBulletinBoard) world.getTileEntity(pos);
        NBTTagCompound compound = new NBTTagCompound();
        te.writeToNBT(compound);
        compound.setByte("rotation", i);
        te.readFromNBT(compound);
        return true;
    }

    public static boolean setBulletinBord(World world, BlockPos pos){
        for(EnumFacing facing : EnumFacing.values()){
            EnumFacing opposite = facing.getOpposite();
            BlockPos offset = pos.offset(opposite);
            if(world.isSideSolid(offset, opposite, false)){
                setBulletinBord(world, pos, facing);
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private static boolean canStay(World worldIn, BlockPos pos){
        ITransformation transformation = ((TileEntityBulletinBoard) worldIn.getTileEntity(pos)).getTransformation();
        EnumFacing facing = transformation.rotate(EnumFacing.NORTH);
        BlockPos offset = pos.offset(facing);
        if(!worldIn.isSideSolid(offset, facing.getOpposite(), true)){
            for(BlockPos neighbors : (Iterable<? extends BlockPos>) BlockPos.getAllInBox(pos.add(-1, -1, -1), pos.add(1, 1, 1))){
                TileEntity te = worldIn.getTileEntity(neighbors);
                if(te == null || !(te instanceof TileEntityBulletinBoard)) continue;
                TileEntityBulletinBoard board = (TileEntityBulletinBoard) te;
                if(!board.getTransformation().equals(transformation)) continue;
                return true;
            }
            return false;
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta){
        return new TileEntityBulletinBoard();
    }

    @Override
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity){
    }

    @Override
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock){
        super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
        if(!canStay(worldIn, pos)) worldIn.setBlockToAir(pos);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos){
        ModelRotation rotation = (ModelRotation) ((TileEntityBulletinBoard) worldIn.getTileEntity(pos)).getTransformation();
        float f = 1 / 16f;
        switch(rotation.rotate(EnumFacing.NORTH)){
            case NORTH:{
                setBlockBounds(0, 0, 0, 16 * f, 16 * f, 1 * f);
                break;
            }
            case SOUTH:{
                setBlockBounds(0, 0, 15 * f, 16 * f, 16 * f, 16 * f);
                break;
            }
            case WEST:{
                setBlockBounds(0, 0, 0, 1 * f, 16 * f, 16 * f);
                break;
            }
            case EAST:{
                setBlockBounds(15 * f, 0, 0, 16 * f, 16 * f, 16 * f);
                break;
            }
        }
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
