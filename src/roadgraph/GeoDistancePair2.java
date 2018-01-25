package roadgraph;

import geography.GeographicPoint;

public class GeoDistancePair2 {
	
	private GeographicPoint g;
	private double distS;
	private double distG;
	
	public GeoDistancePair2(GeographicPoint g, double distS, double distG) {
		this.setG(g);
		this.setDistS(distS);
		this.setDistG(distG);
	}

	public GeographicPoint getG() {
		return g;
	}

	public void setG(GeographicPoint g) {
		this.g = g;
	}

	public double getDistS() {
		return distS;
	}

	public void setDistS(double distS) {
		this.distS = distS;
	}

	public double getDistG() {
		return distG;
	}

	public void setDistG(double distG) {
		this.distG = distG;
	}
	
}
