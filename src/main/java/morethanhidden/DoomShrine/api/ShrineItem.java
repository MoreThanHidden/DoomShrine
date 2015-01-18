package morethanhidden.DoomShrine.api;

import net.minecraft.item.ItemStack;

public class ShrineItem {

	public ItemStack itemStack;
    public int timereturn;
    
	public ShrineItem(ItemStack itemStack, int timereturn) {
		this.itemStack = itemStack;
        this.timereturn = timereturn;
	}
	
	public ItemStack getItem()
    {
        return this.itemStack;
    }
	
	public int getTimeReturn()
    {
        return this.timereturn;
    }


	public boolean doesRequiredItemMatch(ItemStack item) {
		
		if (item == null || this.itemStack == null)
        {
            return false;
        }
		
		return this.itemStack.isItemEqual(item) ? false : true;

	}

}
