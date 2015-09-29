/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

public enum EnumEntity{
    ;
    private final Class<? extends Entity> entityClass;
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

    EnumEntity(Builder builder){
        entityClass = builder.entityClass;
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
    }

    class Builder{
        private Class<? extends Entity> entityClass;
        private int trackingRange;
        private int updateFrequency;
        private boolean sendsVelocityUpdates;
        private Render render;
        private boolean isLiving;
        private int weightedProb;
        private int groupMin;
        private int groupMax;
        private EnumCreatureType typeOfCreature;
        private BiomeGenBase[] spawnBiomes;

        public void setEntityClass(Class<? extends Entity> entityClass){
            this.entityClass = entityClass;
        }

        public void setTrackingRange(int trackingRange){
            this.trackingRange = trackingRange;
        }

        public void setUpdateFrequency(int updateFrequency){
            this.updateFrequency = updateFrequency;
        }

        public void setSendsVelocityUpdates(boolean sendsVelocityUpdates){
            this.sendsVelocityUpdates = sendsVelocityUpdates;
        }

        public void setRender(Render render){
            this.render = render;
        }

        public void setIsLiving(boolean isLiving){
            this.isLiving = isLiving;
        }

        public void setWeightedProb(int weightedProb){
            this.weightedProb = weightedProb;
        }

        public void setGroupMin(int groupMin){
            this.groupMin = groupMin;
        }

        public void setGroupMax(int groupMax){
            this.groupMax = groupMax;
        }

        public void setTypeOfCreature(EnumCreatureType typeOfCreature){
            this.typeOfCreature = typeOfCreature;
        }

        public void setSpawnBiomes(BiomeGenBase[] spawnBiomes){
            this.spawnBiomes = spawnBiomes;
        }
    }
}
