package com.tolman.view.tablesmodels.stock;

import com.tolman.model.Stock;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Table model listener for changes within the {@link StockTableModel}
 * Handles pending state of changes which have happend {@see StockTablePendingState}
 */
public class StockTableModelChangeListener implements TableModelListener {

    private StockTablePendingState stockTablePendingState;
    private StockTableModel stockTableModel;

    public StockTableModelChangeListener(StockTableModel stockTableModel) {
        this.stockTableModel = stockTableModel;
        stockTablePendingState = new StockTablePendingState();
    }

    public StockTablePendingState getStockTablePendingState() {
        return stockTablePendingState;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        List<Stock> stockItemsAffected = new ArrayList<>();
        switch (e.getType()){
            case TableModelEvent.INSERT:
                stockTablePendingState.getInserts().addAll(stockTableModel.getRowsBetween(e.getFirstRow(), e.getLastRow()));
                break;
            case TableModelEvent.UPDATE:
                stockItemsAffected = stockTableModel.getRowsBetween(e.getFirstRow(), e.getLastRow());

                // filter out items that have been inserted and not yet saved
                List<Stock> actualUpdates = new ArrayList<>();
                for (Stock stockItem : stockItemsAffected) {
                    if (!stockTablePendingState.getInserts().contains(stockItem)) {
                        actualUpdates.add(stockItem);
                    }
                }
                stockTablePendingState.getUpdates().addAll(actualUpdates);
                break;
            case TableModelEvent.DELETE:
                stockItemsAffected = stockTableModel.getRowsBetween(e.getFirstRow(), e.getLastRow());
                // if we're deleting remove any save and inserts relating to this item
                stockTablePendingState.getInserts().removeAll(stockItemsAffected);
                stockTablePendingState.getUpdates().removeAll(stockItemsAffected);
                stockTablePendingState.getDeletes().addAll(stockItemsAffected);
                break;
        }
    }
}
