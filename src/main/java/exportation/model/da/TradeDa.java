package exportation.model.da;

import exportation.model.entity.Person;
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
                "INSERT INTO TRADE (TRADE_ID, TRADE_CLIENT, TRADE_STATUS,TRADE_CORRESPONDENCES,TRADE_CONTRACT,TRADE_AGREEMENT) VALUES (?,?,?,?,?,?,)"
        );
        preparedStatement.setInt(1, trade.getId());
        preparedStatement.setString(2,trade.getPerson().getFamily());
        preparedStatement.setString(3, trade.getStatus());
        preparedStatement.setString(4, trade.getCorrespondences());
        preparedStatement.setString(5, trade.getContract());
        preparedStatement.setString(6, trade.getAgreement());
        preparedStatement.execute();
        return trade;
    }

    //Edit
    @Override
    public Trade edit(Trade trade) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE TRADE SET TRADE_STATUS=?,TRADE_CLIENT=? TRADE_CORRESPONDENCES=?, TRADE_CONTRACT=?, TRADE_AGREEMENT=?, WHERE TRADE_ID=?"
        );
        preparedStatement.setString(1,trade.getPerson().getFamily());
        preparedStatement.setString(2, trade.getStatus());
        preparedStatement.setString(3, trade.getCorrespondences());
        preparedStatement.setString(4, trade.getContract());
        preparedStatement.setString(5, trade.getAgreement());
        preparedStatement.setInt(6, trade.getId());
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
                    .id(resultSet.getInt("ID"))
                    .person(Person.builder().family(resultSet.getString("client")).build())
                    .status(resultSet.getString("STATUS"))
                    .correspondences(resultSet.getString("CORRESPONDENCES"))
                    .contract(resultSet.getString("CONTRACT"))
                    .agreement(resultSet.getString("AGREEMENT"))
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
                    .id(resultSet.getInt("ID"))
                    .person(Person.builder().family(resultSet.getString("client")).build())
                    .status(resultSet.getString("STATUS"))
                    .correspondences(resultSet.getString("CORRESPONDENCES"))
                    .contract(resultSet.getString("CONTRACT"))
                    .agreement(resultSet.getString("AGREEMENT"))
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
