package com.tolman.view.tablesmodels;

import com.tolman.view.tablesmodels.stock.StockTableModel;
import com.tolman.model.Stock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class StockTableModelTest {

    @Test
    public void testProof(){
        Stock stockA = new Stock();
        Stock stockB = new Stock();
        Stock stockC = new Stock();

        StockTableModel stockTableModel = new StockTableModel(new ArrayList<>(Arrays.asList(stockA, stockB, stockC)));

        stockTableModel.addTableModelListener(e -> {
            Stock deletedItem = stockTableModel.getRowsBetween(e.getFirstRow(), e.getLastRow()).get(0);
            assert deletedItem.equals(stockB);
        });
        stockTableModel.deleteStockItem(stockB);
    }

}