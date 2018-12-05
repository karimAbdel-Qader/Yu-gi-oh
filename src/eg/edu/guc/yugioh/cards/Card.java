package eg.edu.guc.yugioh.cards;

import eg.edu.guc.yugioh.board.Board;

public class Card {

	private final String name;
	private String description;
	private boolean isHidden;
	private Location location;
	private static Board board;

	public Card(String name, String desc) {

		this.name = name;
		this.description = desc;
		this.isHidden = true;
		this.location = Location.DECK;

	}

	public void action() {

	}

	public void action(MonsterCard monster) {

	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public static Board getBoard() {
		return board;
	}

	public static void setBoard(Board board) {
		Card.board = board;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
