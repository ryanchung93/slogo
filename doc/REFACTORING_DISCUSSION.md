# Refactoring into TurtleManager

### Why?

The Model currently holds a list of Turtles and of TurtleListeners; however, this is a fairly low level implementation detail that the model should not handle. By refactoring these data structures into a new class, we can encapsulate how that data is processed and make modifying that behavior easier. This makes model more readable, more closed, and embraces the concept of substitution. 

### Making the Change

Currently the model passes the turtles to the commands one at a time by iterating over the TurtleManager. This change makes it easier to later modeify our code further, and pass the entire TurtleManager into commands. 


# Refactoring TurtleListeners

### Why?

The TurtleView was implementing ImmutableTurtle, which didn't make much sense. One part of the view, the TurtleStateView, was listening to another part of the view, when logically it should listen directly to the Turtle. 

### Making the Change

We made Turtles accept multiple listeners -- later we will have to use this functionality as turtles are constructed, but for now it is used by driver. We added a method to get a second listener from the view, and modified model to take any number of listeners when adding a turtle. 
