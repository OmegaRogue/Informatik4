package omegaRogue.jfxGUI;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

public class Einstieg extends Application {

    public static void main(String[] args) {
        String cwd = System.getProperty("user.dir");
        System.out.println(cwd);
        launch(args);

    }

    ObservableList<Node> obj;
    Random rand = new Random();
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();



        Dialog message = new Dialog();

        BorderPane ui = new BorderPane();
        FlowPane pain = new FlowPane();


        //obj = pain.getChildren();
        //obj = ui.getChildren();
        //Label lbButton1 = new Label("Mein erster Knopf!");
        //obj.add(lbButton1);

        //Button button1 = new Button("Bitte drücken!");
        //obj.add(button1);

//        button1.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                Label l = new Label("Du hast den Knopf gedrückt");
//                obj.add(l);
//                resize(primaryStage);
//
//
//
//            }
//        });

        //obj.add(pain);

        ui.setTop(createToolbarPane());
        ui.setCenter(createInputPane());
        Scene scene = new Scene(ui,800,600);
        scene.getStylesheets().add(String.valueOf(Einstieg.class.getResource("Styles.css")));


        primaryStage.setScene(scene);
        primaryStage.setTitle("Erste jfxGUI Gehversuche");
        primaryStage.show();


    }
    public void resize(Stage stage) {
        stage.setWidth(rand.nextInt(1000));
        stage.setHeight(rand.nextInt(1000));
    }

    private Pane createToolbarPane() {

        Menu menu1 = new Menu("Bearbeiten");

        MenuItem menuItem1 = new Menu("Test");
        menu1.getItems().add(menuItem1);


        final MenuBar mbar = new MenuBar();
        mbar.getMenus().add(menu1);
        final HBox hbox = new HBox(5,mbar);

        hbox.getStyleClass().add("topBox");
        //hbox.getChildren().addAll();
        return hbox;
    }

    private Pane createInputPane() {
        final GridPane gridPane = new GridPane();
        return gridPane;
    }

}
