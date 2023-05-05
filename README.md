# A* Pathfinding Visualizer

## Description of the logic and the design of the game
The A* Pathfinding game finds the shortest path between startNode and goalNode by using Chebyshev Distance formula (as it gives the best 
results).

When the game begins, it allows the user to place the startNode, goalNode, and different types of terrains including obstacle(which is water, I took into consideration that ant won't be able to cross the water as it will get watched away).

When the user presses down the Enter key, the game would start searching for the optimal path. The game will allow the user to visually see the path search evolution (keep pressing the enter key). Once we reach the goalNode, the optimal path is shown.

Design of the game is as follows:
- To select different terrains and cells, the user would need to press the number key once and then that terrain would be selected. The 
keys that need to be pressed for each terrain have been indicated in the white box next to the grid.
- Once a specific terrain is selected, the user would then need to place those terrains on the grid by using mouse and pressing the left 
mouse button on those respective grid cells to place those terrains.
- Different terrains can be overridden by placing an different terrain over them.
- If the startNode and goalNode have already been placed and if the user wants to change their position, then they would have place some 
different terrain over the current startNode or goalNode. And then press the respective button for startNode or goalNode and place those 
nodes in a different position. 
- If either one of the startNode or goalNode haven't been placed or if both haven't been placed then the game would not start searching. 
In order to search the optimal path, both startNode and goalNode need to be present.
- The orange node indicates the visited nodes and cyan node indicates the optimal path node. 
- A string has been placed over the cells to indicate the type of node/terrain. This can especially be useful when path has been 
visualised as the terrains would get overrided with the visited node or path node.
- StartNode and goalNode don't have a string over them as they can be recognized by their color. Red for startNode and green for 
goalNode. 
- If either of the startNode or goalNode have been surrounded by walls then the game would search and stop searching when the path cannot 
be found. The nodes visited would be in orange but their wouldn't be any optimal path blue node as startNode or goalNode is out of reach. 
- Edges cases like startNode or goalNode hasn't been placed would result in the game doing nothing. Similarly, as mentioned above if 
startNode or goalNode have been surrounded by obstacles then the game is likely to search for the optimal path but because the node is 
out of reach. It is likely to do nothing once all the nodes have been visited.
- Game can be exited by clicking the red cross on the top right. And there is no functionality added to restart the game.
- If a terrain with lower cost is placed then the game is likely to only visit that terrain first as that cost has been taken into 
consideration when calculating gCost, hCost and fCost. And due to it being an A* algorithm, it is likely to search in the direction of 
the goalNode.
- I have set the obstacle terrain (water in this case) to a high cost and also I have set its boolean obstacle attribute as true. This 
results in the game to never visit that node.


## Description of code/program structure
- `main folder`
    - `Main.java`: This is the main class of the game and it sets the JFrame components and runs the game on 
    a thread.
    - `Game.java`: This class consists of the main game logic. 
        - It creates the threads and game loop for proper execution of the game.
        - There are several different methods which give us the row and column of the tile/terrain based on mouse position. And help us manage the node array, the node array is one of the most important components of the game. It keeps track of each and every terrain and cell or whether that node has been visited or has been set path. 
        - We are also implementing the main A* algorithm here.
        - This class also helps keep track of any terrain or cell placement by mouse. For example, we have logic implemented to ensure that the user does not place multiple start nodes or goal nodes.
    - `KeyInput.java`: This class keeps track of the keyboard input. Based on the key pressed, a particular terrain or cell is set to true. 
- `render folder`
    - `Hud.java`: This class sets a particular terrain when a specific keyboard key is pressed. And, this renders the graphical components on the game screen except for the board.
    - `Map.java`: This class graphically renders the board based on the node array in `Game.java`.
- `images folder`: This folder contains various images of the terrain and cells.
- `gameObjects folder`
    - `Node.java`: This class is especially for the Nodes. We have various attributes for each node and we can access them easily using the getters and setters in this class. We also perform calculations regarding fCost, gCost and hCost for each node for our A* algorithm.
    - `NodeType.java`: This is a enum which has NodeTypes of various types like Ant, food, grassland, swampland, obstacle, etc.
    - `NodeObject.java`: This class helps in expanding the object attribute of each Node. Each Node belongs to a specific object, for example: grassland. If the object is grassland, then we have more attributes related to it. And this class expands that a bit more by providing cost and image of that object. 

## Instruction on how to compile/run your game
I have provided all the files in this repo.  I successfully ran my code in Powershell.
If you are running this in WSL then you need Windows X server configured.
- Clone this repository.
- Navigate to the folder `Astar-Pathfinding-Visualizer`.
- To compile the project in java, run command: `javac .\main\Main.java`
- To execute the project in java, run command: `java main.Main`
- To run .jar file, run command: `java -jar .\AStarPathfinding.jar`. Or this .jar file could be run by simply double clicking on it. 
- In case the jar file returns an error (like java.lang.UnsupportedClassVersionError), you can create a new jar file with this command:
    ```
    jar -cfvm AStarPathfinding.jar .\Manifest.txt .\main\*.class .\render\*.class .\images\cells\*.png .\images\terrain\*.png .\gameObjects\*.class
    ```


## Bugs or problems in the game
I personally haven't found any bugs so far. I have done my best to consider every edge case and every situation.
