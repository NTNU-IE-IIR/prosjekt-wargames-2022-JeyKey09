# Models
This is where most of the backend code is. It consist of multiple objects that talks with each other to create a Battle, the object that is required for the application to "run".
At the highest layer lies the Army and Battle classes.
## Map
This is where the map functionality lies, like the:
- BattleMap
- Tokens
- Terrain
- Tile

This was introduced in Assignment part 3 as the Terrain task. This later was expanded to let the use of tokens, tiles and BattleMap to create a 2D space for the battle to happen between 2 armies

## Units
The game will have several units that will be stored here.
The units will all adapt from the Unit class that is the baseline for every unit.

The following units is implemented:
-   Cavalry
-   Infantry
-   Ranged
-   Commander
