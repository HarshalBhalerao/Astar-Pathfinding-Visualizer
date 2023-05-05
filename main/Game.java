package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import gameObjects.Node;
import gameObjects.NodeObject;
import gameObjects.NodeType;
import render.Hud;
import render.Map;

/**
 * Game class
 * 
 * @author Harshal
 *
 */
public class Game extends JPanel implements Runnable {

	private static final long serialVersionUID = 391532989211571837L;

	// Set the screen
	private final int tile = 16; // Each tile on the screen is going to be 16 pixels
	private final int scale = 2; // Increase the scale of the tile by multiplying it with scale number.

	public final int scaledTile = tile * scale; // The tile will now be 48 pixels of width and height.
	public final int screenRow = 20; // Total number of rows on screen
	public final int screenCol = 25; // Total number of columns on screen

	// Calculate the width and height of the screen
	public final int width = scaledTile * screenCol;
	public final int height = scaledTile * screenRow;

	// 16 row x 16 col map area
	public final int mapRow = 16;
	public final int mapCol = 16;

	// Thread
	private Thread thread;
	private boolean executing = false;

	// For keyboard input
	public KeyInput input;

	// Rendering objects
	public Hud hud;
	public Map map;

	// NodeType Enum
	public NodeType type;

	// NodeObjects
	public NodeObject grassland;
	public NodeObject swampland;
	public NodeObject obstacle;
	public NodeObject openTerrain;
	public NodeObject none;
	public NodeObject antObject;
	public NodeObject foodObject;
	public NodeObject checked;
	public NodeObject path;

	// Node
	public Node[][] node;
	public Node startNode, goalNode, currentNode;
	public ArrayList<Node> openList = new ArrayList<>();

	// Others
	public boolean goalReached = false;
	public Vector coordinates;

	/**
	 * Game Constructor
	 */
	public Game() {
		// Set the size of the screen
		this.setPreferredSize(new Dimension(width, height));

		// Make the background of the screen black
		this.setBackground(Color.black);

		// Uses buffer to paint on the screen
		this.setDoubleBuffered(true);

		// Calling the initialize method below
		initialize();

		// Take control of the game as soon as screen spawns
		this.setFocusable(true);
	}

	/**
	 * initialize(): This method would initialize all the objects.
	 */
	public void initialize() {
		coordinates = new Vector(0, 0);

		// Initialize the NodeObjects
		grassland = new NodeObject(NodeType.Grassland);
		obstacle = new NodeObject(NodeType.Obstacle);
		openTerrain = new NodeObject(NodeType.OpenTerrain);
		swampland = new NodeObject(NodeType.Swampland);
		none = new NodeObject(NodeType.None);
		antObject = new NodeObject(NodeType.Ant);
		foodObject = new NodeObject(NodeType.Food);
		checked = new NodeObject(NodeType.Checked);
		path = new NodeObject(NodeType.Path);

		// Initialize the node array and fill it with grassland as the default node
		// object. The node object could be anything but I chose grassland.
		node = new Node[mapRow][mapCol];

		for (int i = 0; i < mapRow; i++) {
			for (int j = 0; j < mapCol; j++) {
				node[i][j] = new Node(i, j, this.grassland);
			}
		}

		// Listen to key inputs from the user.
		input = new KeyInput();
		this.addKeyListener(input);

		// Initialize the rendering objects
		hud = new Hud(this, input);
		map = new Map(this);

		// Listen to mouse inputs from the user
		this.addMouseListener(map);
	}

	/**
	 * start(): This synchronized method starts a new thread. This game runs on a
	 * single thread. We set our boolean variable executing to true for our game
	 * loop.
	 */
	public synchronized void start() {
		try {
			thread = new Thread(this);
			thread.start();
			executing = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * stop(): This synchronized method stops the executing thread. This method is
	 * called at the end of the game loop to safely stop the execution of the
	 * thread.
	 */
	public synchronized void stop() {
		try {
			thread.join();
			executing = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * processInput(): This method makes the thread sleep to ensure smooth running
	 * of the game. It introduces lag which makes the characters in the game move
	 * frame by frame after the player input.
	 */
	private void processInput() {
		try {
			int lag = 30;
			Thread.sleep(lag);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * run(): A simple Game loop which makes the game playable by controlling the
	 * rate of gameplay. Game loop is used as the game is dependent on an players
	 * input
	 */
	@Override
	public void run() {
		while (executing) {
			processInput();
			update();
			repaint();
		}
		stop();
	}

	/**
	 * mouseOver: This method checks if the mouse is over a specific area. It
	 * returns a boolean value respectively.
	 * 
	 * @param mouseX
	 * @param mouseY
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return boolean
	 */
	public boolean mouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
		if (mouseX > x && mouseX < x + width) {
			if (mouseY > y && mouseY < y + width) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * getRowsCols: This method gets the mouse x and y position and gets the
	 * respective row and col of the 16 x 16 node array.
	 * 
	 * @param mouseX
	 * @param mouseY
	 * @return Vector
	 */
	public Vector getRowsCols(int mouseX, int mouseY) {
		int posX = (int) Math.floor(mouseX / (16 * 2));
		int posY = (int) Math.floor(mouseY / (16 * 2));
		Vector coordinates = new Vector(-1, -1);
		if (posX >= 0 && posX < mapCol) {
			if (posY >= 0 && posY < mapRow) {
				coordinates.set(posX, posY);
			}
		}
		return coordinates;
	}

	/**
	 * getNode: Gets the node array
	 * 
	 * @return Node[][]
	 */
	public Node[][] getNode() {
		return node;
	}

	/**
	 * getArrayFromCoordinates: This method gets the specific node from the nodes
	 * array.
	 * 
	 * @param x
	 * @param y
	 * @return Node
	 */
	public Node getArrayFromCoordinates(int x, int y) {
		return node[x][y];
	}

	/**
	 * setNode: This method sets a specific terrain on a specific node
	 * 
	 * @param row
	 * @param col
	 * @param terrain
	 * @return Node [][]
	 */
	public Node[][] setNode(int row, int col, NodeObject terrain) {
		node[row][col] = new Node(row, col, terrain);
		return node;
	}

	/**
	 * placeObject: This method assigns new object to a specific node.
	 * 
	 * @param newObject
	 * @param mouseX
	 * @param mouseY
	 */
	public void placeObject(NodeObject newObject, int mouseX, int mouseY) {
		// Get the row and col to place the terrain
		coordinates = getRowsCols(mouseX, mouseY);

		// If row and col is not out of bounds, then set terrain to new terrain
		if ((int) coordinates.getX() >= 0 && (int) coordinates.getX() < mapCol && (int) coordinates.getY() >= 0
				&& (int) coordinates.getY() < mapRow) {
			if (newObject != antObject && newObject != foodObject) {
				// Assign a new object to that specific node
				node = setNode((int) coordinates.getX(), (int) coordinates.getY(), newObject);
				// If that specific object is an obstacle then we set that node's obstacle
				// boolean to true.
				if (newObject == obstacle) {
					node[(int) coordinates.getX()][(int) coordinates.getY()].setObstacle(true);
				}
			} else {
				// If check if the object is an start or ant object. If it is one then we check
				// if one already exists in the game. This is to avoid duplicates.
				if (newObject == antObject && !exist(antObject)) {
					startNode = node[(int) coordinates.getX()][(int) coordinates.getY()];
					startNode.setStart(true);
					currentNode = startNode; // We point our currentNode to startNode as this is where our search starts
					node = setNode((int) coordinates.getX(), (int) coordinates.getY(), newObject);
					openList.add(currentNode); // Add it to the openList arrayList according to the algorithm.
				}
				// We do the same with foodObject or goalNode
				if (newObject == foodObject && !exist(foodObject)) {
					goalNode = node[(int) coordinates.getX()][(int) coordinates.getY()];
					goalNode.setGoal(true);
					node = setNode((int) coordinates.getX(), (int) coordinates.getY(), newObject);
				}
			}
		}
	}

	/**
	 * exist method checks if that specific objectType exists in our game. Useful
	 * for detecting startNode and goalNode in order to avoid duplicates.
	 * 
	 * @param object
	 * @return boolean
	 */
	private boolean exist(NodeObject object) {
		for (int i = 0; i < mapRow; i++) {
			for (int j = 0; j < mapCol; j++) {
				if (node[i][j].getObject().getType() == object.getType()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * openNode method add the neighbouring/children nodes into our openList
	 * arrayList.
	 * 
	 * @param node
	 */
	private void openNode(Node node) {
		// We will first check if our neighbouring node is an obstacle or whether it is
		// already in our openList array or whether it has already been visited(removed
		// from openList)
		if (!node.getOpen() && !node.getVisited() && !node.getObstacle()) {
			// If the node is not open yet, add it to the open list
			node.setOpen(true);
			// Set its parent
			node.setParent(currentNode);
			// Add it to the openList.
			openList.add(node);
		}
	}

	/**
	 * getUnVisitedNeighbors method will check for currentNode's neighbouring nodes
	 */
	private void getUnVisitedNeighbors() {
		int row = currentNode.getRow();
		int col = currentNode.getCol();
		// Now use the next current node and
		// OPEN THE UP NODE
		if (row > 0) {
			openNode(node[row - 1][col]);
		}

		// OPEN THE LEFT NODE
		if (col > 0) {
			openNode(node[row][col - 1]);
		}

		// OPEN THE DOWN NODE
		if (row < mapRow - 1) {
			openNode(node[row + 1][col]);
		}

		// OPEN THE RIGHT NODE
		if (col < mapCol - 1) {
			openNode(node[row][col + 1]);
		}
	}

	/**
	 * search method searches for the optimal path from the startNode to the
	 * goalNode. Pseudo-code: https://en.wikipedia.org/wiki/A*_search_algorithm
	 */
	public void search() {
		// Check if the startNode, currentNode and goalNode have been set. If they
		// haven't been then we don't run it.
		if (startNode != null && goalNode != null && currentNode != null) {

			// First we set the cost of all the nodes
			for (int i = 0; i < mapRow; i++) {
				for (int j = 0; j < mapCol; j++) {
					Node.calculateCost(node[i][j], startNode, goalNode);
				}
			}

			// We check if the goal has been reached and of the openList isn't empty.
			if (!goalReached && openList.size() != 0) {
				// Now, first we visit all the neighbours surrounding the currentNode in order
				// to calculate fCost and gCost
				this.getUnVisitedNeighbors();

				int optimalIndex = 0;

				// We iterate through each one of those neighbours and find the lowest fCost or
				// gCost. And set the lowest fCost or gCost node to our currentNode
				for (int i = 0; i < openList.size(); i++) {
					if (openList.get(i).getFCost() < openList.get(optimalIndex).getFCost()) {
						optimalIndex = i;
					} else if (openList.get(i).getFCost() == openList.get(optimalIndex).getFCost()) {
						if (openList.get(i).getGCost() < openList.get(optimalIndex).getGCost()) {
							optimalIndex = i;
						}
					}
				}

				// Now we got the optimalIndex of the node with the least fCost or gCost
				// We set the currentNode to the node with optimalIndex
				currentNode = openList.get(optimalIndex);

				// Now we check if the currentNode has reached the goalNode
				if (currentNode.getCol() == goalNode.getCol() && currentNode.getRow() == goalNode.getRow()) {
					goalReached = true;

					// Now until we don't reach the startNode, we get each optimal node's parent and
					// set that path to true.
					while (currentNode != startNode && currentNode != null) {
						currentNode = currentNode.getParent();
						currentNode.setPath(true);
					}
				}

				// Once we have found the optimal path neighbour of the currentNode, we set it
				// to visited. The exception is startNode and goalNode, this is to ensure that
				// they are visible when the algorithm is getting visualised.
				if (currentNode.getObject() != antObject && currentNode.getObject() != foodObject) {
					currentNode.setVisited(true);
				}
				// Now we have used this currentNode and we no longer need in our openList.
				openList.remove(currentNode);
			}
		}
	}

	/**
	 * Update method keeps track of the game logic.
	 */
	public void update() {
		hud.update();
	}

	/**
	 * paintComponent(Graphics g): This method is called in game loop and helps
	 * render the character graphics.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		hud.render(g2d);
		map.render(g2d);
		g2d.dispose();
	}

}
