/**
 * This class represents one square meter of land in a two dimensional array
 * 
 * @author bduck
 *
 */
public class LandNode {
	private boolean isBarren;
	private boolean isVisited;
	
	public LandNode() {
		this.isBarren = false;
		this.isVisited = false;
	}
	
	public boolean isBarren() {
		return isBarren;
	}
	public void setBarren() {
		this.isBarren = true;
	}
	public boolean isVisited() {
		return isVisited;
	}
	public void setVisited() {
		this.isVisited = true;
	}	

}
