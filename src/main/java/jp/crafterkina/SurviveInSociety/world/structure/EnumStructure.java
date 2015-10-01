/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.world.structure;

import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

public enum EnumStructure{
    ;

    private final StructureStart start;
    private final StructureComponent[] components;

    EnumStructure(StructureStart start, StructureComponent... components){
        this.start = start;
        this.components = components;
    }
}
