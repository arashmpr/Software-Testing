package exceptions;

import static defines.Errors.COMMODITY_IS_NOT_IN_THE_BUY_LIST;

public class CommodityIsNotInBuyList extends Exception {
    public CommodityIsNotInBuyList(String s) {
        super(COMMODITY_IS_NOT_IN_THE_BUY_LIST);
    }
}
