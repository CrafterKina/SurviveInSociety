package jp.crafterkina.SurviveInSociety.block;

import jp.crafterkina.SurviveInSociety.SurviveInSociety;
import jp.crafterkina.SurviveInSociety.block.blocks.BlockCapitalTorch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum BlockRegisterer{
    ;

    private static final Logger LOGGER = LogManager.getLogger(SurviveInSociety.MOD_ID);
    
    public static void register(){
        BlockCapitalTorch.register();
    }
}
