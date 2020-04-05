package Test;

/**
 * Testing class: VendingMachineTest
 */
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VendingMachine_Tests {
	
	/*
	 * Vending Machine stub
	 * */
	private VendingMachine vm;
	
	/*
	 * Private: sets the System.in property to a byte array of the string parameter
	 * 
	 * returns nothing
	 * */
	private void setScannerValue(String input) {
		InputStream in = new ByteArrayInputStream(input.getBytes());	
		System.setIn(in);	    
	}
	
	@BeforeEach
	final void setup() {
		vm = new VendingMachine_Stub();
	}
	
	@Test
	final void testInitialBalance() {
		assertThat("balance is initially zero", vm.balance == 0);
	}
	@Test
	final void testInitialStockSize() {
		assertThat("stock has 5 initial items", vm.stock.size() == 5);
	}
	@Test
	final void testInitialStatus() {
		assertThat("initial status (VendingMachine.State.START)", vm.status == VendingMachine.State.START);
	}
	@Test
	final void testInitialMaxCost() {
		assertThat("max cost is positive", vm.maxCost > 0);
	}
	@Test
	final void testInitialSelectionValue() {
		assertThat("selection is initially 0", vm.selection == 0);
	}
	
	/*
	 * select() ~ instance method
	 */
	@Test
	final void onSelectGetRefund() {
		setScannerValue("r");
	    vm.select();
	    
	    assertThat("state changes to Stat.CHANGE (input = r)", vm.status == VendingMachine.State.CHANGE);
	}
	@Test
	final void onSelectInvalidInput() {
		setScannerValue("q");
	    vm.select();
	    
	    assertThat("state changes to Stat.CHANGE (input = r)", vm.selection == -1);
	}
	
	/*
	 * insertMoney() ~ instance method
	 */
	@Test
	final void insertMoneyOnce() {
		setScannerValue("1");
		vm.insertMoney();
		
		assertThat("balance is 1 now", vm.balance == 1);
	}
	@Test
	final void insertMoneyTwice() {
		setScannerValue("1");
		vm.insertMoney();	
		setScannerValue(".25");
		vm.insertMoney();
		
		assertThat("balance is 2 now", vm.balance == 1.25);
	}
	
	/*
	 * start() ~ instance method
	 */
	@Test
	final void startSelectRestock() {
		setScannerValue("r");
		vm.start();
		
		assertThat("status is State.STOCK", vm.status == VendingMachine.State.STOCK);
	}
	@Test
	final void startSelectExit() {
		setScannerValue("e");
		vm.start();
		
		assertThat("status is State.OFF", vm.status == VendingMachine.State.OFF);
	}
	@Test
	final void startSelectAnythingElse() {
		setScannerValue("q");
		vm.start();
		
		assertThat("status is State.INSERT", vm.status == VendingMachine.State.INSERT);
	}
	/******
	 * BUG: the program raises an exception when the character only clicks enter here
	 ******/
	@Test
	final void startSelectNewLine() {
		setScannerValue(System.lineSeparator());
		vm.start();
		
		assertThat("status is State.INSERT", vm.status == VendingMachine.State.INSERT);
	}
}
