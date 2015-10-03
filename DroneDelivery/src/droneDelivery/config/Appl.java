/**
 * The Drone Delvery application is designed to take a flat file containing the following information:
 * 	1) A list of Drones containing the Drone Name and the Maximum Weight Capacity.
 * 	2) A list of Packages containing the Location Name and Package Weight.
 * The application must allocate delivery of the Packages across the Drones with the goal being to minimize
 * the number of trips per Drone.
 * Assumptions:
 * 1) There can only be one Package per Location
 * 2) No single Package will exceed the maximum weight capacity of the largest Drone
 */
package droneDelivery.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import droneDelivery.boundaries.ClientResource;
import droneDelivery.controls.DroneSchedulerImpl;
import droneDelivery.entities.Drone;
import droneDelivery.entities.Pakage;

/**
 * @author paeilers
 *
 */
public class Appl {
	
	private static ClientResource clientResource;
	
	private static DroneSchedulerImpl scheduler;

	/**
	 * @param args	The flat file containing a list of Drones and a list of Packages
	 * @return tripSchedule	The Package assignments by Drone, by Trip
	 */
	public static void main(String[] args) {
		
		clientResource = new ClientResource();
		scheduler = new DroneSchedulerImpl();
		
		// Read in the input file to create the Drone and Package objects
		Path inputFile = Paths.get(args[0]);		
		List<Drone> drones = null;
		ArrayList<Pakage> packages = null;
		
		if (null != inputFile) {
			try {
				// Create lists of Drones and Packages based on the input file provided
				drones = clientResource.parseDrones(inputFile);
				packages = clientResource.parsePackages(inputFile);
				// Create Trip assignments
				scheduler.createTrips(drones, packages);			
				// Print out to the console by Drone, by Trip, the assigned Packages
				clientResource.printSchedule(drones);			
			} catch (InputFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		} else {
			System.out.println("Please provide an input file.");
		}
		
	}

}
