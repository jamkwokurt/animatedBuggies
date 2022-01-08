package bugAnimation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;

public class MainEnrty extends Application{
	private static double WIDTH = 600.0;
	private static double HEIGHT = 600.0;
	final World world = new World(600.0, 600.0);
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane bp = new BorderPane();
		Button button = new Button();
		button.setText("Tick");
		bp.setTop(button);
		bp.setCenter(world);
		addObject();
		world.drawWorldOfImage();
		world.updateWorldOfImage();
		final Group root = new Group();
		final Scene scene = new Scene(root, WIDTH, HEIGHT);
		for(BugWorldObject o : world.getObjects()) {
//			Circle c = o.getCircle();
//			c.setTranslateX(o.getX());
//			c.setTranslateY(o.getY());
			ImageView i = o.getSymbol();
//			i.setTranslateX(o.getX());
//			i.setTranslateY(o.getY());
			root.getChildren().add(i);
		}
		
		KeyFrame frame = new KeyFrame(Duration.millis(20), 
				e -> {
					world.updateWorldOfImage();}
				);
		Timeline timeline = new Timeline();
		timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
		timeline.getKeyFrames().add(frame);
		timeline.play();
		primaryStage.setTitle("Bug World");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void addObject() {
		world.getBugs().add(new Beetle("lennon",100,100,100,world,true));
		world.getBugs().add(new Beetle("mccartney",290,90,100,world,true));
		world.getBugs().add(new Cockroach("Q",180,70,100,world,true));
		world.getBugs().add(new Spider("tom",250,500,100,world,true));
		world.getPlants().add(new Plant(470,520,3,world));
//		world.getPlants().add(new Plant(460,370,2,world));
//		world.getPlants().add(new Plant(90,500,world));
//		world.getPlants().add(new Plant(170,220,world));
//		world.getPlants().add(new Plant(260,370,world));
//		world.getPlants().add(new Plant(90,230,world));
//		world.getMeats().add(new Meat(240,440,world));
//		world.getMeats().add(new Meat(400,70,world));
//		world.getMeats().add(new Meat(490,240,world));
//		world.getMeats().add(new Meat(140,440,world));
//		world.getMeats().add(new Meat(200,70,world));
		world.getMeats().add(new Meat(90,140,world));
		world.getObstacles().add(new Obstacle(560,100));
//		world.getObstacles().add(new Obstacle(280,410));
//		world.getObstacles().add(new Obstacle(80,40));
		world.getFood().addAll(world.getBugs());
		world.getFood().addAll(world.getPlants());
		world.getFood().addAll(world.getMeats());
		for(Bug b : world.getBugs()) {
			BugWorldObject bo = (BugWorldObject)b;
			world.getObjects().add(bo);
		}
		for(Plant p : world.getPlants()) {
			BugWorldObject po = (BugWorldObject)p;
			world.getObjects().add(po);
		}
		for(Meat m : world.getMeats()) {
			BugWorldObject mo = (BugWorldObject)m;
			world.getObjects().add(mo);
		}
		for(Obstacle ob : world.getObstacles()) {
			world.getObjects().add(ob);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
