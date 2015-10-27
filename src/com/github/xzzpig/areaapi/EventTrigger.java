package com.github.xzzpig.areaapi;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.xzzpig.areaapi.event.AreaPlayerInteractEvent;

public class EventTrigger implements Listener{
	@EventHandler
	private void onInteraction(PlayerInteractEvent event) {
		if(Area.getAreas(event.getPlayer().getLocation())==null){
			return;
		}
		AreaPlayerInteractEvent eve = new AreaPlayerInteractEvent(event, Area.getAreas(event.getPlayer().getLocation()));
		Bukkit.getServer().getPluginManager().callEvent(eve);
	}
	@EventHandler
	private void test(AreaPlayerInteractEvent event) {
		event.getPlayer().sendMessage("");
	}
}
