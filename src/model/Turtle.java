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
	public int getShapeIndex();
	public double forward(Command par, CommandManager commands, VariableManager variables);
	public double left(Command input, CommandManager commands, VariableManager variables);
	public double setHeading(Command input, CommandManager commands, VariableManager variables);
	public double setXY(Command xCor, Command yCor, CommandManager commands, VariableManager variables);
	public double setTowards(Command xCor, Command yCor, CommandManager commands, VariableManager variables);
	public void setPenDown(boolean b);
	public void setPenColor(int index);
	public void setPenSize(double pixels);
	public void setBackgroundColor(int index);
	public void setVisible(boolean b);
	public void setShapeIndex(int index);
	public double clearScreen();
	public int getNumTurtles();
	public void setPalette(int indexVal, int rVal, int gVal, int bVal);
}
