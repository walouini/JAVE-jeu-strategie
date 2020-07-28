package Dukes_of_the_Realm;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Affiche extends Application{
	
	private Scene scene;
	Group root;
	private Pane playfieldLayer;
	private ImageView background;	
	private Image image_chateau;
	private Image image_barre_etat;
	private Image image_niveau_Pop_up_1;
	private Image image_tresor_Pop_up_1;
	private Image image_revenu_Pop_up_1;
	private Image image_troupes_Pop_up_1;
	private Image image_tresor;
	private Image image_piquier;
	private Image image_onagre;
	private Image image_chevalier;
	private Image button_moins;
	private Image button_plus;
	private royaume royaume;
	private AnimationTimerExt gameLoop;
	private ArrayList<Chateau> admin = new ArrayList<Chateau>();
	private int sleepNs = 100;
	private int star = Settings.OFF;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//fenêtre principale du jeu
		primaryStage.setTitle("Dukes of the Realm");
		root = new Group();
		playfieldLayer = new Pane();
		root.getChildren().addAll(background,playfieldLayer); 
		scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT + Settings.STATUS_BAR_HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.centerOnScreen();
		
		initialiser_jeu();	
				
		if (star == Settings.ON) {
			
			createRoyaume();
			update_list_chateau_Admin();
			Pop_up.show_Pop_UP_1(admin.get(0),playfieldLayer,image_barre_etat,image_niveau_Pop_up_1,image_tresor_Pop_up_1,image_revenu_Pop_up_1,image_troupes_Pop_up_1);
			
			gameLoop = new AnimationTimerExt(sleepNs) {
				@Override
				public void handle() {
					//fin de jeu
					if(royaume.isGameOver()) {
						Pop_up.show_Pop_UP_Game_Over();
						gameLoop.stop();
					}
					
					update_list_chateau_Admin();
					
					//click_Chateau
					for (Chateau i : royaume.getList_chateau()) {
						i.getView().setOnMouseClicked(new EventHandler<MouseEvent>(){
							@Override
						     public void handle(MouseEvent event) {
								if (event.getClickCount()==1) {
									Pop_up.show_Pop_UP_1(i,playfieldLayer,image_barre_etat,image_niveau_Pop_up_1,image_tresor_Pop_up_1,image_revenu_Pop_up_1,image_troupes_Pop_up_1);
								}
								if (event.getClickCount()==2 && i.getDuc().getName()=="joueur1") {
									Pop_up.show_Pop_UP_2(playfieldLayer,i,image_tresor,image_niveau_Pop_up_1,image_chevalier,image_piquier,image_onagre, button_plus,button_moins);
								}
								if (event.getClickCount()==2 && i.getDuc().getName()!="joueur1") {
									Pop_up.show_Pop_UP_3(admin,i,playfieldLayer,image_tresor,image_chevalier,image_piquier,image_onagre,button_plus,button_moins);
								}
						     }
						});
					}
					
					//mis à jour du jeu
					royaume.update();
					
				}};
			gameLoop.start();
		}
		
	}
	
	//methode init par défaut appeler avant la methode start, tout ce qui est initialisation pour charger le jeu est ici 
	@Override
	public void init() throws Exception {	
		background = new ImageView(getClass().getResource("/images/background.png").toExternalForm());
		image_chateau = new Image(getClass().getResource("/images/chateau.png").toExternalForm());
		image_troupes_Pop_up_1 = new Image(getClass().getResource("/images/image_troupes_Pop_up.png").toExternalForm());
		image_revenu_Pop_up_1 = new Image(getClass().getResource("/images/image_revenu_Pop_up.png").toExternalForm());
		image_niveau_Pop_up_1 = new Image(getClass().getResource("/images/image_niveau_Pop_up.png").toExternalForm());
		image_tresor_Pop_up_1 = new Image(getClass().getResource("/images/image_tresor_Pop_up.png").toExternalForm());
		image_tresor = new Image(getClass().getResource("/images/tresor.png").toExternalForm());
		image_piquier = new Image(getClass().getResource("/images/piquier.png").toExternalForm());
		image_onagre = new Image(getClass().getResource("/images/onagre.png").toExternalForm());
		image_chevalier = new Image(getClass().getResource("/images/chevalier.png").toExternalForm());
		image_barre_etat = new Image(getClass().getResource("/images/barre_etat.png").toExternalForm());
		button_moins = new Image(getClass().getResource("/images/button_moins.png").toExternalForm());
		button_plus = new Image(getClass().getResource("/images/button_plus.png").toExternalForm());
		
	}
	
	private void createRoyaume(){
		Player player = new Player();
		this.royaume = new royaume(player.getDucs(),playfieldLayer,image_chateau,image_chevalier);
		for(int i=0; i<royaume.getList_chateau().size(); i++) {
			double x = royaume.getList_chateau().get(i).getX();
			double y = royaume.getList_chateau().get(i).getY();
			royaume.getList_chateau().get(i).getView().relocate(x, y);
		}
		
	}
	
	public void update_list_chateau_Admin() {
		for (Chateau i : royaume.getList_chateau()) {
			if(i.getDuc().getName()=="joueur1") {
				admin.add(i);
			}
		}
	}
	
	public void initialiser_jeu() {
		VBox vbox = new VBox(20);
		vbox.setPadding(new Insets(25));
		vbox.setAlignment(Pos.CENTER);
		vbox.setTranslateY (Settings.SCENE_HEIGHT-500);
		vbox.setTranslateX (Settings.SCENE_WIDTH/2-150);
		DropShadow shadow = new DropShadow ();
		Button button1 = new Button ();
		button1.setEffect (shadow);
		button1.setText("Valider");
		button1.setContentDisplay(ContentDisplay.CENTER);
		javafx.scene.control.TextField name = new javafx.scene.control.TextField();
		Label msg = new Label("Voulez Vous Saisir Sotre Nom");
		msg.setFont(Font.font("/???/", FontWeight.BOLD, 18));
		msg.setTextFill(Color.RED);
		button1.setOnAction((ActionEvent e) -> {
			playfieldLayer.getChildren().removeAll(vbox);
			star = Settings.ON;
		});
		vbox.getChildren().addAll(msg,name,button1);
		playfieldLayer.getChildren().add(vbox);
	}
	
	//methode stop par défaut appeler lorsque l'application est stopper, tout ce qui est liberation de ressources ce fait ici 
	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
	}
	
	public static void main(String[] args) {
		launch(args);
	}	
	
}
