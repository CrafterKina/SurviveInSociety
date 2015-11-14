/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.world.structure;

import jp.crafterkina.SurviveInSociety.SurviveInSociety;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import org.apache.commons.lang3.ArrayUtils;

public enum EnumStructure{
    ;
    public static final EnumStructure[] values = values();

    private final MapGenStructure generator;
    private final int[] dimensions;
    private final Class<? extends StructureStart> start;
    private final Class<? extends StructureComponent>[] components;

    EnumStructure(MapGenStructure generator, int[] dimensions, Class<? extends StructureStart> start, Class<? extends StructureComponent>... components){
        this.generator = generator;
        this.dimensions = dimensions;
        this.start = start;
        this.components = components;
    }

    /**
     * structures registering method.
     */
    public static void register(){
        for(EnumStructure value : values){
            if(value.onRegister()){
                MapGenStructureIO.registerStructure(value.start, SurviveInSociety.PARENT_PACKAGE + value.name());
                int i = 1;
                for(Class<? extends StructureComponent> component : value.components){
                    MapGenStructureIO.registerStructureComponent(component, SurviveInSociety.PARENT_PACKAGE + value.name() + i++);
                }
            }
        }
    }

    /**
     * on populating chunk.
     *
     * @param event
     *         the PopulateChunkEvent
     */
    public static void tryGen(PopulateChunkEvent.Pre event){
        for(EnumStructure value : values){
            value.generate(event);
        }
    }

    protected boolean onRegister(){
        return true;
    }

    protected void generate(PopulateChunkEvent.Pre event){
        if(ArrayUtils.contains(dimensions, event.world.provider.getDimensionId())){
            generator.func_175792_a(event.chunkProvider, event.world, event.chunkX, event.chunkZ, null);
            generator.func_175794_a(event.world, event.rand, new ChunkCoordIntPair(event.chunkX, event.chunkZ));
        }
    }
}
