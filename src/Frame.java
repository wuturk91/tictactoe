import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Frame {
	
	private JFrame frame;
	private Container contentPane;
	private JPanel boardPanel;
	private JPanel gameBoard;
	private Board board;
	private JPanel playerPanel;
	private JLabel playerLabel;
	private JButton resetButton;
	private JButton quitButton;
	private JLabel p1ScoreText;
	private JLabel p2ScoreText;
	private int p1Scores;
	private int p2Scores;
	private String titleResource;
    private Image titleTile;
    private ImageIcon titleIcon;
	
	public Frame(Board board) {

		//Create Window
		frame = new JFrame("Rick and Morty: Tic Tac Toe - By Cem Salih");
		contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Color.BLACK);
		
		//Create Title Panel
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.setBackground(Color.BLACK);
		JButton title = new JButton();
		title.setPreferredSize(new Dimension(600, 221));
		
		titleResource = "ram_banner.png";
		try {
			titleTile = ImageIO.read(this.getClass().getResourceAsStream(titleResource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		titleIcon = new ImageIcon(titleTile);
		
		title.setIcon(titleIcon);
		title.setBorder(null);
		titlePanel.add(title);
		contentPane.add(titlePanel, BorderLayout.NORTH);
		
		//Create Board Panel
		boardPanel = new JPanel();
		boardPanel.setLayout(new BorderLayout());
		boardPanel.setBackground(Color.BLACK);
		boardPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 0, Color.decode("#73e964")));
		this.board = board;
		gameBoard = board.getPanel();
		gameBoard.setBackground(Color.BLACK);
		boardPanel.add(gameBoard, BorderLayout.CENTER);
		contentPane.add(boardPanel, BorderLayout.CENTER);
		
		//Create Player Panel
		playerPanel = new JPanel();
		playerPanel.setLayout(new FlowLayout());
		playerPanel.setBorder(BorderFactory.createMatteBorder(5, 0, 5, 0, Color.decode("#73e964")));
		playerLabel = new JLabel("Rick's Go");
		playerLabel.setFont(new Font("Serif", Font.BOLD, 35));
		playerLabel.setForeground(Color.WHITE);
		playerPanel.add(playerLabel);
		playerPanel.setBackground(Color.BLACK);
		contentPane.add(playerPanel, BorderLayout.SOUTH);
		
		//Create Right Panel
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setBackground(Color.BLACK);
		resetButton = new JButton("Reset");
		quitButton = new JButton("Quit");
		contentPane.add(rightPanel, BorderLayout.EAST);
		
		//Create ScoreBoard Title Panel
		JPanel scoreBoardTitle = new JPanel();
		scoreBoardTitle.setBackground(Color.BLACK);
		scoreBoardTitle.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.decode("#73e964")));
		JLabel scoreBoardLabel = new JLabel("Score Board");
		scoreBoardLabel.setFont(new Font("Serif", Font.BOLD, 20));
		scoreBoardLabel.setForeground(Color.WHITE);
		scoreBoardTitle.add(scoreBoardLabel);
		rightPanel.add(scoreBoardTitle, BorderLayout.NORTH);
		
		//Create Scores Panel
		p1Scores = 0;
		p2Scores = 0;
		
		JPanel scoresPanel = new JPanel();
		scoresPanel.setLayout(new BoxLayout(scoresPanel, BoxLayout.PAGE_AXIS));
		scoresPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.decode("#73e964")));
		scoresPanel.setBackground(Color.BLACK);
		p1ScoreText = new JLabel("Rick's Score: " + p1Scores);
		p1ScoreText.setForeground(Color.WHITE);
		p1ScoreText.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		scoresPanel.add(p1ScoreText);
		p2ScoreText = new JLabel("Morty's Score: " +  + p2Scores);
		p2ScoreText.setForeground(Color.WHITE);
		p2ScoreText.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		scoresPanel.add(p2ScoreText);
		rightPanel.add(scoresPanel, BorderLayout.CENTER);
		//Create Options Panel
		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(new GridLayout(2,1));
		optionsPanel.setBackground(Color.BLACK);
		optionsPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.decode("#73e964")));
		resetButton = new JButton("Reset");
		optionsPanel.add(resetButton);
		quitButton = new JButton("Quit");
		optionsPanel.add(quitButton);
		rightPanel.add(optionsPanel, BorderLayout.SOUTH);
		
		
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public void changePlayerLabel() {
		if(playerLabel.getText().equals("Rick's Go")) {
			playerLabel.setText("Morty's Go");
		} else {
			playerLabel.setText("Rick's Go");
		}
	}
	
	public void setWinner(String currentPlayer) {
		if(currentPlayer.equals("rick")) {
			playerLabel.setText("RICK WINS!!!");
		} else {
			playerLabel.setText("MORTY WINS!!!");
		} 
	}
	
	public void setDraw() {
		playerLabel.setText("Its a Draw!!!");
	}
	
	public void setScore(String currentPlayer) {
		if(currentPlayer.equals("rick")) {
			p1Scores++;
			p1ScoreText.setText("Rick's Score: " + p1Scores);
		} else {
			p2Scores++;
			p2ScoreText.setText("Morty's Score: " + p2Scores);
		}
	}
	
	public void reset() {
		playerLabel.setText("Rick's Go");
	}
	
	public void quit() {
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
	
	public JButton getResetButton() {
		return resetButton;
	}
	
	public JButton getQuitButton() {
		return quitButton;
	}
}
