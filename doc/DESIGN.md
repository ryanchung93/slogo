
## Design Overview

The Model can be accessed through the Model class, and the data inside is primarily accessed via listeners -- TurtleListeners, which can monitor changes to the Turtles' states; CommandListeners, which receive Strings representing new commands being added to the program; and the VariableListener, which receives an updated map of variable names to values when there is a change to any variable. Within the model, Commands are used to represent individual, executable lines of SLogo code. Subclasses of Command describe specific commands (fd, rt, etc). A SingularTurtle object represents the state of a turtle, and notifies its listeners when changes are made to its state. The TurtleManager handles all of the turtles. CommandBuilders are factories for Commands, and are used by Parser to build instances of commands. 

The View can be accessed through the View class, which essentially organizes the Panes in the window. The TextPrompt contains a text field for users to enter code. The TurtleView shows the turtle with any lines it has drawn, and is a TurtleListener so it can get updates directly from the Turtle. The ReferenceDisplay contains built-in SLogo commands. HistoryDisplay contains all previously run code. VariableDisplay shows the variables currently available to the user and their values, and is updated by the Model as a VariableListener. The UserDefinedCommandDisplay shows the currently available user-defined commands, and is updated by Model as a StringListener.

The Driver connects the two halves of the program. When run, it starts the program. It distributes the listeners from View to Model, and gives the View access to Model’s execute method via a lambda expression.

## Adding new Features

To add a command:
* Create a class implementing Command
* Create a class implementing CommandBuilder
* Add the Command to the language files
* Add the CommandBuilder to the properties file of all CommandBuilder classes

To add a window:
* Create a class implementing SubcomponentViewAPI
* Modify View to add the parent of the new class instance when/where approprate

To add new Parsing behavior (ie grouping)
* Modify the Parser's getCommand method to handle the new syntax

## Design Considerations 

A design decision we discussed was how to pass the backend turtle’s information to the view. We debated whether we should declare a Turtle in view that matches the backend turtle, or rather pass the data individually to view. We decided to use a TurtleListener interface that is implemented by objects in view and detects changes in the backend Turtle without passing the Turtle itself to the view. This allows the program to only extract the most important data from the backend when displaying the turtle: the statuses that changed on a given code execution.

The use of a Controller was briefly debated. We used one, and it makes the separation between Model and View far more explicit, but the View needs access to the Model, so the number of lambda expressions passed into View is awkward. We valued the clear division between the two parts and the added flexibility of having a Controller above the simplicity of interaction that we would have had by making View the Controller. 

## Assumptions

* Tell, Ask, AskWith cannot act on SingularTurtles, so you cannot nest them within turtle-related commands. 
* When you change a user-defined command, it changes instances of it within other user-defined commands, which is strange if you aren't expecting it, but quite reasonable if you are. This simplifies recursion. 
* Users won't disrupt the data used by the program. EG, after saving a file, the user won't go into the resources and delete it, since trying to load it from the program would then throw an uncaught exception. Similarly, they won't modify and misformat resource files. 