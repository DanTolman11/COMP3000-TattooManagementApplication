package com.tolman.model;

import com.tolman.model.database.StockDAO;

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

    public boolean createStock(Stock stock) {
        if(!validateStock(stock)){
            return false;
        }
        if(stock.getId() != 0){
            return false;
        }
        try {
            return stockDAO.createStock(stock);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean updateStock(Stock stock) {
        if(!validateStock(stock)){
            return false;
        }
        if(stock.getId() == 0){
            return false;
        }

        try {
            return stockDAO.updateStock(stock);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public List<Stock> getAllStock(){
        try{
            return stockDAO.getAllStock();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }

    public boolean validateStock(Stock stock) {
        return !stock.getName().equals("Fuck");
    }

    public boolean deleteStock(Stock stockItem) {
        try {
            stockDAO.deleteStock(stockItem);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
}
