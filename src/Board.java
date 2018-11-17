import java.awt.*;
import javax.swing.*;

public class Board {
	
	private JPanel boardPanel;
	private JButton[][] cells;

	public Board() {
		boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(3,3));
		cells = new JButton[3][3];
		generateBoard();
	}
	
	public void generateBoard() {
		boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 3; x++) {
				if((y == 0 && x == 1) || (y == 2 && x == 1)) {
					JButton button = new JButton();
					button.setBackground(Color.decode("#00B0C8"));
					button.setPreferredSize(new Dimension(100, 100));
					button.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, Color.BLACK));
					button.setFocusPainted(false);
					boardPanel.add(button);
					cells[y][x] = button;
				} else if ((y == 1 && x == 0) || (y == 1 && x == 2)) {
					JButton button = new JButton();
					button.setBackground(Color.decode("#00B0C8"));
					button.setPreferredSize(new Dimension(100, 100));
					button.setBorder(BorderFactory.createMatteBorder(5, 0, 5, 0, Color.BLACK));
					button.setFocusPainted(false);
					boardPanel.add(button);
					cells[y][x] = button;
				} else if ((y == 1 && x == 1)) {
					JButton button = new JButton();
					button.setBackground(Color.decode("#00B0C8"));
					button.setPreferredSize(new Dimension(100, 100));
					button.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
					button.setFocusPainted(false);
					boardPanel.add(button);
					cells[y][x] = button;
				} else {
					JButton button = new JButton();
					button.setBackground(Color.decode("#00B0C8"));
					button.setPreferredSize(new Dimension(100, 100));
					button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
					button.setFocusPainted(false);
					boardPanel.add(button);
					cells[y][x] = button;
				}
			}
		}
	}
	
	public JPanel getPanel() {
		return boardPanel;
	}
	
	public JButton[][] getCells() {
		return cells;
	}

}
