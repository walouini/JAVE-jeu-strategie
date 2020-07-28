package Dukes_of_the_Realm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Chateau {
	private Player duc;
	private int tresor;
	private int niveau;
	private ArrayList<Production> production = new ArrayList<Production>();
	private ArrayList<Ordre> ordre = new ArrayList<Ordre>();
	private int revenu ;
	private int porte;
	private double x;
	private double y;
	private ImageView imageView;
	private Pane layer;
	private Circle frontier = new Circle();
	private Random r = new Random();
	private ArrayList<Soldat> reserve = new ArrayList<Soldat>();
	private ArrayList<Soldat> chevaliers = new ArrayList<Soldat>();
	private Image image_chevalier;
	private String nom_chateau;

	
	public Chateau(Player duc, Pane layer, Image image, Image image_chevalier2) {
		
		this.layer=layer;
		this.imageView = new ImageView(image);
		this.duc = duc;
		this.porte = r.nextInt(4);//génération d'un entier >= 0 et < 4
		this.x = r.nextInt(Settings.SCENE_WIDTH-350);
		this.y = r.nextInt(Settings.SCENE_HEIGHT-250);
		this.image_chevalier = image_chevalier2;
		if(this.duc.getName() == "Neutre ") {
			this.tresor = 5000 + r.nextInt(10000 - 5000);
		} else {
			this.tresor = 0;
		}
		this.niveau = 1;
		this.revenu = this.niveau * 10;
		frontier.setRadius(220);
		frontier.setCenterX(this.x+175);
		frontier.setCenterY(this.y+102);
		frontier.setFill(getDuc().getColor_frontier());
		this.layer.getChildren().add(frontier);
		addToLayer();
		
	}

	private boolean collisionsWith(Chateau chateau) {
    	return (getDistance(this.x, chateau.x, this.y, chateau.y)
    			<= this.frontier.getRadius()+chateau.frontier.getRadius());
    }
	
	public boolean collisionsWith(ArrayList<Chateau> lists_chateau) {
		Iterator<Chateau> itr = lists_chateau.iterator();
		Chateau c1 = null;
        while(itr.hasNext()) {
        	c1 = itr.next();
        	while(collisionsWith(c1)) {
        		return true;
        	}
        }
        return false;
	}
	
	public void updateFrontier (ArrayList<Chateau> lists_chateau) {
		Iterator<Chateau> itr = lists_chateau.iterator();
		if(lists_chateau.size()==1) {
			Chateau c1 = null;
	        while(itr.hasNext()) {
	        	c1 = itr.next();
	        	while(collisionsWith(c1)) {
	        		this.x = r.nextInt(Settings.SCENE_WIDTH-350);
					this.y = r.nextInt(Settings.SCENE_HEIGHT-250);
					this.frontier.setCenterX(this.x+175);
					this.frontier.setCenterY(this.y+102);
					//this.frontier.setStroke(Color.BLUE);	
	        	}
	        }
		}
		if(lists_chateau.size()==2) {
			Chateau c1 = null;
			Chateau c2 = null;
	        while(itr.hasNext()) {
	        	c1 = itr.next();
	        	c2 = itr.next();
	        	while(collisionsWith(c2) | collisionsWith(c1)) {
	        		this.x = r.nextInt(Settings.SCENE_WIDTH-350);
					this.y = r.nextInt(Settings.SCENE_HEIGHT-250);
					this.frontier.setCenterX(this.x+175);
					this.frontier.setCenterY(this.y+102);
					//this.frontier.setStroke(Color.BLUE);
	        	}
	        }
		}
		if(lists_chateau.size()==3) {
			Chateau c1 = null;
			Chateau c2 = null;
			Chateau c3 = null;
	        while(itr.hasNext()) {
	        	c1 = itr.next();
	        	c2 = itr.next();
	        	c3 = itr.next();
	        	while(collisionsWith(c2) | collisionsWith(c1) | collisionsWith(c3) ) {
	        		this.x = r.nextInt(Settings.SCENE_WIDTH-350);
					this.y = r.nextInt(Settings.SCENE_HEIGHT-250);
					this.frontier.setCenterX(this.x+175);
					this.frontier.setCenterY(this.y+102);
					//this.frontier.setStroke(Color.BLUE);
	        	}
	        }
		}
		if(lists_chateau.size()==4) {
			Chateau c1 = null;
			Chateau c2 = null;
			Chateau c3 = null;
			Chateau c4 = null;
	        while(itr.hasNext()) {
	        	c1 = itr.next();
	        	c2 = itr.next();
	        	c3 = itr.next();
	        	c4 = itr.next();
	        	while(collisionsWith(c2) | collisionsWith(c1) | collisionsWith(c3) | collisionsWith(c4)) {
	        		this.x = r.nextInt(Settings.SCENE_WIDTH-350);
					this.y = r.nextInt(Settings.SCENE_HEIGHT-250);
					this.frontier.setCenterX(this.x+175);
					this.frontier.setCenterY(this.y+102);
					//this.frontier.setStroke(Color.BLUE);
	        	}
	        }
		}
	}

	private double getDistance(double x1, double x2, double y1, double y2) {
		double xDistance = x2 - x1;
		double yDistance = y2 - y1;
		return Math.sqrt(xDistance*xDistance + yDistance*yDistance); 
	}
	
	public void addToLayer() {
        layer.getChildren().add(this.imageView);
    }

    public void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
    }
    
    public void updateChateau() {
    	updateOrdres();
    	updateProduction();
    	UpdateRevenu();
    	setTresor(tresor + revenu);
    }
    
    public void updateOrdres() {
    	for(int i = 0; i<ordre.size();i++) {
    		if(ordre.get(i).getEtat()==Settings.OFF) {
    			ordre.remove(i);
    			i--;
    		}else {
    			ordre.get(i).action();
    		}
    	}
	}
    
    public void updateProduction() {
    	if(!production.isEmpty()) {
    		if(production.get(0).getEtat()==Settings.OFF) {
    			production.remove(0);
    			updateProduction();
    		}else {
    			production.get(0).update();
    		}
    	}
    }
    
    public void updateColorFrontier() {
    	this.frontier.setFill(getDuc().getColor_frontier());
    }
    
    public ImageView getView() {
        return this.imageView;
    }
    
    public Boolean gameOver_Chateau(){
    	return this.chevaliers.size()==0;
    }
    
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

	public Player getDuc() {
		return this.duc;
	}


	public void setDuc(Player duc) {
		this.duc = duc;
	}


	public int getTresor() {
		return this.tresor;
	}


	public void setTresor(int tresor) {
		this.tresor = tresor;
	}
	
	public void reduceTresor(int value) {
		this.tresor -= value;
	}
	
	public void uPTresor(int value) {
		this.tresor += value;
	}

	public int getNiveau() {
		return niveau;
	}
	
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	
	public int getRevenu() {
		return this.revenu;
	}


	public void UpdateRevenu() {
		this.revenu = this.niveau*10;
	}

	public ArrayList<Soldat> getReserve() {
		return this.reserve;
	}

	public void setReserve(ArrayList<Soldat> reserve) {
		this.reserve = reserve;
	}
	
	public void setDamagePvReserve(int unite) {
		int pv = reserve.get(unite).getPoints_de_vie();
		if((pv-1)==0) {
			reserve.remove(unite);
		}else {
			reserve.get(unite).setPoints_de_vie(pv - 1);
		}
	}
	
	public boolean hasNoMoreReserve() {
		return reserve.isEmpty();
	}

	public ArrayList<Production> getProduction() {
		return production;
	}

	public void setProduction(String type, int n) {
		for (int i = 0; i < n; i++) {
			Production prod = new Production(this);
			production.add(prod);
			production.get(production.size()-1).addProduction(type);
		}
	}

	public ArrayList<Ordre> getOrdre() {
		return ordre;
	}

	public void setOrdre(Chateau cible, int soldat_A_Envoyer) {
		ArrayList<Soldat> soldats = new ArrayList<Soldat>();
		for(int i = 0; i < soldat_A_Envoyer; i++) {
			soldats.add(reserve.get(0));
			reserve.remove(0);
		}
		this.ordre.add(new Ordre(this, layer));
		this.ordre.get(this.ordre.size()-1).setOrdre(soldats, cible);
	}

	public int getPorte() {
		return porte;
	}
	
	public double getPorteX() {
		return x + 95;
	}
	
	public double getPorteY() {
		return y + 105;
	}

	public ArrayList<Soldat> getChevaliers() {
		return chevaliers;
	}

	public void setChevaliers(Chevalier chevalier) {
		this.chevaliers.add(chevalier);
	}

	public Pane getLayer() {
		return layer;
	}

	public Image getImage_chevalier() {
		return image_chevalier;
	}

	public String getNom_chateau() {
		return nom_chateau;
	}

	public void setNom_chateau(String nom_chateau) {
		this.nom_chateau = nom_chateau;
	}


}
