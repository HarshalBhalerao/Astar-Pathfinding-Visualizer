package gameObjects;

/**
 * Node class
 * 
 * @author Harshal
 *
 */
public class Node {

	// Node variables
	// For node row and col
	private int row;
	private int col;

	// Node's parent
	private Node parent;

	// A* algorithm costs
	private int fcost; // Final cost
	private int gCost; // Cost from StartNode to CurrentNode
	private int hCost; // Cost from CurrentNode to GoalNode

	private NodeObject nodeObject; // Get the ObjectType of the node
	private boolean start; // For checking if the node is a start node
	private boolean goal; // For checking if the node is goal node
	private boolean obstacle; // For checking if the node is obstacle
	private boolean open; // For checking if the node is in the openList
	private boolean visited; // For checking if the node has been visited
	private boolean path; // For setting the path

	/**
	 * Node Constructor
	 * 
	 * @param row
	 * @param col
	 * @param nodeObject
	 */
	public Node(int row, int col, NodeObject nodeObject) {
		this.row = row;
		this.col = col;
		this.nodeObject = nodeObject;
	}

	// Getters
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public NodeObject getObject() {
		return nodeObject;
	}

	public Node getParent() {
		return parent;
	}

	public boolean getStart() {
		return start;
	}

	public boolean getGoal() {
		return goal;
	}

	public boolean getObstacle() {
		return obstacle;
	}

	public boolean getOpen() {
		return open;
	}

	public boolean getVisited() {
		return visited;
	}

	public boolean getPath() {
		return path;
	}

	public int getFCost() {
		return fcost;
	}

	public int getGCost() {
		return gCost;
	}

	public int getHCost() {
		return hCost;
	}

	// Setters
	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public void setObject(NodeObject nodeObject) {
		if (start == false && goal == false) {
			this.nodeObject = nodeObject;
		}
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public void setGoal(boolean goal) {
		this.goal = goal;
	}

	public void setObstacle(boolean obstacle) {
		this.obstacle = obstacle;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public void setPath(boolean path) {
		this.path = path;
	}

	public void setFCost(int fCost) {
		this.fcost = fCost;
	}

	public void setGCost(int gCost) {
		this.gCost = gCost;
	}

	/**
	 * calculateGCost method calculates the distance between the startNode and the
	 * currentNode
	 * 
	 * @param node
	 * @param startNode
	 * @return int
	 */
	public static int calculateGCost(Node node, Node startNode) {
		return Math.max(Math.abs(startNode.col - node.col), Math.abs(startNode.row - node.row));
	}

	/**
	 * calculateHCost method calculates the distance between the currentNode and the
	 * goalNode
	 * 
	 * @param node
	 * @param goalNode
	 * @return int
	 */
	public static int calculateHCost(Node node, Node goalNode) {
		return Math.max(Math.abs(goalNode.col - node.col), Math.abs(goalNode.row - node.row));
	}

	/**
	 * calculateFCost method calculates the finalCost by adding the sum of gCost and
	 * hCost
	 * 
	 * @param gCost
	 * @param hCost
	 * @return int
	 */
	public static int calculateFCost(int gCost, int hCost) {
		return gCost + hCost;
	}

	/**
	 * calculateCost method calculates the final cost by adding gCost and hCost. We
	 * also calculate the distance between two nodes by multiplying it with the cost
	 * of the terrain.
	 * 
	 * @param node
	 * @param startNode
	 * @param goalNode
	 */
	public static void calculateCost(Node node, Node startNode, Node goalNode) {
		int gCost = Node.calculateGCost(node, startNode) * node.getObject().getTerrainCost();
		int hCost = Node.calculateHCost(node, goalNode) * node.getObject().getTerrainCost();
		int fCost = Node.calculateFCost(gCost, hCost);
		node.setGCost(gCost);
		node.setFCost(fCost);
	}
}
