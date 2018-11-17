
public class Run {

	public static void main(String[] args) {
		Board board = new Board();
		Frame frame = new Frame(board);
		TicTacToe tictactoe = new TicTacToe(frame, board);

	}

}
