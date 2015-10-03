/**
 * ClientResource accepts the flat file input and presents the output (to the console) for the DroneDelivery application.
 */
package droneDelivery.boundaries;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import droneDelivery.config.InputFileException;
import droneDelivery.entities.Drone;
import droneDelivery.entities.Pakage;
import droneDelivery.entities.Trip;

/**
 * @author paeilers
 * 
 */
public class ClientResource {
	
	Logger logger = LoggerFactory.getLogger(ClientResource.class);
	
	public ClientResource() {}
	
	/**
	 * Assumes the first line in the file represents a list of Drones and the remaining lines 
	 * each represent a Package.
	 * Assumes a structured Package line as follows [Location Name], [Package Weight]
	 * @param inputFile
	 * @return List<Drone> A list of the Drone objects parsed from the inputFile
	 * @throws InputFileException Indicates there was a problem parsing the inputFile
	 */
	public List<Drone> parseDrones(Path inputFile) throws InputFileException {
		List<Drone> drones = new ArrayList<Drone>();
		String line = null;
		// Check for file existence
		if (Files.isReadable(inputFile)) {
			// Process the first line as the set of Drone objects
			Charset charset = Charset.forName("US-ASCII");
			Scanner scanner = null;
			try (BufferedReader reader = Files.newBufferedReader(inputFile, charset)) {
				line = reader.readLine();
				if ( null != line) {
					// Process line to create Drones
					Drone drone = null;
					scanner = new Scanner(line);
					scanner.useDelimiter(", ");
					while (scanner.hasNext()) {
						drone = new Drone();
						String name = scanner.next();
						String maximumWeight = scanner.next();
						logger.debug("Drone name is: " + name + " and weight is: " + maximumWeight);
						drone.setName(name);
						drone.setMaximumCapacity(Integer.valueOf(maximumWeight));
						drones.add(drone);
					}
					scanner.close();
				} else {
					logger.error("ERROR: The input file contains a blank first line and is incorrectly formatted.");
					throw new InputFileException("Input file is not formatted correctly", "FORMAT_ERROR");
				}
			} catch (IOException exc) {
				logger.error("ERROR: Problem while reading input file", exc);
				throw new InputFileException("Unable to read the input file", "READ_ERROR");
			}
			finally {
				if (null != scanner) scanner.close();
			}
		} else {
			logger.error("ERROR: The input file either does not exist or cannot be read. Please provide full path.");
			throw new InputFileException("Unable to access the input file; check file path and name", "ACCESS_ERROR");
		}
		return drones;
	}
	
	/**
	 * Assumes the first line in the file represents a list of Drones and the remaining lines 
	 * each represent a Package.
	 * Assumes a structured Package line as follows [Location Name], [Package Weight]
	 * @param inputFile The file provided with lists of the Drones and the Packages
	 * @return pakages List of packages extracted from the input file
	 * @throws InputFile Exception InputFileException Indicates there was a problem parsing the inputFile
	 */
	public ArrayList<Pakage> parsePackages(Path inputFile) throws InputFileException {
		ArrayList<Pakage> pakages = new ArrayList<Pakage>();
		String line = null;
		int i = 1;
		// Check for file existence
		if (Files.isReadable(inputFile)) {
			// Process all but the first line as the set of Package objects
			Charset charset = Charset.forName("US-ASCII");
			Scanner scanner = null;
			try (BufferedReader reader = Files.newBufferedReader(inputFile, charset)) {
				while ((line = reader.readLine()) != null) {
					if ( i == 1) {
						// Skip the first line	
						i++;
					} else {
						// Process remaining files to create Packages
						// Parse every line to extract the Package Location and Weight
						Pakage pakage = new Pakage();
						scanner = new Scanner(line);
						scanner.useDelimiter(", ");
						if (scanner.hasNext()) {
							String location = scanner.next();
							String weight = scanner.next();
							logger.debug("Package location is: " + location + " and weight is: " + weight);
							pakage.setLocationName(location);
							pakage.setWeight(Integer.valueOf(weight));
						} else {
							logger.error("ERROR: Empty or invalid line in the file; unable to process.");
							throw new InputFileException("Input file is not formatted correctly", "FORMAT_ERROR");
						}
						scanner.close();
						pakages.add(pakage);
					}
				}
			} catch (IOException exc) {
				logger.error("ERROR: Problem while reading input file", exc);
				throw new InputFileException("Unable to read the input file", "READ_ERROR");
			} finally {
				if (null != scanner) scanner.close();
			}
		} else {
			logger.error("ERROR: The input file either does not exist or cannot be read. Please provide full path.");
			throw new InputFileException("Unable to access the input file; check file path and name", "ACCESS_ERROR");
		}
		return pakages;
	}
	
	/**
	 * Print to the console in order by Drone, by Trip the Drone Name, Trip Number, and Location Name
	 * @param trips The set of Trips optimized for minimal runs
	 */
	public void printSchedule(List<Drone> drones) {
		// Sort drones in order by Name 
		Collections.sort(drones);
		// Loop through Trips, sorted by Drone and then by Trip
		for (Drone drone : drones) {
			System.out.println("\n" + drone.getName());
			List<Trip> trips = drone.getTrips();
			for (Trip trip : trips) {
				System.out.println("Trip " + trip.getTripNumber());
				List<Pakage> packages = trip.getPackages();
				String locations = "Location ";
				for (Pakage pkg : packages) {
					locations += pkg.getLocationName() + ", ";
				}
				// Strip out trailing ", "
				System.out.println(locations.substring(0, locations.length()-2));
			}
		}
	}
}
