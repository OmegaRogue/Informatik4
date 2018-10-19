package omegaRogue;

import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.shape.*;

import java.util.EventListener;

import javafx.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.stage.*;
public class Intro extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@SuppressWarnings("restriction")
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Circle kreis = new Circle(190,40,30);
		kreis.setFill(Color.RED);
		
		Rectangle rechteck = new Rectangle();
		rechteck.setX(200);
		rechteck.setY(90);
		rechteck.setWidth(180);
		rechteck.setHeight(90);
		rechteck.setArcHeight(20);
		rechteck.setArcWidth(40);
		rechteck.setFill(Color.rgb(120, 70, 150));
		
		Line line = new Line(40.0,50.0,140.0,150.0);
		line.setStrokeWidth(4);
		
		Group gesicht = produziereEinGesicht(70,80);
		Group root = new Group();
		root.getChildren().addAll(kreis, rechteck, line, gesicht);
		Scene scene = new Scene(root,800,600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Erste jfx Gehversuche");
		primaryStage.show();
		
		
	}
	public Group produziereEinGesicht(double x, double y) {
		Group gruppe = new Group();
		Circle kopf = new Circle(x,y,80,Color.YELLOW );
		kopf.setStroke(Color.ORANGE);
		kopf.setStrokeWidth(3);
		Circle auge1 = new Circle(x-30, y-40, 15, Color.WHITE);
		auge1.setStroke(Color.BLACK);
		Circle auge2 = new Circle(x+35, y-40, 15, Color.WHITE);
		auge2.setStroke(Color.BLACK);
		Arc mund = new Arc(x, y+30, 60, 30, 180, 180);
		mund.setFill(Color.RED);
		gruppe.getChildren().addAll(kopf,auge1,auge2,mund);
		return gruppe;
	}

}
