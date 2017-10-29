package view.Toolbar;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import view.ErrorWindow;
import view.SubcomponentViewAPI;

/**
 * Class allowing users to view and use toolbar functions.
 * 
 * @author DavidTran
 *
 */
public class ToolbarView implements SubcomponentViewAPI {

	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");
	private static final double NODE_SPACING = 50;
	private HBox myToolbar;
	private Hyperlink myHelpLink;
	private BackgroundOptionView myBackgroundOptionView;
	private PenOptionView myPenOptionView;
	private PenSlider myPenSlider;
	private PenButtons myPenButtons;
	private TurtleImageOptionView myImageOptionView;
	private LanguageOptionView myLanguageOptionView;

	public ToolbarView(double width) {

		myToolbar = new HBox(NODE_SPACING);
		myToolbar.setAlignment(Pos.CENTER);
		myToolbar.setMinWidth(width);
		addBackgroundColorOption();
		addPenColorOption();
		addPenSlider();
		addPenButtons();
		addTurtleImageOption();
		addLanguageOption();
		addHelpLink();

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

	private void addHelpLink() {

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

		myToolbar.getChildren().add(myHelpLink);

	}

	private void addBackgroundColorOption() {
		myBackgroundOptionView = new BackgroundOptionView();
		myToolbar.getChildren().add(myBackgroundOptionView.getParent());
	}

	private void addPenColorOption() {
		myPenOptionView = new PenOptionView();
		myToolbar.getChildren().add(myPenOptionView.getParent());
	}

	private void addPenSlider() {
		myPenSlider = new PenSlider();
		myToolbar.getChildren().add(myPenSlider.getParent());
	}
	
	private void addPenButtons() {
		myPenButtons = new PenButtons();
		myToolbar.getChildren().add(myPenButtons.getParent());
	}

	private void addTurtleImageOption() {
		myImageOptionView = new TurtleImageOptionView();
		myToolbar.getChildren().add(myImageOptionView.getParent());
	}

	private void addLanguageOption() {
		myLanguageOptionView = new LanguageOptionView();
		myToolbar.getChildren().add(myLanguageOptionView.getParent());
	}

}
