package morethanhidden.DoomShrine.items;

import morethanhidden.DoomShrine.DoomShrine;
import net.minecraft.item.Item;

public class Leaf  extends Item {

    public Leaf() {
            setMaxStackSize(64);
            setCreativeTab(DoomShrine.tabdoomshrine);
            setUnlocalizedName("Leaf");
            setTextureName("doomshrine:leaf");
    }
}