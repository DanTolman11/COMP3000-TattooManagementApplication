package com.tolman.model;

import com.tolman.model.database.StockDAO;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockService {

    private static StockService INSTANCE;

    private StockDAO stockDAO = StockDAO.getInstance();

    private StockService(){

    }

    public static StockService getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new StockService();
        }
        return INSTANCE;
    }

    public List<Stock> getAllStock(DefaultListModel<Stock> stockListModel){
        try{
            return stockDAO.getAllStock();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }

}
