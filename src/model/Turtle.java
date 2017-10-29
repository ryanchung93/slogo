package model;

public interface Turtle {

	public int getID();
	public double getX();
	public double getY();
	public double getHeading();
	public boolean getPenDown();
	public boolean isVisible();
	public int getPenColorIndex();
	public double getPenSize();
	public double forward(Command par, CommandManager commands, VariableManager variables);
	public double left(Command input, CommandManager commands, VariableManager variables);
	public double setHeading(Command input, CommandManager commands, VariableManager variables);
	public double setXY(Command xCor, Command yCor, CommandManager commands, VariableManager variables);
	public double setTowards(Command xCor, Command yCor, CommandManager commands, VariableManager variables);
	public void setPenDown(boolean b);
	public void setVisible(boolean b);
	public void setPenSize(double pixels);
	public double clearScreen();
	public int getNumTurtles();
}
