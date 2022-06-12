package Mario;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/***************************
 * Nicholas Paris
 ****************************/

/// Handles Keyboard input
public class KeyBoard extends JPanel implements KeyListener {

Mario mario;

	public KeyBoard(Mario m){
		System.out.println(m);
		mario = m;
	}

	public void keyPressed(KeyEvent evt) {
		int keyCode = evt.getKeyCode();

		System.out.println(evt);

		if (keyCode == KeyEvent.VK_ESCAPE) {
			// escape key
			GameStateManager.StartPause();
			// allows the game to be paused
		}

		if (keyCode == KeyEvent.VK_SPACE) {
			// space key
			GameStateManager.StartGame();
			// starts the game
		}

		if (keyCode == KeyEvent.VK_ENTER) {
			// Enter key

			// if in game mode makes the blocks instantly teleport to the bottom
			//Blocks.FastFall();

			// if in game over stop input
			//ScoreManager.StopInput();
		}

		if (keyCode == KeyEvent.VK_LEFT) {
			// left arrow key
			mario.player.moveleft();
			System.out.print("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa ");
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			// right arrow key
			System.out.print("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb ");
			mario.player.moveRight();
		}
		if (keyCode == KeyEvent.VK_UP) {
			// up arrow key
			mario.player.jump();
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			// down arrow key

		}
	}

	public void keyTyped(KeyEvent e) {

		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_LEFT) {
			// left arrow key
			mario.player.moveleft();
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			// right arrow key
			mario.player.moveRight();
		} else if (keyCode == KeyEvent.VK_UP) {
			// up arrow key
			mario.player.jump();
		} else if (keyCode == KeyEvent.VK_DOWN) {
			// down arrow key

		}
	}

	// this funtion is necessary to stop run time error but is unused
	public void keyReleased(KeyEvent e) {
	}

}
