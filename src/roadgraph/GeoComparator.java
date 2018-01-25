package roadgraph;

import java.util.Comparator;

public class GeoComparator implements Comparator<GeoDistancePair>{

	public int compare(GeoDistancePair g1, GeoDistancePair g2) {
		// TODO Auto-generated method stub
		if(g1.getDistance()<g2.getDistance()) return -1;
		if(g1.getDistance()>g2.getDistance()) return 1;
		else return 0;
	}

}
