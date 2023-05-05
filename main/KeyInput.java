package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * KeyInput class
 * 
 * @author Harshal
 *
 */
public class KeyInput implements KeyListener {

	public boolean[] key = new boolean[10];

	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * keyPressed(KeyEvent e): Sets a specific boolean variable to true when that
	 * specific key is pressed.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_ENTER) {
			key[0] = true;
		}
		if (keyCode == KeyEvent.VK_1) {
			key[1] = true;
		}
		if (keyCode == KeyEvent.VK_2) {
			key[2] = true;
		}
		if (keyCode == KeyEvent.VK_3) {
			key[3] = true;
		}
		if (keyCode == KeyEvent.VK_4) {
			key[4] = true;
		}
		if (keyCode == KeyEvent.VK_5) {
			key[5] = true;
		}
		if (keyCode == KeyEvent.VK_6) {
			key[6] = true;
		}
		if (keyCode == KeyEvent.VK_R) {
			key[7] = true;
		}
	}

	/**
	 * keyReleased(KeyEvent e): Sets a specific boolean variable to false when that
	 * specific key is released.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_ENTER) {
			key[0] = false;
		}
		if (keyCode == KeyEvent.VK_1) {
			key[1] = false;
		}
		if (keyCode == KeyEvent.VK_2) {
			key[2] = false;
		}
		if (keyCode == KeyEvent.VK_3) {
			key[3] = false;
		}
		if (keyCode == KeyEvent.VK_4) {
			key[4] = false;
		}
		if (keyCode == KeyEvent.VK_5) {
			key[5] = false;
		}
		if (keyCode == KeyEvent.VK_6) {
			key[6] = false;
		}
		if (keyCode == KeyEvent.VK_R) {
			key[7] = false;
		}
	}

}
