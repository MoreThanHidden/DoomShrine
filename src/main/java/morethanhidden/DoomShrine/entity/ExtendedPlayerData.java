package morethanhidden.DoomShrine.entity;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayerData implements IExtendedEntityProperties {
	
	private final EntityPlayer entplayer;
	private int maxTime;
	public boolean GivenShrine;
	public final static String PlayerData = "DoomShrinePlayerData";
	public int defaultTimeInTicks = 12000;
	
	public static final int Time_Watcher = 12;
	
	public ExtendedPlayerData(EntityPlayer player) {
		this.entplayer = player;
		this.maxTime = defaultTimeInTicks / 20;
		// Set Timer to max Time
		this.entplayer.getDataWatcher().addObject(Time_Watcher, this.maxTime);
		
	}
	
	public void copy(ExtendedPlayerData props) {
		this.GivenShrine = props.GivenShrine;
		//Clone Time Value
		entplayer.getDataWatcher().updateObject(Time_Watcher, props.getShrineTimer());
	}
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(ExtendedPlayerData.PlayerData, new ExtendedPlayerData(player));
	}

	public static final ExtendedPlayerData get(EntityPlayer player) {
		return (ExtendedPlayerData) player.getExtendedProperties(PlayerData);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		
		NBTTagCompound properties = new NBTTagCompound();
		//Save Time from DataWatcher to NBT
		properties.setInteger("ShrineTimer", entplayer.getDataWatcher().getWatchableObjectInt(Time_Watcher));
		properties.setBoolean("GivenShrine", this.GivenShrine);
		
		compound.setTag(PlayerData, properties);
		
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(PlayerData);
		//Get Time from NBT to DataWatcher
		entplayer.getDataWatcher().updateObject(Time_Watcher, properties.getInteger("ShrineTimer"));
		this.GivenShrine = properties.getBoolean("GivenShrine");
	}

	@Override
	public void init(Entity entity, World world) {}
	
	/**
	 * Decreases the Timer in Seconds
	 */
	public final boolean decreaseShrineTimer(int amount) {
		boolean sufficient = amount <= getShrineTimer();
		setShrineTimer(getShrineTimer() - (sufficient ? amount : getShrineTimer()));
		return sufficient;
	}
	
	/**
	 * Increases the Timer in Seconds or maxTime if exceeded
	 */
	public final boolean increaseShrineTimer(int amount) {
		boolean sufficient = amount + getShrineTimer() >= maxTime;
		if (sufficient){
			resetShrineTimer();
		}else{
			setShrineTimer(getShrineTimer() + amount);
		}
			
		return sufficient;
	}
	
	
	/**
	 * Resets the Players Doom Shrine Timer
	 */
	public final void resetShrineTimer() {
		setShrineTimer(this.maxTime);
	}

	/**
	 * Returns current  Doom Shrine Timer Value
	 */
	public final int getShrineTimer() {
		return entplayer.getDataWatcher().getWatchableObjectInt(Time_Watcher);
	}

	/**
	 * Sets current Time to amount or maxTime if exceeded
	 */
	public final void setShrineTimer(int amount) {
		entplayer.getDataWatcher().updateObject(Time_Watcher, amount > 0 ? (amount < maxTime ? amount : maxTime) : 0);
	
	}

}
