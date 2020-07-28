package Dukes_of_the_Realm;

import java.util.ArrayList;

import javafx.animation.PathTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Ordre {
	
	private int etat;
	private Chateau cible;
	private Chateau admin;
	private ArrayList<Soldat> troupe = new ArrayList<Soldat>();
	private int nbrTroupe;
	private String action;
	private Pane layer;	
	private ImageView image;
	
	public Ordre(Chateau admin, Pane layer) {
		this.admin = admin;
		this.layer = layer;
		this.etat = Settings.OFF;
		this.action = "None";
	}
	
	public void action() {
		switch(action){
			case "Ost":
				createOst();
				break;
			case "Move":
				if(troupe.isEmpty()) {
					this.etat = Settings.OFF;
					this.action = "None";
				}else {
					move();
				}
				break;
		}
	}
	
	public void createOst() {
		if(nbrTroupe > 0) {
			int i;
			for(i = 0; i<3;i++) {
				if(nbrTroupe<i+1) { break;}
				this.image = this.troupe.get(nbrTroupe-i-1).getView();
				this.troupe.get(nbrTroupe-i-1).setX(admin.getX()+ 95+i*10);
				this.troupe.get(nbrTroupe-i-1).setY(admin.getY()+105+i*10);
				double x = this.troupe.get(nbrTroupe-i-1).getX();
				double y = this.troupe.get(nbrTroupe-i-1).getY();
				this.troupe.get(nbrTroupe-i-1).addToLayer();
				image.relocate(x, y);
			}
			nbrTroupe-=i;
		}else {
			this.action ="Move";
			this.nbrTroupe = 0;
		}
	}
	
	public void move() {
		double x;
		double y;
		int vitesse, dx, dy;
		int i = 0;
		while(i<troupe.size()) {
			x = troupe.get(i).getX();
			y = troupe.get(i).getY();
			vitesse = troupe.get(i).getVitesse();
			dx = 1;
			dy = 1;
			if((!minDistance(x, cible.getPorteX()))|| (!minDistance(y,cible.getPorteY()))) {
				for(int j = 0; j<vitesse;j++) {
					if(x<cible.getPorteX()) {
						x=x+dx;
					}else {
						if(!minDistance(x, cible.getPorteX())){
							x = x-dx;
						}
					}
					if(y<cible.getPorteY()) {
						y=y+dy;
					}else {
						if(!minDistance(y, cible.getPorteY())){
							y = y-dy;
						}
					}
					troupe.get(i).setX(x);
					troupe.get(i).setY(y);
					troupe.get(i).getView().relocate(x, y);
					if(minDistance(x, cible.getPorteX())&& minDistance(y,cible.getPorteY())){
						break;
					}
				}
			}else {
				attack(i);
			}
			if(troupe.size()>i && troupe.get(i).getDegats()==0) {
				troupe.get(i).removeFromLayer();
				troupe.remove(i);
			}else {
				i++;
			}
		}
	}
	
	public void attack(int soldat) {
			if(admin.getDuc().equals(cible.getDuc())) {
				cible.setChevaliers((Chevalier) troupe.get(0));
				troupe.get(0).removeFromLayer();
				troupe.remove(0);
			}else {
				if(cible.getReserve().size()==0) {
					cible.setDuc(admin.getDuc());
					cible.updateColorFrontier();
					attack(soldat);
				}else {
					//int x = rand.nextInt(cible.getReserve().size());
					int damage = troupe.get(soldat).getDegats();
					cible.setDamagePvReserve(0);
					troupe.get(soldat).setDegats(damage-1);
				}
			}	
	}
	
	public void moveChevalier() {
		if(this.etat==Settings.ON) {
			//Group test = new Group();
			
			Circle cir = new Circle(10);
			//cir.setLayoutX(getAdmin().getX()+95);
			//cir.setLayoutY(getAdmin().getY()+105);
			cir.setFill(Color.AQUAMARINE);
			
			Path path = new Path();
			path.getElements().add(new MoveTo(getAdmin().getX()+95, getAdmin().getY()+105));
			path.getElements().add(new LineTo(cible.getX()+95, cible.getY()+105));
			
			PathTransition trans = new PathTransition();
		    trans.setNode(cir);
		    trans.setDuration(Duration.seconds(2));
		    trans.setPath(path);
		    //trans.setAutoReverse(true);
		    trans.setCycleCount(PathTransition.INDEFINITE);
			trans.play();
			this.layer.getChildren().addAll(cir);
		}
	}
	
	public boolean minDistance(double x, double y) {
		return ((x-y)<1 && (x-y)>-1)||((y-x)<1 && (y-x)>-1);
	}

	
	public Chateau getAdmin() {
		return this.admin;
	}

	public void setAdmin(Chateau admin) {
		this.admin = admin;
	}

	public Chateau getCible() {
		return cible;
	}
	
	public void setCible(Chateau cible) {
		this.cible = cible;
	}
	
	public int getEtat() {
		return etat;
	}

	public ArrayList<Soldat> getTroupe_A_Envoyer() {
		return troupe;
	}
	
	public void setOrdre(ArrayList<Soldat> troupe, Chateau cible) {
		if(troupe.isEmpty()) {
			throw new RuntimeException("troupes non choisis ");
		}
		this.cible = cible;
		this.troupe.addAll(troupe);
		this.etat = Settings.ON;
		this.action = "Ost";
		this.nbrTroupe = troupe.size();
	}
	
	

}