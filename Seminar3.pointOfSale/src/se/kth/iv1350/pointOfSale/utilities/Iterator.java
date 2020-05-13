package se.kth.iv1350.pointOfSale.utilities;

import se.kth.iv1350.pointOfSale.integration.ItemDTO;

/**
 * The iterator interface.
 * @author Yassin
 */
public interface Iterator {
	public boolean hasNext();
	public ItemDTO next();
}
