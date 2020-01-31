import java.util.ArrayList;
import java.util.List;

public class Solver {

	public static List<Board> getSolutions(Board partialBoard, List<Card> remainingCards) {
		List<Board> validSolutions = new ArrayList<>();

		if (partialBoard.isSolution()) {
			validSolutions.add(partialBoard);
		} else {
			
			List<Card> clonedCards = cloneRemainingCards(remainingCards);
			Card newCard = clonedCards.get(0);
			clonedCards.remove(newCard);

			List<Coordinate> coordsWithEmptyCard = getCoordsWithEmptyCard(partialBoard);

			for (Coordinate coord : coordsWithEmptyCard) {
				for (int i = 0; i < 4; i++) {
					Board clonedBoard = partialBoard.clone();
					clonedBoard.getSquares().put(coord.clone(), newCard.clone());

					if (clonedBoard.isSolution()) {
						List<Board> solutions = getSolutions(clonedBoard, clonedCards);
						for (Board solution : solutions) {
							solutions.add(solution);							
						}
					}
					newCard = newCard.rotate90Degrees();
				}
			}
		}
		return validSolutions;
	}
	
	private static List<Card> cloneRemainingCards(List<Card> remainingCards) {
		List<Card> clonedCards = new ArrayList<>();
		for (Card card : remainingCards) {
			clonedCards.add(card.clone());
		}
		return clonedCards;
	}
	
	private static List<Coordinate> getCoordsWithEmptyCard(Board partialBoard) {
		List<Coordinate> coordsWithEmptyCard = new ArrayList<>();

		for (Coordinate coord : partialBoard.getSquares().keySet()) {
			if (partialBoard.getCard(coord) != null) {
				coordsWithEmptyCard.add(coord);
			}
		}
		return coordsWithEmptyCard;
	}

}
