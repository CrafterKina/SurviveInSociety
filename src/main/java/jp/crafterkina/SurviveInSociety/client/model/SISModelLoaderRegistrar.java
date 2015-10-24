/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.client.model;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import jp.crafterkina.SurviveInSociety.block.EnumBlock;
import jp.crafterkina.SurviveInSociety.internal.SISInformation;
import jp.crafterkina.SurviveInSociety.item.EnumItem;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nullable;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

public class SISModelLoaderRegistrar{
    public static void register(){
        Multimap<String,ResourceLocation> map = HashMultimap.create();
        HasCustomModel annotation;
        for(EnumBlock enumBlock : EnumBlock.values()){
            annotation = enumBlock.getBlock().getClass().getAnnotation(HasCustomModel.class);
            map.put(annotation.value(), new ResourceLocation(annotation.location()));
            if(enumBlock.getItem() == null) continue;
            annotation = enumBlock.getItem().getClass().getAnnotation(HasCustomModel.class);
            map.put(annotation.value(), new ResourceLocation(annotation.location()));
        }
        for(EnumItem enumItem : EnumItem.values()){
            annotation = enumItem.getItem().getClass().getAnnotation(HasCustomModel.class);
            map.put(annotation.value(), new ResourceLocation(annotation.location()));
        }
        SISInformation.getLogger().debug("trying registration %d custom models", map.size());
        for(Map.Entry<String,Collection<ResourceLocation>> entry : map.asMap().entrySet()){
            try{
                ModelLoaderRegistry.registerLoader(new NormalModelLoader(entry.getKey(), entry.getValue()));
            }catch(ClassNotFoundException e){
                SISInformation.getLogger().info("model class name wrong." + entry, e);
            }
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface HasCustomModel{
        String value();

        String location() default "";
    }

    private static class NormalModelLoader implements ICustomModelLoader{
        private final Class<? extends IModel> modelClass;
        private final ResourceLocation[] location;
        private IResourceManager resourceManager;

        @SuppressWarnings("unchecked")
        private NormalModelLoader(String modelClass, Collection<ResourceLocation> locations) throws ClassNotFoundException{
            this.modelClass = (Class<? extends IModel>) Class.forName(modelClass);
            this.location = Collections2.transform(locations, new Function<ResourceLocation,ResourceLocation>(){
                @Nullable
                @Override
                public ResourceLocation apply(ResourceLocation input){
                    return new ResourceLocation(input.getResourceDomain(), "models/" + input.getResourcePath());
                }
            }).toArray(new ResourceLocation[locations.size()]);
        }

        @Override
        public boolean accepts(ResourceLocation modelLocation){
            return ArrayUtils.contains(location, modelLocation);
        }

        @Override
        @SuppressWarnings("unchecked")
        public IModel loadModel(ResourceLocation modelLocation) throws IOException{
            try{
                return modelClass.getConstructor(IResourceManager.class).newInstance(resourceManager);
            }catch(NoSuchMethodException ignore){
            }catch(InvocationTargetException e){
                throw new AssertionError(e);
            }catch(InstantiationException e){
                throw new AssertionError(e);
            }catch(IllegalAccessException e){
                throw new AssertionError(e);
            }
            try{
                return modelClass.newInstance();
            }catch(IllegalAccessException e){
                throw new AssertionError(e);
            }catch(InstantiationException e){
                throw new AssertionError(e);
            }
        }

        @Override
        public void onResourceManagerReload(IResourceManager resourceManager){
            this.resourceManager = resourceManager;
        }
    }
}
