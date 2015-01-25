package morethanhidden.DoomShrine.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import morethanhidden.DoomShrine.DoomShrine;
import morethanhidden.DoomShrine.TileDoomShrine;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DoomShrineBlock extends BlockContainer
{
    private ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

	public DoomShrineBlock()
	{
		super(Material.rock);
		setBlockName("DoomShrine");
		setCreativeTab(DoomShrine.tabdoomshrine);
		setStepSound(soundTypePiston);
		setHardness(3.0F);
		setResistance(5.0F);
		setHarvestLevel("pickaxe", 2);
		
	}
	

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9){
		
		TileDoomShrine te = (TileDoomShrine) world.getTileEntity(x, y, z);

		if (te == null || player.isSneaking()){
			return false;
	    }

		 ItemStack playerItem = player.getCurrentEquippedItem();

		if (te.getOwner() == null){
			te.setOwner(player.getCommandSenderName());
		}

		if (te.getOwner() == player.getCommandSenderName()) {

			if (te.getStackInSlot(0) == null && playerItem != null) {
				ItemStack newItem = playerItem.copy();
				newItem.stackSize = 1;
				--playerItem.stackSize;
				te.setInventorySlotContents(0, newItem);


			} else if (te.getStackInSlot(0) != null && playerItem == null) {
				player.inventory.addItemStackToInventory(te.getStackInSlot(0));
				te.setInventorySlotContents(0, null);
			}

		}else if (world.isRemote){player.addChatComponentMessage(new ChatComponentTranslation("Sorry this Shrine belongs to: " + te.getOwner()));}

		world.markBlockForUpdate(x, y, z);
		return true;
		
	} 

	
	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
		
		final TileDoomShrine te = (TileDoomShrine) world.getTileEntity(x, y, z);
			if (te.getStackInSlot(0) != null){
				ItemStack itemstack = te.getStackInSlot(0);
				ret.add(itemstack);
			}
		return super.removedByPlayer(world, player, x, y, z, willHarvest);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack par6ItemStack){
		if( entityLivingBase instanceof EntityPlayer ) {
			EntityPlayer player = (EntityPlayer) entityLivingBase;

			final TileDoomShrine te = (TileDoomShrine) world.getTileEntity(x, y, z);

			te.setOwner(player.getCommandSenderName());
		}
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ret.add(new ItemStack (DoomShrine.doomShrineBlock, 1));
		return ret;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
	 return false;
	}
	
    @Override
    public boolean hasTileEntity()
    {
        return true;
    }  
    
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileDoomShrine();
	}
	
	@Override
    public boolean renderAsNormalBlock() {
            return false;
    }
    
	@Override
	public void registerBlockIcons(IIconRegister icon){
            this.blockIcon = icon.registerIcon("doomshrine:DoomShrine");
    }

}