/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.client.model.state;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.property.IUnlistedProperty;

public class BlockPosProperty implements IUnlistedProperty<BlockPos>{
    private final String name;
    private final Predicate<BlockPos> validator;

    public BlockPosProperty(String name, Predicate<BlockPos> validator){
        this.name = name;
        this.validator = validator;
    }

    public BlockPosProperty(String name){
        this(name, Predicates.<BlockPos>alwaysTrue());
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public boolean isValid(BlockPos value){
        return validator.apply(value);
    }

    @Override
    public Class<BlockPos> getType(){
        return BlockPos.class;
    }

    @Override
    public String valueToString(BlockPos value){
        return value.toString();
    }
}
