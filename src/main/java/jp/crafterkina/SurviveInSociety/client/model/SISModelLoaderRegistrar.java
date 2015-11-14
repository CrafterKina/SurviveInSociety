/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.client.model;

import com.google.common.base.CaseFormat;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import jp.crafterkina.SurviveInSociety.SurviveInSociety;
import jp.crafterkina.SurviveInSociety.block.EnumBlock;
import jp.crafterkina.SurviveInSociety.internal.SISInformation;
import jp.crafterkina.SurviveInSociety.item.EnumItem;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

public class SISModelLoaderRegistrar{
    public static void register(){
        Multimap<Class<? extends IModel>,ResourceLocation> map = HashMultimap.create();
        HasCustomModel annotation;
        for(EnumBlock enumBlock : EnumBlock.values()){
            annotation = wrapAnnotation(enumBlock.getBlock().getClass().getAnnotation(HasCustomModel.class), enumBlock.getName());
            if(annotation != null) map.put(annotation.value(), new ResourceLocation(annotation.location()));
            if(enumBlock.getItem() == null) continue;
            annotation = wrapAnnotation(enumBlock.getItem().getClass().getAnnotation(HasCustomModel.class), enumBlock.getName());
            if(annotation != null) map.put(annotation.value(), new ResourceLocation(annotation.location()));
        }
        for(EnumItem enumItem : EnumItem.values()){
            annotation = wrapAnnotation(enumItem.getItem().getClass().getAnnotation(HasCustomModel.class), CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, enumItem.name()));
            if(annotation != null) map.put(annotation.value(), new ResourceLocation(annotation.location()));
        }
        SISInformation.getLogger().debug("trying registration %d custom models", map.size());
        for(Map.Entry<Class<? extends IModel>,Collection<ResourceLocation>> entry : map.asMap().entrySet()){
            try{
                ModelLoaderRegistry.registerLoader(new NormalModelLoader(entry.getKey(), entry.getValue().toArray(new ResourceLocation[entry.getValue().size()])));
            }catch(ClassNotFoundException e){
                SISInformation.getLogger().info("model class name wrong." + entry, e);
            }
        }
    }

    private static HasCustomModel wrapAnnotation(final HasCustomModel model, final String name){
        return model == null ? null : new HasCustomModel(){
            @Override
            public Class<? extends Annotation> annotationType(){
                return HasCustomModel.class;
            }

            @Override
            public Class<? extends IModel> value(){
                return model.value();
            }

            @Override
            public String location(){
                return SurviveInSociety.PARENT_PACKAGE + ":" + "models/block/" + (model.location().isEmpty() ? name : model.location());
            }
        };
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface HasCustomModel{
        Class<? extends IModel> value();

        String location() default "";
    }

    private static class NormalModelLoader implements ICustomModelLoader{
        private final Class<? extends IModel> modelClass;
        private final ResourceLocation[] location;
        private IResourceManager resourceManager;

        @SuppressWarnings("unchecked")
        private NormalModelLoader(Class<? extends IModel> modelClass, ResourceLocation... locations) throws ClassNotFoundException{
            this.modelClass = modelClass;
            this.location = locations;
        }

        @Override
        public boolean accepts(ResourceLocation modelLocation){
            return ArrayUtils.contains(location, modelLocation);
        }

        @Override
        @SuppressWarnings("unchecked")
        public IModel loadModel(ResourceLocation modelLocation) throws IOException{
            try{
                return modelClass.getConstructor(Object[].class).newInstance(resourceManager, modelLocation);
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
