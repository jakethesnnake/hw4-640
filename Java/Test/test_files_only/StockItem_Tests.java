package Test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


/**
 * Unit tests on public attributes
 * of stock item class
 */
class StockItem_Tests {
	
	/**
	 * Attribute values
	 */
	private static StockItem item;
	private static String name = "ItemName";
	private static double cost = 5.75;
	private static int count = 100;

	/**
	 * Initialization
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		item = new StockItem(name, cost, count);
	}

	/**
	 * Test: public 'name' attribute
	 */
	@Test
	final void testStockItemName() {
		assert(item.name == name);
	}
	
	/**
	 * Test: public 'cost' attribute
	 */
	@Test
	final void testStockItemCost() {
		assert(item.cost == cost);
	}
	
	/**
	 * Test: public 'count' attribute
	 */
	@Test
	final void testStockItemCount() {
		assert(item.count == count);
	}
}