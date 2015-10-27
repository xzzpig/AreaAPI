package com.github.xzzpig.areaapi;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.xzzpig.areaapi.event.AreaCreateEvent;

public class Commands {
	public static boolean create(CommandSender sender,Command cmd,String label,String[] args){
		Player player = (Player)sender;
		Location left,right;
		World world;
		String area;
		String prefix = "§6["+cmd.getName()+"]§f";
		if(Vars.left.containsKey(player.getName()))
			left = Vars.left.get(player.getName());
		else{
			sender.sendMessage(prefix+ "§41号坐标还未选定");
			return true;
		}
		if(Vars.right.containsKey(player.getName()))
			right = Vars.right.get(player.getName());
		else{
			sender.sendMessage(prefix+ "§41号坐标还未选定");
			return true;
		}
		if(args.length<2){
			sender.sendMessage(prefix+ "§4请输入区域[area]名称");
			return true;
		}
		else
			area = args[1];
		world = left.getWorld();
		Area areas = new Area(area,left,right);
		areas.save();
		sender.sendMessage(prefix+ "§a区域"+ args[1]+"已创建于");
		sender.sendMessage(prefix+ "§aworld:"+world.getName());
		sender.sendMessage(prefix+ "§a坐标1:"+left.getBlockX()+","+left.getBlockY()+","+left.getBlockZ());
		sender.sendMessage(prefix+ "§a坐标2:"+right.getBlockX()+","+right.getBlockY()+","+right.getBlockZ());
		AreaCreateEvent eve = new AreaCreateEvent(areas, player);
		Bukkit.getServer().getPluginManager().callEvent(eve);
		return true;
	}

	public static boolean list(CommandSender sender,Command cmd,String label,String[] args){
		String prefix = "§6["+cmd.getName()+"]§f";
		if(args.length<2){
			sender.sendMessage(prefix+"§aarea列表：");
			if(Area.getAreas() == null)
			{
				sender.sendMessage(prefix+"§a无");
				return true;
			}
			for(Area area:Area.getAreas()){
				sender.sendMessage("§e-"+area.getName());
			}
			if (sender instanceof Player){
				sender.sendMessage(prefix+ "§a你当前所在area："+Area.getsAreas(((Player)sender).getLocation()));			
			}
		}
		else if(args.length<3){
			if(Area.hasArea(args[1])){
				Area area = Area.getArea(args[1]);
				Location[] loc = area.getLocations();
				World world = loc[0].getWorld();
				int x1=loc[0].getBlockX(),
					x2=loc[1].getBlockX(),
					y1=loc[0].getBlockY(),
					y2=loc[1].getBlockY(),
					z1=loc[0].getBlockZ(),
					z2=loc[1].getBlockZ();
				sender.sendMessage(prefix+"§earea:"+area.getName());
				sender.sendMessage(prefix+"§eworld:"+world.getName());
				sender.sendMessage(prefix+"§e位置1:"+x1+","+y1+","+z1);
				sender.sendMessage(prefix+"§e位置2:"+x2+","+y2+","+z2);
				if (sender instanceof Player){
					if(area.isInArea(((Player) sender).getLocation()))
						sender.sendMessage(prefix+ "§a你在此area");
					else
						sender.sendMessage(prefix+ "§a你不在此area");

				}
			}
			else
				sender.sendMessage(prefix+"§4不存在该area");
		}
		else
			sender.sendMessage(prefix+ "§7/<command> list <areaname> -列出所有已创建区域");
		return true;
	}

	public static boolean select(CommandSender sender,Command cmd,String label,String[] args){
		String arg1;			
		String prefix = "§6["+cmd.getName()+"]§f";
		Player player = (Player)sender;
		{if(args.length<2)
				arg1 = "help";
			else
				arg1 = args[1];
		}
		if(arg1.equalsIgnoreCase("help")){
			sender.sendMessage(prefix + "§7/<command> select sky  -将选择区域延伸到天空和地底");
			return true;
		}
		else if(arg1.equalsIgnoreCase("sky")){
			Location left,right;
			if(Vars.left.containsKey(player.getName()))
				left = Vars.left.get(player.getName());
			else{
				sender.sendMessage(prefix+ "§41号坐标还未选定");
				return true;
			}
			if(Vars.right.containsKey(player.getName()))
				right = Vars.right.get(player.getName());
			else{
				sender.sendMessage(prefix+ "§41号坐标还未选定");
				return true;
			}
			left.setY(left.getWorld().getMaxHeight());
			right.setY(0);
			sender.sendMessage(prefix + "§a已将选择区域延伸到天空和地底");
		}
		return false;
	}
	
	public static boolean test(CommandSender sender,Command cmd,String label,String[] args){
		if(!Debuger.isdebug)
			return true;		
		//String prefix = "§6["+cmd.getName()+"]§f";
		Player player = (Player)sender;
		Area.getAreas(player.getLocation())[0].denyEntity(player);
		return true;
	}
}


