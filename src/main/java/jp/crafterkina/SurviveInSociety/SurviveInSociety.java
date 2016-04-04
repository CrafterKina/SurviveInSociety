package jp.crafterkina.SurviveInSociety;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.InstanceFactory;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = SurviveInSociety.MOD_ID)
public enum SurviveInSociety{
    INSTANCE;

    public static final String MOD_ID = "jp.crafterkina.SurviveInSociety";

    @InstanceFactory
    private static SurviveInSociety getInstance(){
        return INSTANCE;
    }

    /*
    FML Internal Event
    @EventHandler
    private void construct(FMLConstructionEvent event){
    }
    */

    @EventHandler
    private void fingerprint(FMLFingerprintViolationEvent event){
    }

    @EventHandler
    private void preInit(FMLPreInitializationEvent event){
    }

    @EventHandler
    private void handleIMC(FMLInterModComms.IMCEvent event){
    }

    @EventHandler
    private void init(FMLInitializationEvent event){
    }

    @EventHandler
    private void postInit(FMLPostInitializationEvent event){
    }

    /*
    FML Internal Event
    @EventHandler
    private void complete(FMLLoadCompleteEvent event){
    }
    */

    @EventHandler
    private void handleServerAboutToStart(FMLServerAboutToStartEvent event){
    }

    @EventHandler
    private void handleServerStarting(FMLServerStartingEvent event){
    }

    @EventHandler
    private void handleServerStarted(FMLServerStartedEvent event){
    }

    @EventHandler
    private void handleServerStopping(FMLServerStoppingEvent event){
    }

    @EventHandler
    private void handleServerStopped(FMLServerStoppedEvent event){
    }
}