/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.client.model;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;

import java.io.IOException;

public enum SISModelLoader implements ICustomModelLoader{
    TEST{
        @Override
        public void onResourceManagerReload(IResourceManager resourceManager){

        }

        @Override
        public boolean accepts(ResourceLocation modelLocation){
            return false;
        }

        @Override
        public IModel loadModel(ResourceLocation modelLocation) throws IOException{
            return null;
        }
    };

    public static void register(){
        for(SISModelLoader loader : values()){
            ModelLoaderRegistry.registerLoader(loader);
        }
    }
}
