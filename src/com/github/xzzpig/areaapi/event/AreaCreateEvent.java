package com.github.xzzpig.areaapi.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import com.github.xzzpig.areaapi.Area;

public class AreaCreateEvent extends AreaEvent {
	private static final HandlerList handlers = new HandlerList();
	
	private Area area;
	private Player creater;
	
	public AreaCreateEvent(Area area,Player creater) {		
		super("AreaCreateEvent", new Area[]{area});
		this.area = area;
		this.creater = creater;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
	public Area getArea(){
		return this.area;
	}
	
	public Player getWhoCreated(){
		return this.creater;
	}
}
