package render;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gameObjects.NodeType;
import main.Game;

/**
 * Map class
 * 
 * @author Harshal
 *
 */

public class Map extends MouseAdapter {

	// Variables
	private Game game;

	/**
	 * Map constructor
	 * 
	 * @param game
	 */
	public Map(Game game) {
		this.game = game;
	}

	/**
	 * mousePressed: This method keeps track of mouse being pressed.
	 * 
	 * @param event
	 */
	public void mousePressed(MouseEvent event) {
		int mouseX = event.getX();
		int mouseY = event.getY();
		game.placeObject(game.hud.previousObject, mouseX, mouseY);
	}

	/**
	 * renderMap: This method renders the map onto the screen based on the Node
	 * array's object type. Any changes to the node array would result in the map
	 * changing.
	 * 
	 * @param g2d
	 */
	public void renderMap(Graphics2D g2d) {
		int x = 0;
		int y = 0;

		for (int col = 0; col < game.node.length; col++) {
			for (int row = 0; row < game.node[col].length; row++) {
				g2d.drawImage(game.node[row][col].getObject().getImage(), x, y, game.scaledTile, game.scaledTile, null);
				if (game.node[row][col].getVisited() == true) {
					g2d.drawImage(game.checked.getImage(), x, y, game.scaledTile, game.scaledTile, null);
				}
				if (game.node[row][col].getPath() == true) {
					g2d.drawImage(game.path.getImage(), x, y, game.scaledTile, game.scaledTile, null);
				}

				// Print the terrain's first or first two letters for easy recognition once
				// those nodes have been visted or have been set as path.
				if (game.node[row][col].getObject().getType() == NodeType.Grassland) {
					g2d.drawString("G" + game.node[row][col].getObject().getTerrainCost(), x + game.scaledTile / 3,
							y + game.scaledTile / 2 + game.scaledTile / 6);
				}
				if (game.node[row][col].getObject().getType() == NodeType.OpenTerrain) {
					g2d.drawString("OT" + game.node[row][col].getObject().getTerrainCost(), x + game.scaledTile / 5,
							y + game.scaledTile / 2 + game.scaledTile / 6);
				}
				if (game.node[row][col].getObject().getType() == NodeType.Obstacle) {
					g2d.drawString("O", x + game.scaledTile / 3, y + game.scaledTile / 2 + game.scaledTile / 6);
				}
				if (game.node[row][col].getObject().getType() == NodeType.Swampland) {
					g2d.drawString("S" + game.node[row][col].getObject().getTerrainCost(), x + game.scaledTile / 3,
							y + game.scaledTile / 2 + game.scaledTile / 6);
				}
				x += game.scaledTile;
			}
			y += game.scaledTile;
			x = 0;
		}
	}

	public void update() {
	}

	/**
	 * render: Renders graphics on the screen
	 * 
	 * @param g2d
	 */
	public void render(Graphics2D g2d) {
		renderMap(g2d);
	}
}
