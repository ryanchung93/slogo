package view.Toolbar;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import view.ErrorWindow;
import view.SubcomponentViewAPI;

/**
 * Class allowing users to view and use toolbar functions.
 * 
 * @author DavidTran, taekwhunchung
 *
 */

public class ToolbarView implements SubcomponentViewAPI {

	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");
	private static final double NODE_SPACING = 50;

	private HBox myToolbar;
	private WorkSpaceButtons myWorkSpaceButtons;
	private BackgroundOptionView myBackgroundOptionView;
	private PenOptionView myPenOptionView;
	private PenSlider myPenSlider;
	private PenButtons myPenButtons;
	private TurtleImageOptionView myImageOptionView;
	private LanguageOptionView myLanguageOptionView;
	private List<String> myImageNameList;
	private List<String> myColorList;
	private ViewSelector myViewSelector;
	private WindowObservable<String> myActiveView;
	private Hyperlink myHelpLink;

	public ToolbarView(double width, List<String> imgList, List<String> colorList, Runnable newWorkspace,
			Consumer<String> saveConsumer, Consumer<String> loadConsumer, WindowObservable<String> activeView) {

		myToolbar = new HBox(NODE_SPACING);
		myToolbar.setAlignment(Pos.CENTER);
		myToolbar.setMinWidth(width);

		myImageNameList = imgList;
		myColorList = colorList;
		myActiveView = activeView;

		myWorkSpaceButtons = new WorkSpaceButtons(newWorkspace, saveConsumer, loadConsumer);
		makeSubcomponents();
		makeHelpLink();

		myToolbar.getChildren().addAll(myWorkSpaceButtons.getParent(), myBackgroundOptionView.getParent(),
				myPenOptionView.getParent(), myPenSlider.getParent(), myPenButtons.getParent(),
				myImageOptionView.getParent(), myLanguageOptionView.getParent(), myHelpLink);

	}

	@Override
	public Parent getParent() {
		return myToolbar;
	}

	// Must add to API
	public BackgroundOptionView getBackgroundOptionView() {
		return myBackgroundOptionView;
	}

	public PenOptionView getPenOptionView() {
		return myPenOptionView;
	}

	public PenSlider getPenSlider() {
		return myPenSlider;
	}

	public PenButtons getPenButtons() {
		return myPenButtons;
	}

	public LanguageOptionView getLanguageOptionView() {
		return myLanguageOptionView;
	}

	// Must add to API
	public TurtleImageOptionView getImageOptionView() {
		return myImageOptionView;
	}

	private void makeHelpLink() {

		myHelpLink = new Hyperlink();
		myHelpLink.setText("Help");
		myHelpLink.setId("help-hyperlink");

		myHelpLink.setOnAction(e -> {
			if (Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop().browse(new URI(myResources.getString("HelpURL")));
				} catch (IOException e1) {
					e1.printStackTrace();
					new ErrorWindow(e1.getMessage());
				} catch (URISyntaxException e1) {
					new ErrorWindow(e1.getMessage());
				}
			}
		});

	}

	private void makeSubcomponents() {
		myBackgroundOptionView = new BackgroundOptionView(myColorList);
		myPenOptionView = new PenOptionView(myColorList);
		myPenSlider = new PenSlider();
		myPenButtons = new PenButtons();
		myImageOptionView = new TurtleImageOptionView(myImageNameList);
		myLanguageOptionView = new LanguageOptionView();
		addViewSelector(myActiveView);
	}

	private void addViewSelector(WindowObservable<String> activeView) {
		myViewSelector = new ViewSelector(activeView);
		Button openViewSelector = createButton(myResources.getString("ToolbarViewSelectorPrompt"));
		openViewSelector.setOnAction(e -> myViewSelector.run());
	}

	private Button createButton(String string) {
		Button button = new Button(string);
		myToolbar.getChildren().add(button);
		return button;

	}

}
