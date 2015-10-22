/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.client.model;

import jp.crafterkina.SurviveInSociety.block.EnumBlock;
import jp.crafterkina.SurviveInSociety.item.EnumItem;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class SISModelLoaderRegistrar{
    public static void register(){
        for(EnumBlock enumBlock : EnumBlock.values()){
            registerLoader(enumBlock.getBlock().getClass().getAnnotation(HasCustomModelLoader.class));
        }
        for(EnumItem enumItem : EnumItem.values()){
            registerLoader(enumItem.getItem().getClass().getAnnotation(HasCustomModelLoader.class));
        }
    }

    private static void registerLoader(HasCustomModelLoader annotation){
        if(annotation == null) return;
        String className = annotation.value();
        ICustomModelLoader loader;
        try{
            loader = (ICustomModelLoader) Class.forName(className).newInstance();
        }catch(InstantiationException e){
            return;
        }catch(IllegalAccessException e){
            return;
        }catch(ClassNotFoundException e){
            return;
        }
        ModelLoaderRegistry.registerLoader(loader);
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface HasCustomModelLoader{
        String value();
    }
}
