/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.client.model.models;

import com.google.common.base.CaseFormat;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimaps;
import jp.crafterkina.SurviveInSociety.block.EnumBlock;
import jp.crafterkina.SurviveInSociety.item.EnumItem;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.*;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ModelCube implements IModel{
    public ListMultimap<EnumFacing,Pair<ResourceLocation,Integer>> textures;

    public ModelCube(Object... args){
        ResourceLocation location = (ResourceLocation) args[1];
        String name = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, location.toString().replaceFirst(".+:models/(block|item)/", ""));
        if(location.getResourcePath().contains("block")){
            EnumBlock enumBlock = EnumBlock.valueOf(name);
            if(enumBlock.getBlock() instanceof User){
                textures = ((User) enumBlock.getBlock()).getTextures();
            }
        }else if(location.getResourcePath().contains("item")){
            EnumItem enumItem = EnumItem.valueOf(name);
            if(enumItem.getItem() instanceof User){
                textures = ((User) enumItem.getItem()).getTextures();
            }
        }
    }

    @Override
    public Collection<ResourceLocation> getDependencies(){
        return Collections.emptyList();
    }

    @Override
    public Collection<ResourceLocation> getTextures(){
        return Collections2.transform(textures.values(), new Function<Pair<ResourceLocation,Integer>,ResourceLocation>(){
            @Nullable
            @Override
            public ResourceLocation apply(Pair<ResourceLocation,Integer> input){
                return input.getLeft();
            }
        });
    }

    @Override
    public IFlexibleBakedModel bake(IModelState state, VertexFormat format, final Function<ResourceLocation,TextureAtlasSprite> bakedTextureGetter){
        return new Baked(Multimaps.transformValues(textures, new Function<Pair<ResourceLocation,Integer>,Pair<TextureAtlasSprite,Integer>>(){
            @Nullable
            @Override
            public Pair<TextureAtlasSprite,Integer> apply(@Nullable Pair<ResourceLocation,Integer> input){
                return input != null ? Pair.of(bakedTextureGetter.apply(input.getLeft()), input.getRight()) : null;
            }
        }));
    }

    @Override
    public IModelState getDefaultState(){
        return ModelRotation.X0_Y0;
    }

    public interface User{
        ListMultimap<EnumFacing,Pair<ResourceLocation,Integer>> getTextures();
    }

    @SuppressWarnings("deprecation")
    private class Baked implements IFlexibleBakedModel, IPerspectiveAwareModel{
        private final ListMultimap<EnumFacing,Pair<TextureAtlasSprite,Integer>> multimap;
        private final Vector3f from = new Vector3f(0, 0, 0);
        private final Vector3f to = new Vector3f(16, 16, 16);
        private final BlockFaceUV uv = new BlockFaceUV(new float[]{0.0F, 0.0F, 16.0F, 16.0F}, 0);
        private final FaceBakery bakery = new FaceBakery();
        private final Vector3f translationThirdPerson = new Vector3f(0.0F, 0.1F, -0.175F);
        private final Vector3f scaleThirdPerson = new Vector3f(0.375F, 0.375F, 0.375F);
        private final Quat4f rotateThirdPerson = TRSRTransformation.quatFromYXZDegrees(new Vector3f(10, 0, 0));
        private final Quat4f rotateFirstPerson = TRSRTransformation.quatFromYXZDegrees(new Vector3f(0, 90, 0));

        {
            rotateThirdPerson.mul(TRSRTransformation.quatFromYXZDegrees(new Vector3f(0, -45, 0)));
            rotateThirdPerson.mul(TRSRTransformation.quatFromYXZDegrees(new Vector3f(0, 0, 170)));
        }

        private Baked(ListMultimap<EnumFacing,Pair<TextureAtlasSprite,Integer>> multimap){
            this.multimap = multimap;
        }

        @Override
        public List<BakedQuad> getFaceQuads(final EnumFacing side){
            return Lists.transform(multimap.get(side), new Function<Pair<TextureAtlasSprite,Integer>,BakedQuad>(){
                @Nullable
                @Override
                public BakedQuad apply(@Nullable Pair<TextureAtlasSprite,Integer> input){
                    return input != null ? bakeQuad(side, input.getLeft(), input.getRight()) : null;
                }
            });
        }

        private BakedQuad bakeQuad(EnumFacing facing, TextureAtlasSprite texture, int tintindex){
            return bakery.makeBakedQuad(from, to, new BlockPartFace(EnumFacing.NORTH, tintindex, "", uv), texture, facing, ModelRotation.X0_Y0, null, true, true);
        }

        @Override
        public List<BakedQuad> getGeneralQuads(){
            return Collections.emptyList();
        }

        @Override
        public boolean isAmbientOcclusion(){
            return true;
        }

        @Override
        public boolean isGui3d(){
            return true;
        }

        @Override
        public boolean isBuiltInRenderer(){
            return false;
        }

        @Override
        public TextureAtlasSprite getTexture(){
            return multimap.get(null).iterator().next().getLeft();
        }

        @Override
        public ItemCameraTransforms getItemCameraTransforms(){
            return ItemCameraTransforms.DEFAULT;
        }

        @Override
        public VertexFormat getFormat(){
            return Attributes.DEFAULT_BAKED_FORMAT;
        }

        @Override
        public Pair<IBakedModel,Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType){
            Matrix4f matrix4f = null;
            if(ItemCameraTransforms.TransformType.THIRD_PERSON == cameraTransformType){
                matrix4f = TRSRTransformation.mul(translationThirdPerson, rotateThirdPerson, scaleThirdPerson, null);
            }else if(ItemCameraTransforms.TransformType.FIRST_PERSON == cameraTransformType){
                matrix4f = TRSRTransformation.mul(null, rotateFirstPerson, null, null);
            }
            return Pair.of((IBakedModel) this, matrix4f);
        }
    }
}
