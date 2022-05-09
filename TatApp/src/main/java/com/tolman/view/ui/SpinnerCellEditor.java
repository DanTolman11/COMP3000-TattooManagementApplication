package com.tolman.view.ui;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class SpinnerCellEditor extends AbstractCellEditor implements TableCellEditor {

    JSpinner spinner;

    public SpinnerCellEditor(JSpinner spinner) {
        this.spinner = spinner;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        try {
            spinner.setValue(value);
        }catch (Exception e){
            System.out.println("Value invalid selecting default");
        }
        return spinner;
    }

    @Override
    public Object getCellEditorValue() {
        return spinner.getValue();
    }
}
