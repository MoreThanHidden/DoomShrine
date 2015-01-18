package morethanhidden.DoomShrine.handler;

import morethanhidden.DoomShrine.DoomShrine;
import morethanhidden.DoomShrine.entity.ExtendedPlayerData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.player.PlayerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class DoomEventHandler {
	

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer && ExtendedPlayerData.get((EntityPlayer) event.entity) == null){
				EntityPlayer player = (EntityPlayer)event.entity;
				ExtendedPlayerData.register(player);
		}
	}
	
	@SubscribeEvent
	public void onPlayerJoined(PlayerLoggedInEvent event) {
		 // Lookup player
        ExtendedPlayerData prop = ExtendedPlayerData.get(event.player);
        
        // Give New Players a Doom Shrine
        if (!prop.GivenShrine) {
        	prop.GivenShrine = true;
        	event.player.inventory.addItemStackToInventory(new ItemStack(DoomShrine.doomShrineBlock));
        }
        
	   }
	
	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone event) {
		ExtendedPlayerData.get(event.entityPlayer).copy(ExtendedPlayerData.get(event.original));
	}
}
