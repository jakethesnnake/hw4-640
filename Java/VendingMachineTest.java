
// package Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author jake
 *
 */
class VendingMachineTest {
	
	private static VendingMachine machine;
	private static String filepath = "sampleStock";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUp() throws Exception {
		machine = mock(VendingMachine.class);
		when(machine.getFileName()).thenReturn(filepath);
		// when(machine.insertMoney(amount)).thenReturn();
	}

	/**
	 * Test method for {@link VendingMachine#VendingMachine()}.
	 */
	@Test
	final void testVendingMachine() {
		String output = machine.getFileName();
		double bal = machine.balance;
		System.out.print(bal);
		
		assert(bal >= 0);
		assert(output == filepath);
	}

}
