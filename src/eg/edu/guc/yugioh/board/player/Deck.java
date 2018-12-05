package eg.edu.guc.yugioh.board.player;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

	private static ArrayList<Card> monsters;
	private static ArrayList<Card> spells;
	private final ArrayList<Card> deck;

	public Deck() throws IOException {

		if ((monsters == null) || (spells == null)) {

			monsters = loadCardsFromFile("Database-Monsters.csv");
			spells = loadCardsFromFile("Database-Spells.csv");

		}

		deck = new ArrayList<Card>();
		buildDeck(monsters, spells);
		shuffleDeck();

	}

	public ArrayList<Card> loadCardsFromFile(String path)
			throws NumberFormatException, IOException {

		ArrayList<Card> cards = new ArrayList<Card>();
		String line;

		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);

		while ((line = br.readLine()) != null) {

			String[] cardInfo = line.split(",");

			if (cardInfo[0].equalsIgnoreCase("Monster")) {

				String name = cardInfo[1];
				String desc = cardInfo[2];
				int level = Integer.parseInt(cardInfo[5]);
				int attack = Integer.parseInt(cardInfo[3]);
				int defense = Integer.parseInt(cardInfo[4]);
				cards.add(new MonsterCard(name, desc, level, attack, defense));

			} else {

				switch (cardInfo[1]) {

				case "Card Destruction":
					cards.add(new CardDestruction(cardInfo[1], cardInfo[2]));
					break;
				case "Change Of Heart":
					cards.add(new ChangeOfHeart(cardInfo[1], cardInfo[2]));
					break;
				case "Dark Hole":
					cards.add(new DarkHole(cardInfo[1], cardInfo[2]));
					break;
				case "Graceful Dice":
					cards.add(new GracefulDice(cardInfo[1], cardInfo[2]));
					break;
				case "Harpie's Feather Duster":
					cards.add(new HarpieFeatherDuster(cardInfo[1], cardInfo[2]));
					break;
				case "Heavy Storm":
					cards.add(new HeavyStorm(cardInfo[1], cardInfo[2]));
					break;
				case "Mage Power":
					cards.add(new MagePower(cardInfo[1], cardInfo[2]));
					break;
				case "Monster Reborn":
					cards.add(new MonsterReborn(cardInfo[1], cardInfo[2]));
					break;
				case "Pot of Greed":
					cards.add(new PotOfGreed(cardInfo[1], cardInfo[2]));
					break;
				case "Raigeki":
					cards.add(new Raigeki(cardInfo[1], cardInfo[2]));
					break;

				}

			}

		}

		br.close();

		return (cards);

	}

	private void buildDeck(ArrayList<Card> Monsters, ArrayList<Card> Spells) {

		int monstersQouta = 15;
		int spellsQouta = 5;

		Random r = new Random();

		for (; monstersQouta > 0; monstersQouta--) {

			int randomIndex = r.nextInt(monsters.size());
			MonsterCard monster = (MonsterCard) monsters.get(randomIndex);

			MonsterCard clone = new MonsterCard(monster.getName(),
					monster.getDescription(), monster.getLevel(),
					monster.getAttackPoints(), monster.getDefensePoints());
			clone.setMode(monster.getMode());
			clone.setHidden(monster.isHidden());
			clone.setLocation(Location.DECK);
			deck.add(clone);

		}

		for (; spellsQouta > 0; spellsQouta--) {

			int randomIndex = r.nextInt(spells.size());
			SpellCard spell = (SpellCard) spells.get(randomIndex);

			SpellCard clone = new SpellCard(spell.getName(),
					spell.getDescription());
			clone.setHidden(spell.isHidden());
			clone.setLocation(Location.DECK);
			deck.add(clone);

		}

	}

	private void shuffleDeck() {

		Collections.shuffle(deck);

	}

	public ArrayList<Card> drawNCards(int n) {

		ArrayList<Card> cards = new ArrayList<Card>(n);

		for (int i = 0; i < n; i++)
			cards.add(deck.remove(0));

		return (cards);

	}

	public Card drawOneCard() {

		return (deck.remove(0));

	}

	public static ArrayList<Card> getMonsters() {
		return monsters;
	}

	public static void setMonsters(ArrayList<Card> monsters) {
		Deck.monsters = monsters;
	}

	public static ArrayList<Card> getSpells() {
		return spells;
	}

	public static void setSpells(ArrayList<Card> spells) {
		Deck.spells = spells;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

}
