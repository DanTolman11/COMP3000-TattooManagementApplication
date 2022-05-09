package com.tolman.view.tablesmodels.stock;

import com.tolman.model.Stock;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple POJO of the pending state of changes, managed by the the StockTablePendingState
 */
public class StockTablePendingState {

    private List<Stock> updates = new ArrayList<>();
    private List<Stock> inserts = new ArrayList<>();
    private List<Stock> deletes = new ArrayList<>();

    public List<Stock> getUpdates() {
        return updates;
    }

    public List<Stock> getInserts() {
        return inserts;
    }

    public List<Stock> getDeletes() {
        return deletes;
    }

    public void reset(){
        updates.clear();
        inserts.clear();
        deletes.clear();
    }
}
