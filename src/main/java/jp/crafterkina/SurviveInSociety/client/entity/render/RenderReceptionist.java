/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.client.entity.render;

import jp.crafterkina.SurviveInSociety.SurviveInSociety;
import jp.crafterkina.SurviveInSociety.client.entity.model.ModelReceptionist;
import jp.crafterkina.SurviveInSociety.entity.entities.EntityReceptionist;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderReceptionist extends RenderBiped{
    public RenderReceptionist(RenderManager renderManager){
        super(renderManager, new ModelReceptionist(), 0.5f);
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerCustomHead(this.getPlayerModel().bipedHead));
    }

    /**
     * returns the more specialized type of the model as the player model.
     */
    public ModelReceptionist getPlayerModel(){
        return (ModelReceptionist) super.getMainModel();
    }

    public void doRender(EntityReceptionist p_180596_1_, double p_180596_2_, double p_180596_4_, double p_180596_6_, float p_180596_8_, float p_180596_9_){
        double d3 = p_180596_4_;

        if(p_180596_1_.isSneaking() && !(p_180596_1_.worldObj.isRemote)){
            d3 = p_180596_4_ - 0.125D;
        }

        super.doRender((EntityLivingBase) p_180596_1_, p_180596_2_, d3, p_180596_6_, p_180596_8_, p_180596_9_);
    }

    public void transformHeldFull3DItemLayer(){
        GlStateManager.translate(0.0F, 0.1875F, 0.0F);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity>) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doe
     *
     * @param entityYaw
     *         The yaw rotation of the passed entity
     */
    public void doRender(EntityLivingBase entity, double x, double y, double z, float entityYaw, float partialTicks){
        this.doRender((EntityReceptionist) entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity entity){
        return new ResourceLocation(SurviveInSociety.PARENT_PACKAGE, "textures/entity/receptionist.png");
    }

    public ModelBase getMainModel(){
        return this.getPlayerModel();
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity>) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doe
     *
     * @param entityYaw
     *         The yaw rotation of the passed entity
     */
    public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks){
        this.doRender((EntityReceptionist) entity, x, y, z, entityYaw, partialTicks);
    }
}
