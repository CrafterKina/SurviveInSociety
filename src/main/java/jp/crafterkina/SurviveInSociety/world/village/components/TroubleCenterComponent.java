/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.world.village.components;

import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.List;
import java.util.Random;

public class TroubleCenterComponent extends StructureVillagePieces.Village implements VillagerRegistry.IVillageCreationHandler{

    public TroubleCenterComponent(){}

    public TroubleCenterComponent(StructureVillagePieces.Start start, int componentType, Random random, StructureBoundingBox box, EnumFacing facing){
        super(start, componentType);
        coordBaseMode = facing;
        boundingBox = box;
    }

    @Override
    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i){
        return new StructureVillagePieces.PieceWeight(TroubleCenterComponent.class, 20, MathHelper.getRandomIntegerInRange(random, i, i + 1));
    }

    @Override
    public Class<?> getComponentClass(){
        return TroubleCenterComponent.class;
    }

    @Override
    public Object buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List pieces, Random random, int x, int y, int z, EnumFacing facing, int p5){
        StructureBoundingBox box = StructureBoundingBox.func_175897_a(x, y, z, 0, 0, 0, 10, 6, 7, facing);
        return canVillageGoDeeper(box) && StructureComponent.findIntersecting(pieces, box) == null ? new TroubleCenterComponent(startPiece, componentType, random, box, facing) : null;
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

        func_175804_a(worldIn, structureBoundingBoxIn, 0, 0, 0, 4, 10, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);

        fillWithAir(worldIn, structureBoundingBoxIn, 1, 1, 1, 3, 9, 3);

        placeDoorCurrentPosition(worldIn, structureBoundingBoxIn, randomIn, 0, 1, 2, coordBaseMode);

        return true;
    }
}
