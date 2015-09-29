/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.entity;

import com.google.common.base.CaseFormat;
import jp.crafterkina.SurviveInSociety.SurviveInSociety;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public enum EnumEntity{
    ;

    public static final EnumEntity[] values = values();

    private final Class<? extends Entity> entityClass;
    private final String name;
    private final int trackingRange;
    private final int updateFrequency;
    private final boolean sendsVelocityUpdates;
    private final Render render;
    private final boolean isLiving;
    private final int weightedProb;
    private final int groupMin;
    private final int groupMax;
    private final EnumCreatureType typeOfCreature;
    private final BiomeGenBase[] spawnBiomes;
    private final boolean enableEgg;
    private final int primaryColor;
    private final int secondaryColor;

    EnumEntity(Builder builder){
        entityClass = builder.entityClass;
        name = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, name());
        trackingRange = builder.trackingRange;
        updateFrequency = builder.updateFrequency;
        sendsVelocityUpdates = builder.sendsVelocityUpdates;
        render = builder.render;
        isLiving = builder.isLiving;
        weightedProb = builder.weightedProb;
        groupMin = builder.groupMin;
        groupMax = builder.groupMax;
        typeOfCreature = builder.typeOfCreature;
        spawnBiomes = builder.spawnBiomes;
        enableEgg = builder.enableEgg;
        primaryColor = builder.primaryColor;
        secondaryColor = builder.secondaryColor;
    }

    public static void registerEntity(){
        for(EnumEntity value : values){
            EntityRegistry.registerModEntity(value.entityClass, value.name, value.ordinal(), SurviveInSociety.getInstance(), value.trackingRange, value.updateFrequency, value.sendsVelocityUpdates);
            if(value.isLiving){
                EntityRegistry.addSpawn(value.name, value.weightedProb, value.groupMin, value.groupMax, value.typeOfCreature, value.spawnBiomes);
            }
            if(value.enableEgg){
                EntityRegistry.registerEgg(value.entityClass, value.primaryColor, value.secondaryColor);
            }
        }
    }

    public static void registerRender(){
        for(EnumEntity value : values){
            RenderingRegistry.registerEntityRenderingHandler(value.entityClass, value.render);
        }
    }

    class Builder{
        private Class<? extends Entity> entityClass;
        private int trackingRange = 250;
        private int updateFrequency = 1;
        private boolean sendsVelocityUpdates = false;
        private Render render;
        private boolean isLiving = false;
        private int weightedProb = 20;
        private int groupMin = 1;
        private int groupMax = 4;
        private EnumCreatureType typeOfCreature = EnumCreatureType.CREATURE;
        private BiomeGenBase[] spawnBiomes = new BiomeGenBase[]{BiomeGenBase.plains};
        private boolean enableEgg = false;
        private int primaryColor = 0xffffff;
        private int secondaryColor = 0xffffff;

        public Builder setEntityClass(Class<? extends Entity> entityClass){
            this.entityClass = entityClass;
            return this;
        }

        public Builder setTrackingRange(int trackingRange){
            this.trackingRange = trackingRange;
            return this;
        }

        public Builder setUpdateFrequency(int updateFrequency){
            this.updateFrequency = updateFrequency;
            return this;
        }

        public Builder setSendsVelocityUpdates(boolean sendsVelocityUpdates){
            this.sendsVelocityUpdates = sendsVelocityUpdates;
            return this;
        }

        public Builder setRender(Render render){
            this.render = render;
            return this;
        }

        public Builder setIsLiving(boolean isLiving){
            this.isLiving = isLiving;
            return this;
        }

        public Builder setWeightedProb(int weightedProb){
            this.weightedProb = weightedProb;
            return this;
        }

        public Builder setGroupMin(int groupMin){
            this.groupMin = groupMin;
            return this;
        }

        public Builder setGroupMax(int groupMax){
            this.groupMax = groupMax;
            return this;
        }

        public Builder setTypeOfCreature(EnumCreatureType typeOfCreature){
            this.typeOfCreature = typeOfCreature;
            return this;
        }

        public Builder setSpawnBiomes(BiomeGenBase... spawnBiomes){
            this.spawnBiomes = spawnBiomes;
            return this;
        }

        public Builder setColor(int primaryColor, int secondaryColor){
            this.enableEgg = true;
            this.primaryColor = primaryColor;
            this.secondaryColor = secondaryColor;
            return this;
        }
    }
}
