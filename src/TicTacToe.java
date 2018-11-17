import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.imageio.ImageIO;
import javax.swing.*;

public class TicTacToe {

	private Frame frame;
	private Board board;
	private JButton[][] cells;
	private String currentPlayer;
	//Player 1 Tile
    private String p1Resource;
    private Image p1Tile;
    private ImageIcon p1Icon;
    //Player 2 Tile
    private String p2Resource;
    private Image p2Tile;
    private ImageIcon p2Icon;
	private int moves;
	private boolean theEnd;
	private JButton resetButton;
	private JButton quitButton;
	private Clip clip;
	
	public TicTacToe(Frame frame, Board board) {
		this.frame = frame;
		this.board = board;
		generatePlayerResources();
		resetButton = frame.getResetButton();
		quitButton = frame.getQuitButton();
		clip = null;
		startAudio();
		start();
		
	}
	
	public void start() {
		cells = board.getCells();
		currentPlayer = "rick";
		moves = 0;
		//Add action event listener
		addActionEventListeners();
		theEnd  = false;
	
	}
	
	public void startAudio() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("start.wav"));
	        clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	        // If you want the sound to loop infinitely, then put: clip.loop(Clip.LOOP_CONTINUOUSLY); 
	        // If you want to stop the sound, then use clip.stop();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	public void drawAudio() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("draw.wav"));
	        clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	        // If you want the sound to loop infinitely, then put: clip.loop(Clip.LOOP_CONTINUOUSLY); 
	        // If you want to stop the sound, then use clip.stop();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	public void winnerAudio() {
	    if(currentPlayer.equals("rick")) {
	    	try {
		        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("rick.wav"));
		        clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.start();
		        // If you want the sound to loop infinitely, then put: clip.loop(Clip.LOOP_CONTINUOUSLY); 
		        // If you want to stop the sound, then use clip.stop();
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
	    } else {
	    	try {
		        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("morty.wav"));
		        clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.start();
		        // If you want the sound to loop infinitely, then put: clip.loop(Clip.LOOP_CONTINUOUSLY); 
		        // If you want to stop the sound, then use clip.stop();
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
	    }
		
	}
	
	public void addActionEventListeners() {
		for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
            	cells[y][x].addActionListener(
                    (ActionEvent e) -> { move(e); });
            }
        }
		
		resetButton.addActionListener(
                (ActionEvent e) -> { reset(); });
		quitButton.addActionListener((ActionEvent e) -> { 
			quit();
			frame.quit(); 
		});
	}
	
	public void generatePlayerResources() {
		//Player 1
		p1Resource = "p1.png";
		try {
			p1Tile = ImageIO.read(this.getClass().getResourceAsStream(p1Resource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		p1Icon = new ImageIcon(p1Tile);
		
		
		//Player 2
		p2Resource = "p2.png";
		try {
			p2Tile = ImageIO.read(this.getClass().getResourceAsStream(p2Resource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		p2Icon = new ImageIcon(p2Tile);
	}
	
	public void move(ActionEvent e) {
		if(!theEnd) {
			moves++;
			JButton button = null;
			Object obj = e.getSource();
			if(obj instanceof JButton) {
				button = (JButton) obj;
			}
			if(button.getIcon() == null) {
				
				button.setIcon(getCurrentPlayerIcon());
				
				if(moves > 3 && moves < 9) {
					if(checkForWinner()) { 
						frame.setWinner(currentPlayer);
						theEnd = true;
						frame.setScore(currentPlayer);
						winnerAudio();
					} else {
						switchCP();
					}
				} else if (moves == 9) {
					if(checkForWinner()) {
						frame.setWinner(currentPlayer);
						theEnd = true;
						frame.setScore(currentPlayer);
						winnerAudio();
					} else {
						frame.setDraw();
						drawAudio();
					}
				} else {
					switchCP();
				}
	
			}
		}
	}
	
	public void reset() {
		//Stop audio
		clip.stop();
		//Clear all Icons
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 3; x++) {
				cells[y][x].setIcon(null);
			}
		}
		theEnd = false;
		frame.reset();
		currentPlayer = "rick";
		moves = 0;
	}
	public void quit() {
		//Stop audio
		clip.stop();
	}
	
	public void switchCP() {
		if(currentPlayer.equals("rick")) {
			currentPlayer = "morty";
		} else {
			currentPlayer = "rick";
		}
		frame.changePlayerLabel();
	}
	
	public ImageIcon getCurrentPlayerIcon() {
		if(currentPlayer.equals("rick")) {
			return p1Icon;
		} else {
			return p2Icon;
		}
	}
	
	public boolean checkForWinner() {
		boolean winner = false;
		ImageIcon cp = getCurrentPlayerIcon();
		int streak = 0;
			
		if(streak < 3) {
			//Check Top Horizontal
			for(int a = 0; a < 3; a++) {
				if(cells[0][a].getIcon() == cp) {
					streak++;
				} else if(cells[0][a].getIcon() != cp ) {
					streak = 0;
					a = 3;
				}
			}
		}
		if(streak < 3) {
			//Check Middle Horizontal
			for(int a = 0; a < 3; a++) {
				if(cells[1][a].getIcon() == cp) {
					streak++;
				} else if(cells[1][a].getIcon() != cp ) {
					streak = 0;
					a = 3;
				}
			}
		}
		if(streak < 3) {
			//Check Bottom Horizontal
			for(int a = 0; a < 3; a++) {
				if(cells[2][a].getIcon() == cp) {
					streak++;
				} else if(cells[2][a].getIcon() != cp ) {
					streak = 0;
					a = 3;
				}
			}
		}
		if(streak < 3) {
			//Check Right Vertical
			for(int a = 0; a < 3; a++) {
				if(cells[a][2].getIcon() == cp) {
					streak++;
				} else if(cells[a][2].getIcon() != cp ) {
					streak = 0;
					a = 3;
				}
			}
		}
		if(streak < 3) {
			//Check Left Vertical
			for(int a = 0; a < 3; a++) {
				if(cells[a][0].getIcon() == cp) {
					streak++;
				} else if(cells[a][0].getIcon() != cp ) {
					streak = 0;
					a = 3;
				}
			}
		}
		if(streak < 3) {
			//Check Middle Vertical
			for(int a = 0; a < 3; a++) {
				if(cells[a][1].getIcon() == cp) {
					streak++;
				} else if(cells[a][1].getIcon() != cp ) {
					streak = 0;
					a = 3;
				}
			}
		}
		if(streak < 3) {
			//Check Left Diagonal Top
			for(int a = 0; a < 3; a++) {
				if(cells[a][a].getIcon() == cp) {
					streak++;
				} else if(cells[a][a].getIcon() != cp ) {
					streak = 0;
					a = 3;
				}
			}
		}
		if(streak < 3) {
			//Check Right Diagonal Bottom
			int b = 0;
			for(int a = 2; a > -1 ; a--) {
				if(cells[a][b].getIcon() == cp) {
					streak++;
					b++;
				} else if(cells[a][b].getIcon() != cp ) {
					streak = 0;
					a = -1;
				}
			}
		}
		
		if(streak == 3) {
			winner = true;
			streak = 0;
		}
		
		return winner;
	}

}
