package eg.edu.guc.yugioh.board;

import java.util.Random;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;

public class Board {

	private Player activePlayer;
	private Player opponentPlayer;
	private Player winner;

	public Board() {

		Card.setBoard(this);

	}

	public void whoStarts(Player p1, Player p2) {

		Random r = new Random();

		if (r.nextInt(2) == 0) {
			setActivePlayer(p1);
			setOpponentPlayer(p2);
		} else {
			setActivePlayer(p2);
			setOpponentPlayer(p1);
		}

	}

	public void startGame(Player p1, Player p2) {

		p1.getField().addNCardsToHand(5);
		p2.getField().addNCardsToHand(5);

		whoStarts(p1, p2);

		activePlayer.getField().addCardToHand();

	}

	public void nextPlayer() {

		Player temp = activePlayer;
		setActivePlayer(opponentPlayer);
		setOpponentPlayer(temp);
		activePlayer.getField().addCardToHand();

	}

	public Player getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}

	public Player getOpponentPlayer() {
		return opponentPlayer;
	}

	public void setOpponentPlayer(Player opponentPlayer) {
		this.opponentPlayer = opponentPlayer;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

}
