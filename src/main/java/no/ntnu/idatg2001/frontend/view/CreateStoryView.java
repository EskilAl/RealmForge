package no.ntnu.idatg2001.frontend.view;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import no.ntnu.idatg2001.backend.SettingsModel;
import no.ntnu.idatg2001.backend.gameinformation.Story;
import no.ntnu.idatg2001.frontend.controller.Controller;
import no.ntnu.idatg2001.frontend.controller.CreateStoryController;

public class CreateStoryView extends BorderPane {

  private static final String CSS_FILE = "/CSS/NewStoryView.css";
  private ButtonBar buttonBar;
  private TableView<Story> storyTableView;
  private TableColumn<Story, String> columnStoryName;
  private TableColumn<Story, Integer> columnStoryPassageAmount;
  private TableColumn<Story, Integer> columnStoryLinkAmount;
  private CreateStoryController controller;
  private JFXButton storyNameButton;
  private JFXButton editStoryButton;
  private JFXButton loadButton;
  private JFXButton backButton;
  private final ResourceBundle resourceBundle;

  public CreateStoryView() {
    resourceBundle = ResourceBundle.getBundle("languages/createStoryView", SettingsModel.getInstance().getLocale());
    setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.8), null, null)));
    this.setController(controller);
    // Initialize the view
    getStylesheets().add(CSS_FILE);
    init();
  }

  private void init() {
    // Set up the Objects in the View
    createNewStoryNameButton();
    createEditStoryButton();
    createLoadStoryButton();
    createBackButton();
    createStoryTableView();

    buttonBar = new ButtonBar();
    buttonBar.setButtonMinWidth(10);
    buttonBar.getButtons().addAll(storyNameButton,editStoryButton,loadButton, backButton);
    HBox buttonHbox = new HBox(buttonBar);
    buttonHbox.setAlignment(Pos.CENTER);
    buttonHbox.setPadding(new Insets(10,0,0,0));


    // Set up the layout
    setPadding(new Insets(50,100,0,100));

    VBox centerBox = new VBox();

    centerBox.getChildren().addAll(storyTableView,buttonHbox);
    centerBox.setAlignment(Pos.CENTER);
    setCenter(centerBox);
  }

  private void createStoryTableView() {
    createStoryTableColumnName();
    createStoryTableColumnPassageAmount();
    createStoryTableColumnLinkAmount();
    storyTableView = new TableView<>();
    storyTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    storyTableView.getColumns().addAll(columnStoryName,columnStoryPassageAmount,columnStoryLinkAmount);

  }

  private void createStoryTableColumnName() {
    columnStoryName = new TableColumn<>(resourceBundle.getString("newStoryView.tableName"));
    columnStoryName.setPrefWidth(100);
  }

  private void createStoryTableColumnPassageAmount() {
    columnStoryPassageAmount = new TableColumn<>(resourceBundle.getString("newStoryView.tablePassageAmount"));
    columnStoryPassageAmount.setPrefWidth(100);
  }

  private void createStoryTableColumnLinkAmount() {
    columnStoryLinkAmount = new TableColumn<>(resourceBundle.getString("newStoryView.tableLinkAmount"));
    columnStoryLinkAmount.setPrefWidth(100);
  }

  private void createNewStoryNameButton() {
    storyNameButton = new JFXButton(resourceBundle.getString("newStoryView.newStoryButton"));
    storyNameButton.setOnAction(event -> {
      controller.onNewStory();
    });
  }

  private void createEditStoryButton() {
    editStoryButton = new JFXButton(resourceBundle.getString("newStoryView.editStoryButton"));
    editStoryButton.setOnAction(event -> {
      controller.getSelectedItemInTableView();
      //TODO this is where i want the user to select the story it wants to edit open new View where
      // user edits the current selected story.
    });
  }

  private void createLoadStoryButton() {
    loadButton = new JFXButton(resourceBundle.getString("newStoryView.loadStoryButton"));
    loadButton.setOnAction(event -> {
      //TODO want to add it sso the user can load a text file and then add it all to the DAO.
    });
  }

  private void createBackButton() {
    backButton = new JFXButton(resourceBundle.getString("newStoryView.backButton"));
    backButton.setOnAction(event -> {
      try {
        controller.onBackToMainMenuButtonPressed(event);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }

  public void setController(CreateStoryController controller) {
    this.controller = controller;
  // Add any additional methods, event handlers, or getters/setters as needed
  }

  public JFXButton getBackButton() {
    return backButton;
  }
  public JFXButton getLoadButton() {
    return loadButton;
  }
  public JFXButton getStoryNameButton() {
    return storyNameButton;
  }

  public TableView<Story> getStoryTableView() {
    return storyTableView;
  }

  public TableColumn<Story, String> getColumnStoryName() {
    return columnStoryName;
  }

  public TableColumn<Story, Integer> getColumnStoryPassageAmount() {
    return columnStoryPassageAmount;
  }

  public TableColumn<Story, Integer> getColumnStoryLinkAmount() {
    return columnStoryLinkAmount;
  }
}
