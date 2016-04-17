package jp.crafterkina.SurviveInSociety.org;

import com.google.common.collect.Lists;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraftforge.common.util.Constants;

import java.util.List;

import static jp.crafterkina.SurviveInSociety.SurviveInSociety.*;

public class OrganizationManager extends WorldSavedData{
    private List<Organization> organizationList = Lists.newArrayList();

    public OrganizationManager(String name){
        super(name);
    }

    public OrganizationManager(World world){
        super(MOD_ID+":organization"+world.provider.getDimensionType().getSuffix());
    }

    public static OrganizationManager getManager(World world){
        if(world == null)return null;
        if(world.getMinecraftServer() == null)return null;

    }

    @Override
    public void readFromNBT(NBTTagCompound nbt){
        NBTTagList rawList = nbt.getTagList("Organizations", Constants.NBT.TAG_COMPOUND);
        for(int i = 0; i < rawList.tagCount(); i++){
            Organization org = new Organization();
            org.deserializeNBT(rawList.getCompoundTagAt(i));
            organizationList.add(org);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt){
        NBTTagList tagList = new NBTTagList();
        for(Organization org : organizationList){
            tagList.appendTag(org.serializeNBT());
        }
        nbt.setTag("Organizations",tagList);
    }
}