package model.world;

import java.awt.Point;

import java.util.ArrayList;

import model.abilities.Ability;
import model.effects.Effect;

public class Champion {
	private String name; // name of champion
	private int maxHP; // max HO of champion (upper bound of currentHP)
	private int currentHP; // current HP of champion
	private int mana; // amount of mana available to champion all game
	private int maxActionPointsPerTurn; // number of action points the champions receives every turn
	private int currentActionPoints; // amount of action points remaining for the champion in their current turn
	private int attackRange; // normal attack range for the champion
	private int attackDamage; // damage inflicted on champions or cover when the champion performs a normal attack
	private int speed; //This number represents the speed attribute for the champion, and will influence when the champion’s turn takes place
	private ArrayList<Ability> abilities; // containing all the available abilities for the champion
	private ArrayList<Effect> appliedEffects; // containing all the current effects being applied on the champion
	private Condition condition; // An attribute representing the champion’s state in the game
	private Point location; // point representing the champion’s location on the board
	
	public Champion (String name, int maxHP, int mana, int maxActions, int speed, int attackRange, int attackDamage){
		this.name = name;
		this.maxHP = maxHP;
		this.mana = mana;
		this.maxActionPointsPerTurn = maxActions;
		this.speed = speed;
		this.attackRange = attackRange;
		this.attackDamage = attackDamage;
	}
	
	public String getName () {
		return this.name;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}

	public int getMana() {
		return mana;
	}

	public int getMaxActionPointsPerTurn() {
		return maxActionPointsPerTurn;
	}

	public void setMaxActionPointsPerTurn(int maxActionPointsPerTurn) {
		this.maxActionPointsPerTurn = maxActionPointsPerTurn;
	}

	public int getCurrentActionPoints() {
		return currentActionPoints;
	}

	public int getAttackRange() {
		return attackRange;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public ArrayList<Ability> getAbilities() {
		return abilities;
	}

	public ArrayList<Effect> getAppliedEffects() {
		return appliedEffects;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}
	
	
	
	
	
}
