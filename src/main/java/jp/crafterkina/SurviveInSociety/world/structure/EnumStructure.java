/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.world.structure;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

public enum EnumStructure{
    ;

    private final Class<? extends StructureStart> start;
    private final Class<? extends StructureComponent>[] components;

    EnumStructure(Class<? extends StructureStart> start, Class<? extends StructureComponent>... components){
        this.start = start;
        this.components = components;
    }

    public static void register(){
        for(EnumStructure value : values()){
            if(value.onRegister()){
                MapGenStructureIO.registerStructure(value.start, value.name());
                int i = 1;
                for(Class<? extends StructureComponent> component : value.components){
                    MapGenStructureIO.registerStructureComponent(component, value.name() + i++);
                }
            }
        }
    }

    public boolean onRegister(){
        return true;
    }
}
