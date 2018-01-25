package roadgraph;

import geography.GeographicPoint;

public class RoadEdge {
	private String roadType;
	private String roadName;
	private GeographicPoint start;
	private GeographicPoint end;
	private double length;
	
	public RoadEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) {
		this.setRoadType(roadType);
		this.setRoadName(roadName);
		this.setStart(from);
		this.setEnd(to);
		this.setLength(length);
	}

//	@Override
//	public int hashCode() {
//		double temp = (start.getX()+start.getY())-(end.getX()+end.getY());
//		int hashCode = Double.hashCode(temp);
//		return hashCode;
//	}
//	@Override
//	public boolean equals(Object o) {
//		RoadEdge other = (RoadEdge)o;
//		if(!this.roadName.equals(other.getRoadName()))return false;
//		if(!this.roadType.equals(other.getRoadType()))return false;
//		if(!(this.start.getX() == other.getStart().getX()))return false;
//		if(!(this.start.getY() == other.getStart().getY()))return false;
//		return true;
//	}
	
	public String getRoadType() {
		return roadType;
	}

	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public GeographicPoint getStart() {
		return start;
	}

	public void setStart(GeographicPoint start) {
		this.start = start;
	}

	public GeographicPoint getEnd() {
		return end;
	}

	public void setEnd(GeographicPoint end) {
		this.end = end;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}
}
