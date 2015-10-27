package com.github.xzzpig.areaapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Debuger {
	public static boolean isdebug = true;
	@SuppressWarnings("deprecation")
	public static void print(String s)
	{
		if(isdebug == false)return;
		System.out.println("\n****************\n"+s+"\n****************");
		for(Player p: Bukkit.getServer().getOnlinePlayers())
		{
			if(p.isOp())
				p.sendMessage(s);
		}
	}
}
