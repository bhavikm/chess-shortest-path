import java.util.*;

//ChessShortestPath provides a solution to the shortest path between a starting position and end position for a knight chess piece
class ChessShortestPath {
	
	public ChessCoordinate startPos;
	public ChessCoordinate endPos;
	public ChessPiece pieceType;

	public ChessShortestPath(ChessCoordinate start, ChessCoordinate end, ChessPiece piece)
	{
		startPos = start;
		endPos = end;
		pieceType = piece;
	}

	//Finds the shortest path between a start node and end node for a unweighted graph using Breadth First Search 
	//
	//For Chess Shortest Path, the chess board is an unweighted graph where the current position of the knight is the visited node in Breadth First Search and the positions of the possible next moves are the adjacent nodes
	//
	//Prints out the shortest path in terms of the steps of the shortest path, excluding the starting position
	public void shortestPath()
	{
		//Keep track of visited nodes and the parents of visited nodes (for finding the shortest path)
		HashMap<ChessCoordinate, ChessCoordinate> parentNode = new HashMap<ChessCoordinate, ChessCoordinate>();

		//Queue of nodes to visit 
		Queue<ChessCoordinate> positionQueue = new LinkedList<ChessCoordinate>();

		//intially add the starting node
		parentNode.put(startPos,null);
		positionQueue.add(startPos);

		//Breadth first search
		while (positionQueue.peek() != null) //check if anymore nodes to visit
		{
			ChessCoordinate currentPosition = positionQueue.poll();

			if (currentPosition.equals(endPos))
			{
				break; //we have reached the end position on the graph via the shortest path so stop searching
			}

			//otherwise get adjacent nodes (possible moves from current position for knight)
			ArrayList<ChessCoordinate> nextPositions = pieceType.validMoves(currentPosition);
			for (ChessCoordinate adjacentPosition : nextPositions)
			{
				//if this adjacent nodes is one that hasn't been visited add it to the queue
				//also keep track of the adjacent node's parent (the current node)
				if (!parentNode.containsKey(adjacentPosition))
				{
					parentNode.put(adjacentPosition,currentPosition);
					positionQueue.add(adjacentPosition);
				}
			}
		}

		//traverse back from end position coordinate to start position using the parent map to get shortest path
		//build up string of shortest path at same time
		ChessCoordinate currentNode = endPos; //start at the end node
		String shortestPath = "";
		while (parentNode.get(currentNode) != null) //stop once we are at the start node
		{
			shortestPath = currentNode.toString() + " " + shortestPath;
			currentNode = parentNode.get(currentNode);
		}

		if (shortestPath.length() == 0) //When start position = end position
		{
			shortestPath = startPos.toString();
		}

		//Print out the shortest path found, excluding start position and including end position
		System.out.println(shortestPath);
	}

	public static void main(String[] args)
	{
		if (args.length != 2) //check for right number of arguments from command line 
		{
			System.out.println("Must inlcude start position and end position.");
			System.out.println("Usage is: java ChessShortestPath startPos endPos");
			System.out.println("where startPos/endPos are in algebraic chess notation. Board size is: " + ChessCoordinate.BOARD_SIZE + " by " + ChessCoordinate.BOARD_SIZE);
		} else {
			try {
				//must catch checked exception for invalid co-ordinates arguments
				ChessShortestPath knightShortestPath = new ChessShortestPath(new ChessCoordinate(args[0]), new ChessCoordinate(args[1]), new Knight());
				knightShortestPath.shortestPath();
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}