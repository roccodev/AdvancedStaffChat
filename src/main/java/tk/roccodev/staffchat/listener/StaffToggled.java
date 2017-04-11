package tk.roccodev.staffchat.listener;

import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import tk.roccodev.staffchat.SCMain;

public class StaffToggled implements Listener{

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
	public void onChatMessageSent(AsyncPlayerChatEvent evt){
		if(!SCMain.toggledStaffChat.contains(evt.getPlayer())) return;
		evt.setCancelled(true);
		String message = evt.getMessage();
		String ign = evt.getPlayer().getDisplayName();
		String prefix = "";
		if(evt.getPlayer() instanceof Player){
			AnjoPermissionsHandler handler = SCMain.groupManager.getWorldsHolder().getWorldPermissionsByPlayerName(evt.getPlayer().getName());
			prefix = handler.getUserPrefix(evt.getPlayer().getName());
			}
			else{
				prefix = "";
			}
			SCMain.sendToStaffChat(SCMain.chatFormat.replaceAll("\\{name\\}", ign).replaceAll("\\{message\\}", message).replaceAll("\\{prefix\\}", prefix));
		
		
	}

}
