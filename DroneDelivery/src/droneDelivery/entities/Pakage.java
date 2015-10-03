/**
 * A Package is the payload that gets assigned to a Drone via a Trip.
 * A Package has a Location and a Weight (assumed to be of type Integer).
 * Assume that a Location can be associated with multiple Packages.
 */
package droneDelivery.entities;

import java.util.Comparator;

/**
 * @author paeilers
 *
 */
public class Pakage implements Comparable<Pakage> {
	
	private String locationName = "";
	private Integer weight = 0;
	private boolean isAllocated = false;
	
	public static class Comparators {
		
		public static Comparator<Pakage> WEIGHT = new Comparator<Pakage>() {
			@Override
			public int compare(Pakage pkg1, Pakage pkg2) {
				return ( (pkg1.getWeight() > pkg2.getWeight()) ? -1 :
						 (pkg1.getWeight() < pkg2.getWeight()) ? 1 : 0);
			}			
		};
		
		public static Comparator<Pakage> NAME = new Comparator<Pakage>() {
			@Override 
			public int compare(Pakage pkg1, Pakage pkg2) {
				return ( pkg1.getLocationName().compareTo(pkg2.getLocationName()) );
			}			
		};
	}

	public Pakage() {}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public boolean isAllocated() {
		return isAllocated;
	}

	public void setAllocated(boolean isAllocated) {
		this.isAllocated = isAllocated;
	}

	@Override
	public int compareTo(Pakage pkg) {
		return ( this.getLocationName().compareTo(pkg.getLocationName()) );
	}


}
