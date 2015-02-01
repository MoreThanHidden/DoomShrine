package morethanhidden.DoomShrine.integration.tweaker;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import morethanhidden.DoomShrine.api.ShrineItem;
import morethanhidden.DoomShrine.api.ShrineItemRegistry;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * MineTweaker3 DoomShrine
 */

@ZenClass("mods.doomshrine.doomshrine")
public class DoomShrine
{
    @ZenMethod
    public static void addRecipe(ItemStack input, int time) {
        MineTweakerAPI.apply(new Add(new ShrineItem(input, time)));
    }

    private static class Add implements IUndoableAction 
    {
        private final ShrineItem sItem;
        
        public Add(ShrineItem sitem) 
        {
            this.sItem = sitem;
        }
        
        @Override
        public void apply() 
        {
        	ShrineItemRegistry.shrineItem.add(sItem);
        }

        @Override
        public boolean canUndo() 
        {
            return ShrineItemRegistry.shrineItem != null;
        }

        @Override
        public void undo() 
        {
        	ShrineItemRegistry.shrineItem.remove(sItem);
        }

        @Override
        public String describe() 
        {
            return "Adding Shrine Recipe for " + ((ShrineItem) sItem).getItem().getDisplayName();
        }

        @Override
        public String describeUndo() 
        {
            return "Removing Shrine Recipe for " + ((ShrineItem) sItem).getItem().getDisplayName();
        }

        @Override
        public Object getOverrideKey() 
        {
            return null;
        }
    }

    @ZenMethod
    public static void removeRecipe(ItemStack input) {
        MineTweakerAPI.apply(new Remove(input));
    }

    private static class Remove implements IUndoableAction 
    {
        private final ItemStack input;
        private ShrineItem sItem;
        
        public Remove(ItemStack input) 
        {
            this.input = input;
        }
        
        @Override
        public void apply() 
        {
            for (ShrineItem r : ShrineItemRegistry.shrineItem) 
            {
                if (r.getItem() != null && r.getItem().isItemEqual(input)) 
                {
                    sItem = r;
                    break;
                }
            }

            ShrineItemRegistry.shrineItem.remove(sItem);
        }

        @Override
        public boolean canUndo() 
        {
            return ShrineItemRegistry.shrineItem != null && sItem != null;
        }

        @Override
        public void undo() 
        {
        	ShrineItemRegistry.shrineItem.add(sItem);
        }

        @Override
        public String describe() 
        {
            return "Removing Shrine Recipe for " + input.getDisplayName();
        }

        @Override
        public String describeUndo() 
        {
            return "Restoring Shrine Recipe for " + input.getDisplayName();
        }

        @Override
        public Object getOverrideKey() 
        {
            return null;
        }
    }
}