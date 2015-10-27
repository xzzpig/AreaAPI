package com.github.xzzpig.areaapi.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.xzzpig.areaapi.Area;

public class AreaPlayerInteractEvent extends AreaEvent implements AreaPlayerEvent{
	private Player player;
	private PlayerInteractEvent ievent;
	
	private static final HandlerList handlers = new HandlerList();
	
	public AreaPlayerInteractEvent(PlayerInteractEvent event,Area[] areas) {
		super("AreaPlayerInteractEvent", areas);
		this.player = event.getPlayer();
		this.ievent = event;
	}
	
	@Override
	public Player getPlayer() {
		return this.player;
	}
	
	@Override
	public HandlerList getHandlers() {
		return AreaPlayerInteractEvent.handlers;
	}
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
	public PlayerInteractEvent getIevent() {
		return this.ievent;
	}
	@Override
	public void setCancelled(boolean arg0) {
		super.setCancelled(arg0);
		this.ievent.setCancelled(arg0);
	}
}
