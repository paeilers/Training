/**
 * A Trip is comprised of one or more Packages, each Package having a unique Location, 
 * and is associated with a unique Drone.
 * NOTE: Since Package (package) are reserved words Pakage (pakage) is intentionally mis-spelled here.
 */
package droneDelivery.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paeilers
 *
 */
public class Trip {
	
	private Integer tripNumber;
	private Drone drone;
	private List<Pakage> packages = new ArrayList<Pakage>();
	private int tripCapacity = 0;
	private int remainingCapacity = 0;

	public Trip() {}

	public Integer getTripNumber() {
		return tripNumber;
	}

	public void setTripNumber(Integer tripNumber) {
		this.tripNumber = tripNumber;
	}

	public Drone getDrone() {
		return drone;
	}

	public void setDrone(Drone drone) {
		this.drone = drone;
	}

	public List<Pakage> getPackages() {
		return packages;
	}

	public void setPackages(List<Pakage> packages) {
		this.packages = packages;
	}

	public int getTripCapacity() {
		return tripCapacity;
	}

	public void setTripCapacity(int tripCapacity) {
		this.tripCapacity = tripCapacity;
	}

	public int getRemainingCapacity() {
		return remainingCapacity;
	}

	public void setRemainingCapacity(int remainingCapacity) {
		this.remainingCapacity = remainingCapacity;
	}
		
}
