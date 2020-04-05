/**
 * Stub for VendingMachine class
 * 
 * Class can be initialized without User I/O
 */
package Test;
public class VendingMachine_Stub extends VendingMachine {
	private static final String filename = "sampleStock";
	
	public VendingMachine_Stub() {
		super();
	}
	
	/**
	 * Returns filename instead of requesting User I/O
	 */
	@Override
	public String getFileName() {
		return filename;
	}
}