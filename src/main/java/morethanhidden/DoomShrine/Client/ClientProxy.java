package morethanhidden.DoomShrine.Client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import morethanhidden.DoomShrine.DoomShrine;
import morethanhidden.DoomShrine.TileDoomShrine;
import morethanhidden.DoomShrine.common;
import morethanhidden.DoomShrine.handler.GuiDoomShrine;
import morethanhidden.DoomShrine.renderers.RenderDoomShrine;
import morethanhidden.DoomShrine.renderers.RenderDoomShrineItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderBlaze;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends common {
	
	@Override
		public void registerRenderers() {
		
		//Doom Shrine
        ClientRegistry.bindTileEntitySpecialRenderer(TileDoomShrine.class, new RenderDoomShrine());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DoomShrine.doomShrineBlock), new RenderDoomShrineItem(new RenderDoomShrine(), new TileDoomShrine()));
        
        //GUI
        MinecraftForge.EVENT_BUS.register(new GuiDoomShrine(Minecraft.getMinecraft()));
        
	}
	

}
