package morethanhidden.DoomShrine.api;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class ShrineItemRegistry {

	public static List<ShrineItem> shrineItem = new LinkedList();
	
	 public static void registerShrineItem(ItemStack itemStack, int timereturn)
	    {
		 shrineItem.add(new ShrineItem(itemStack, timereturn));
	    }
	 
	 public static void removeShrineItem(ItemStack itemStack)
	    {
		 for (ShrineItem item : shrineItem)
	        {
	            if (item.doesRequiredItemMatch(itemStack))
	            {
	            	shrineItem.remove(itemStack);
	            }else{
	            	System.out.println("Couldn't Remove Item:" + itemStack.getDisplayName());
	            }
	        }
	    }
	 
	 public static int getTimeforItem(ItemStack testItem)
	    {
	        for (ShrineItem item : shrineItem)
	        {
	            if (item.doesRequiredItemMatch(testItem))
	            {
	                return item.getTimeReturn();
	            }
	        }

	        return 0;
	    }
}