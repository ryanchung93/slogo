package view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.API.StringListener;
import view.API.TurtleListener;
import view.API.VariableListener;
import view.API.ViewAPI;

public class View implements ViewAPI {

	private static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private static final int SCREEN_WIDTH = 1000;
	private static final int SCREEN_HEIGHT = 700;

	private static final String BALL_IMAGE = "ball.gif";

	private Stage myStage;
	private Scene myScene;
	private Timeline myTimeline;
	private GridPane myRoot;
	private CanvasView myCanvas;

	/**
	 * Constructor for setting up animation.
	 * 
	 * @param stage
	 */
	public View(Stage stage) {
		myStage = stage;
		start();
	}

	public void start() {
		myTimeline = setupTimeline();
		setupLayout();
		addCanvasView();
		addTurtle();
		myTimeline.play();
		
	}

	/**************** PUBLIC METHODS *******************/

	@Override
	public TurtleListener getTurtleListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VariableListener getVariableListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringListener getCommandListener() {
		// TODO Auto-generated method stub
		return null;
	}

	/*************** PRIVATE METHODS *******************/

	/**
	 * Sets up Timeline for animations.
	 */
	private Timeline setupTimeline() {
		Timeline tl = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		tl.setCycleCount(Animation.INDEFINITE);
		tl.getKeyFrames().add(frame);
		return tl;
	}

	/**
	 * Steps to update interface.
	 * @param elaspedTime
	 */
	private void step(double elaspedTime) {
		// do stuff
	}

	/**
	 * Sets up the general layout of the scene.
	 */
	private void setupLayout() {
		myRoot = new GridPane();
		myScene = new Scene(myRoot, SCREEN_WIDTH, SCREEN_HEIGHT);
		ColumnConstraints col1 = new ColumnConstraints();
		ColumnConstraints col2 = new ColumnConstraints();
		ColumnConstraints col3 = new ColumnConstraints();
		RowConstraints row1 = new RowConstraints();
		RowConstraints row2 = new RowConstraints();
		col1.setPercentWidth(25);
		col2.setPercentWidth(50);
		col3.setPercentWidth(25);
		row1.setPercentHeight(10);
		row2.setPercentHeight(90);
		myRoot.getColumnConstraints().addAll(col1, col2, col3);
		myRoot.getRowConstraints().addAll(row1, row2);

		ScrollPane leftSP = new ScrollPane();
		leftSP.setFitToWidth(true);
		leftSP.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		leftSP.setHbarPolicy(ScrollBarPolicy.NEVER);
		myRoot.add(leftSP, 0, 1, 1, 1);
		
		ScrollPane rightSP = new ScrollPane();
		rightSP.setFitToWidth(true);
		rightSP.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		rightSP.setHbarPolicy(ScrollBarPolicy.NEVER);
		myRoot.add(rightSP, 2, 1, 1, 1);
		
		myStage.setScene(myScene);
		myStage.show();

	}

	/**
	 * Add canvas where turtles will be placed.
	 */
	private void addCanvasView() {

		double[][] ret = new double[0][0];

		try {
			java.lang.reflect.Method m = myRoot.getClass().getDeclaredMethod("getGrid");
			m.setAccessible(true);
			ret = (double[][]) m.invoke(myRoot);
			
			myCanvas = new CanvasView(ret[0][1],ret[1][1]);
			myRoot.add(myCanvas, 1, 1);
			myCanvas.setLayoutX(myCanvas.getMaxWidth() / 2);
			myCanvas.setLayoutY(myCanvas.getMaxHeight() / 2);

			GridPane.setConstraints(myCanvas, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
			
		} catch (Exception e) {
			e.printStackTrace();
			showError(e.getMessage());
		}
	}
	
	/**
	 * for testing
	 */
	private void addTurtle() {
		Image image = new Image(getClass().getClassLoader().getResourceAsStream("resources/images/" + BALL_IMAGE));
		TurtleView tv = new TurtleView(myCanvas, image);
		myCanvas.getChildren().add(tv.getImage());
		tv.locationChange(0, 100);
		tv.locationChange(100,100);
		tv.locationChange(100,0);
		tv.locationChange(0,0);

	}
	
	/**
	 * Display error message
	 * @param message
	 */
	private void showError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
