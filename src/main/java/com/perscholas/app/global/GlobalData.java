package com.perscholas.app.global;

import java.util.HashMap;
import java.util.Map;

public class GlobalData {

public static Map<Integer, Integer> productInStock;

static  {
    productInStock = new HashMap<Integer, Integer>();
    productInStock.put(1,15);
    productInStock.put(2,15);
    productInStock.put(3,15);
    productInStock.put(4,15);
    productInStock.put(5,15);
    productInStock.put(6,15);
    productInStock.put(7,15);
    productInStock.put(8,15);
    productInStock.put(9,15);
    productInStock.put(10,15);
}

public enum CategoryIdentifier{
    INDOOR_PLANTS(1),
    OUTDOOR_PLANTS(2),
    HERBS(3),
    POTS_AND_TOOLS(4),
    SALE(5);

    public int category_identifier;

    CategoryIdentifier(int category_identifier){
        this.category_identifier = category_identifier;
    }

}

}
