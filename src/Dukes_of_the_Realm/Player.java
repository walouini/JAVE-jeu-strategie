package Dukes_of_the_Realm;

import java.util.ArrayList;

import javafx.scene.paint.Color;


public class Player {
	
    private Color color;
    private Color color_frontier;
	private String name;
	private ArrayList<Player> ducs = new ArrayList<Player>();
	
	
	public Player() {
		Player joueur1 = new Player(Color.RED,"joueur1",Color.rgb(255, 0, 0, 0.13));
		Player joueur2 = new Player(Color.LIME,"joueur2",Color.rgb(0, 255, 0, 0.12));
		Player joueur3 = new Player(Color.FUCHSIA,"joueur3",Color.rgb(255, 0, 255, 0.13));
		Player Neutre1 = new Player(Color.BLUE,"Neutre ",Color.rgb(0,0,255, 0.12));
		Player Neutre2 = new Player(Color.DARKTURQUOISE,"Neutre ",Color.rgb(0,206, 209,0.13));

		this.ducs.add(joueur1);
		this.ducs.add(joueur2);
		this.ducs.add(joueur3);
		this.ducs.add(Neutre1);
		this.ducs.add(Neutre2);
	}
	
	
	public Player(Color color, String duc, Color color_frontier) {
		this.color = color;
		this.name = duc;
		this.color_frontier = color_frontier;
	}


	public Color getColor() {
		return color;
	}


	public Color getColor_frontier() {
		return color_frontier;
	}


	public void setColor_frontier(Color color_frontier) {
		this.color_frontier = color_frontier;
	}


	public void setColor(Color color) {
		this.color = color;
	}



	public String getName() {
		return name;
	}



	public void setName(String duc) {
		this.name = duc;
	}



	public void setDucs(ArrayList<Player> ducs) {
		this.ducs = ducs;
	}



	public ArrayList<Player> getDucs() {
		return ducs;
	}

}
