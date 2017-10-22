package view;

import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.SLogoException;
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
	private ResourceBundle myResource = ResourceBundle.getBundle("resources.view/view");

	private static final String TURTLE_IMAGE = "turtle.png";

	private Stage myStage;
	private Scene myScene;
	private Timeline myTimeline;

	private GridPane myGrid;
	private ScrollPane myLeftSP;
	private ScrollPane myRightSP;
	private VBox myLeftVBox;
	private VBox myRightVBox;
	private CanvasView myCanvas;
	private TextPromptView myTextPrompt;
	private VariableView myVarView;
	private ReferenceView myRefView;
	private HistoryView myHistoryView;
	private TurtleView myTurtleView;
	private ToolbarView myToolbarView;
	private BackgroundOptionView myBackgroundOptionView;

	/**
	 * Constructor for setting up animation.
	 * 
	 * @param stage
	 */
	public View(Stage stage, Consumer<String> commandConsumer) {
		myStage = stage;
		start(commandConsumer);
	}

	public void start(Consumer<String> commandConsumer) {
		myTimeline = setupTimeline();
		setupLayout();
		addCanvasView();
		addTurtle();
		addTextPrompt(commandConsumer);
		addVariableView();
		addReferenceView();
		addHistoryView();
		addToolbar();
		myTimeline.play();
	}

	/**************** PUBLIC METHODS *******************/

	@Override
	public TurtleListener getTurtleListener() {
		return myTurtleView;
	}

	@Override
	public VariableListener getVariableListener() {
		return myVarView;
	}

	@Override
	public StringListener getCommandListener() {
		return myRefView;
	}
	
	@Override 
	public void display(SLogoException e) {
		//TODO
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
	 * 
	 * @param elaspedTime
	 */
	private void step(double elaspedTime) {
		// Read command

		// Pass into execute
	}

	/**
	 * Sets up the general layout of the scene.
	 */
	private void setupLayout() {
		myGrid = new GridPane();
		myScene = new Scene(myGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
		ColumnConstraints col1 = new ColumnConstraints();
		ColumnConstraints col2 = new ColumnConstraints();
		ColumnConstraints col3 = new ColumnConstraints();
		RowConstraints row1 = new RowConstraints();
		RowConstraints row2 = new RowConstraints();
		RowConstraints row3 = new RowConstraints();
		col1.setPercentWidth(25);
		col2.setPercentWidth(50);
		col3.setPercentWidth(25);
		row1.setPercentHeight(10);
		row2.setPercentHeight(70);
		row3.setPercentHeight(20);
		myGrid.getColumnConstraints().addAll(col1, col2, col3);
		myGrid.getRowConstraints().addAll(row1, row2, row3);

		myLeftSP = createScrollPane();
		myLeftVBox = new VBox();
		myLeftSP.setContent(myLeftVBox);
		myGrid.add(myLeftSP, 0, 1, 1, 2);

		myRightSP = createScrollPane();
		myRightVBox = new VBox();
		myRightSP.setContent(myRightVBox);
		myGrid.add(myRightSP, 2, 1, 1, 2);

		myStage.setScene(myScene);
		myStage.show();

	}

	/**
	 * Add canvas where turtles will be placed.
	 */
	private void addCanvasView() {

		double[][] dims = getGridDimensions();
		myCanvas = new CanvasView(dims[0][1], dims[1][1]);

		myCanvas.setLayoutX(myCanvas.getMaxWidth() / 2);
		myCanvas.setLayoutY(myCanvas.getMaxHeight() / 2);

		myGrid.add(myCanvas, 1, 1);
		GridPane.setConstraints(myCanvas, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
	}

	/**
	 * Add text prompt where commands are entered and run.
	 */
	private void addTextPrompt(Consumer<String> commandConsumer) {
		double[][] dims = getGridDimensions();
		myTextPrompt = new TextPromptView(dims[0][1], dims[1][2], commandConsumer);
		myGrid.add(myTextPrompt, 1, 2, 2, 1);
	}

	/**
	 * for testing
	 */
	private void addTurtle() {
		Image image = new Image(getClass().getClassLoader().getResourceAsStream("resources/images/" + TURTLE_IMAGE));
		myTurtleView = new TurtleView(myCanvas, image);
		myCanvas.getChildren().add(myTurtleView.getImage());

	}

	/**
	 * Create major scrollpanes.
	 * 
	 * @return
	 */
	private ScrollPane createScrollPane() {
		ScrollPane sp = new ScrollPane();
		sp.setFitToWidth(true);
		sp.setPannable(true);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);

		return sp;
	}

	/**
	 * Create a view for supported commands.
	 */
	private void addReferenceView() {
		double[][] ret = getGridDimensions();
		myRefView = new ReferenceView(ret[0][0], myRightSP.getHeight() / 2);
		myRightVBox.getChildren().add(myRefView.getParent());
	}

	/**
	 * Create a view for current variables.
	 */
	private void addVariableView() {
		double[][] ret = getGridDimensions();
		myVarView = new VariableView(ret[0][0], myLeftSP.getHeight() / 2);
		myLeftVBox.getChildren().add(myVarView.getParent());
	}

	/**
	 * Create a view for command history.
	 */
	private void addHistoryView() {
		double[][] ret = getGridDimensions();
		myHistoryView = new HistoryView(ret[0][0], myRightSP.getHeight() / 2);
		myRightVBox.getChildren().add(myHistoryView.getParent());
	}
	
	/**
	 * Create a view for toolbar with subcomponents.
	 */
	private void addToolbar() {
		myToolbarView = new ToolbarView();
		myBackgroundOptionView = new BackgroundOptionView();
		myBackgroundOptionView.addBackgroundOptionListener(myCanvas);
		myToolbarView.addNode(myBackgroundOptionView.getParent());
		myGrid.add(myToolbarView.getParent(), 0, 0);
		
	}

	/**
	 * @return array containing dimensions of cells in grid
	 */
	private double[][] getGridDimensions() {
		double[][] ret = new double[4][4];
		try {
			java.lang.reflect.Method m = myGrid.getClass().getDeclaredMethod("getGrid");
			m.setAccessible(true);
			ret = (double[][]) m.invoke(myGrid);
//			for (int i = 0; i < ret.length; i++) {
//				for (int j = 0; j < ret[0].length; j++)
//					System.out.println(i + "," + j + " " + ret[i][j]);
//			}
//			System.out.println(ret);
		} catch (Exception e) {
			e.printStackTrace();
			showError(e.getMessage());
		}

		return ret;
	}

	/**
	 * Display error message
	 * 
	 * @param message
	 */
	private void showError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
