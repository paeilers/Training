/**
 * A DroneScheduler must implement all the methods in this interface.
 */
package droneDelivery.controls;

import java.util.ArrayList;
import java.util.List;

import droneDelivery.entities.Drone;
import droneDelivery.entities.Pakage;

/**
 * @author paeilers
 *
 */
public interface DroneScheduler {
	
	public List<Drone> createTrips(List<Drone> drones, ArrayList<Pakage> pakages);
	
}
