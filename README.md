# Project Rapport, WarGame:

## Introduction:
Trough the semester of Spring 2022 was the student within IDATx2001 tasked with creating a War Game application as part of their evaluation of the subject.   

The War Game was given specification trough out the semester with PDF Files given with obligatory tasks. Every assignment can be found within the git repository within the "docs\Assignment" folder.
## Specification:
The features this application support is:
- Loading files:
- Editing armies
- Running a battle between armies
- Giving a animation when the battle is running
- Tables that updates as the armies losses or gets soldiers
## Design:
This project has used a maven architecture with javaFX as a application framework. The GUI is designed with a main menu as the central component: ![](docs%5CApplication%20pictures%5CMain%20menu.png) 
That contains the ability to call to other windows within the top menu to other feature like: 
- Editing armies:  
![](docs%5CApplication%20pictures%5CEdit%20Armies.png)
- Loading armies from files:  
![](docs%5CApplication%20pictures%5CLoad%20Army.png)

This is to make it simpler to add other features that may require another design then what the main window allows. It is also to ensure that the main window does not get manipulated.

As for the backend, class diagram looks like this:

## Implementation:
I have split the code in mostly two parts, Front/back-end. This makes it so I can easily copy the backend code and build it a new application with another framework. 
### Front-End
The front-end is a combination of java, css, png and map txt files. The application loads up FXML files and where it is necessary creates and places objects. These are objects that may require to be specified like the columns in a TableView or like a TilePane.  

On the java side it mostly consist of multiple Controller classes and the package fxmodels. While the controllers is directly used for FXML documents, consist the package fxmodels of objects that I felt required to be either setup in some kind of way or made the code less readable within the controllers.

The resources for this part of the project consist of some styling trough css, pictures for the different troops and txt files that consist of characters for a map.

### Back-End
The back-end is pure java code. It consist of multiple packages. 
- Factories: Consist just of the UnitFactory specified trough the assignment part 3.
- Models: Consist of mostly all back end objects split into multiple categories. Within the first layer lies the battle and army classes. 
    - Maps: This consist of what a map is created by: BattleMap, Terrain, Tokens and Tile. 
    - Units: The different units that the game was required trough assignment part 1.

## Process:
Trough the semester I have actively worked with this project when other assignment or subjects had a priority.

While the period
## Reflection:
Trough this period I have had many ideas on how to implement or add more functionality into the project. While this has worked in my favor to work with the project, it has also reflected negative towards me with creating documentation and tests. It has also made that some of my work seems "rushed" in the hope of it being finished. This can be mostly been seen in the Map package where I did not have a clear idea how I would want object to interact with each other.  

While I have made many choices trough the project, many of them has been created trough instinct or because I was lacking a functionality I wanted. Much of my code or the model of package has been refactored and been trough a couple iteration before it became this final product. This is again a consequence of me not planning fully out what or how I want to do a certain feature. 

When working with this project I would often find myself pushing code to master because I found a fix or I had missed something when I last pushed. Big refactoring or implementing of new features would mostly be created as a branch to ensure that I had a working code before deadlines. 
## Conclusion:
