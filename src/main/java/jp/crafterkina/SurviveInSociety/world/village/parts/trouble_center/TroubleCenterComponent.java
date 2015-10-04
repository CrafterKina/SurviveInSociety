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

    public TroubleCenterComponent(StructureVillagePieces.Start start, int componentType, Random random, StructureBoundingBox box, EnumFacing facing){
        super(start, componentType);
        coordBaseMode = facing;
        boundingBox = box;
    }

    protected static TroubleCenterComponent build(StructureVillagePieces.Start startPiece, List pieces, Random random, int x, int y, int z, EnumFacing facing, int componentType){
        StructureBoundingBox box = StructureBoundingBox.func_175897_a(x, y, z, 0, 0, 0, 10, 6, 7, facing);
        return canVillageGoDeeper(box) && StructureComponent.findIntersecting(pieces, box) == null ? new TroubleCenterComponent(startPiece, componentType, random, box, facing) : null;
    }

    @Override
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn){
        return false;
    }
}
