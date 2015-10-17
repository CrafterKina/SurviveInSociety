/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.client.model;

import com.google.common.collect.Maps;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

public enum SISModelLoader implements ICustomModelLoader{
    Common{
        private Map<String,IModel> modelMap = Maps.newHashMap();

        @Override
        public void onResourceManagerReload(IResourceManager resourceManager){

        }

        @Override
        public boolean accepts(ResourceLocation modelLocation){
            return modelMap.containsKey(modelLocation.toString());
        }

        @Override
        public IModel loadModel(ResourceLocation modelLocation) throws IOException{
            return modelMap.get(modelLocation.toString());
        }

        public void registerModel(ResourceLocation location, IModel model){
            String path = location.getResourcePath();
            path = (path.startsWith("builtin/") || path.startsWith("models/") ? "" : "models/") + path;
            location = new ResourceLocation(location.getResourceDomain(), path);
            modelMap.put(location.toString(), model);
        }
    };

    public static void register(){
        for(SISModelLoader loader : values()){
            ModelLoaderRegistry.registerLoader(loader);
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface HasCustomModelLoader{
        String value();
    }
}
