/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.client.entity.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderReceptionist extends RenderBiped{
    public RenderReceptionist(RenderManager renderManager){
        super(renderManager, new ModelBiped(1, 0, 64, 64), 1);
    }
}
