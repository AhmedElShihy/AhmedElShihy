package model.abilities;

public class Ability {
	private String name; // name of ability
	private int manaCost; // cost of mana of ability
	private int baseCooldown; // number of turns a champion must wait after casting the ability in order to cast it again
	private int currentCooldown; // number of turns that the champion is currently waiting for in order to recast the ability (read & write)
	private int castRange; // max cast range of ability
	private int requiredActionPoints; // needed action points to cast the ability
	private AreaOfEffect castArea; // area of effect of the ability
	
	public Ability (String name, int cost, int baseCoolDown, int castRange, AreaOfEffect area , int required) {
		this.name = name;
		this.manaCost = cost;
		this.baseCooldown = baseCoolDown;
		this.castRange = castRange;
		this.castArea = area;
		this.requiredActionPoints = required;
	}

	public String getName() {
		return name;
	}

	public int getManaCost() {
		return manaCost;
	}

	public int getBaseCooldown() {
		return baseCooldown;
	}

	public int getCastRange() {
		return castRange;
	}

	public int getRequiredActionPoints() {
		return requiredActionPoints;
	}

	public AreaOfEffect getCastArea() {
		return castArea;
	}

	public int getCurrentCooldown() {
		return currentCooldown;
	}

	public void setCurrentCooldown(int currentCooldown) {
		this.currentCooldown = currentCooldown;
	}
}
