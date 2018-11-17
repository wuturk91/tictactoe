
public class Run {

	//Create board and frame and inititialise TicTacToe
	public static void main(String[] args) {
		Board board = new Board();
		Frame frame = new Frame(board);
		TicTacToe tictactoe = new TicTacToe(frame, board);

	}

}
