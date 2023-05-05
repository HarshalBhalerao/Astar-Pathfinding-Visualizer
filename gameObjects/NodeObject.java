package gameObjects;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * NodeObject class
 * 
 * @author Harshal
 *
 */
public class NodeObject {
	// Variables
	public BufferedImage image;
	public boolean collision = false;
	public int cost;
	public NodeType type;

	private BufferedImage grassland;
	private BufferedImage obstacle;
	private BufferedImage swampland;
	private BufferedImage openTerrain;
	private BufferedImage ant;
	private BufferedImage food;
	private BufferedImage checked;
	private BufferedImage path;

	/**
	 * NodeObject constructor
	 * 
	 * @param type
	 */
	public NodeObject(NodeType type) {
		this.type = type;
		initializeImage();
	}

	/**
	 * initializeImage: This method initializes all the images at once to avoid
	 * fetching them every time. This helps improve performance of the game.
	 */
	public void initializeImage() {
		try {
			grassland = ImageIO.read(getClass().getResourceAsStream("/images/terrain/grassland.png"));
			obstacle = ImageIO.read(getClass().getResourceAsStream("/images/terrain/obstacle.png"));
			swampland = ImageIO.read(getClass().getResourceAsStream("/images/terrain/swampland.png"));
			openTerrain = ImageIO.read(getClass().getResourceAsStream("/images/terrain/openTerrain.png"));
			ant = ImageIO.read(getClass().getResourceAsStream("/images/cells/ant.png"));
			food = ImageIO.read(getClass().getResourceAsStream("/images/cells/food.png"));
			checked = ImageIO.read(getClass().getResourceAsStream("/images/cells/checked.png"));
			path = ImageIO.read(getClass().getResourceAsStream("/images/cells/path.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * getType: This method returns the type of the node.
	 * 
	 * @return NodeType
	 */
	public NodeType getType() {
		if (type == null) {
			return NodeType.None;
		}
		return type;
	}

	/**
	 * setObject: We can set the node object type to some other terrain type from
	 * this method.
	 * 
	 * @param type
	 */
	public void setObject(NodeType type) {
		this.type = type;
	}

	/**
	 * getImage: This method would return a specific image for a specific terrain
	 * type.
	 * 
	 * @return BufferedImage
	 */
	public BufferedImage getImage() {
		if (this.type == NodeType.Grassland) {
			image = this.grassland;
		} else if (this.type == NodeType.Obstacle) {
			image = this.obstacle;
		} else if (this.type == NodeType.Swampland) {
			image = this.swampland;
		} else if (this.type == NodeType.OpenTerrain) {
			image = this.openTerrain;
		} else if (this.type == NodeType.Ant) {
			image = this.ant;
		} else if (this.type == NodeType.Food) {
			image = this.food;
		} else if(this.type == NodeType.Checked) {
			image = this.checked;
		} else if(this.type == NodeType.Path) {
			image = this.path;
		}
		return image;
	}

	/**
	 * getTerrainCost: This method returns the terrainCost for respective terrains.
	 * The obstacle is set to 0 but the collision variable for obstacle is true.
	 * Thus, its cost would not matter.
	 * 
	 * @return int
	 */
	public int getTerrainCost() {
		if (this.type == NodeType.Grassland) {
			cost = 3;
		} else if (this.type == NodeType.Obstacle) {
			cost = 1000;
		} else if (this.type == NodeType.Swampland) {
			cost = 4;
		} else if (this.type == NodeType.OpenTerrain) {
			cost = 1;
		}
		return cost;
	}

	/**
	 * getTerrainCollision: This method checks if the NodeType is an obstacle. If it
	 * is one then it sets collision to true. This means that this node is not in
	 * the search area for shortest path and this node cannot be passed through.
	 * 
	 * @return boolean
	 */
	public boolean getTerrainCollision() {
		if (this.type == NodeType.Obstacle) {
			collision = true;
		} else if (this.type == NodeType.Grassland || this.type == NodeType.Swampland
				|| this.type == NodeType.OpenTerrain) {
			collision = false;
		}
		return collision;
	}
}
