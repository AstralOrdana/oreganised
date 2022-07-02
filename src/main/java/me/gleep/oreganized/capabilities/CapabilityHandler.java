package me.gleep.oreganized.capabilities;

import me.gleep.oreganized.Oreganized;
import me.gleep.oreganized.capabilities.engravedblockscap.EngravedBlocksProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityHandler{
    public static final ResourceLocation ENGRAVEDBLOCKS_CAP = new ResourceLocation( Oreganized.MOD_ID , "engravedblocks" );

    // Capability Attaching Event for Levels
    @SubscribeEvent
    public void attachLevelCapabilities( AttachCapabilitiesEvent <Level> event ){
        EngravedBlocksProvider engravedBlocksProvider = new EngravedBlocksProvider();
        event.addCapability( ENGRAVEDBLOCKS_CAP, engravedBlocksProvider );
        event.addListener( engravedBlocksProvider::invalidate );
    }

    // Capability Attaching Event for Entities
    @SubscribeEvent
    public void attachEntityCapabilities( AttachCapabilitiesEvent <Entity> event ){
        if( event.getObject() instanceof LivingEntity ) {

        }
    }
}
