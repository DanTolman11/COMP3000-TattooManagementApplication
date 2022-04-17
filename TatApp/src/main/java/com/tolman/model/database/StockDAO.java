package com.tolman.model.database;

import com.tolman.model.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {
    private static StockDAO INSTANCE;
    private DBConnection connection = DBConnection.getInstance();
    private StockDAO(){

    }

    public List<Stock> getAllStock() throws SQLException {
        List<Stock> results = new ArrayList<>();
        String sql = String.format("select * from stock");
        Statement statement = connection.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            Stock stock = new Stock();
            stock.setName(rs.getString("name"));
            results.add(stock);
        }
        return results;
    }
    public static StockDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StockDAO();
        }

        return INSTANCE;
    }
}
