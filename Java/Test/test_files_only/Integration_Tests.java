package Test;

/**
 * Testing class: VendingMachineTest
 */
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Integration_Tests {	
	/*
	 * Vending Machine stub
	 */
	private VendingMachine vm;
	
	@BeforeEach
	final void setup() {
		vm = new VendingMachine_Stub();
	}
	
	/*
	 * Private: sets the System.in property to a byte array of the string parameter
	 * 
	 * returns nothing
	 */
	private void setScannerValue(String input) {
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
	}
	
	@Test
	final void insertMoneyAndSelectMostExpensive() {
		setScannerValue("1");
		vm.insertMoney();
		setScannerValue("1");
		vm.insertMoney();
		
		/* Purchase 2: Surge(2) @$2.0 */
		setScannerValue("2");
		vm.select();
		
		assert(vm.selection == 2);
		assert(vm.status == VendingMachine.State.DISPENSE);
	}
	
	@Test
	final void insertMoneyAndSelectLeastExpensive() {
		setScannerValue(".25");
		vm.insertMoney();
		setScannerValue(".25");
		vm.insertMoney();
		setScannerValue(".25");
		vm.insertMoney();
		
		/* Purchase 3: Fruit Snacks(7) @$0.75 */
		setScannerValue("3");
		vm.select();
		
		assert(vm.selection == 3);
		assert(vm.status == VendingMachine.State.DISPENSE);
	}
	
	@Test
	final void dispenseSelectionThenCheckBalance() {
		setScannerValue("1");
		vm.insertMoney();
		setScannerValue(".25");
		vm.insertMoney();
		setScannerValue("1");
		vm.select();
		vm.dispenseSelection();
		
		assertThat("the machine's balance is now zero", vm.balance == 0);
	}
	
	@Test
	final void dispenseSelection() {
		setScannerValue("1");
		vm.insertMoney();
		setScannerValue(".25");
		vm.insertMoney();
		
		/* 1: Diet Cola(5) @$1.25 */
		int selection = 1;
		StockItem item = vm.stock.get(selection);
		int old_amt = item.count;
		setScannerValue("1");
		vm.select();
		vm.dispenseSelection();
		
		assertThat("the item count is decremented", item.count == old_amt - 1);
	}
	
	@Test
	final void dispenseChangeAndResetBalance() {
		setScannerValue("1");
		vm.insertMoney();
		setScannerValue("1");
		vm.insertMoney();
		vm.dispenseChange();
		
		assert(vm.balance == 0);
		assert(vm.status == VendingMachine.State.START);
	}
	
	/*****
	 * BUG: user buys drink with insufficient funds successfully
	 *****/
	@Test
	final void insertMoneyAndSelectTooExpensive() {
		setScannerValue("1");
		vm.insertMoney();
		setScannerValue(".25");
		vm.insertMoney();
		
		/* Purchase Jerky: 4: Jerky(9) @$1.5 */
		setScannerValue("4");
		vm.select();
		
		assertThat("selection is set to -1 since funds are insufficient", vm.selection == -1);
	}
	
	/*****
	 * BUG: user inserts illegal coin value successfully
	 *****/
	@Test
	final void insertIllegalCoinValues() {
		setScannerValue("1");
		vm.insertMoney();
		setScannerValue(".03");
		vm.insertMoney();
		
		assertThat("coin value is not valid (.05, .10, .25, 1)", vm.balance != 1.03);
	}
	
	/******
	 * BUG: the machine should dispense change when the balance exceeds the cost
	 ******/
	@Test
	final void dispenseSelectionWithChange() {
		setScannerValue("1");
		vm.insertMoney();
		setScannerValue("1");
		vm.insertMoney();
		
		/* 1: Diet Cola(5) @$1.25 */
		setScannerValue("1");
		vm.select();
		vm.dispenseSelection();
		
		assertThat("the status should be State.CHANGE when change exists", vm.status == VendingMachine.State.CHANGE);
	}
}
