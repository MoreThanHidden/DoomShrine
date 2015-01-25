package morethanhidden.DoomShrine;

import morethanhidden.DoomShrine.api.ShrineItemRegistry;
import morethanhidden.DoomShrine.entity.ExtendedPlayerData;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.Packet;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import cpw.mods.fml.common.Optional;

import java.util.List;


public class TileDoomShrine extends TileEntity implements IInventory{
	
	private ItemStack[] inventory;
	private String Owner;
	protected DataWatcher dataWatcher;
	private int Owner_DataID = 12;

	public TileDoomShrine(){
		this.inventory = new ItemStack[1];
	}
	
	@Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        
        NBTTagList tagList = data.getTagList("Inventory", Constants.NBT.TAG_COMPOUND);
		Owner = data.getString("Owner");

        for (int i = 0; i < tagList.tagCount(); i++)
        	        {
        	            NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
        	            int slot = tag.getByte("Slot");

        	            if (slot >= 0 && slot < inventory.length)
        	            {
        	                inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
        	            }
        	        }
    }

	@Override
    public void writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        
        NBTTagList itemList = new NBTTagList();
		data.setString("OwnerName", Owner);

        for (int i = 0; i < inventory.length; i++)
        {
            ItemStack stack = inventory[i];

            if (inventory[i] != null)
            {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                inventory[i].writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }
        
        data.setTag("Inventory", itemList);

    }
 
    
	
		@Override
		public int getSizeInventory() {
			return inventory.length;
		}

		@Override
		public ItemStack getStackInSlot(int slot) {
			return inventory[slot];
		}

		@Override
		public ItemStack decrStackSize(int slot, int amount) {
			ItemStack stack = getStackInSlot(slot);

	        if (stack != null)
	        {
	            if (stack.stackSize <= amount)
	            {
	                setInventorySlotContents(slot, null);
	            } else {
	                stack = stack.splitStack(amount);

	                if (stack.stackSize == 0){
	                    setInventorySlotContents(slot, null);
	                }
	            }
	        }

	        return stack;
		}

		@Override
		public ItemStack getStackInSlotOnClosing(int slot) {
			ItemStack stack = getStackInSlot(slot);

	        if (stack != null)
	        {
	            setInventorySlotContents(slot, null);
	        }
	        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	        return stack;
		}
		
		@Override
		public void setInventorySlotContents(int slot, ItemStack itemstack) {
			inventory[slot] = itemstack;
	        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

	        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
	        {
	            itemstack.stackSize = getInventoryStackLimit();
	        }
		}

		public void setOwner(String OwnerName){
			this.Owner = OwnerName;
		}

		public String getOwner() {
			return this.Owner;
		}

		@Override
		public String getInventoryName() {
			return "TestBlock";
		}

		@Override
		public boolean hasCustomInventoryName() {
			return false;
		}

		@Override
		public int getInventoryStackLimit() {
			return 1;
		}

		@Override
		public boolean isUseableByPlayer(EntityPlayer player) {
			return true;
		}

		@Override
		public void openInventory() {

		}

		@Override
		public void closeInventory() {
		}

		@Override
		public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
			return true;
		}

		@Override
		public void updateEntity(){

			if (this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord)){

				if (getStackInSlot(0) != null){

					int Time = ShrineItemRegistry.getTimeforItem(this.getStackInSlot(0));

					if (Time != 0) {
						EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().func_152612_a(Owner);
						ExtendedPlayerData properties = ExtendedPlayerData.get(player);
						properties.increaseShrineTimer(Time);
						setInventorySlotContents(0, null);
					}
				}
			}
		}
}


