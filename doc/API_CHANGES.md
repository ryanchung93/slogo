Command
public double execute(Turtle t, Collection commands, Collection variables) → 
public double execute(Turtle t, CommandManager commands, VariableManager variables)
Now passes in CommandManager and VariableManager, which hold categorized Collections (by default vs. user defined Commands) and listeners, rather than just passing in Collections.

CommandDef
	public Command build(TokenDispenser dispenser)
CommandDef creates a new Command object using the parameters acquired from dispenser. This interface was added so that CommandManager could hold a list of generic CommandDef objects that, when their build methods are called, build the appropriate Command object.

CommandManager
public void setLanguage(String language) 
	public void clear() 
	public CommandDef get(String s)
	public void addListener(StringListener commandListener) 
	public void put(String name, CommandDef definition) 
	public boolean checkIfBuiltIn(String name)
A more abstract representation of the list of Commands. Allows loading known commands in a given language and adding new ones while notifying the listeners. Allows regex expressions in known commands.

VariableManager
public VariableManager() 
	public double getValue(String s) 
	public void setValue(String s, double val) 
	public void setLocalValue(String s, double val) 
	public void setGlobalValue(String s, double val) 
	public void enterLocalScope() 
	public void leaveLocalScope() 
	public void addListener(VariableListener listener) 
	public void notifyListeners()
A more abstract representation of the list of Variables. Hides the implementation of local scopes and notifies listeners.

Parser implements TokenDispenser
public boolean hasNextCommand()
public Command getNextCommand()
Added Parser (initially a private class of Model) to convert a string to a series of commands. Most of its methods are in TokenDispenser.

TokenDispenser
public String getNextToken()
	public String getNextVariable()
	public String peek()
	public Command getNextCommand()
	public List<Command> getNextCommandList()
	public List<String> getNextTokenList()
	public List<String> getNextVariableList()
Passed into CommandDefs to be used in building Commands. Typically a representation of Parser, but hiding some commands. 

SLogoException
  	public SLogoException(String key, Object... objects) 
For use by the back-end when parsing SLogo code. Reads all error messages from a properties file. 

Turtle
	public int getID()
For multiple turtles. 

Model
	public void setLanguage(String language)
So that the Driver can modify the language based on View. 

ImmutableTurtle
	public int getPenColorIndex()
Changed to index -- colors are stored by view. 

**Internal View:**

SubcomponentViewAPI
	
	public Parent getParent()

* Allows View.java to obtain the node represented by each view component class.

BackgroundOptionListener
	
	public void backgroundColorChange(int index)

* Enables communication between background color choice box and canvas class.

TurtleImageOptionListener
	
	public void imageChange(int index)

* Enables communication between turtle image choice box and TurtleImageListener class.

PenOptionListener
	
	public void penColorChange(int index)

* Enables communication between turtle image choice box and TurtleListener.

BackgroundColorDisplay
	
	public void addBackgroundOptionListener(BackgroundOptionListener listener)

* Passes the instance of BackgroundOptionListener in order to call public methods on it.

TurtleImageOptionDisplay
	
	public void addTurtleImageListener(TurtleImageListener listener)

* Passes the instance of TurtleImageListener in order to call public methods on it.

PenOptionDisplay
	
	public void addPenOptionListener(TurtleListener listener)

* Passes the instance of TurtleListener in order to call public methods on it.

LanguageOptionDisplay
	
	public void addLanguageOptionListener(LanguageListener listener)

* Passes the LanguageListener in order to call public methods on it.

TextPromptDisplay
	
	public void runCommand(String s)

* Allows View.java to send a command to TextPrompt when user inputs key commands.


**External View:**

View
	
	public void start(Consumer<String> commandConsumer)
	public void display(SlogoException e)
	public void getUserdefinedCommandListener()

* Allows errors messages to be send from model to View, Controller to start stage setup in View, and to pass a listener from View to Model to update user defined commands displayed.

TurtleListener
	
	public void addTurtleStateListener(TurtleStateListener listener)
	public void penColorChange(int index)

* Allows hanged parameter from color to index.

LanguageListener
	
	public void languageChange(String s)

* Allows communication to change language between language option choice-box (in View) and model.
