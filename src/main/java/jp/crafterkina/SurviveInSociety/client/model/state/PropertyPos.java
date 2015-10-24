/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.client.model.state;

import com.google.common.base.Predicate;
import net.minecraft.util.BlockPos;

public class PropertyPos extends UnlistedPropertyBase<BlockPos>{
    public PropertyPos(String name){
        super(name);
    }

    public PropertyPos(String name, Predicate<BlockPos> validator){
        super(name, validator);
    }

    @Override
    public Class<BlockPos> getType(){
        return BlockPos.class;
    }
}
