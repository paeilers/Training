/**
 * Drone is the vehicle used to deliver packages.
 * Each Drone has a maximum weight capacity, assumed to be of type Integer.
 */
package droneDelivery.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author paeilers
 *
 */
public class Drone implements Comparable<Drone> {
	
	private String name = "";
	private Integer maximumCapacity = 0;
	private List<Trip> trips = new ArrayList<Trip>();
	
	public static class Comparators {
		
		public static Comparator<Drone> CAPACITY = new Comparator<Drone>() {
			@Override
			public int compare(Drone drone1, Drone drone2) {
				return ( (drone1.getMaximumCapacity() > drone2.getMaximumCapacity()) ? -1 :
						 (drone1.getMaximumCapacity() < drone2.getMaximumCapacity()) ? 1 : 0);
			}			
		};
		
		public static Comparator<Drone> NAME = new Comparator<Drone>() {
			@Override 
			public int compare(Drone drone1, Drone drone2) {
				return ( drone1.getName().compareTo(drone2.getName()) );
			}			
		};
	}

	public Drone() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMaximumCapacity() {
		return maximumCapacity;
	}
	public void setMaximumCapacity(Integer maximumCapacity) {
		this.maximumCapacity = maximumCapacity;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	@Override // Sort on name in ascending order
	public int compareTo(Drone drone) {
		return ( this.getName().compareTo(drone.getName()) );
	}
	
}
