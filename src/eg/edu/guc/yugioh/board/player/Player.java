package eg.edu.guc.yugioh.board.player;

import java.io.IOException;

public class Player {

	private final String name;
	private int lifePoints;
	private Field field;

	public Player(String name) throws IOException {

		this.name = name;
		this.lifePoints = 8000;
		this.field = new Field();

	}

	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}

	public String getName() {
		return name;
	}

	public Field getField() {
		return field;
	}

}
