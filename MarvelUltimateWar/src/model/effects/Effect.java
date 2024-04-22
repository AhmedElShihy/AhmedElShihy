package model.effects;

public class Effect {
	private String name; // name of effect
	private int duration; // duration of effect
	private EffectType type; // effect type of effect (buff or debuff)
	
	public Effect (String name, int duration, EffectType type) {
		this.name = name;
		this.duration = duration;
		this.type = type;
	}
	
	public String getName () {
		return name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public EffectType getType() {
		return type;
	}
	
	
}
