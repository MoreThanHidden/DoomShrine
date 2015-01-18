package morethanhidden.DoomShrine;

import morethanhidden.DoomShrine.blocks.DoomShrineBlock;
import morethanhidden.DoomShrine.handler.DoomEventHandler;
import morethanhidden.DoomShrine.handler.GuiDoomShrine;
import morethanhidden.DoomShrine.handler.TickHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

	@Mod(modid="DoomShrine", name="Doom Shrine", version="0.3.4")
	//@NetworkMod(clientSideRequired=true) // not used in 1.7
	public class DoomShrine {
			//Items I have Added

			public static Block doomShrineBlock;
			
	        @Instance(value = "DoomShrine")
	        public static DoomShrine instance;
	        
	        @SidedProxy(clientSide="morethanhidden.DoomShrine.Client.ClientProxy", serverSide="morethanhidden.DoomShrine.common")
	        public static common proxy;
	        
	        @EventHandler
	        public void preLoad(FMLPreInitializationEvent PreEvent) {
	        }
	        
	        public static CreativeTabs tabdoomshrine = new CreativeTabs("Doom Shrine") {
	            @Override
	            @SideOnly(Side.CLIENT)
	            public Item getTabIconItem() {
	                return Item.getItemFromBlock(doomShrineBlock);
	            }
	        };
	        
	        @EventHandler
	        public void init(FMLInitializationEvent event)
	        {
	        	//Register Handlers
	        	TickHandler tickHandler = new TickHandler();
	            FMLCommonHandler.instance().bus().register(tickHandler);
	            
	        	DoomEventHandler eventHandler = new DoomEventHandler();
	            MinecraftForge.EVENT_BUS.register(eventHandler);
	            FMLCommonHandler.instance().bus().register(eventHandler);
	        }
	        
	        @EventHandler
	        public void preInit(FMLPreInitializationEvent event) {  
	        	
	        	
	        	doomShrineBlock = new DoomShrineBlock();
	        	
	        	GameRegistry.registerTileEntity(TileDoomShrine.class, "TileDoomShrine");
	        	GameRegistry.registerBlock(doomShrineBlock, "DoomShrine");
	        }
	        
	        @EventHandler 
	        public void load(FMLInitializationEvent event) {
	        	proxy.registerRenderers();
	        	addNames();
	        }
	        
	        public static void addNames(){
	                LanguageRegistry.addName(doomShrineBlock, "Doom Shrine");
	                LanguageRegistry.instance().addStringLocalization("itemGroup.Doom Shrine", "en_US", "Doom Shrine");
	        }
	        
	        @EventHandler
	        public void postInit(FMLPostInitializationEvent event) {
	        	
	        }
	        
	}
