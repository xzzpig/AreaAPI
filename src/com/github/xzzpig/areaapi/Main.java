package com.github.xzzpig.areaapi;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{

	@Override
	public void onEnable() {
		getLogger().info(getName()+"插件已被加载");
		getServer().getPluginManager().registerEvents(new SelectArea(), this);
		this.saveDefaultConfig();
		Vars.pl = this;
		Area.loadAreas();
		loadconfig();
	}

	@Override
	public void onDisable() {
		getLogger().info(getName()+"插件已被停用 ");
	}

	//命令
    @Override
    public boolean onCommand(CommandSender sender,Command cmd,String label,String[] args)
    {
    	//主命令
    	if(label.equalsIgnoreCase("area")||label.equalsIgnoreCase("aa")||label.equalsIgnoreCase("AreaAPI")){
    		if(args.length<1){
    			sender.sendMessage(Funs.prefix + "§4输入/area help查看帮助");
    			return true;
    		}
    		if(args[0].equalsIgnoreCase("help")){
    			sender.sendMessage(Funs.prefix + "§7/<command> create [areaname]  -创建一个区域(若已存在则覆盖)");
    			sender.sendMessage(Funs.prefix + "§7/<command> select [help|sky]  -选择区域");
    			sender.sendMessage(Funs.prefix + "§7/<command> list <areaname> -列出所有已创建区域");
    			sender.sendMessage("§6提示<> -可省略，[]-不可省略");
    		}
    		if(args[0].equalsIgnoreCase("create")){
    			if (!(sender instanceof Player)){
    				sender.sendMessage(Funs.prefix + "§4控制台无法该命令/<command> create");
    				return true;
    			}
    			else
    				return Commands.create(sender, cmd, label, args);
    		}
    		else if(args[0].equalsIgnoreCase("list")){
    			return Commands.list(sender, cmd, label, args);
    		}
    		else if(args[0].equalsIgnoreCase("select")){
    			if (!(sender instanceof Player)){
    				sender.sendMessage(Funs.prefix + "§4控制台无法该命令/<command> select");
    				return true;
    			}
    			else
    				return Commands.select(sender, cmd, label, args);
    		}
    		else
    			sender.sendMessage(Funs.prefix + "§4输入/area help查看帮助");
    	}
    	return false;
	}
	//加载所有配置
	void loadconfig(){
		Vars.selectitem = this.getConfig().getInt("item",271);
	}
	
}

