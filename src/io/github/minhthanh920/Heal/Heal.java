package io.github.minhthanh920.Heal;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Heal extends JavaPlugin {
	public String Heal = getConfig().getString("Messages.Heal");
	public String HealBy = getConfig().getString("Messages.HealBy");
	public String HealOtherPlayer = getConfig().getString("Messages.HealOtherPlayer");
	public String Console = getConfig().getString("Messages.Console");
	public String NoPermission = getConfig().getString("Messages.NoPermission");
	public String NotFound = getConfig().getString("Messages.NotFound");
	public String reload = getConfig().getString("Messages.reload");
	public String help = getConfig().getString("Messages.help");
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		Heal = getConfig().getString("Messages.Heal").replace("&", "§");
		HealBy = getConfig().getString("Messages.HealBy").replace("&", "§");
		HealOtherPlayer = getConfig().getString("Messages.HealOtherPlayer").replace("&", "§");
		Console = getConfig().getString("Messages.Console").replace("&", "§");
		NoPermission = getConfig().getString("Messages.NoPermission").replace("&", "§");
		NotFound = getConfig().getString("Messages.NotFound").replace("&", "§");
		reload = getConfig().getString("Messages.reload").replace("&", "§");
		help = getConfig().getString("Messages.help").replace("&", "§");
	}
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		super.onDisable();
	}
	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		super.onLoad();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("heal")) {
			CommandHandler ch = new CommandHandler();
			if(!(sender instanceof Player)){
				if(args.length == 0) {
					sender.sendMessage(ChatColor.GOLD  + help);
					return true;
				}
				try {
					if(!(args[0] == null)) {
						if(args.length == 1) {
							Player tagert = Bukkit.getPlayer(args[0]);
							if(args[0].equalsIgnoreCase("reload")) {
								this.reloadConfig();
								Heal = getConfig().getString("Messages.Heal").replace("&", "§");
								HealBy = getConfig().getString("Messages.HealBy").replace("&", "§");
								HealOtherPlayer = getConfig().getString("Messages.HealOtherPlayer").replace("&", "§");
								Console = getConfig().getString("Messages.Console").replace("&", "§");
								NoPermission = getConfig().getString("Messages.NoPermission").replace("&", "§");
								NotFound = getConfig().getString("Messages.NotFound").replace("&", "§");
								reload = getConfig().getString("Messages.reload").replace("&", "§");
								help = getConfig().getString("Messages.help").replace("&", "§");
								sender.sendMessage(ChatColor.GOLD + reload);
								return true;
							}
							if(tagert == null) {
								sender.sendMessage(ChatColor.GOLD + NotFound);
								return true;
							}else {
								ch.setTagertplayer(tagert);
								ch.setHeal(tagert.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
								tagert.setHealth(ch.getHeal());
								HealOtherPlayer = HealOtherPlayer.replace("%player%", tagert.getDisplayName());
								HealBy = HealBy.replace("%player%", sender.getName());
								sender.sendMessage(ChatColor.GOLD + HealOtherPlayer);
								tagert.sendMessage(ChatColor.GOLD + HealBy);
								return true;
							}
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					sender.sendMessage(ChatColor.GOLD + args[0] + "Unknow");
				}

			}
			if(sender instanceof Player){
				Player player = (Player) sender;
				if(player.hasPermission("heal.use") || player.hasPermission("heal.reload")){
					if(args.length == 0) {
						ch.setTagertplayer(player);
						ch.setHeal(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
						player.setHealth(ch.getHeal());
						sender.sendMessage(ChatColor.GOLD + Heal);	
						return true;
					}
					if(args.length == 1) {
						if(args[0].equalsIgnoreCase("reload")) {
							this.reloadConfig();
							Heal = getConfig().getString("Messages.Heal").replace("&", "§");
							HealBy = getConfig().getString("Messages.HealBy").replace("&", "§");
							HealOtherPlayer = getConfig().getString("Messages.HealOtherPlayer").replace("&", "§");
							Console = getConfig().getString("Messages.Console").replace("&", "§");
							NoPermission = getConfig().getString("Messages.NoPermission").replace("&", "§");
							NotFound = getConfig().getString("Messages.NotFound").replace("&", "§");
							reload = getConfig().getString("Messages.reload").replace("&", "§");
							help = getConfig().getString("Messages.help").replace("&", "§");
							sender.sendMessage(ChatColor.GOLD + reload);
							return true;
						} 
						Player tagert = Bukkit.getPlayer(args[0]);
						if(tagert == null) {
							sender.sendMessage(ChatColor.GOLD + NotFound);
						} else {
							ch.setTagertplayer(tagert);
							ch.setHeal(tagert.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
							tagert.setHealth(ch.getHeal());
							HealOtherPlayer = HealOtherPlayer.replace("%player%", tagert.getDisplayName());
							HealBy = HealBy.replace("%player%", sender.getName());
							player.sendMessage(ChatColor.GOLD + HealOtherPlayer);
							tagert.sendMessage(ChatColor.GOLD + HealBy);
							return true;
						}
					}
				} else
					sender.sendMessage(ChatColor.GOLD + NoPermission);
					return true;
				}
			}
		return false;
	} 
}
