import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Framed extends JFrame {
   
       
    private static final int GRIDSIZE = 3;
    private LightButton [][] lightButton = new LightButton[GRIDSIZE][GRIDSIZE];
   
    private static final int SQUARESTOSTART = 4;
   
    private int [] startRow = new int [SQUARESTOSTART];
    private int [] startCol = new int [SQUARESTOSTART];
   
    public Framed(){
        initGUI();
        setUp();
       
        setTitle("Framed");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       
    }
       
    private void initGUI(){
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);
        add(titlePanel, BorderLayout.PAGE_START);
        Font titleFont = new Font("Arial", Font.BOLD, 32);
        JLabel titleLabel = new JLabel("Framed");
        titleLabel.setBackground(Color.BLACK);
        titleLabel.setOpaque(true);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(titleLabel);
       
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE));
        add(centerPanel,BorderLayout.CENTER);
        for (int r = 0; r < GRIDSIZE; r++){
            for (int c = 0; c < GRIDSIZE; c++){
                lightButton[r][c] = new LightButton(r, c);
                centerPanel.add(lightButton[r][c]);
                lightButton[r][c].addActionListener (new ActionListener() {
                    public void actionPerformed (ActionEvent e) {
                        LightButton button = (LightButton) e.getSource();
                        int row = button.getRow();
                        int col = button.getCol();
                        lightButton [row][col].toggle();//makes light turn on/off
                        if (checkBounds(row-1,col)) {//makes other lights go on/off around light clicked,
                        lightButton [row-1][col].toggle();
                        }if (checkBounds(row+1,col)) {
                        lightButton [row+1][col].toggle();
                        }if (checkBounds(row,col-1)) {
                        lightButton [row][col-1].toggle();
                        }if (checkBounds(row,col+1)) {
                        lightButton [row][col+1].toggle();
                        }
                        checkIfWin();
            }
        });

        }}
       
       
    }
       
   
    public void ActivateToggle (int r, int c) {//what does this do
       
    }
   
    public void setUp() {
        Random rand = new Random();//makes random arrangment of red and black
        int pickRow;
        int pickCol;
        for (int i = 0; i < SQUARESTOSTART; i++) {    
            do {
                pickRow = rand.nextInt(GRIDSIZE);
                pickCol = rand.nextInt(GRIDSIZE);
            }while(lightButton[pickRow][pickCol].isLit());
            lightButton[pickRow][pickCol].turnOn();
            startRow[i] = pickRow;
            startCol[i] = pickCol;
           
        }}
   
       
    public static void main(String[] args) {
        try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch ( Exception e) {}
       
        EventQueue.invokeLater(new Runnable (){
            @Override
            public void run() {
                new Framed();
            }  
        });

    }
    public boolean checkBounds(int r, int c) {//checks to see if toggle is in bounds like we did in watch your step,
    //litterally copied it over from watch your step
		if (r >= 0 && r< GRIDSIZE && c >= 0 && c< GRIDSIZE) {
		return true;
		}else {
		return false;
		}
    }
	
	public void checkIfWin () {
		int midR = 1;
		int midC = 1;
	
		if (lightButton[midR][midC +1].isLit() && lightButton[midR+1][midC +1].isLit() && lightButton[midR+1][midC].isLit()
				 && lightButton[midR-1][midC +1].isLit() && lightButton[midR-1][midC].isLit() && lightButton[midR +1][midC -1].isLit() &&
				 lightButton[midR][midC-1].isLit() && lightButton[midR-1][midC - 1].isLit()) {
			System.out.println("you won");
			// open a window asking if you want a new game
			//open window:
			//int input = JOptionPane.showConfirmDialog(null, "Would you like to open a new game? ");
			//if (input == "yes") {
				//initGUI();
			endGame();
				
			}
			
			//String newGame = String.substring(input1);
			//if yes open new game
			
			
		
		
		if (lightButton[midR][midC].isLit() && !lightButton[midR][midC +1].isLit() && !lightButton[midR+1][midC +1].isLit() && !lightButton[midR+1][midC].isLit()
				 && !lightButton[midR-1][midC +1].isLit() && !lightButton[midR-1][midC].isLit() && !lightButton[midR +1][midC -1].isLit() &&
				 !lightButton[midR][midC-1].isLit() && !lightButton[midR-1][midC - 1].isLit()) {
			System.out.println("you won");
			String input1 = JOptionPane.showInputDialog(null, "Would you like to open a new game? ");
			if (input1 == "yes") {
				initGUI();
				
			}
		}
		
	}




public void endGame () {
	int reply = JOptionPane.showConfirmDialog(this,
			"n/Do you want to play again?", "Play again?", JOptionPane.YES_NO_OPTION);
	if (reply == JOptionPane.YES_OPTION) {
		newGame();
		setUp();
		
	}else {
		System.exit(0); 
	}
}

public void newGame () {
	for (int r = 0; r < GRIDSIZE; r ++) {
		for (int c= 0; c< GRIDSIZE; c++) {
			lightButton [r] [c].reset();
		}
	}
}
}


