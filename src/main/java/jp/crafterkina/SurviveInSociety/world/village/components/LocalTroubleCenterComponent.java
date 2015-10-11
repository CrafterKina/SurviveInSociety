/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.world.village.components;

import net.minecraft.nbt.NBTTagCompound;
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
        StructureBoundingBox box = StructureBoundingBox.func_175897_a(x, y, z, 0, 0, 0, 10, 6, 7, facing);
        return canVillageGoDeeper(box) && StructureComponent.findIntersecting(pieces, box) == null ? new LocalTroubleCenterComponent(startPiece, componentType, random, box, facing) : null;
    }

    @Override
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn){
        if(this.field_143015_k < 0){
            this.field_143015_k = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

            if(this.field_143015_k < 0){
                return true;
            }

            this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 6 - 1, 0);
        }
        switch(type){
            case Tiny:
                return addTinyParts(worldIn, randomIn, structureBoundingBoxIn);
            default:
                return false;
        }
    }

    private boolean addTinyParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn){
        return false;
    }

    private enum Type{
        Tiny,;
        private static final Type[] values = values();
    }
}
