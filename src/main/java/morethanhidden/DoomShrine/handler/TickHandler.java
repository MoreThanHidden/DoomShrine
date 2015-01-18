package morethanhidden.DoomShrine.handler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import morethanhidden.DoomShrine.DoomShrine;
import morethanhidden.DoomShrine.entity.ExtendedPlayerData;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.awt.*;
import java.util.Calendar;

import org.lwjgl.opengl.GL11;

public class TickHandler {

    public static boolean stop = false;
    public static long timeInMili = 0;	
    
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
    	
      if (event.phase == TickEvent.Phase.END) {
    	  if (event.player !=  null){
    		  
    		  ExtendedPlayerData properties = ExtendedPlayerData.get(event.player);
    		  
    		  if (!stop) {
    			  
    			  	timeInMili++;
    			  	
                	if(timeInMili > 20){
                		if (properties.decreaseShrineTimer(1)){
                			timeInMili = 0;
                		}
                	}
                	
                if (properties.getShrineTimer() <= 0) {
                    stop = true;
                }
                
            if (stop) {
            		properties.resetShrineTimer();
            		event.player.worldObj.spawnEntityInWorld(new EntityLightningBolt(event.player.worldObj, event.player.posX, event.player.posY, event.player.posZ));
            		event.player.setHealth(0);
                    stop = false;
            }
            }
          }
    	}
      }
}