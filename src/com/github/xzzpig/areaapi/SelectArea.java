package com.github.xzzpig.areaapi;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SelectArea implements Listener{
	@SuppressWarnings("deprecation")
	@EventHandler
	private void onInteraction(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		int handid = player.getItemInHand().getTypeId();
		if(!event.hasBlock())
			return;
		Location loc = event.getClickedBlock().getLocation();
		Action action = event.getAction();
		if(handid == Vars.selectitem){
			if(action.equals(Action.LEFT_CLICK_BLOCK)){
				Vars.left.put(player.getName(),loc);
				player.sendMessage(Funs.prefix + "§e1号坐标已设在:" + loc.getBlockX() +","+ loc.getBlockY() +","+ loc.getBlockZ());
			}
			else if(action.equals(Action.RIGHT_CLICK_BLOCK)){
				Vars.right.put(player.getName(),loc);	
				player.sendMessage(Funs.prefix + "§e2号坐标已设在:" + loc.getBlockX() +","+ loc.getBlockY() +","+ loc.getBlockZ());
			}
			event.setCancelled(true);
		}
	}
}

