package render;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import gameObjects.NodeObject;
import gameObjects.NodeType;
import main.Game;
import main.KeyInput;

/**
 * Hud class
 * 
 * @author Harshal
 */

public class Hud {

	// Variables
	private Game game;
	public NodeObject previousObject;
	private KeyInput input;

	/**
	 * Hud constructor
	 * 
	 * @param game
	 * @param input
	 */
	public Hud(Game game, KeyInput input) {
		this.game = game;
		previousObject = new NodeObject(NodeType.Grassland);
		this.input = input;
	}

	/**
	 * getHudObject: This method returns a NodeObject whenever a specific key is
	 * pressed
	 * 
	 * @return NodeObject
	 */
	public NodeObject getHudObject() {
		if (input.key[0] == true) {
			// If the ENTER key is pressed, then we execute search function.
			game.search();
		}
		if (input.key[1] == true) {
			previousObject = game.grassland;
		}
		if (input.key[2] == true) {
			previousObject = game.obstacle;
		}
		if (input.key[3] == true) {
			previousObject = game.openTerrain;
		}
		if (input.key[4] == true) {
			previousObject = game.swampland;
		}
		if (input.key[5] == true) {
			previousObject = game.antObject;
		}
		if (input.key[6] == true) {
			previousObject = game.foodObject;
		}
		return previousObject;
	}

	/**
	 * update: Updates the state of the game
	 */
	public void update() {
		this.getHudObject();
	}

	/**
	 * render: Renders graphics on the screen. In this case this renders the box
	 * "Terrains and Cells" on the screen.
	 * 
	 * @param g2d
	 */
	public void render(Graphics2D g2d) {
		// Make a rectangle with rounded edges
		g2d.setColor(Color.WHITE);
		g2d.fillRoundRect(game.width - (8 * game.scaledTile), game.scaledTile / 2,
				game.width - (int) (17.5 * game.scaledTile), game.height - (int) (5 * game.scaledTile), 2, 2);

		// Create two different fonts
		Font title = new Font("arial", 1, 25);
		Font body = new Font("arial", 1, 10);

		g2d.setColor(Color.BLACK);
		// Set title font and text
		g2d.setFont(title);
		g2d.drawString("Terrains and Cells", game.width - (int) (7.75 * game.scaledTile), game.scaledTile + 10);

		// Now we render terrain tiles in the box and give some useful information
		// For grassland
		g2d.drawImage(game.grassland.getImage(), game.width - (8 * game.scaledTile) + 10,
				game.height - (18 * game.scaledTile), (int) Math.floor(game.scaledTile * 1.5),
				(int) Math.floor(game.scaledTile * 1.5), null);
		g2d.setFont(body);
		g2d.drawString("Grassland: Press 1 to activate", game.width - (6 * game.scaledTile),
				game.height - (int) (17.5 * game.scaledTile));
		g2d.drawString("Cost : 3", game.width - (6 * game.scaledTile), game.height - (17 * game.scaledTile));

		// For obstacle
		g2d.drawImage(game.obstacle.getImage(), game.width - (8 * game.scaledTile) + 10,
				game.height - (16 * game.scaledTile), (int) Math.floor(game.scaledTile * 1.5),
				(int) Math.floor(game.scaledTile * 1.5), null);
		g2d.setFont(body);
		g2d.drawString("Obstacle: Press 2 to activate", game.width - (6 * game.scaledTile),
				game.height - (int) (15.5 * game.scaledTile));
		g2d.drawString("Cost : 1000", game.width - (6 * game.scaledTile), game.height - (15 * game.scaledTile));

		// For openTerrain
		g2d.drawImage(game.openTerrain.getImage(), game.width - (8 * game.scaledTile) + 10,
				game.height - (14 * game.scaledTile), (int) Math.floor(game.scaledTile * 1.5),
				(int) Math.floor(game.scaledTile * 1.5), null);
		g2d.setFont(body);
		g2d.drawString("Open Terrain: Press 3 to activate", game.width - (6 * game.scaledTile),
				game.height - (int) (13.5 * game.scaledTile));
		g2d.drawString("Cost : 1", game.width - (6 * game.scaledTile), game.height - (13 * game.scaledTile));

		// For swampland
		g2d.drawImage(game.swampland.getImage(), game.width - (8 * game.scaledTile) + 10,
				game.height - (12 * game.scaledTile), (int) Math.floor(game.scaledTile * 1.5),
				(int) Math.floor(game.scaledTile * 1.5), null);
		g2d.setFont(body);
		g2d.drawString("Swampland: Press 4 to activate", game.width - (6 * game.scaledTile),
				game.height - (int) (11.5 * game.scaledTile));
		g2d.drawString("Cost : 4", game.width - (6 * game.scaledTile), game.height - (11 * game.scaledTile));

		// For startNode
		g2d.drawImage(game.antObject.getImage(), game.width - (8 * game.scaledTile) + 10,
				game.height - (10 * game.scaledTile), (int) Math.floor(game.scaledTile * 1.5),
				(int) Math.floor(game.scaledTile * 1.5), null);
		g2d.setFont(body);
		g2d.drawString("Ant / Start Cell: Press 5 to activate", game.width - (6 * game.scaledTile),
				game.height - (int) (9.25 * game.scaledTile));

		// For endNode
		g2d.drawImage(game.foodObject.getImage(), game.width - (8 * game.scaledTile) + 10,
				game.height - (8 * game.scaledTile), (int) Math.floor(game.scaledTile * 1.5),
				(int) Math.floor(game.scaledTile * 1.5), null);
		g2d.setFont(body);
		g2d.drawString("Food / Goal Cell: Press 6 to activate", game.width - (6 * game.scaledTile),
				game.height - (int) (7.25 * game.scaledTile));
	}
}
