
package robotGUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author Ilektra Pavlopoulou
 */
public class RobotInterface extends Application implements Serializable {
	/**
	 * author Ilektra Pavlopoulou
	 */
	private static final long serialVersionUID = 262900183834808006L;
	private GraphicsContext gc; // graphics context for drawing it
	private AnimationTimer timer; // timer used for animation
	private VBox rtPane; // vertical box for putting info
	private RobotArena arena;
	private Stage stage;

	/**
	 * function to show in a box about the program
	 */
	private void showAbout() {
		Alert alert = new Alert(AlertType.INFORMATION); // define what box is
		alert.setTitle("About"); // say is About
		alert.setHeaderText(null);
		alert.setContentText("Demo of robot simulator program by Ilektra Pavlopoulou"); // give text
		alert.showAndWait(); // show box and wait for user to close
	}

	/**
	 * Function for inputting a value to alter x size of the arena
	 */
	private void showXinput() {

		TextInputDialog dialog = new TextInputDialog("X Size");
		dialog.setTitle("Input X Size");
		dialog.setHeaderText("X Size");
		dialog.setContentText("Please enter a value for x:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			System.out.println("X Value: " + result.get());
			String xbuffer = result.get();
			int xbufferparsed = Integer.parseInt(xbuffer);
			arena.setXSize(xbufferparsed);
		}
	}

	/**
	 * Function for inputting a value to alter y size of the arena
	 */
	private void showYinput() {
		TextInputDialog dialog = new TextInputDialog("Y Size");
		dialog.setTitle("Input Y Size");
		dialog.setHeaderText("Y Size");
		dialog.setContentText("Please enter a value for Y:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			System.out.println("Y Value: " + result.get());
			String ybuffer = result.get();
			int ybufferparsed = Integer.parseInt(ybuffer);
			arena.setYSize(ybufferparsed);
		}
	}

	
	public void initialise(Stage primaryStage) {
		this.stage = primaryStage;
	}
	/**
	 * saves the arena and its contents in a file
	 */
	public void saveFile(){
	
		FileChooser fc = new FileChooser();
		
		File file = fc.showSaveDialog(stage);
		
		FileOutputStream fileos= null;
		ObjectOutputStream objectos = null;
		
			try {
				fileos = new FileOutputStream(file);
				objectos = new ObjectOutputStream(fileos);
				objectos.writeObject(this.arena);
				objectos.flush();
				objectos.close();
				fileos.flush();
				fileos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (file != null) {
				System.out.println("File output: " + file);
			}

	}
	/**
	 * loads the arena and its contents from a file that has been saved
	 */
	public void loadFile() {
		FileChooser fc = new FileChooser();
		
		File file = fc.showOpenDialog(stage);
		
		FileInputStream filein = null;
		ObjectInputStream objectin = null;
		
			try {

				filein = new FileInputStream(file);
				objectin = new ObjectInputStream(filein);
				this.arena = (RobotArena)objectin.readObject();
				objectin.close();
				filein.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		drawWorld();
	}
	/**
	 * 
	 */
	public void startUp() {

		int ctr = 0; // initialise counter
		try {
			File file = new File(
					"StartUp.bin");
			FileInputStream inFile = new FileInputStream(file);
			ObjectInputStream inObj = new ObjectInputStream(inFile);
			this.arena = (RobotArena) inObj.readObject();
			inFile.close();
			inObj.close();
			drawWorld();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			ctr++;
		} catch (IOException e) {
			e.printStackTrace();
			ctr++;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			ctr++;
		}
		if (ctr != 0) {
			System.out.println("File cannot be loaded");
			this.arena = new RobotArena(400, 500);
			arena.addLightDetecctorRobot();
			arena.addLightRobot();
			arena.addRobot();
			arena.addWhiskerRobot();
			arena.addWall();
			drawWorld();
		}
	}

	/**
	 * set up the mouse event - when mouse pressed, put bot there
	 * 
	 * @param canvas
	 */
	void setMouseEvents(Canvas canvas) {
		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, // for MOUSE PRESSED
															// event
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						drawWorld();
						drawStatus();

					}
				});
	}
	
	public GraphicsContext getgc() {
		return gc;
	}

	/**
	 * set up the menu of commands for the GUI 
	 * @return the menu bar
	 */
	MenuBar setMenu() {
		MenuBar menuBar = new MenuBar(); // create main menu

		Menu mFile = new Menu("File"); // add File main menu

		MenuItem mExit = new MenuItem("Exit"); // whose sub menu has Exit
		mExit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) { // action on exit is
				timer.stop(); // stop timer
				System.exit(0); // exit program
			}
		});
		
		MenuItem mSave = new MenuItem("Save"); // whose sub menu has Exit
		mSave.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) { // action on exit is
				saveFile();
			}
		});
		MenuItem mLoad = new MenuItem("Load"); // whose sub menu has Exit
		mLoad.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) { // action on exit is
				loadFile();
			}
		});
		
		
		mFile.getItems().addAll(mExit, mSave, mLoad); // add exit to File menu

		Menu mHelp = new Menu("Help"); // create Help menu
		MenuItem mAbout = new MenuItem("About"); // add About sub men item
		mAbout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				showAbout(); // and its action to print about
			}
		});
		mHelp.getItems().addAll(mAbout); // add About to Help main item

		Menu mConfig = new Menu("Configure");
		MenuItem msetX = new MenuItem("Set X Size of Arena");
		msetX.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				showXinput();

			}
		});
		MenuItem msetY = new MenuItem("Set Y Size of Arena");
		msetY.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg1) {
				showYinput();

			}
		});
		mConfig.getItems().addAll(msetX, msetY);

		Menu mRun = new Menu("Run");
		MenuItem mStart = new MenuItem("Start");
		msetY.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg1) {
				timer.start();

			}
		});
		mRun.getItems().addAll(mStart);
		menuBar.getMenus().addAll(mFile, mHelp, mConfig, mRun); // set main menu
																// with File,
																// Help
		return menuBar; // return the menu
	}

	/**
	 * set up the horizontal box for the bottom with relevant buttons
	 */
	private Button setButtons() {
		
		
		Button btnStart = new Button("Start"); // create button for starting
		btnStart.setOnAction(new EventHandler<ActionEvent>() { // now define
																// event when it
																// is pressed
			@Override
			public void handle(ActionEvent event) {
				timer.start(); // its action is to start the timer
			}
		});
		return btnStart;
	}
	/**
	 * set up button for pausing
	 */
	private Button setButtons1() {

		Button btnStop = new Button("Pause"); // now button for stop
		btnStop.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				timer.stop(); // and its action to stop the timer
			}
		});
		return btnStop;
	}
	/**
	 * set up button to add a new robot every time it is pressed
	 */
	private Button setButtons2() {

		Button btnAdd = new Button("Robot"); // button to add more robots
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				arena.addRobot(); // and its action to stop the timer
				drawWorld();
				drawStatus();
			}
		});
		return btnAdd;
	}
	/**
	 * set up button to add a light to the arena every time it is pressed
	 */
	private Button setButtons3() {
		
		Button btnLight = new Button("Light"); // button to add a light
		btnLight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				arena.addLightRobot();
				drawWorld();
				drawStatus();
			}
		});
		return btnLight;
	}
	/**
	 * set up button to add a light detecting robot to the arena
	 */
	private Button setButtons4() {
		
		Button btnLightDet = new Button("Light Detector"); //button to add a light detecting robot
		btnLightDet.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				arena.addLightDetecctorRobot();
				drawWorld();
				drawStatus();
			}
		});
		return btnLightDet;
	}
	/**
	 * set up button to add a whisker robot to the arena
	 */
	private Button setButtons5() {

		Button btnWhiskerBot = new Button("Whisker Bot"); //button to add a robot w/ whiskers
		btnWhiskerBot.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				arena.addWhiskerRobot(); // and its action to stop the timer
				drawWorld();
				drawStatus();
			}
		});
		return btnWhiskerBot;
	}		
	/**
	 * set up button to add a wall to the arena
	 * every time it is pressed
	 */
	private Button setButtons6() {

		Button btnWall = new Button("Wall"); //button to add a wall
		btnWall.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				arena.addWall(); 
				drawWorld();
				drawStatus();
			}
		});
		return btnWall;
	}
	/**
	 * set up button to clear the arena
	 */
	private Button setButtons7() {

		Button btnCls = new Button("Clear All"); 
		btnCls.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				arena = new RobotArena(400,500);
				drawWorld();
				drawStatus();
			}
		});
		return btnCls;
	}

	

	/**
	 * function to convert char c to actual colour used
	 * 
	 * @param c
	 * @return Color
	 */
	Color colFromChar(char c) {
		Color ans = Color.BLACK;
		switch (c) {
		case 'y':
			ans = Color.YELLOW;
			break;
		case 'r':
			ans = Color.RED;
			break;
		case 'g':
			ans = Color.GREEN;
			break;
		case 'b':
			ans = Color.BLUE;
			break;
		case 'o':
			ans = Color.ORANGE;
			break;
		}
		return ans;
	}


	/**
	 * draw the world with robot in it
	 */
	public void drawWorld() {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, arena.getXSize(), arena.getYSize());
		arena.drawArena(this);
	}

	/**
	 * show where robot is, in pane on right
	 */
	public void drawStatus() {
		rtPane.getChildren().clear(); // clear rtpane
		ArrayList<String> allRs = arena.describeAll();
		for (String s : allRs) {
			Label l = new Label(s); // turn description into a label
			rtPane.getChildren().add(l); // add label
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Ilektra's Robot Simulator");
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(10, 20, 10, 20));

		bp.setTop(setMenu()); // put menu at the top

		Group root = new Group(); // create group with canvas
		Canvas canvas = new Canvas(400, 500);
		root.getChildren().add(canvas);
		bp.setLeft(root); // load canvas to left area

		gc = canvas.getGraphicsContext2D(); // context for drawing

		setMouseEvents(canvas); // set up mouse events

		//arena = new RobotArena(400,500);
		//drawWorld();
		startUp();
		
		timer = new AnimationTimer() { // set up timer
			public void handle(long currentNanoTime) { // and its action when on
				arena.adjustRobots();
				drawWorld();
				drawStatus(); // indicate where balls are
				
			}
		};

		rtPane = new VBox(); // set vBox on right to list items
		rtPane.setAlignment(Pos.TOP_LEFT); // set alignment
		rtPane.setPadding(new Insets(5, 50, 50, 5)); // padding
		bp.setRight(rtPane); // add rtPane to borderpane right

		
		HBox bottomMenu = new HBox();
		bottomMenu.getChildren().addAll(setButtons());
		bottomMenu.getChildren().addAll(setButtons1());
		bottomMenu.getChildren().addAll(setButtons2());
		bottomMenu.getChildren().addAll(setButtons3());
		bottomMenu.getChildren().addAll(setButtons4());
		bottomMenu.getChildren().addAll(setButtons5());
		bottomMenu.getChildren().addAll(setButtons6());
		bottomMenu.getChildren().addAll(setButtons7());

		bp.setBottom(bottomMenu); 			/// add button to bottom


		Scene scene = new Scene(bp, 700, 600); // set overall scene
		bp.prefHeightProperty().bind(scene.heightProperty());
		bp.prefWidthProperty().bind(scene.widthProperty());

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(args); // launch the GUI

	}

}