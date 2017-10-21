import controller.Driver;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.ImmutableTurtle;
import model.Model;
import model.Turtle;
import view.API.TurtleListener;

public class Main extends Application {

	public void start(Stage stage) {
		Driver driver = new Driver(stage);
		driver.run();
	}

	public static void main(String[] args) {
		// For testing
		//test();
		launch(args);
	}

	private static void test() {
		Model m = new Model();
		m.addTurtle(new Turtle(0,0,0),new TurtleListener() {

			ImmutableTurtle t;
			
			@Override
			public void setTurtle(ImmutableTurtle turtle) {
				t = turtle;
			}

			@Override
			public void locationChange(double newX, double newY) {
				System.out.println("(" + newX + ", " + newY + ") | " + "(" + t.getX() + ", " + t.getY() + ")");
				
			}

			@Override
			public void headingChange(double dtheta) {
				// TODO Auto-generated method stub

			}

			@Override
			public void penChange(boolean down) {
				// TODO Auto-generated method stub

			}

			@Override
			public void visibilityChange(boolean isVisible) {
				// TODO Auto-generated method stub

			}

			@Override
			public void penColorChange(Color color) {
				// TODO Auto-generated method stub

			}

			@Override
			public void clearScreen() {
				// TODO Auto-generated method stub

			}

		});

		m.execute("fd 50 fd 20 repeat 3 [ fd 50 ]");
	}
}
