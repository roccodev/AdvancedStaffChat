package tk.roccodev.staffchat;

import java.util.ArrayList;
import java.util.List;

import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import tk.roccodev.staffchat.listener.StaffToggled;

public class SCMain extends JavaPlugin{

	public static List<CommandSender> toggledStaffChat = new ArrayList<CommandSender>();
	public static String chatFormat;
	public static GroupManager groupManager;
	
	
	
	@Override
	public void onEnable(){
		if(!(Bukkit.getPluginManager().getPlugin("GroupManager") != null && Bukkit.getPluginManager().getPlugin("GroupManager").isEnabled())){
			
			this.getLogger().info("GroupManager not found!");
			Bukkit.getPluginManager().disablePlugin(this);
			
		}
		this.getConfig().addDefault("format", "&6&lStaff » &r{prefix}{name}:&r {message}");
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		SCMain.chatFormat = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("format"));
		Bukkit.getPluginManager().registerEvents(new StaffToggled(), this);
		this.getCommand("sc").setExecutor(new SCCommands());
		this.getCommand("sct").setExecutor(new SCCommands());
		groupManager = (GroupManager) Bukkit.getPluginManager().getPlugin("GroupManager");
		
		
	}
	
	
	public static void sendToStaffChat(String message1){
		String message = ChatColor.translateAlternateColorCodes('&', message1);
		Bukkit.broadcast(message, "staffchat.use");
		ConsoleCommandSender ccs = Bukkit.getConsoleSender();
		ccs.sendMessage(message);
	}

}
