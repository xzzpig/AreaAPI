package com.github.xzzpig.areaapi;
import org.bukkit.*;

import java.util.*;
import java.util.Map.Entry;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;

import com.github.xzzpig.BukkitTools.TConfig;
import com.github.xzzpig.BukkitTools.TString;

public class Area
{
	private static HashMap<String,Area> areas = new HashMap<String,Area>();
	
	private String name;
	private World world;
	private Location loc;
	private int x,y,z;
	
	private HashMap<String,String> sdatas = new HashMap<String,String>();
	private HashMap<String,Integer> idatas = new HashMap<String,Integer>();
	private HashMap<String,Boolean> bdatas = new HashMap<String,Boolean>();
	
	public Area(String name,Location loc1,Location loc2){
		this.name = name;
		this.world = loc1.getWorld();
		int x1,x2,y1,y2,z1,z2,xm,ym,zm,x0,y0,z0;
		x1 = loc1.getBlockX();
		x2 = loc2.getBlockX();
		y1 = loc1.getBlockY();
		y2 = loc2.getBlockY();
		z1 = loc1.getBlockZ();
		z2 = loc2.getBlockZ();
		if(x1<=x2)
		{
			xm = x1;
			x0 = x2-x1;
		}
		else
		{
			xm = x2;
			x0 = x1-x2;
		}
		if(y1<=y2)
		{
			ym = y1;
			y0 = y2-y1;
		}
		else
		{
			ym = y2;
			y0 = y1-y2;
		}
		if(z1<=z2)
		{
			zm = z1;
			z0 = z2-z1;
		}
		else
		{
			zm = z2;
			z0 = z1-z2;
		}
		this.loc = new Location(loc1.getWorld(),xm,ym,zm);
		this.x = x0;
		this.y = y0;
		this.z = z0;
		
		Area.areas.put(name,this);
	}
	public Area(String name)
	{
		String world;
		int x1,y1,z1,x2,y2,z2;
		FileConfiguration config = TConfig.getConfigFile("AreaAPI", "Area.yml");
		world = config.getString("Area."+name+".world",null);
		x1 = config.getInt("Area."+name+".x1");
		x2 = config.getInt("Area."+name+".x2");
		y1 = config.getInt("Area."+name+".y1");
		y2 = config.getInt("Area."+name+".y2");
		z1 = config.getInt("Area."+name+".z1");
		z2 = config.getInt("Area."+name+".z2");
		Location loc1 = new Location(Vars.pl.getServer().getWorld(world),x1,y1,z1);
		Location loc2 = new Location(Vars.pl.getServer().getWorld(world),x2,y2,z2);
		new Area(name,loc1,loc2);
	}
	
	public static Area getArea(String name)
	{
		if(!areas.containsKey(name))
			return null;
		return areas.get(name);
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean isInArea(Location loc1)
	{
		if(loc1 == null)
			return false;
		int x1,y1,z1;
		x1 = loc1.getBlockX();
		y1 = loc1.getBlockY();
		z1 = loc1.getBlockZ();
		if(x1 < this.loc.getBlockX()||x1-this.loc.getBlockX() > this.x)
			return false;
		if(y1 < this.loc.getBlockY()||y1-this.loc.getBlockY() > this.y)
			return false;
		if(z1 < this.loc.getBlockZ()||z1-this.loc.getBlockZ() > this.z)
			return false;
		return true;
	}
	public boolean isInArea(Player player)
	{
		if(player == null)
			return false;
		return this.isInArea(player.getLocation());
	}
	public boolean isInArea(Entity entity)
	{
		if(entity == null)
			return false;
		return this.isInArea(entity.getLocation());
	}
	public static Area[] getAreas()
	{
		List<Area> as = new ArrayList<Area>();
		Iterator<Entry<String, Area>> i = areas.entrySet().iterator();
		while(i.hasNext())
		{
			Area a = (Area) i.next().getValue();
			as.add(a);
		}
		if(as.size() == 0)
			return null;
		as.toArray(new Area[0]);
		return as.toArray(new Area[0]);
	}
	public static Area[] getAreas(Location loc1)
	{
		List<Area> as = new ArrayList<Area>();
		for(Area a:getAreas()){
			if(a.isInArea(loc1))
			{
				as.add(a);
			}
		}
		if(as.size() == 0)
			return null;
		return as.toArray(new Area[0]);
	}
	public static String getsAreas(Location loc1){
		Area[] areas = getAreas(loc1);
		String area = "|";
		if(areas == null)
			return "none";
		for(Area forarea:areas){
			area = area + forarea.getName() + "|";
		}
		return area;
	}
	
	public static void loadAreas()
	{
		String[] areas = null;
		try {
			areas = TConfig.getConfigPath("AreaAPI", "Area.yml","Area");
		} catch (Exception e) {
			TString.Print(Funs.prefix + "§4Area读取失败");
			return;
		}
		for(String area:areas)
		{
			new Area(area);
		}
	}
	public void save()
	{
		String world;
		int x1,y1,z1,x2,y2,z2;
		world = this.world.getName();
		x1 = this.loc.getBlockX();
		y1 = this.loc.getBlockY();
		z1 = this.loc.getBlockZ();
		x2 = x1+this.x;
		y2 = y1+this.y;
		z2 = z1+this.z;
		FileConfiguration config = TConfig.getConfigFile("AreaAPI","Area.yml");
		config.set("Area."+name+".world",world);
		config.set("Area."+name+".x1",x1);
		config.set("Area."+name+".x2",x2);
		config.set("Area."+name+".y1",y1);
		config.set("Area."+name+".y2",y2);
		config.set("Area."+name+".z1",z1);
		config.set("Area."+name+".z2",z2);
		TConfig.saveConfig("AreaAPI", config,"Area.yml");
	}
	public static void saveAreas()
	{
		Iterator<Area> i = areas.values().iterator();
		while(i.hasNext())
		{
			Area a = (Area) i.next();
			a.save();
		}
	}
	
	public static boolean hasArea(String name)
	{
		return areas.containsKey(name);
	}
	
	public Location[] getLocations()
	{
		Location[] locs = new Location[2];
		locs[0] = this.loc;
		locs[1] = new Location(loc.getWorld(),loc.getBlockX()+x,loc.getBlockY()+y,loc.getBlockZ()+z);
		return locs;
	}

	@Override
	public String toString()
	{
		return this.name;
	}
	
	@SuppressWarnings("deprecation")
	public Player[] getInPlayer()
	{
		List<Player> ps = new ArrayList<Player>();
		for(Player p:Vars.pl.getServer().getOnlinePlayers())
		{
			if(this.isInArea(p))
			{
				ps.add(p);
			}
		}
		return (Player[])ps.toArray();
	}
	public Entity[] getInEntity()
	{
		List<Entity> es = world.getEntities();
		List<Entity> es2 = new ArrayList<Entity>();
		for(Entity e:es)
		{
			if(this.isInArea(e))
			{
				es2.add(e);
			}
		}
		return (Entity[])es2.toArray();
	}
	
	public void setData(String key,String value)
	{
		sdatas.put(key,value);
		Vars.pl.getConfig().set("Area."+name+".data.string."+key,value);
		Vars.pl.saveConfig();
	}
	public void setData(String key,int value)
	{
		idatas.put(key,value);
		Vars.pl.getConfig().set("Area."+name+".data.int."+key,value);
		Vars.pl.saveConfig();
	}
	public void setData(String key,boolean value)
	{
		bdatas.put(key,value);
		Vars.pl.getConfig().set("Area."+name+".data.boolean."+key,value);
		Vars.pl.saveConfig();
	}
	
	public String getStringData(String key)
	{
		if(!this.sdatas.containsKey(key))
			return null;
		return this.sdatas.get(key);
	}
	public int getIntData(String key)
	{
		if(!this.idatas.containsKey(key))
			return 0;
		return this.idatas.get(key);
	}
	public boolean getBooleanData(String key)
	{
		if(!this.bdatas.containsKey(key))
			return false;
		return this.bdatas.get(key);
	}
	
	public void denyEntity(Entity entity)
	{
		if(!this.isInArea(entity)||entity == null)
			return;
		int x1,x2,y1,y2,z1,z2;
		Location eloc = entity.getLocation();
		x1 = eloc.getBlockX() - this.loc.getBlockX();
		x2 = this.x - x1;
		y1 = eloc.getBlockY() - this.loc.getBlockY();
		y2 = this.y - y1;
		z1 = eloc.getBlockZ() - this.loc.getBlockZ();
		z2 = this.z - z1;
		int[] ints = {x1,x2,y1,y2,z1,z2};
		int minid = Statics.getMinid(ints);
		switch(minid)
		{
			case 0:
				eloc.add(-x1,0,0);
				break;
			case 1:
				eloc.add(x2,0,0);
				break;
			case 2:
				eloc.add(0,-y1,0);
				break;
			case 3:
				eloc.add(0,y2,0);
				break;
			case 4:
				eloc.add(0,0,-z1);
				break;
			case 5:
				eloc.add(0,0,z2);
				break;
		}
		entity.teleport(eloc);
	}
}
