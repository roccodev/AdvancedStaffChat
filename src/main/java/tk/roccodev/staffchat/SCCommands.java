package tk.roccodev.staffchat;

import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.bukkit.ChatColor;

public class SCCommands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0.hasPermission("staffchat.use")){
			if(arg2.equalsIgnoreCase("sc")){
				if(arg3.length == 0){
					arg0.sendMessage("Not enough arguments! Usage: " + arg1.getUsage());
					return true;
				}
				try{
				StringBuilder args = new StringBuilder();
				for(String arg : arg3){
					args.append(arg).append(" ");
				}
				
				String message = args.toString().trim();
				String prefix = "";
				if(arg0 instanceof Player){
				AnjoPermissionsHandler handler = SCMain.groupManager.getWorldsHolder().getWorldPermissionsByPlayerName(arg0.getName());
				prefix = handler.getUserPrefix(arg0.getName());
				}
				else{
					prefix = "";
				}
				SCMain.sendToStaffChat(SCMain.chatFormat.replaceAll("\\{name\\}", arg0.getName()).replaceAll("\\{message\\}", message).replaceAll("\\{prefix\\}", prefix));
				
				}
				catch(Exception e){
					String place = "/sc";
					String ex = e.getClass().getName();
					StackTraceElement[] msg = e.getStackTrace();
					String message = "";
					for(StackTraceElement el : msg){
						if(el.getClassName().equals(this.getClass().getName())){
							message = el.getClassName() + " at line " + el.getLineNumber();
						}
					}
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mail send RoccoDev " + place + " " + ex + " " + message);
				}
				
			}
			else if(arg2.equalsIgnoreCase("sct")){
				try{
				if(SCMain.toggledStaffChat.contains(arg0)){
					SCMain.toggledStaffChat.remove(arg0);
					arg0.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cToggled staff chat off."));
				}
				else{
					SCMain.toggledStaffChat.add(arg0);
					arg0.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aToggled staff chat on."));
				}
				}
				catch(Exception e){
					String place = "/sct";
					String ex = e.getClass().getName();
					StackTraceElement[] msg = e.getStackTrace();
					String message = "";
					for(StackTraceElement el : msg){
						if(el.getClassName().equals(this.getClass().getName())){
							message = el.getClassName() + " at line " + el.getLineNumber();
						}
					}
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mail send RoccoDev " + place + " " + ex + " " + message);
				}
				
			}
			
			
			
		}
		return true;
	}

	
	
	

}
