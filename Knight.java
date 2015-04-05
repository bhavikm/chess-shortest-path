import java.util.*;

class Knight extends ChessPiece {

	public ArrayList<ChessCoordinate> validMoves(ChessCoordinate startPosition)
	{
		//Will build up list of valid co-ordinates for next move
		ArrayList<ChessCoordinate> validCoordiantes = new ArrayList<ChessCoordinate>();

		int[] moves = {-2, -1, 1, 2};

		for (int xMove : moves) {
			for (int yMove : moves) {
				if (Math.abs(xMove) != Math.abs(yMove)) { //A Knight can only move 1 space in one direction and 2 in the other
					if (ChessCoordinate.isValidCoordinate(startPosition.x + xMove, startPosition.y + yMove))
					{
						validCoordiantes.add((new ChessCoordinate((startPosition.x + xMove),(startPosition.y + yMove))));
					}
				}
			}
		}

		return validCoordiantes;
	}

}