/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.client.model.models;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import jp.crafterkina.SurviveInSociety.block.EnumBlock;
import jp.crafterkina.SurviveInSociety.block.blocks.BlockBulletinBoard;
import jp.crafterkina.SurviveInSociety.block.entities.TileEntityBulletinBoard;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.*;
import net.minecraftforge.common.property.IExtendedBlockState;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.BufferUtils;

import javax.vecmath.Vector3f;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static net.minecraft.util.EnumFacing.*;

public class ModelBulletinBoard implements IModel{
    @Override
    public Collection<ResourceLocation> getDependencies(){
        return Collections.emptyList();
    }

    @Override
    public Collection<ResourceLocation> getTextures(){
        return Collections.emptyList();
    }

    @Override
    public IFlexibleBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation,TextureAtlasSprite> bakedTextureGetter){
        return new Baked(bakedTextureGetter.apply(new ResourceLocation("blocks/stone")), bakedTextureGetter.apply(new ResourceLocation("blocks/planks_oak")), bakedTextureGetter.apply(new ResourceLocation("items/paper")));
    }

    @Override
    public IModelState getDefaultState(){
        return ModelRotation.X0_Y0;
    }

    @SuppressWarnings("deprecation")
    private class Baked implements IFlexibleBakedModel, ISmartBlockModel{
        private final FaceBakery bakery = new FaceBakery();
        private final TextureAtlasSprite base;
        private final Pair<Vector3f,Vector3f> baseShape = Pair.of(new Vector3f(0, 0, 0), new Vector3f(16, 16, 1));
        private final BlockFaceUV[] baseUVs = new BlockFaceUV[]{new BlockFaceUV(new float[]{0, 0, 16, 1}, 0), new BlockFaceUV(new float[]{0, 0, 1, 16}, 0), new BlockFaceUV(new float[]{0, 0, 16, 16}, 0), new BlockFaceUV(new float[]{0, 0, 16, 16}, 0), new BlockFaceUV(new float[]{0, 0, 1, 16}, 0), new BlockFaceUV(new float[]{0, 0, 1, 16}, 0)};
        private final TextureAtlasSprite frame;
        private final Pair<Vector3f,Vector3f> backFrameShape = Pair.of(new Vector3f(0, 0, 0), new Vector3f(16, 16, 0));
        private final BlockFaceUV backFrameUV = new BlockFaceUV(new float[]{0, 0, 16, 16}, -1);
        private final Pair<Vector3f,Vector3f> leftFrameShape = Pair.of(new Vector3f(0, 0, 0), new Vector3f(1, 16, 2));
        private final Pair<Vector3f,Vector3f> bottomFrameShape = Pair.of(new Vector3f(0, 0, 0), new Vector3f(16, 1, 2));
        private final Pair<Vector3f,Vector3f> topFrameShape = Pair.of(new Vector3f(0, 15, 0), new Vector3f(16, 16, 2));
        private final Pair<Vector3f,Vector3f> rightFrameShape = Pair.of(new Vector3f(15, 0, 0), new Vector3f(16, 16, 2));
        private final BlockFaceUV[] verticalFrameUVs = new BlockFaceUV[]{new BlockFaceUV(new float[]{0, 0, 1, 2}, 0), new BlockFaceUV(new float[]{0, 0, 1, 2}, 0), new BlockFaceUV(new float[]{0, 0, 1, 16}, 0), new BlockFaceUV(new float[]{0, 0, 1, 16}, 0), new BlockFaceUV(new float[]{0, 0, 2, 16}, 0), new BlockFaceUV(new float[]{0, 0, 2, 16}, 0)};
        private final BlockFaceUV[] horizontalFrameUVs = new BlockFaceUV[]{new BlockFaceUV(new float[]{0, 0, 16, 2}, 0), new BlockFaceUV(new float[]{0, 0, 16, 2}, 0), new BlockFaceUV(new float[]{0, 0, 16, 1}, 0), new BlockFaceUV(new float[]{0, 0, 16, 1}, 0), new BlockFaceUV(new float[]{0, 0, 2, 1}, 0), new BlockFaceUV(new float[]{0, 0, 2, 1}, 0)};
        private final TextureAtlasSprite paperTex;
        private List<BakedQuad> paperModel = Collections.emptyList();
        private ITransformation transformation = ModelRotation.X0_Y0;
        private BlockPos pos = BlockPos.ORIGIN;

        private Baked(TextureAtlasSprite base, TextureAtlasSprite frame, TextureAtlasSprite paper){
            this.base = base;
            this.frame = frame;
            this.paperTex = paper;
        }

        @Override
        public List<BakedQuad> getFaceQuads(EnumFacing side){
            return Collections.emptyList();
        }

        @Override
        public List<BakedQuad> getGeneralQuads(){
            List<BakedQuad> quads = Lists.newArrayList();

            quads.add(bakeFace(SOUTH, base, baseShape, baseUVs[SOUTH.getIndex()], 0));

            for(EnumFacing facing : EnumFacing.values()){
                if(facing == NORTH) continue;
                if(!isBoard(WEST)){
                    quads.add(bakeFace(facing, frame, leftFrameShape, verticalFrameUVs[facing.getIndex()], -1));
                }
                if(!isBoard(EAST)){
                    quads.add(bakeFace(facing, frame, rightFrameShape, verticalFrameUVs[facing.getIndex()], -1));
                }
                if(!isBoard(UP)){
                    quads.add(bakeFace(facing, frame, topFrameShape, horizontalFrameUVs[facing.getIndex()], -1));
                }
                if(!isBoard(DOWN)){
                    quads.add(bakeFace(facing, frame, bottomFrameShape, horizontalFrameUVs[facing.getIndex()], -1));
                }
            }

            quads.add(bakeFace(NORTH, frame, backFrameShape, backFrameUV, -1));

            quads.addAll(paperModel);

            return quads;
        }

        private BakedQuad bakeFace(EnumFacing facing, TextureAtlasSprite texture, Pair<Vector3f,Vector3f> shape, BlockFaceUV uv, int tintindex){
            return bakery.makeBakedQuad(shape.getLeft(), shape.getRight(), new BlockPartFace(facing, tintindex, "", uv), texture, facing, transformation, null, true, true);
        }

        private boolean isBoard(EnumFacing facing){
            facing = transformation.rotate(facing);
            BlockPos blockPos = pos.add(facing.getFrontOffsetX(), facing.getFrontOffsetY(), facing.getFrontOffsetZ());
            return Minecraft.getMinecraft().theWorld.getBlockState(blockPos).getBlock() == EnumBlock.BulletinBoard.getBlock() && transformation.equals(((TileEntityBulletinBoard) Minecraft.getMinecraft().theWorld.getTileEntity(blockPos)).getTransformation());
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
            return base;
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
        public IBakedModel handleBlockState(IBlockState state){
            pos = ((IExtendedBlockState) state).getValue(BlockBulletinBoard.POS);
            TileEntityBulletinBoard entity = (TileEntityBulletinBoard) Minecraft.getMinecraft().theWorld.getTileEntity(pos);
            transformation = entity.getTransformation();
            paperModel = entity.getContent() == null ? Collections.<BakedQuad>emptyList() : Collections.singletonList(getQuadsForSprite(-1, paperTex, transformation.rotate(NORTH)));
            return this;
        }

        public BakedQuad getQuadsForSprite(int tint, TextureAtlasSprite sprite, EnumFacing facing){
            VertexFormat format = Attributes.DEFAULT_BAKED_FORMAT;
            ByteBuffer buf = BufferUtils.createByteBuffer(4 * format.getNextOffset());
            switch(facing){
                case NORTH:
                    return buildQuad(buf, format, facing, tint, 1, 0, 1f / 16f + 1e-4f, sprite.getMaxU(), sprite.getMaxV(), 1, 1, 1f / 16f + 1e-4f, sprite.getMaxU(), sprite.getMinV(), 0, 1, 1f / 16f + 1e-4f, sprite.getMinU(), sprite.getMinV(), 0, 0, 1f / 16f + 1e-4f, sprite.getMinU(), sprite.getMaxV());
                case SOUTH:
                    return buildQuad(buf, format, facing, tint, 0, 0, 15f / 16f - 1e-4f, sprite.getMinU(), sprite.getMaxV(), 0, 1, 15f / 16f - 1e-4f, sprite.getMinU(), sprite.getMinV(), 1, 1, 15f / 16f - 1e-4f, sprite.getMaxU(), sprite.getMinV(), 1, 0, 15f / 16f - 1e-4f, sprite.getMaxU(), sprite.getMaxV());
                case WEST:
                    return buildQuad(buf, format, facing, tint, 1f / 16f + 1e-4f, 1, 0, sprite.getMinU(), sprite.getMinV(), 1f / 16f + 1e-4f, 1, 1, sprite.getMaxU(), sprite.getMinV(), 1f / 16f + 1e-4f, 0, 1, sprite.getMaxU(), sprite.getMaxV(), 1f / 16f + 1e-4f, 0, 0, sprite.getMinU(), sprite.getMaxV());
                case EAST:
                    return buildQuad(buf, format, facing, tint, 15f / 16f - 1e-4f, 0, 1, sprite.getMaxU(), sprite.getMaxV(), 15f / 16f - 1e-4f, 1, 1, sprite.getMaxU(), sprite.getMinV(), 15f / 16f - 1e-4f, 1, 0, sprite.getMinU(), sprite.getMinV(), 15f / 16f - 1e-4f, 0, 0, sprite.getMinU(), sprite.getMaxV());
            }
            throw new IllegalArgumentException();
        }

        private BakedQuad buildQuad(ByteBuffer buf, VertexFormat format, EnumFacing side, int tint, float x0, float y0, float z0, float u0, float v0, float x1, float y1, float z1, float u1, float v1, float x2, float y2, float z2, float u2, float v2, float x3, float y3, float z3, float u3, float v3){
            buf.clear();
            putVertex(buf, format, side, x0, y0, z0, u0, v0);
            putVertex(buf, format, side, x1, y1, z1, u1, v1);
            putVertex(buf, format, side, x2, y2, z2, u2, v2);
            putVertex(buf, format, side, x3, y3, z3, u3, v3);
            buf.flip();
            int[] data = new int[4 * format.getNextOffset() / 4];
            buf.asIntBuffer().get(data);
            return new BakedQuad(data, tint, side);
        }

        private void put(ByteBuffer buf, VertexFormatElement e, Float... fs){
            Attributes.put(buf, e, true, 0f, fs);
        }

        @SuppressWarnings("unchecked")
        private void putVertex(ByteBuffer buf, VertexFormat format, EnumFacing side, float x, float y, float z, float u, float v){
            for(VertexFormatElement e : (List<VertexFormatElement>) format.getElements()){
                switch(e.getUsage()){
                    case POSITION:
                        put(buf, e, x, y, z, 1f);
                        break;
                    case COLOR:
                        put(buf, e, 1f, 1f, 1f, 1f);
                        break;
                    case UV:
                        put(buf, e, u, v, 0f, 1f);
                        break;
                    case NORMAL:
                        put(buf, e, (float) side.getFrontOffsetX(), (float) side.getFrontOffsetY(), (float) side.getFrontOffsetZ(), 0f);
                        break;
                    default:
                        put(buf, e);
                        break;
                }
            }
        }
    }
}
