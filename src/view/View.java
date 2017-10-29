package view;

import java.util.function.Consumer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.SLogoException;
import view.Animation.CanvasView;
import view.Animation.TextPromptView;
import view.Animation.TurtleListener;
import view.Animation.TurtleViewManager;
import view.Toolbar.LanguageListener;
import view.Toolbar.ToolbarView;
import view.Windows.HistoryView;
import view.Windows.ReferenceView;
import view.Windows.ScrollPaneView;
import view.Windows.StringListener;
import view.Windows.TurtleStateView;
import view.Windows.UserDefinedCommandView;
import view.Windows.VariableListener;
import view.Windows.VariableView;

/**
 * Class that displays the GUI and SLogo animations.
 * 
 * @author DavidTran
 *
 */
public class View implements ViewAPI {

	private static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private static final int SCREEN_WIDTH = 1000;
	private static final int SCREEN_HEIGHT = 700;
	private static final String STYLESHEET = "/resources/view/view.css";
	private static final String DEFAULT_TURTLE_IMAGE = "Turtle_up.png";

	private Stage myStage;
	private Scene myScene;
	private Timeline myTimeline;

	private GridPane myGrid;
	private ScrollPane myLeftSP;
	private ScrollPane myRightSP;
	private VBox myLeftVBox;
	private VBox myRightVBox;

	private CanvasView myCanvas;
	private TurtleViewManager myTurtleViewManager;
	private TextPromptView myTextPrompt;
	private UserDefinedCommandView myUDCView;
	private VariableView myVarView;
	private ReferenceView myRefView;
	private HistoryView myHistoryView;
	private ToolbarView myToolbarView;
	private TurtleStateView myTurtleStateView;
	private LanguageListener myLanguageListener;
	
	private Consumer<String> myCommandConsumer;

	/**
	 * Constructor for setting up animation.
	 * 
	 * @param stage
	 */
	public View(Stage stage, LanguageListener langListener, Consumer<String> commandConsumer, Runnable reset) {
		myStage = stage;
		myLanguageListener = langListener;
		myStage.setTitle("SLogo Interpreter");
		start(commandConsumer);
	}

	/**************** PUBLIC METHODS *******************/

	@Override
	public void start(Consumer<String> commandConsumer) {
		myTimeline = setupTimeline();
		myCommandConsumer = commandConsumer;
		setupLayout();
		addScrollPaneComponents();
		addAnimationComponents();
		addTextPrompt(commandConsumer, s -> myHistoryView.updateHistory(s));
		addToolbar();
		myTimeline.play();
	}

	@Override
	public TurtleListener getNewTurtleListener() {
		return myTurtleViewManager.getNewListener();
	}

	@Override
	public TurtleListener getStateViewListener() {
		return myTurtleStateView;
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
	public StringListener getUserDefinedCommandListener() {
		return myUDCView;
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
	 */
	private void step(double elaspedTime) {}

	/**
	 * Sets up the general layout of the scene.
	 */
	private void setupLayout() {
		myGrid = new GridPane();

		myScene = new Scene(myGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
		myScene.getStylesheets().add(STYLESHEET);
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));

		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(25);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(50);
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setPercentWidth(25);
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(10);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(70);
		RowConstraints row3 = new RowConstraints();
		row3.setPercentHeight(20);

		myGrid.getColumnConstraints().addAll(col1, col2, col3);
		myGrid.getRowConstraints().addAll(row1, row2, row3);

		myStage.setScene(myScene);
		myStage.show();

	}

	private void handleKeyInput(KeyCode code) {
		myTextPrompt.handleInput(code);
	}

	/**
	 * Add canvas and turtle components for viewing animations.
	 */
	private void addAnimationComponents() {
		double[][] dims = getGridDimensions();
		myCanvas = new CanvasView(dims[0][1], dims[1][1]);

		myGrid.add(myCanvas, 1, 1);
		GridPane.setConstraints(myCanvas, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);

		Image image = new Image(getClass().getClassLoader().getResourceAsStream("resources/images/" + DEFAULT_TURTLE_IMAGE));
		myTurtleViewManager = new TurtleViewManager(myCanvas, image);
	}

	/**
	 * Add text prompt where commands are entered and run.
	 */
	private void addTextPrompt(Consumer<String> commandConsumer, Consumer<String> historyConsumer) {
		double[][] dims = getGridDimensions();
		myTextPrompt = new TextPromptView(dims[0][1], dims[1][2], commandConsumer, historyConsumer);
		myGrid.add(myTextPrompt, 1, 2, 2, 1);
	}

	/**
	 * Add subcomponents of major scroll panes.
	 */
	private void addScrollPaneComponents() {
		double dims[][] = getGridDimensions();

		myLeftSP = (ScrollPane) new ScrollPaneView().getParent();
		myLeftVBox = new VBox();
		myLeftSP.setContent(myLeftVBox);
		myGrid.add(myLeftSP, 0, 1, 1, 2);

		myRightSP = (ScrollPane) new ScrollPaneView().getParent();
		myRightVBox = new VBox();
		myRightSP.setContent(myRightVBox);
		myGrid.add(myRightSP, 2, 1, 1, 2);

		myUDCView = new UserDefinedCommandView((dims[1][1] + dims[1][2]) / 2);
		myVarView = new VariableView((dims[1][1] + dims[1][2]) / 2);
		myTurtleStateView = new TurtleStateView((dims[1][1] + dims[1][2]) / 2);
		myRefView = new ReferenceView((dims[1][1] + dims[1][2]) / 2);
		myHistoryView = new HistoryView((dims[1][1] + dims[1][2]) / 2, myCommandConsumer);
		
		myLeftVBox.getChildren().addAll(myTurtleStateView.getParent(), myUDCView.getParent(), myVarView.getParent());
		myRightVBox.getChildren().addAll(myRefView.getParent(), myHistoryView.getParent());
	}

	/**
	 * Add toolbar and its subcomponents.
	 */
	private void addToolbar() {
		myToolbarView = new ToolbarView(SCREEN_WIDTH);
		// set a listener for background, pen, image, language changes.
		myToolbarView.getBackgroundOptionView().addTextPrompt(myTextPrompt);
		myToolbarView.getPenOptionView().addTextPrompt(myTextPrompt);
		myToolbarView.getPenSlider().addTextPrompt(myTextPrompt);
		myToolbarView.getPenButtons().addTextPrompt(myTextPrompt);
		myToolbarView.getImageOptionView().addTextPrompt(myTextPrompt);
		myToolbarView.getLanguageOptionView().addLanguageOptionListener(myLanguageListener);
		myToolbarView.getLanguageOptionView().addLanguageOptionListener(myTextPrompt);
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
		} catch (Exception e) {
			e.printStackTrace();
			new ErrorWindow(e.getMessage());
		}
		return ret;
	}

}
