package roadgraph;

import java.util.Comparator;

public class GeoComparator2 implements Comparator<GeoDistancePair2>{

	public int compare(GeoDistancePair2 g1, GeoDistancePair2 g2) {
		// TODO Auto-generated method stub
		if((g1.getDistS()+g1.getDistG())<(g2.getDistS()+g2.getDistG())) return -1;
		if((g1.getDistS()+g1.getDistG())>(g2.getDistS()+g2.getDistG())) return 1;
		else return 0;
	}
	
}
