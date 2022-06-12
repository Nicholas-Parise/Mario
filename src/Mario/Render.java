package Mario;

import java.awt.*;
import javax.swing.*;

/***************************
 * Nicholas Parise, 
 * Sile Keenan, 
 * ICS4U, 
 * Tetris
 ****************************/


// This class deals with the rendering window
public class Render extends JPanel {

 // Private var

 // colors
 private Color colors[] = { new Color(80, 80, 80), new Color(255, 255, 255), new Color(255, 50, 50),
   new Color(50, 255, 50), new Color(50, 50, 255), new Color(225, 225, 50), new Color(225, 50, 225),
   new Color(50, 225, 225), new Color(250, 170, 0), new Color(255, 50, 50, 50), new Color(50, 255, 50, 50),
   new Color(50, 50, 255, 50), new Color(225, 225, 50, 50), new Color(225, 50, 225, 50),
   new Color(50, 225, 225, 50), new Color(250, 170, 0, 50) };
 // Normal: black0,white1,red2,green3,blue4,yellow5,purple6,cyan7,orange8,
 // Lighter: red9,green10,blue11,yellow12,purple13,cyan14,orange15,

 private static int DeltaTitle = 0;
 private static int SplashFall = 0;

 private int[] ColorWave = new int[6];

 private int[] ColorWave1 = { 2, 3, 4, 5, 6, 7 };
 private int[] ColorWave2 = { 7, 2, 3, 4, 5, 6 };
 private int[] ColorWave3 = { 6, 7, 2, 3, 4, 5 };
 private int[] ColorWave4 = { 5, 6, 7, 2, 3, 4 };
 private int[] ColorWave5 = { 4, 5, 6, 7, 2, 3 };
 private int[] ColorWave6 = { 3, 4, 5, 6, 7, 2 };

 private int[][] TempMatrix = new int[5][3];

 // public var

// public static JFrame frame = new JFrame();
// public static KeyBoard keyboard = new KeyBoard();
// public static Mouse mouse = new Mouse();

 public JFrame frame;
 public KeyBoard keyboard;
 public Mouse mouse;

 Mario mario;

 // ----------------------------

 public Render(Mario m){

  mario = m;

  frame = new JFrame();
  keyboard = new KeyBoard(mario);
  mouse = new Mouse();

  frame.getContentPane().add(this);

  frame.setTitle("Tetris");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setBackground(Color.red);
  frame.setLocationRelativeTo(null);
  frame.setSize(800, 400);
  frame.setVisible(true);

  addKeyListener(keyboard);

  setFocusable(true);

  addMouseListener(mouse);
  addMouseMotionListener(mouse);
  setFocusable(true);

  //frame.add(keyboard);
  add(keyboard);
  add(mouse);
  getKeyListeners();

 }

 private void ArrayBuilder(Graphics g, int xOff, int yOff, int blockSize, int color, int color2, int color3, int[][] arr) {
  // Takes x,y size, color and 2d arr and draws it to the screen

  int numberOfColumns = arr[0].length;
  int numberOfRows = arr.length;

  for (int j = 0; j < numberOfColumns; j++) {
   for (int i = 0; i < numberOfRows; i++) {

    int x = blockSize * j + xOff;
    int y = blockSize * i + yOff;

    if (arr[i][j] == 1) {
     g.setColor(colors[color]);
     g.fillRect(x, y, blockSize, blockSize);
    } else if (arr[i][j] == 2) {
    if(color2!=-1) {
     g.setColor(colors[color2]);
     g.fillRect(x, y, blockSize, blockSize);
    }
    } else if (arr[i][j] == 0) {
     if(color3!=-1) {
      g.setColor(colors[color3]);
      g.fillRect(x, y, blockSize, blockSize);
     }
     }
   }
  }
 }

 private void WireBox(Graphics g, int xOff, int yOff, int Width, int Height, int color) {
  // Takes x,y, color, width, hight and draws a square wire frame to the screen
  g.setColor(colors[color]);

  g.drawLine(xOff, yOff + Width - 1, xOff, yOff);
  g.drawLine(xOff, yOff, xOff + Height - 1, yOff);
  g.drawLine(xOff + 1, yOff + Width - 1, xOff + Height - 1, yOff + Width - 1);
  g.drawLine(xOff + Height - 1, yOff + Width - 1, xOff + Height - 1, yOff + 1);
 }



 protected void paintComponent(Graphics g) {
  super.paintComponent(g);

  g.setColor(colors[0]);
  g.fillRect(0, 0, 1000, 1000);



  g.setColor(colors[4]);
  g.fillRect( mario.player.getX(),  mario.player.getY(),  mario.player.getHitBoxX(), mario.player.getHitBoxY());

   int[][] arr = {
          {1,1,1,1,1,1},
          {1,0,0,0,0,1},
          {1,0,0,1,1,1},
          {1,0,0,0,0,1},
          {1,0,0,0,0,1},
          {1,1,1,1,1,1}};

  ArrayBuilder(g, 30, 50, 50, 2, -1, -1, arr);

 // int[][] test =  mario.level.arr;

 //ArrayBuilder(g, 300, 250, 5, 2, 0, 0,test);

// ArrayBuilder(g, 288, 200, 5, 2, 0, 0, AssetManager.ScoreText);

 }

/*
 public void paint(Graphics g) {


  /*

  // -------------

  // background
  g.setColor(colors[0]);
  g.fillRect(0, 0, 1000, 1000);


      int x = 25 * j + (j + 25);
      int y = 25 * i + (i + 25);
      int width = 25;
      int height = 25;


      Color color = colors[0];


      g.setColor(color);
      g.fillRect(x, y, width, height);



    /////

    // score and high score

      String HighscoreString ="";//= Integer.toString(ScoreManager.Highscore);
    String ScoreString ="";//= Integer.toString(ScoreManager.score);

    int[][] ScoreArr = AssetManager.AssetCreater(ScoreString);
    int[][] HighScoreArr = AssetManager.AssetCreater(HighscoreString);

    ArrayBuilder(g, 300, 250, 5, 1, 0, 0, ScoreArr);

    ArrayBuilder(g, 300, 400, 5, 1, 0, 0, HighScoreArr);

    ArrayBuilder(g, 288, 20, 5, 1, 0, 0, AssetManager.NextPieceText);

    ArrayBuilder(g, 288, 200, 5, 2, 0, 0, AssetManager.ScoreText);

    ArrayBuilder(g, 288, 350, 5, 2, 0, 0, AssetManager.HighScoreText);

    // Box

    WireBox(g, 300, 50, 100, 100, 1);



 }
 */


 public void Update() {
  // updates the render window
  validate();
  repaint();

 }

}