package morethanhidden.DoomShrine.items;

import morethanhidden.DoomShrine.DoomShrine;
import net.minecraft.item.Item;

public class Scriptures extends Item {

    public Scriptures() {
            setMaxStackSize(64);
            setCreativeTab(DoomShrine.tabdoomshrine);
            setUnlocalizedName("Scriptures");
            setTextureName("doomshrine:Scriptures");
    }
}