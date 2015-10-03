package droneDelivery.controls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import droneDelivery.entities.Drone;
import droneDelivery.entities.Pakage;
import droneDelivery.entities.Trip;

public class DroneSchedulerImpl implements DroneScheduler {

	@Override
	public List<Drone> createTrips(List<Drone> drones, ArrayList<Pakage> pakages) {
		
		int tripNumber = 1;			
		Collections.sort(drones, Drone.Comparators.CAPACITY);
		
		// While there are unallocated Packages
		while (!pakages.isEmpty()) {
			// For each Drone
			for (Drone drone : drones) {
				if (!pakages.isEmpty()) {
					Collections.sort(pakages, Pakage.Comparators.WEIGHT);	
					// Create a new Trip for the Drone
					Trip trip = new Trip();
					trip.setDrone(drone);
					trip.setTripNumber(tripNumber);
					trip.setTripCapacity(drone.getMaximumCapacity());
					trip.setRemainingCapacity(drone.getMaximumCapacity());
					// While Drone Trip Remaining Capacity > the smallest weight Package
					while ( !pakages.isEmpty() && (trip.getRemainingCapacity() > pakages.get(pakages.size()-1).getWeight()) ) {
						// For each Package
						for (Pakage pkg : pakages) {
							// If Package Weight < Drone Remaining Capacity
							if (pkg.getWeight() <= trip.getRemainingCapacity()) {
								// Add the Package to the Drone Trip
								trip.setDrone(drone);
								trip.getPackages().add(pkg);
								trip.setRemainingCapacity( trip.getRemainingCapacity() - pkg.getWeight() );
								pkg.setAllocated(true);
							} 
						}
						// Remove allocated Packages from the list of Packages for performance
						pakages.removeIf( p -> p.isAllocated() );
						pakages.trimToSize();
					}
					// Add the trip to the drone object
					drone.getTrips().add(trip);
				}					
			}
			// Increment Trip Number
			tripNumber++;
		}
		return drones;
	}
	
}
