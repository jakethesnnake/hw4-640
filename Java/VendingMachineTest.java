/**
 * Testing class: VendingMachineTest
 */

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

// https://www.softwaretestinghelp.com/mock-private-static-void-methods-mockito/

// @RunWith(PowerMockRunner.class)
// @PrepareForTest(VendingMachine.class)
public class VendingMachineTest {
	/*
	 * Initialization
	 * */
	
	private VendingMachine vm;
	
	@BeforeEach
	final void setup() {
		vm = new VendingMachineStub();
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
	 * Select()
	 * */
	
	
	@Test
	final void setinputOnSelect() {
		String input = "r" ; // + System.lineSeparator();
	    InputStream in = new ByteArrayInputStream(input.getBytes());	    
	    System.setIn(in);
	    
	    vm.select();
	    
	    assertThat("state changes to Stat.CHANGE (input = r)", vm.status == VendingMachine.State.CHANGE);
	}
	
	@Test
	final void setinputOnSelect2() {
		String input = "r" ;
	    InputStream in = new ByteArrayInputStream(input.getBytes());	    
	    System.setIn(in);
	    
	    vm.select();
	    
	    assertThat("state changes to State.CHANGE (input = r)", vm.selection == -1);
	}
	
	/*
	 * InsertMoney();
	 * 
	 * */
	
	@Test
	final void insertMoneyLegal() {
		
	}
}
