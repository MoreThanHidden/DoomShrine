package morethanhidden.DoomShrine.integration.tweaker;


import minetweaker.MineTweakerAPI;

/**
 * MineTweaker3 Integration
 */
public class MineTweakerIntegration {
	 public static void register() 
	    {
	        MineTweakerAPI.registerClass(DoomShrine.class);
	    }
}
