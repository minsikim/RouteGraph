package roadgraph;

import geography.GeographicPoint;

public class GeoDistancePair {
	
	private GeographicPoint g;
	private double distance;
	
	public GeoDistancePair(GeographicPoint g, double distance) {
		this.setG(g);
		this.setDistance(distance);
	}
	
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public GeographicPoint getG() {
		return g;
	}
	public void setG(GeographicPoint g) {
		this.g = g;
	}
}
