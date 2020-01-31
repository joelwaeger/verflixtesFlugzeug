import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Board {

	private Map<Coordinate, Card> squares;

	public Board(Map<Coordinate, Card> squares) {
		super();
		this.squares = squares;
	}

	public Board() {
		initiateCoordinates();
	}

	public void initiateCoordinates() {
		squares = new HashMap<Coordinate, Card>();
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				squares.put(new Coordinate(x, y), null);
			}
		}
	}

	public Board clone() {
		Board clonedBoard = new Board();

		for (Coordinate coord : squares.keySet()) {
			Card currentCard = squares.get(coord);

			if (currentCard != null) {
				currentCard = currentCard.clone();
			}

			clonedBoard.getSquares().put(coord.clone(), currentCard);
		}

		return clonedBoard;

	}

	public boolean isSolution() {
		for (Entry<Coordinate, Card> entry : this.squares.entrySet()) {
			Card currentCard = entry.getValue();

			// board can't be a solution
			if (currentCard == null) {
				return false;
			}
			// board may be a solution
			else {
				Card cardAbove = squares.get(new Coordinate(entry.getKey().getX(), entry.getKey().getY() + 1));
				Card cardToRight = squares.get(new Coordinate(entry.getKey().getX() + 1, entry.getKey().getY()));
				Card cardBelow = squares.get(new Coordinate(entry.getKey().getX(), entry.getKey().getY() - 1));
				Card cardToLeft = squares.get(new Coordinate(entry.getKey().getX() - 1, entry.getKey().getY()));

				// Surrounding Squares exist -> solution is possible
				if (cardAbove != null) {
					if (currentCard.getSideByDirection("up").getPart() == cardAbove.getSideByDirection("bottom")
							.getPart()
							&& currentCard.getSideByDirection("up").getColor() == cardAbove.getSideByDirection("bottom")
									.getColor()) {
						return true;
					}
				}
				if (cardToRight != null) {
					if (currentCard.getSideByDirection("right").getPart() == cardToRight.getSideByDirection("left")
							.getPart()
							&& currentCard.getSideByDirection("right").getColor() == cardToRight
									.getSideByDirection("left").getColor()) {
						return true;
					}
				}
				if (cardBelow != null) {
					if (currentCard.getSideByDirection("bottom").getPart() == cardBelow.getSideByDirection("top")
							.getPart()
							&& currentCard.getSideByDirection("bottom").getColor() == cardBelow
									.getSideByDirection("top").getColor()) {
						return true;
					}
				}
				if (cardToLeft != null) {
					if (currentCard.getSideByDirection("left").getPart() == cardToLeft.getSideByDirection("right")
							.getPart()
							&& currentCard.getSideByDirection("left").getColor() == cardToLeft
									.getSideByDirection("right").getColor()) {
						return true;
					}
				}

			}

		}
		return false;
	}

	public Map<Coordinate, Card> getSquares() {
		return squares;
	}

	public Card getCard(Coordinate coord) {
		return squares.get(coord);
	}

	public void setSquares(Map<Coordinate, Card> squares) {
		this.squares = squares;
	}

}
