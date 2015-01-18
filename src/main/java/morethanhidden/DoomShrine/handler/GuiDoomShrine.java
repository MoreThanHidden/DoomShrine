package morethanhidden.DoomShrine.handler;

import java.text.DecimalFormat;

import morethanhidden.DoomShrine.entity.ExtendedPlayerData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiDoomShrine extends Gui{
	private Minecraft mc;

	private static final ResourceLocation texture = new ResourceLocation("doomshrine:textures/gui/doom_gui.png");
	 
	public GuiDoomShrine(Minecraft mc){
	    super();
	    this.mc = mc;
	  }

	  @SubscribeEvent(priority = EventPriority.NORMAL)
	  public void onRenderExperienceBar(RenderGameOverlayEvent event){
		  
		    if(event.isCancelable() || event.type != ElementType.EXPERIENCE) {      
		      return;
		    }
		    
		    //GUI Position
		  	int xPos = 2;
			int yPos = 12;
			
			//Calculate Minutes and seconds
			int Time = 0;
			
			if (this.mc.thePlayer != null){
				ExtendedPlayerData properties = ExtendedPlayerData.get(this.mc.thePlayer);
				if (properties != null) {
					Time = properties.getShrineTimer();
				}
			}

			//Format with Two Digits
			DecimalFormat formatter = new DecimalFormat("00");
			String minutes = formatter.format(Time / 60);
			String seconds = formatter.format(Time - (Time / 60 * 60));
			
			int MaxWidth = 50;
			int currentwidth = Time * MaxWidth / (12000 / 20);
			
			this.mc.getTextureManager().bindTexture(texture);
			
			drawTexturedModalRect(xPos, yPos - 1, 0, 0, 56, 9);
			drawTexturedModalRect(xPos + 3, yPos + 2, 0, 9, currentwidth, 3);
			
			yPos += 10;
			
			String s = "Doom Shrine";
			String t = minutes + ":" + seconds;
			
			this.mc.fontRenderer.drawString(s, xPos + 1, yPos - 20, 0);
			this.mc.fontRenderer.drawString(s, xPos - 1, yPos - 20, 0);
			this.mc.fontRenderer.drawString(s, xPos, yPos - 19, 0);
			this.mc.fontRenderer.drawString(s, xPos, yPos - 21, 0);
			this.mc.fontRenderer.drawString(s, xPos, yPos - 20, 0xAA0000);
			
			this.mc.fontRenderer.drawString(t, xPos + 16, yPos, 0);
			this.mc.fontRenderer.drawString(t, xPos + 14, yPos, 0);
			this.mc.fontRenderer.drawString(t, xPos + 15, yPos + 1, 0);
			this.mc.fontRenderer.drawString(t, xPos + 15, yPos - 1, 0);
			this.mc.fontRenderer.drawString(t, xPos + 15, yPos, 0xAA00AA);
			
	  }
}
