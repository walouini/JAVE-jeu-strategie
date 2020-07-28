package Dukes_of_the_Realm;

import java.util.ArrayList;

import javafx.event.ActionEvent;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Pop_up {
	
	private static int Soldat_A_Envoyer = 0;
	private static int production_chevalier = 0;
	private static Chateau admin = null;
	
	private static Chateau uP_list_Admin(ArrayList<Chateau> list){
		Chateau c = list.get(0);
		if(list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				if(c.getReserve().size() < list.get(i).getReserve().size()) {
					c = list.get(i);
				}
			}
		}
		return c ;
	}
	
	private static Chateau uP_Chateau_Admin(ArrayList<Chateau> list,Chateau chateau){
		Chateau c = null;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).equals(chateau)) {
				if (i==list.size()-1) {
					return list.get(0);
				}
				c = list.get(i+1);
				return c;
			}
		}
		return c;
	}
	
	// Pop-UP_1 Info Chateau
		static void show_Pop_UP_1(Chateau chateau, Pane layer, Image image_barre_etatt, Image image_niveau_Pop_up_11, Image image_tresor_Pop_up_11, Image image_revenu_Pop_up_11, Image image_troupes_Pop_up_11) {
			ImageView image_niveau_Pop_up_1 = new ImageView(image_niveau_Pop_up_11);
			ImageView image_tresor_Pop_up_1 = new ImageView(image_tresor_Pop_up_11);
			ImageView image_revenu_Pop_up_1 = new ImageView(image_revenu_Pop_up_11);
			ImageView image_troupes_Pop_up_1 = new ImageView(image_troupes_Pop_up_11);

			Group jouer = new Group();
			ImageView image_barre_etat = new ImageView(image_barre_etatt);
			jouer.setTranslateY (Settings.SCENE_HEIGHT-110);
			jouer.setTranslateX (15);
			HBox box = new HBox(20);
			Label duc = new Label("                         "+chateau.getDuc().getName());duc.setFont(Font.font("Cambria",FontWeight.BOLD,20));duc.setTextFill(chateau.getDuc().getColor());
			Label niveau = new Label("Niveau Du Château: "+chateau.getNiveau());niveau.setFont(Font.font("Cambria",FontWeight.BOLD,20));
			Label trésor = new Label("Trésor: "+chateau.getTresor());trésor.setFont(Font.font("Cambria",FontWeight.BOLD,20));
			Label revenu = new Label("Revenu: "+chateau.getRevenu());revenu.setFont(Font.font("Cambria",FontWeight.BOLD,20));
			Label quantité_Troupes = new Label("Quantité De Soldat Présentes: "+chateau.getReserve().size());quantité_Troupes.setFont(Font.font("Cambria",FontWeight.BOLD,20));
			box.setPadding(new Insets(55));
			box.setAlignment(Pos.CENTER);
			box.getChildren().addAll(duc,image_niveau_Pop_up_1,niveau,image_tresor_Pop_up_1,trésor,image_revenu_Pop_up_1,revenu,image_troupes_Pop_up_1,quantité_Troupes);
			jouer.getChildren().addAll(image_barre_etat,box);
			layer.getChildren().add(jouer);
		}
			
		
		// Pop-UP_2 Production_Chateau_Admin
		static void show_Pop_UP_2(Pane layer, Chateau chateau, Image image_tresorr, Image image_niveau_Pop_up_11, Image image_chevalierr, Image image_piquierr, Image image_onagree, Image button_pluss, Image button_moinn) {
			ImageView image_niveau_Pop_up_1 = new ImageView(image_niveau_Pop_up_11);
			ImageView image_piquier = new ImageView(image_piquierr);
			ImageView image_piquier_1 = new ImageView(image_piquierr);
			ImageView image_onagre = new ImageView(image_onagree);
			ImageView image_onagre_1 = new ImageView(image_onagree);
			ImageView button_plus = new ImageView(button_pluss);
			ImageView button_plus_1 = new ImageView(button_pluss);
			ImageView button_plus_2 = new ImageView(button_pluss);
			ImageView button_moin = new ImageView(button_moinn);
			ImageView button_moin_1 = new ImageView(button_moinn);
			ImageView button_moin_2 = new ImageView(button_moinn);
			ImageView image_chevalier = new ImageView(image_chevalierr);
			ImageView image_chevalier3 = new ImageView(image_chevalierr);
			ImageView image_tresor = new ImageView(image_tresorr);

			Stage otherStage = new Stage();
			otherStage.setTitle("       Chateau");
			VBox root_2 = new VBox(10);
			root_2.setPadding(new Insets(25));
			root_2.setAlignment(Pos.CENTER);
			Label jouer = new Label(chateau.getDuc().getName());jouer.setTextFill(chateau.getDuc().getColor());
			jouer.setFont(Font.font("/???/", FontWeight.BOLD, 18));
			
			DropShadow shadow = new DropShadow ();

			HBox tresor = new HBox(10);
			tresor.setPadding(new Insets(25));
			tresor.setAlignment(Pos.CENTER);
			Label statusTresor = new Label("Tresor:  "+chateau.getTresor());
			Label statusNiveau = new Label("Niveau:  "+chateau.getNiveau());
			statusTresor.setFont(Font.font("Avenir", 16));
			tresor.getChildren().addAll(image_tresor, statusTresor, image_niveau_Pop_up_1, statusNiveau);
			
			HBox nb_Troupe = new HBox(10);
			nb_Troupe.setPadding(new Insets(25));
			nb_Troupe.setAlignment(Pos.CENTER);
			Label statusNb_Troupe = new Label("Nombre de troupes présentes:  "+chateau.getReserve().size());
			statusNb_Troupe.setFont(Font.font("Avenir", 16));
			nb_Troupe.getChildren().addAll(image_piquier,image_chevalier3, image_onagre,statusNb_Troupe);
			
			HBox chevalier = new HBox(10);
			chevalier.setPadding(new Insets(25));
			chevalier.setAlignment(Pos.CENTER);
			Label statusChevalier = new Label("chevaliers:  "+chateau.getReserve().size());statusChevalier.setFont(Font.font("Avenir", 16));
			Button button = new Button ();
			button.setEffect (shadow);button.setGraphic(button_plus);button.setContentDisplay(ContentDisplay.CENTER);
			Button button_11 = new Button ();
			button_11.setEffect (shadow);button_11.setGraphic(button_moin);button_11.setContentDisplay(ContentDisplay.CENTER);
			production_chevalier = chateau.getReserve().size();
			button.setOnAction ((ActionEvent e) -> {
				if(chateau.getTresor() > Settings.COUT_CHEVALIER) {
					production_chevalier ++;
					chateau.reduceTresor(Settings.COUT_CHEVALIER);
					statusChevalier.setText("Chevaliers: "+ production_chevalier);
					statusTresor.setText("Tresor: "+ chateau.getTresor());
				}
			});
			button_11.setOnAction ((ActionEvent e) -> {
				if(chateau.getTresor() > Settings.COUT_CHEVALIER) {
					chateau.uPTresor(Settings.COUT_CHEVALIER);
					production_chevalier --;
					if(production_chevalier<chateau.getReserve().size()) {production_chevalier = chateau.getReserve().size();}
					statusChevalier.setText("Chevaliers: "+ production_chevalier);
					statusTresor.setText("Tresor: "+ chateau.getTresor());
				}
			});
			chevalier.getChildren().addAll(image_chevalier, statusChevalier, button, button_11);
			
			HBox piquier = new HBox(10);
			piquier.setPadding(new Insets(25));
			piquier.setAlignment(Pos.CENTER);
			Label statusPiquier = new Label("Piquiers:  0");
			statusPiquier.setFont(Font.font("Avenir", 16));
			Button button_1 = new Button ();
			button_1.setEffect (shadow);button_1.setGraphic(button_plus_1);button_1.setContentDisplay(ContentDisplay.CENTER);
			Button button_111 = new Button ();
			button_111.setEffect (shadow);button_111.setGraphic(button_moin_1);button_111.setContentDisplay(ContentDisplay.CENTER);
			piquier.getChildren().addAll(image_piquier_1, statusPiquier, button_1, button_111);
			
			HBox onagre = new HBox(10);
			onagre.setPadding(new Insets(25));
			onagre.setAlignment(Pos.CENTER);
			Label statusOnagre = new Label("onagre:  0");
			statusOnagre.setFont(Font.font("Avenir", 16));
			Button button_3 = new Button ();
			button_3.setEffect (shadow);button_3.setGraphic(button_plus_2);button_3.setContentDisplay(ContentDisplay.CENTER);
			Button button_31 = new Button ();
			button_31.setEffect (shadow);button_31.setGraphic(button_moin_2);button_31.setContentDisplay(ContentDisplay.CENTER);
			onagre.getChildren().addAll(image_onagre_1, statusOnagre, button_3, button_31);
			
			Button button_valid = new Button ();
			button_valid.setEffect (shadow);button_valid.setText("Valide");button_valid.setContentDisplay(ContentDisplay.CENTER);
			button_valid.setOnAction ((ActionEvent e) -> {
				chateau.setProduction(Settings.PRODUCTION_TYPE_CHEVALIER,production_chevalier-chateau.getReserve().size());
				otherStage.hide();
			});

			root_2.getChildren().addAll(jouer, tresor, nb_Troupe, chevalier, piquier, onagre, button_valid);
			Scene scene_2 = new Scene(root_2, Settings.SCENE_WIDTH/3, Settings.SCENE_HEIGHT/1.5+120);
			otherStage.setScene(scene_2);
			otherStage.setResizable(false);//on peut pas redimensionner cette fenetre 
			otherStage.show();
			otherStage.centerOnScreen();	
			//un Thread pour fermer la fenetre pop-up aprés 100s
			new Thread(() -> {
				try {
					Thread.sleep(100000);
					//tous qui est manipilation de l'interface graphique ce fait au niveau JavaFxThread
					javafx.application.Platform.runLater(() -> otherStage.hide()); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
		
		// Pop-UP_3 Info_Attaque_Chateau
		static void show_Pop_UP_3(ArrayList<Chateau> adminn, Chateau cible, Pane layer, Image image_tresorr, Image image_chevalierr, Image image_piquierr, Image image_onagree, Image button_plus,Image button_moinn) {
			ImageView button_plus_3= new ImageView(button_plus);
			ImageView button_moin = new ImageView(button_moinn);
			ImageView image_tresor= new ImageView(image_tresorr);
			ImageView image_chevalier = new ImageView(image_chevalierr);
			ImageView image_chevalier3 = new ImageView(image_chevalierr);
			ImageView image_onagre= new ImageView(image_onagree);
			ImageView image_piquier = new ImageView(image_piquierr);
			ImageView image_chevalier13 = new ImageView(image_chevalierr);
			ImageView image_onagre13= new ImageView(image_onagree);
			ImageView image_piquier13 = new ImageView(image_piquierr);
			admin = uP_list_Admin(adminn);
			
			Stage otherStage = new Stage();
			otherStage.setTitle("pop-up Attaque");
			VBox root_2 = new VBox(10);
			root_2.setPadding(new Insets(10));
			root_2.setAlignment(Pos.CENTER);
			
			Label jouer = new Label(cible.getNom_chateau());
			jouer.setFont(Font.font("Avenir", FontWeight.BOLD, 18));jouer.setTextFill(cible.getDuc().getColor());
			DropShadow shadow = new DropShadow ();
						
			HBox tresor = new HBox(10);
			tresor.setPadding(new Insets(25));
			tresor.setAlignment(Pos.CENTER);
			Label statusTresor = new Label("Tresor:  "+cible.getTresor());
			statusTresor.setFont(Font.font("Avenir", 16));
			tresor.getChildren().addAll(image_tresor, statusTresor);
			
			HBox nb_Troupe = new HBox(10);
			nb_Troupe.setPadding(new Insets(25));
			nb_Troupe.setAlignment(Pos.CENTER);
			Label statusNb_Troupe = new Label("Nombre de Soldats présentes Dans Ce Chateau:  "+cible.getReserve().size());
			statusNb_Troupe.setFont(Font.font("Avenir", 16));
			nb_Troupe.getChildren().addAll(image_piquier,image_chevalier3, image_onagre,statusNb_Troupe);
			
			HBox nb_Troupe_Admin = new HBox(10);
			nb_Troupe_Admin.setPadding(new Insets(25));
			nb_Troupe_Admin.setAlignment(Pos.CENTER);
			Label statusNb_Troupe_Admin = new Label("Nombre De Vos Soldats dans "+admin.getNom_chateau()+":  "+admin.getReserve().size());
			statusNb_Troupe_Admin.setFont(Font.font("Avenir", 16));
			Button button_suivant = new Button();
			button_suivant.setEffect (shadow);
			button_suivant.setText("Chateau Suivant");
			button_suivant.setContentDisplay(ContentDisplay.CENTER);
			button_suivant.setOnAction((ActionEvent e) -> {
				
				admin = uP_Chateau_Admin(adminn,admin);
				statusNb_Troupe_Admin.setText("Nombre De Vos Soldats dans "+admin.getNom_chateau()+":  "+admin.getReserve().size());
				
			});
			nb_Troupe_Admin.getChildren().addAll(image_piquier13,image_chevalier13, image_onagre13,statusNb_Troupe_Admin,button_suivant);
			
			HBox chevalier = new HBox(10);
			chevalier.setPadding(new Insets(25));
			chevalier.setAlignment(Pos.CENTER);
			Label Chevaliers_A_Envoyer = new Label("Chevaliers A Envoyer ");Label statusSoldat_A_Envoyer = new Label("Chevaliers: "+ Soldat_A_Envoyer);
			Button button_2 = new Button (); 
			button_2.setEffect (shadow);button_2.setGraphic(button_plus_3);button_2.setContentDisplay(ContentDisplay.CENTER);
			button_2.setOnAction((ActionEvent e) -> {
				if(Soldat_A_Envoyer < admin.getReserve().size()) {
					Soldat_A_Envoyer++;
					statusSoldat_A_Envoyer.setText("Chevaliers: "+ Soldat_A_Envoyer);
				}
			});
			Button button_22 = new Button (); 
			button_22.setEffect (shadow);button_22.setGraphic(button_moin);button_22.setContentDisplay(ContentDisplay.CENTER);
			button_22.setOnAction((ActionEvent e) -> {
				if(Soldat_A_Envoyer > 0) {
					Soldat_A_Envoyer--;
					statusSoldat_A_Envoyer.setText("Chevaliers: "+ Soldat_A_Envoyer);
				}
			});
			chevalier.getChildren().addAll(Chevaliers_A_Envoyer,image_chevalier, statusSoldat_A_Envoyer, button_2, button_22);
			Button button = new Button ();
			button.setEffect (shadow);
			button.setText("Attaque");
			button.setContentDisplay(ContentDisplay.CENTER);
			button.setOnAction((ActionEvent e) -> {
				otherStage.hide();
				admin.setOrdre(cible, Soldat_A_Envoyer);
				Soldat_A_Envoyer = 0;
			});
			
			root_2.getChildren().addAll(jouer,tresor,nb_Troupe,nb_Troupe_Admin,chevalier,button);
			Scene scene_2 = new Scene(root_2, Settings.SCENE_WIDTH/2, Settings.SCENE_HEIGHT/1.5 + Settings.STATUS_BAR_HEIGHT);
			otherStage.setScene(scene_2);
			otherStage.setResizable(false);//on peut pas redimensionner cette fenetre 
			otherStage.show();
			otherStage.centerOnScreen();	
		}
		
		
		// Pop-UP_4_Game_Over
		static void show_Pop_UP_Game_Over() {
			Stage otherStage = new Stage();
			otherStage.setTitle("Game_Over");
			VBox root_2 = new VBox(10);
			root_2.setPadding(new Insets(10));
			root_2.setAlignment(Pos.CENTER);
			Label game = new Label("Game Over");
			game.setFont(Font.font("Avenir", FontWeight.BOLD, 50));game.setTextFill(Color.RED);
			root_2.getChildren().addAll(game);
			Scene scene_2 = new Scene(root_2, Settings.SCENE_WIDTH/3, Settings.SCENE_HEIGHT/3 );
			otherStage.setScene(scene_2);
			otherStage.setResizable(false);//on peut plus redimensionner cette fenetre 
			otherStage.show();
			otherStage.centerOnScreen();
		}
		
		// Pop-UP_4_Game_Over
		static void show_Pop_UP_Pause() {
			Stage otherStage = new Stage();
			otherStage.setTitle("Game_Over");
			VBox root_2 = new VBox(10);
			root_2.setPadding(new Insets(10));
			root_2.setAlignment(Pos.CENTER);
			Label game = new Label("Pause");
			game.setFont(Font.font("Avenir", FontWeight.BOLD, 50));game.setTextFill(Color.GREEN);
			root_2.getChildren().addAll(game);
			Scene scene_2 = new Scene(root_2, Settings.SCENE_WIDTH/3, Settings.SCENE_HEIGHT/3 );
			otherStage.setScene(scene_2);
			otherStage.setResizable(false);//on peut plus redimensionner cette fenetre 
			otherStage.show();
			otherStage.centerOnScreen();
		}		

				
}
