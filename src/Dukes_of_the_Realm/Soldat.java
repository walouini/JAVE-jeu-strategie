package Dukes_of_the_Realm;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Soldat{

	private Pane layer;
	private	ImageView image;
	private int cout;
	private int tmpProduc;
	private int vitesse;
	private int points_de_vie;
	private int degats;
	private double x;
	private double y;

	public Soldat( Pane layer, Image image_chevalier, int cout, int tmpProduc, int vitesse, int points_de_vie, int degats) {
		this.layer = layer;
		this.image = new ImageView(image_chevalier);
		this.cout = cout; 
		this.tmpProduc = tmpProduc; 
		this.vitesse = vitesse;
		this.points_de_vie = points_de_vie;
		this.degats = degats;
		image.relocate(x, y);
	}

	public void addToLayer() {
        layer.getChildren().add(this.image);
    }

    public void removeFromLayer() {
        this.layer.getChildren().remove(this.image);
    }
    
    public ImageView getView() {
        return this.image;
    }

	public int getCout() {
		return cout;
	}

	public void setCout(int cout) {
		this.cout = cout;
	}

	public int getTmpProduc() {
		return tmpProduc;
	}

	public void setTmpProduc(int tmpProduc) {
		this.tmpProduc = tmpProduc;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public int getPoints_de_vie() {
		return points_de_vie;
	}

	public void setPoints_de_vie(int points_de_vie) {
		this.points_de_vie = points_de_vie;
	}

	public int getDegats() {
		return degats;
	}

	public void setDegats(int degats) {
		this.degats = degats;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
}
