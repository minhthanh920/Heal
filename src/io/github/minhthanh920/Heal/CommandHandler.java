package io.github.minhthanh920.Heal;

import org.bukkit.entity.Player;

public class CommandHandler {
	private Player tagertplayer;
	private Double heal;
	public Player getTagertplayer() {
		return tagertplayer;
	}
	public void setTagertplayer(Player p) {
		this.tagertplayer = p;
	}
	public Double getHeal() {
		return heal;
	}
	public void setHeal(Double heal) {
		this.heal = heal;
	}

}
