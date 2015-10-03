package droneDelivery.controls;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import droneDelivery.boundaries.ClientResource;
import droneDelivery.config.InputFileException;
import droneDelivery.entities.Drone;
import droneDelivery.entities.Pakage;

public class TestDroneSchedulerImpl {
	
	private final Path testFile = Paths.get("C:/Temp/DroneDeliveryInput.txt");
	private int expectedTotalTrips = 4; // Need to create the test file and determine what this should be
	private int expectedTotalDrones = 3; // Need to create the test file and determine what this should be
	private int expectedTotalPackages = 27; // Need to create the test file and determine what this should be
	private List<Drone> dronesList; // Need to create test file and determine what the Drones will be
	private ArrayList<Pakage> packagesList; // Need to create test file and determine how many Packages there will be
	
	private static ClientResource clientResource = new ClientResource();
	private static DroneScheduler scheduler = new DroneSchedulerImpl();


	@Test
	public void testAllocatePackages() {
		int numOfDrones = 0;
		int numOfPackages = 0;
		int numOfTrips = 0;
		try {
			dronesList = clientResource.parseDrones(testFile);
			packagesList = clientResource.parsePackages(testFile);
			numOfDrones = dronesList.size();
			numOfPackages = packagesList.size();
			
			scheduler.createTrips(dronesList, packagesList);
			
			// Calculate total number of resulting trips
			for (Drone drone : dronesList) {
				numOfTrips += drone.getTrips().size();
			}
			
			clientResource.printSchedule(dronesList); 
			
		} catch (InputFileException exc) {
			exc.printStackTrace();			
		}
		
		assertTrue( (numOfDrones == expectedTotalDrones) &&
					(numOfPackages == expectedTotalPackages) &&
					(numOfTrips == expectedTotalTrips));

	}

}
