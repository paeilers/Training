/**
 * InputFileException is used to provide specific information about improperly formatted input or otherwise file read/access errors.
 */
package droneDelivery.config;

/**
 * @author paeilers
 *
 */
public class InputFileException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String errorCode="UNKNOWN";
	
	public InputFileException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return this.errorCode;
	}

}
