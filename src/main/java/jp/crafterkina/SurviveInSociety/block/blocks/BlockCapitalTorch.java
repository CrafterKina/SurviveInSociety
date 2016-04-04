package jp.crafterkina.SurviveInSociety.block.blocks;

import jp.crafterkina.SurviveInSociety.block.entities.TileEntityCapitalTorch;
import jp.crafterkina.SurviveInSociety.client.tesr.TESRCapitalTorch;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static jp.crafterkina.SurviveInSociety.SurviveInSociety.*;

public class BlockCapitalTorch extends BlockContainer{
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.4D, 0.0D, 0.4D, 0.6D, 0.6D, 0.6D);
    private BlockCapitalTorch(){
        super(Material.circuits);
        setCreativeTab(CreativeTabs.tabDecorations);
    }

    public static void register(){
        GameRegistry.register(new BlockCapitalTorch(),new ResourceLocation(MOD_ID,"capital_torch"));
        GameRegistry.registerTileEntity(TileEntityCapitalTorch.class,new ResourceLocation(MOD_ID,"capital_torch").toString());
        if(FMLCommonHandler.instance().getSide().isClient()){
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCapitalTorch.class,new TESRCapitalTorch());
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return AABB;
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBox(IBlockState blockState, World worldIn, BlockPos pos){
        return NULL_AABB;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state){
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state){
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta){
        return new TileEntityCapitalTorch();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state){
        return EnumBlockRenderType.MODEL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer(){
        return BlockRenderLayer.CUTOUT;
    }
}