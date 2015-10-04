/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.world.village.parts.trouble_center;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import java.util.List;
import java.util.Random;

public class TroubleCenterComponent extends StructureVillagePieces.Village{
    public TroubleCenterComponent(){}

    public TroubleCenterComponent(StructureVillagePieces.Start p_i45571_1_, int p_i45571_2_, Random p_i45571_3_, StructureBoundingBox p_i45571_4_, EnumFacing p_i45571_5_){

    }

    protected static TroubleCenterComponent build(StructureVillagePieces.Start startPiece, List pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5){
        StructureBoundingBox box = StructureBoundingBox.func_175897_a(p1, p2, p3, 0, 0, 0, 10, 6, 7, facing);
        return canVillageGoDeeper(box) && StructureComponent.findIntersecting(pieces, box) == null ? new TroubleCenterComponent(startPiece, p5, random, box, facing) : null;
    }

    @Override
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn){
        return false;
    }
}
