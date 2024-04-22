package engine;

import java.util.ArrayList;

import model.world.Champion;

public class Player {
	private String name; // name of player
	private Champion leader; // leader of player
	private ArrayList<Champion> team; // champions of players team
	
	
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public Champion getLeader() {
		return leader;
	}

	public void setLeader(Champion leader) {
		this.leader = leader;
	}

	public ArrayList<Champion> getTeam() {
		return team;
	}
	
	
	
}
