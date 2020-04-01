/**
 * Stub for VendingMachine class
 * 
 * Class can be initialized without User I/O
 */
import java.util.Scanner;
public class VendingMachineStub extends VendingMachine {
	private static final String filename = "sampleStock";
	
	public VendingMachineStub() {
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