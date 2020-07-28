package Dukes_of_the_Realm;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class royaume {
	
	private ArrayList<Chateau> list_chateau = new ArrayList<Chateau>();

	public royaume(ArrayList<Player> Ducs, Pane layer, Image image, Image image_chevalier) {
		Player Duc;
		Chateau chateau;
		//ArrayList<Point2D> points = listePoints(image, Ducs);
		for(int i = 0; i<Ducs.size();i++){
			Duc = Ducs.get(i);
			chateau = new Chateau(Duc, layer, image, image_chevalier);
			//chateau.setX(points.get(i).getX());
			//chateau.setY(points.get(i).getY());
			for (int i1 = 0; i1 < 5; i1++) {
				chateau.setChevaliers(new Chevalier(layer, image_chevalier)); 		
			}
			chateau.setReserve(chateau.getChevaliers());
			chateau.setNom_chateau("chateau_"+(i+1));
			this.list_chateau.add(chateau);
		}
		checkCollisions(list_chateau);
	}

	public ArrayList<Chateau> getList_chateau() {
		return list_chateau;
	}
	
	private void checkCollisions(ArrayList<Chateau> lists_chateau) {
        Iterator<Chateau> itr = lists_chateau.iterator();
        ArrayList<Chateau> lists_2_chateau = new ArrayList<Chateau>();
        Chateau c1 = null;
        Chateau c2 = null;
        while(itr.hasNext()) {
        	c1 = itr.next();
        	if (c2 != null) {
            	lists_2_chateau.add(c2);
        		if(c1.collisionsWith(lists_2_chateau)) {
        			c1.updateFrontier(lists_2_chateau);
        		}
        	}
        	c2 = c1;
        }
	}
	
	public boolean isGameOver() {
		Player player = list_chateau.get(0).getDuc();
		for(int i = 1; i<list_chateau.size();i++) {
			if(!player.equals(list_chateau.get(i).getDuc())) {
				return false;
			}
		}return true;
	}
	
	public void update() {
		list_chateau.forEach(Chateau -> Chateau.updateChateau());
	}
//	private ArrayList<Point2D> listePoints(Image image, ArrayList<String> Ducs){
//		double height = image.getHeight();
//		double width = image.getWidth();
//		Random r = new Random();
//		double x = r.nextDouble() * (Settings.SCENE_WIDTH - width);
//		double y = r.nextDouble() * (Settings.SCENE_HEIGHT - Settings.STATUS_BAR_HEIGHT - height);
//		Point2D point = new Point2D(x, y);
//		ArrayList<Point2D> liste = new ArrayList<Point2D>();
//		liste.add(point);
//		while(liste.size()!=Ducs.size()) {
//			x = r.nextDouble() * (Settings.SCENE_WIDTH - width);
//			y = r.nextDouble() * (Settings.SCENE_HEIGHT - Settings.STATUS_BAR_HEIGHT - height);
//			point = new Point2D(x, y);
//			for(int i = 0; i<liste.size(); i++) {
//				if(point.equals(liste.get(i))||point.distance(liste.get(i))<500) {
//					break;
//				}else {
//					if(i==liste.size()-1) {
//						liste.add(point);
//					}
//				}
//			}
//		}
//		return liste;
//	}
}
