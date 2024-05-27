package exportation.model.da;

import exportation.model.entity.Trade;
import lombok.extern.log4j.Log4j;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class TradeDa implements AutoCloseable, CRUD<Trade> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public TradeDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    //save
    @Override
    public Trade save(Trade trade) throws Exception {
        trade.setId(ConnectionProvider.getConnectionProvider().getNextId("TRADE_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO TRADE (TRADE_ID,TRADE_STATUS,CORRESPONDENCES,TRADE_CONTRACT,TRADE_AGREEMENT,TRADE_INVOICE) VALUES (?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, trade.getId());
        preparedStatement.setString(2, trade.getStatus());
        preparedStatement.setString(3, trade.getCorrespondences());
        preparedStatement.setString(4, trade.getContract());
        preparedStatement.setString(5, trade.getAgreement());
        preparedStatement.setString(6, trade.getInvoice());
        preparedStatement.execute();
        return trade;
    }

    //Edit
    @Override
    public Trade edit(Trade trade) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE TRADE SET TRADE_STATUS=?, CORRESPONDENCES=?, TRADE_CONTRACT=?, ATRADE_GREEMENT=?, TRADE_INVOICE=?, WHERE TRADE_ID=?"
        );
        preparedStatement.setInt(1, trade.getId());
        preparedStatement.setString(2, trade.getStatus());
        preparedStatement.setString(3, trade.getCorrespondences());
        preparedStatement.setString(4, trade.getContract());
        preparedStatement.setString(5, trade.getAgreement());
        preparedStatement.setString(6, trade.getInvoice());
        preparedStatement.execute();
        return trade;
    }

    //Remove
    @Override
    public Trade remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM TRADE WHERE TRADE_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<Trade> findAll() throws Exception {
        List<Trade> tradeList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM TRADE ORDER BY TRADE_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Trade trade = Trade
                    .builder()
                    .id(resultSet.getInt("TRADE_ID"))
                    .status(resultSet.getString("TRADE_STATUS"))
                    .correspondences(resultSet.getString("CORRESPONDENCES"))
                    .contract(resultSet.getString("TRADE_CONTRACT"))
                    .agreement(resultSet.getString("TRADE_AGREEMENT"))
                    .invoice(resultSet.getString("TRADE_INVOICE"))
                    .build();

            tradeList.add(trade);
        }

        return tradeList;
    }

    //FindById
    @Override
    public Trade findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM TRADE WHERE TRADE_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Trade trade = null;
        if (resultSet.next()) {
            trade = Trade
                    .builder()
                    .id(resultSet.getInt("TRADE_ID"))
                    .status(resultSet.getString("TRADE_STATUS"))
                    .correspondences(resultSet.getString("CORRESPONDENCES"))
                    .contract(resultSet.getString("TRADE_CONTRACT"))
                    .agreement(resultSet.getString("TRADE_AGREEMENT"))
                    .invoice(resultSet.getString("TRADE_INVOICE"))
                    .build();
        }
        return trade;
    }



    //Close
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
//...