package engine;
import model.effects.*;
import java.io.BufferedReader; //import buffer reader
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;     //import file reader
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Effect;
import model.effects.Shock;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Cover;
import model.world.Hero;
import model.world.Villain;

public class Game {
	private Player firstPlayer; // first player
	private Player secondPlayer; // second player
	private boolean firstLeaderAbilityUsed; // whether the first player has used his leader ability
	private boolean secondLeaderAbilityUsed; // whether the second player has used his leader ability
	private Object[][] board; // The 2D grid representing the game arena.It is a 5x5 grid
	private static ArrayList<Champion> availableChampions; // Contains all characters available to pick from
	private static ArrayList<Ability> availableAbilities; // Contains all champions available for both players to pick from.
	private PriorityQueue turnOrder; //Contains all characters available to pick from arranged based on speed
	private static final int BOARDHEIGHT = 5; // A value representing the height of the board. This value can not be changed once initialized.
	private static final int BOARDWIDTH = 5; // A value representing the width of the board.This value can not be changed once initialized.
	
	public Game(Player first, Player second){
		this.firstPlayer = first;
		this.secondPlayer = second; 
		this.firstLeaderAbilityUsed=false;
		this.secondLeaderAbilityUsed=false;
		board=new Object[BOARDHEIGHT][BOARDWIDTH];
		Game.availableChampions=new ArrayList<Champion>(); //to initialize array list of champions and ability
		Game.availableAbilities=new ArrayList<Ability>();
		this.turnOrder=new PriorityQueue(6);
		//loadAbilities("Abilities.csv");  
		//loadChampions("Champions.csv");
		placeChampions();    //places champions and covers 
		placeCovers();
		
	}                                                        
	private void placeChampions () {//what is missing?
		ArrayList<Champion> teamPlayer1 = firstPlayer.getTeam();
		ArrayList<Champion> teamPlayer2 = secondPlayer.getTeam();
	
		board[4][1] = teamPlayer1.get(0); // places champions in 5x5 grid in bottom edge without corners
		board[4][2] = teamPlayer1.get(1);
		board[4][3] = teamPlayer1.get(2);
		
		board[0][1] = teamPlayer2.get(0); // places champions in 5x5 grid in top edge without corners
		board[0][2] = teamPlayer2.get(1);
		board[0][3] = teamPlayer2.get(2);
		
	}
	
	private void placeCovers () {
		int i=0;
		while(i<5) {
			int x = randomX ();
			int y = randomY ();
			
			if (board [y][x] == null) {
				Cover cover=new Cover(y,x);
				board[y][x]=cover;
				i++;
			}
		}
		
	}
	
	public int randomX () {
		int max= BOARDWIDTH-1;
		int min=0;
		int x=(int)(Math.random()*(max-min))+min;
		return x;
	}
	
	 public int randomY () {
		 int max= BOARDHEIGHT-1;
			int min=1;
			int y=(int)(Math.random()*(max-min))+min;
			return y;
	}
	
	
	
    public static void loadAbilities(String filePath) throws Exception {
    	BufferedReader br= new BufferedReader(new FileReader(filePath));
    	String line=br.readLine();
    	while(line!=null) {
    		String[]s=br.readLine().split(",");
    		String name=s[1];
			int Cost=Integer.parseInt(s[2]);
			int baseCoolDown=Integer.parseInt(s[4]);
			int castRange=Integer.parseInt(s[3]);
			AreaOfEffect area=AreaOfEffect.valueOf(s[8]);
			int required=Integer.parseInt(s[6]);
    		if(s[0].equals("DMG")) {
    			
    			int damageAmount=Integer.parseInt(s[7]);
    			DamagingAbility x=new DamagingAbility(name, Cost, baseCoolDown, castRange, area,required,damageAmount);
    			availableAbilities.add(x);
    		}
    		else if(s[0].equals("HEL")) {
    			int healAmount=Integer.parseInt(s[7]);
    			HealingAbility x=new HealingAbility(name, Cost, baseCoolDown, castRange, area,required,healAmount);
    			availableAbilities.add(x);
    			
    		}else if(s[0].equals("CC")) {
    			Effect effect=null;
    			if(s[7].equals("Shock")) {
    				effect=new Shock(Integer.parseInt(s[8]));
    			}
    			else if(s[7].equals("Disarm")) 
    			{
    				effect=new Disarm(Integer.parseInt(s[8]));
    			}
    			else if(s[7].equals("Dodge")) 
    			{
    				effect=new Dodge(Integer.parseInt(s[8]));
    			}
    			else if(s[7].equals("Embrace")) 
    			{
				effect=new Embrace(Integer.parseInt(s[8]));
    			}
    			else if(s[7].equals("PowerUp")) 
    			{
    			effect=new PowerUp(Integer.parseInt(s[8]));	
    			}
    			else if(s[7].equals("Root"))
    			{
    			effect=new Root(Integer.parseInt(s[8]));	
    			}
    			else if(s[7].equals("Shield"))
    			{
    			effect=new Shield(Integer.parseInt(s[8]));	
    			}else if(s[7].equals("Silence"))
    			{
    			effect=new Silence(Integer.parseInt(s[8]));	
    			}else if(s[7].equals("SpeedUp"))
    			{
    			effect=new SpeedUp(Integer.parseInt(s[8]));	
    			}else if(s[7].equals("Stun"))
    			{
    			effect=new Stun(Integer.parseInt(s[8]));	
    			}
    			
    			CrowdControlAbility x=new CrowdControlAbility(name, required, required, required, area, required, effect);
    			availableAbilities.add(x);
    		}
    		line=br.readLine();
    	}
    }
    
    public static void loadChampions(String filePath) throws Exception{
    	BufferedReader br= new BufferedReader(new FileReader(filePath));
    	String line=br.readLine();
    	while(line!=null) { 
    		
    		String[]s=line.split(",");
    		
    		String name=s[1];
			int maxHp=Integer.parseInt(s[2]);
			int mana=Integer.parseInt(s[3]);
			int actions=Integer.parseInt(s[4]);
			int speed=Integer.parseInt(s[5]);
			int attackRange=Integer.parseInt(s[6]);
			int attackDamage=Integer.parseInt(s[7]);
			
			Ability ability1=getAbility(s[8]);
			Ability ability2=getAbility(s[8]);
			Ability ability3=getAbility(s[8]);
		    
    		if(s[0]=="H") {
    			
    			Hero h=new Hero(name,maxHp,mana,actions,speed,attackRange,attackDamage);
    			
    			h.getAbilities().add(ability1);
    			h.getAbilities().add(ability2);
    			h.getAbilities().add(ability3);
    			
    			availableChampions.add(h);
    			
    		}else if(s[0]=="A") {
    			
    			AntiHero a=new AntiHero(name,maxHp,mana,actions,speed,attackRange,attackDamage);
    			
    			a.getAbilities().add(ability1);
    			a.getAbilities().add(ability2);
    			a.getAbilities().add(ability3);
    			
    			availableChampions.add(a);
    			
    		}else if(s[0]=="V") {
    			
    			Villain v=new Villain(name,maxHp,mana,actions,speed,attackRange,attackDamage);
    			
    			v.getAbilities().add(ability1);
    			v.getAbilities().add(ability2);
    			v.getAbilities().add(ability3);
    			
    			availableChampions.add(v);
    			
    		}
    		line=br.readLine();
    	}
    }
    
    public static Ability getAbility(String s) {
    	int i=0;
    	while(!availableAbilities.get(i).getName().equals(s))
    	{
    		i++;
    	}
    	return availableAbilities.get(i);
    }

    
    
	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public Player getSecondPlayer() {
		return secondPlayer;
	}

	public boolean isFirstLeaderAbilityUsed() {
		return firstLeaderAbilityUsed;
	}

	public boolean isSecondLeaderAbilityUsed() {
		return secondLeaderAbilityUsed;
	}

	public Object[][] getBoard() {
		return board;
	}

	public static ArrayList<Champion> getAvailableChampions() {
		return availableChampions;
	}

	public static ArrayList<Ability> getAvailableAbilities() {
		return availableAbilities;
	}

	public PriorityQueue getTurnOrder() {
		return turnOrder;
	}

	public static int getBOARDHEIGHT() {
		return BOARDHEIGHT;
	}

	public static int getBOARDWIDTH() {
		return BOARDWIDTH;
	}
	
	
}
