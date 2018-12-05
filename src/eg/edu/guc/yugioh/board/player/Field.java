package eg.edu.guc.yugioh.board.player;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;

import java.io.IOException;
import java.util.ArrayList;

public class Field {

	private final Deck deck;
	private ArrayList<MonsterCard> monstersArea;
	private ArrayList<SpellCard> spellArea;
	private ArrayList<Card> hand;
	private ArrayList<Card> graveyard;
	private Phase phase;

	public Field() throws IOException {

		monstersArea = new ArrayList<MonsterCard>();
		spellArea = new ArrayList<SpellCard>();
		hand = new ArrayList<Card>();
		graveyard = new ArrayList<Card>();
		deck = new Deck();
		phase = Phase.MAIN1;

	}

	public void addMonsterToField(MonsterCard monster, Mode m, boolean isHidden) {

		if (monstersArea.size() < 5) {

			monster.setHidden(isHidden);
			monster.setMode(m);
			monster.setLocation(Location.FIELD);
			monstersArea.add(monster);

		}

	}

	public void addMonsterToField(MonsterCard monster, Mode m,
			ArrayList<MonsterCard> sacrifices) {

		// if(monstersArea.size() >= sacrifices.size()){

		monster.setHidden((m == Mode.ATTACK) ? true : false);
		monster.setMode(m);
		monster.setLocation(Location.FIELD);
		if (sacrifices != null)
			removeMonsterToGraveyard(sacrifices);
		monstersArea.add(monster);

		// }

	}

	public void removeMonsterToGraveyard(MonsterCard monster) {

		monstersArea.remove(monster);
		graveyard.add(monster);
		monster.setLocation(Location.GRAVEYARD);

	}

	public void removeMonsterToGraveyard(ArrayList<MonsterCard> monsters) {

		for (int i = 0; i < monsters.size(); i++)
			removeMonsterToGraveyard(monsters.get(i));

	}

	public void addSpellToField(SpellCard spell, MonsterCard monster,
			boolean hidden) {

		spellArea.add(spell);
		spell.setLocation(Location.FIELD);
		if (!hidden)
			activateSetSpell(spell, monster);

	}

	public void activateSetSpell(SpellCard spell, MonsterCard monster) {

		if (getSpellArea().contains(spell)) {

			spell.action(monster);
			removeSpellToGraveyard(spell);

		}

	}

	public void removeSpellToGraveyard(SpellCard spell) {

		spellArea.remove(spell);
		graveyard.add(spell);
		spell.setLocation(Location.GRAVEYARD);

	}

	public void removeSpellToGraveyard(ArrayList<SpellCard> spells) {

		for (int i = 0; i < spells.size(); i++)
			removeSpellToGraveyard(spells.get(i));

	}

	public void addCardToHand() {

		Card temp = deck.drawOneCard();
		hand.add(temp);
		temp.setLocation(Location.HAND);

	}

	public void addNCardsToHand(int n) {

		hand.addAll(deck.drawNCards(n));

	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public Deck getDeck() {
		return deck;
	}

	public ArrayList<MonsterCard> getMonstersArea() {
		return monstersArea;
	}

	public ArrayList<SpellCard> getSpellArea() {
		return spellArea;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public ArrayList<Card> getGraveyard() {
		return graveyard;
	}

	public int discardHand() {

		int discardedCards = hand.size();
		for (int i = 0; i < hand.size();) {
			graveyard.add(hand.get(i));
			hand.remove(i).setLocation(Location.GRAVEYARD);
		}
		return (discardedCards);

	}

	public MonsterCard strongestMonsterInGraveyard() {

		MonsterCard strongest = new MonsterCard("", "", 0, 0, 0);
		int strongestValue = 0;
		for (int i = 0; i < graveyard.size(); i++) {

			Card currentCard = graveyard.get(i);
			if (currentCard instanceof MonsterCard) {

				if (((MonsterCard) currentCard).getAttackPoints() > strongestValue) {

					strongest = (MonsterCard) currentCard;
					strongestValue = ((MonsterCard) currentCard)
							.getAttackPoints();

				}

			}

		}

		return (strongest);

	}

}
