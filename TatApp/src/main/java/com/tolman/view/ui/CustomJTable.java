package com.tolman.view.ui;

import com.tolman.view.tablesmodels.stock.StockTableModel;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CustomJTable extends JTable {

    private Map<Integer, Color> rowColor = new HashMap();

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
    {
        Component c = super.prepareRenderer(renderer, row, column);
        Color color = null;

        //do for other models
        if(this.getModel() instanceof StockTableModel){
            StockTableModel model = (StockTableModel) this.getModel();
            if(model.getPendingState().getInserts().contains(model.getStockItem(row))){
                color = Color.YELLOW;
            }
            if(model.getPendingState().getUpdates().contains(model.getStockItem(row))){
                color = Color.GREEN;
            }
        }

        if(rowColor.containsKey(row)) {
            color = rowColor.get( row );
        }
        c.setBackground(color == null ? getBackground() : color);
        return c;
    }

    public void setRowColor(int row, Color color)
    {
        rowColor.put(row, color);
    }

    public void clearColors(){
        rowColor.clear();
    }

}
