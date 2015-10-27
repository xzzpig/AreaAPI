package com.github.xzzpig.areaapi.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.github.xzzpig.areaapi.Area;

public class AreaEvent extends Event implements Cancellable{
	private String eventName;
	private Area[] areas;
	private boolean cancel = false;
	
	private static final HandlerList handlers = new HandlerList();
	
	public AreaEvent(String eventName,Area[] areas) {
		this.eventName = eventName;
		this.areas = areas;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
	public Area[] getAreas() {
		return areas;
	}
	public String getEventName(){
		return this.eventName;
	}
	
	@Override
	public boolean isCancelled() {
		return this.cancel;
	}
	@Override
	public void setCancelled(boolean arg0) {
		this.cancel = arg0;
	}
}
