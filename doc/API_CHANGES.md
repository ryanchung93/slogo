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







Internal View: 
SubcomponentViewAPI
	public Parent getParent()

BackgroundOptionListener
	public void backgroundColorChange(int index)

TurtleImageOptionListener
	public void imageChange(int index)

PenOptionListener
	public void penColorChange(int index)

BackgroundColorDisplay
	public void addBackgroundOptionListener(BackgroundOptionListener listener)

TurtleImageOptionDisplay
	public void addTurtleImageListener(TurtleImageListener listener)

PenOptionDisplay
	public void addPenOptionListener(TurtleListener listener)

LanguageOptionDisplay
	public void addLanguageOptionListener(TurtleListener listener)

CanvasDisplay
	public void setBackgroundColor(Color c)

TextpromptDisplay
	public void runCommand(String s)




External View:

View
	public void start(Consumer<String> commandConsumer)
	public void display(SlogoException e)
	public void getUserdefinedCommandListener()

TurtleListener
	public void addTurtleStateListener(TurtleStateListener listener)
	public void penColorChange(int index)

LanguageListener
	public void languageChange(String s)
