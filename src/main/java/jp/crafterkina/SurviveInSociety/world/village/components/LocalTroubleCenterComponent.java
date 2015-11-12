/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.world.village.components;

import jp.crafterkina.SurviveInSociety.block.blocks.BlockBulletinBoard;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWallSign;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.List;
import java.util.Random;

public class LocalTroubleCenterComponent extends StructureVillagePieces.Village implements VillagerRegistry.IVillageCreationHandler{
    private Type type;

    public LocalTroubleCenterComponent(){}

    public LocalTroubleCenterComponent(StructureVillagePieces.Start start, int componentType, Random random, StructureBoundingBox box, EnumFacing facing){
        super(start, componentType);
        coordBaseMode = facing;
        boundingBox = box;
        type = Type.values[random.nextInt(Type.values.length)];
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound){
        super.writeStructureToNBT(tagCompound);
        tagCompound.setInteger("LTCType", type.ordinal());
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound){
        super.readStructureFromNBT(tagCompound);
        type = Type.values[tagCompound.getInteger("LTCType")];
    }

    @Override
    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i){
        return new StructureVillagePieces.PieceWeight(LocalTroubleCenterComponent.class, 20, MathHelper.getRandomIntegerInRange(random, i, i + 1));
    }

    @Override
    public Class<LocalTroubleCenterComponent> getComponentClass(){
        return LocalTroubleCenterComponent.class;
    }

    @Override
    public Object buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List pieces, Random random, int x, int y, int z, EnumFacing facing, int p5){
        StructureBoundingBox box = StructureBoundingBox.func_175897_a(x, y, z, -1, 0, 0, 7, 9, 10, facing);
        return canVillageGoDeeper(box) && StructureComponent.findIntersecting(pieces, box) == null ? new LocalTroubleCenterComponent(startPiece, componentType, random, box, facing) : null;
    }

    @Override
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn){
        if(this.field_143015_k < 0){
            this.field_143015_k = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

            if(this.field_143015_k < 0){
                return true;
            }

            this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 9 - 1, 0);
        }
        switch(type){
            case Tiny:
                return addTinyParts(worldIn, randomIn, structureBoundingBoxIn);
            default:
                return false;
        }
    }

    private boolean addTinyParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn){
        this.func_175804_a(worldIn, structureBoundingBoxIn, 1, 0, 1, 4, 0, 7, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
        this.func_175804_a(worldIn, structureBoundingBoxIn, 0, 0, 0, 5, 3, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
        this.func_175804_a(worldIn, structureBoundingBoxIn, 0, 0, 8, 5, 3, 8, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
        this.func_175804_a(worldIn, structureBoundingBoxIn, 0, 2, 5, 5, 3, 5, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
        this.func_175804_a(worldIn, structureBoundingBoxIn, 0, 0, 5, 5, 1, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
        this.func_175804_a(worldIn, structureBoundingBoxIn, 0, 0, 1, 0, 1, 7, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
        this.func_175804_a(worldIn, structureBoundingBoxIn, 5, 0, 1, 5, 1, 7, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
        this.func_175804_a(worldIn, structureBoundingBoxIn, 0, 2, 1, 0, 3, 7, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
        this.func_175804_a(worldIn, structureBoundingBoxIn, 5, 2, 1, 5, 3, 7, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
        this.func_175804_a(worldIn, structureBoundingBoxIn, 1, 4, 0, 1, 4, 8, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
        this.func_175804_a(worldIn, structureBoundingBoxIn, 4, 4, 0, 4, 4, 8, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
        this.func_175804_a(worldIn, structureBoundingBoxIn, 2, 5, 0, 3, 5, 8, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
        this.func_175804_a(worldIn, structureBoundingBoxIn, 2, 4, 5, 3, 4, 5, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
        this.setBlockState(worldIn, Blocks.planks.getDefaultState(), 2, 4, 0, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.planks.getDefaultState(), 3, 4, 0, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.planks.getDefaultState(), 2, 4, 8, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.planks.getDefaultState(), 3, 4, 8, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.iron_bars.getDefaultState(), 2, 2, 5, structureBoundingBoxIn);
        int i = this.getMetadataWithOffset(Blocks.oak_stairs, 0);
        int j = this.getMetadataWithOffset(Blocks.oak_stairs, 1);
        int k;
        int l;

        for(k = -1; k <= 2; ++k){
            for(l = 0; l <= 8; ++l){
                this.setBlockState(worldIn, Blocks.oak_stairs.getStateFromMeta(i), k, 4 + k, l, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.oak_stairs.getStateFromMeta(j), 5 - k, 4 + k, l, structureBoundingBoxIn);
            }
        }

        this.placeDoorCurrentPosition(worldIn, structureBoundingBoxIn, randomIn, 0, 1, 2, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.iron_door, 0)), Blocks.iron_door);//�\

        EnumFacing facing = coordBaseMode.getAxis() == EnumFacing.Axis.Z ? EnumFacing.EAST : EnumFacing.SOUTH;
        this.setBlockState(worldIn, Blocks.wall_sign.getDefaultState().withProperty(BlockWallSign.FACING, facing.getOpposite()), -1, 2, 3, structureBoundingBoxIn);
        BlockPos blockpos = getFixedPos(new BlockPos(-1, 2, 3));
        if(worldIn.getTileEntity(blockpos) != null){
            ((TileEntitySign) worldIn.getTileEntity(blockpos)).signText[1] = new ChatComponentText("Enter");
        }
        for(k = 1; k <= 4; k++){
            BlockBulletinBoard.setBulletinBord(worldIn, getFixedPos(new BlockPos(4, 2, k)), facing);
            BlockBulletinBoard.setBulletinBord(worldIn, getFixedPos(new BlockPos(4, 3, k)), facing);
        }

        this.placeDoorCurrentPosition(worldIn, structureBoundingBoxIn, randomIn, 5, 1, 6, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.iron_door, 0)), Blocks.iron_door);//��

        this.setBlockState(worldIn, Blocks.wall_sign.getDefaultState().withProperty(BlockWallSign.FACING, facing), 6, 2, 6, structureBoundingBoxIn);
        blockpos = getFixedPos(new BlockPos(6, 2, 6));
        if(worldIn.getTileEntity(blockpos) != null){
            TileEntitySign sign = (TileEntitySign) worldIn.getTileEntity(blockpos);
            sign.signText[0] = new ChatComponentText("AUTHORIZED");
            sign.signText[1] = new ChatComponentText("PERSONNEL");
            sign.signText[2] = new ChatComponentText("ONLY");
            sign.signText[3] = new ChatComponentText("--×--×--×--×--×--×--");
        }

        for(k = 0; k < 8; ++k){
            for(l = 0; l < 6; ++l){
                this.clearCurrentPositionBlocksUpwards(worldIn, l, 7, k, structureBoundingBoxIn);
                this.replaceAirAndLiquidDownwards(worldIn, Blocks.cobblestone.getDefaultState(), l, -1, k, structureBoundingBoxIn);
            }
        }
        return true;
    }

    protected void placeDoorCurrentPosition(World worldIn, StructureBoundingBox boundingBoxIn, Random rand, int x, int y, int z, EnumFacing facing, Block door){
        BlockPos blockpos = new BlockPos(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));

        if(boundingBoxIn.isVecInside(blockpos)){
            ItemDoor.placeDoor(worldIn, blockpos, facing.rotateYCCW(), door);
        }
    }

    protected BlockPos getFixedPos(BlockPos pos){
        return new BlockPos(this.getXWithOffset(pos.getX(), pos.getZ()), this.getYWithOffset(pos.getY()), this.getZWithOffset(pos.getX(), pos.getZ()));
    }

    private enum Type{
        Tiny,;
        private static final Type[] values = values();
    }
}
