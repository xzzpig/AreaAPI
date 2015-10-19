package com.github.xzzpig.areaapi;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.World;

public class Funs {

	static String prefix = "§6[AreaAPI]§r";

	public static void saveconfig(String key,Object arg){
		Main.getPlugin(Main.class).getConfig().set(key,arg);
	}

	public static void saveconfig(){
		Main.getPlugin(Main.class).saveConfig();
	}

	public static void loadconfig(){
		Vars.selectitem = Main.getPlugin(Main.class).getConfig().getInt("selectitem",271);
	}

}

