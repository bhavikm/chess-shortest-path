# chess-shortest-path

Uses Breadth First Search to find the shortest path between a given starting position and end position on a chess board. 

Right now this is just for a Knight but can easily extended to other pieces using the ChessPiece abstract class.

Outputs shortest path moves a Knight should take from the starting position provided up to the end position.

Compile:
-----------
$ javac *.java  

Run:
-----------
$ java ChessShortestPath [startPosition] [endPosition]  
where positions are in algebraic chess notation

Example:
-----------
$ java ChessShortestPath E1 A7  
$ C2 A3 B5 A7
