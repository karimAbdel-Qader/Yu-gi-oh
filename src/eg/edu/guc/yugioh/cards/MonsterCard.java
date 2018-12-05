package eg.edu.guc.yugioh.cards;

public class MonsterCard extends Card {

	private int level;
	private int defensePoints;
	private int attackPoints;
	private Mode mode;

	public MonsterCard(String n, String desc, int l, int a, int d) {

		super(n, desc);
		this.level = l;
		this.attackPoints = a;
		this.defensePoints = d;
		this.mode = Mode.DEFENSE;

	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getDefensePoints() {
		return defensePoints;
	}

	public void setDefensePoints(int defensePoints) {
		this.defensePoints = defensePoints;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

}
