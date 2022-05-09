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
            stock.setId(rs.getInt("id"));
            stock.setName(rs.getString("name"));
            stock.setCategory(rs.getString("category"));
            stock.setAmount(rs.getInt("amount"));
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

    public boolean createStock(Stock stock) throws SQLException {
        String sql = String.format("INSERT INTO stock(name, category, amount) values('%s', '%s', '%d')", stock.getName(), stock.getCategory(), stock.getAmount());
        Statement statement = connection.getConnection().createStatement();
        statement.execute(sql);
        ResultSet rs =  statement.getGeneratedKeys();
        if (rs.next()){
            stock.setId(rs.getInt(1));
        }
        return true;
    }

    public boolean updateStock(Stock stock) throws SQLException {
        String sql = String.format("UPDATE stock set name='%s', category='%s', amount='%s' WHERE id='%d'", stock.getName(), stock.getCategory(), stock.getAmount(), stock.getId());
        Statement statement = connection.getConnection().createStatement();
        statement.execute(sql);
        return true;
    }

    public void deleteStock(Stock stock) throws SQLException {
        String sql = String.format("DELETE FROM stock WHERE id='%d'", stock.getId());
        Statement statement = connection.getConnection().createStatement();
        statement.execute(sql);
    }
}
