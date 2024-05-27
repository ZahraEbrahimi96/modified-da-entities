package exportation.model.da;

import exportation.model.entity.*;
import exportation.model.entity.Transportation;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransportationDa implements AutoCloseable, CRUD<Transportation> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public TransportationDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    //save
    @Override
    public Transportation save(Transportation transportation) throws Exception {
        transportation.setId(ConnectionProvider.getConnectionProvider().getNextId("TRADE_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO TRADE (ID,DIRECTION,ORIGIN,fREIGHT,ITEM,TRANSPORTTYPE) VALUES (?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, transportation.getId());
        preparedStatement.setString(2, transportation.getDirection());
        preparedStatement.setString(3, transportation.getOrigin());
        preparedStatement.setDouble(4, transportation.getFreight());
        preparedStatement.setFloat(5, transportation.getItem().getCost());
        preparedStatement.execute();
        return transportation;
    }

    //Edit
    @Override
    public Transportation edit(Transportation transportation) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE TRANSPORTATION SET DIRECTION=?, ORIGIN=?, fREIGHT=?, ITEM=?, TRANSPORTTYPE=?, WHERE ID=?"
        );
        preparedStatement.setInt(1, transportation.getId());
        preparedStatement.setString(2, transportation.getDirection());
        preparedStatement.setString(3, transportation.getOrigin());
        preparedStatement.setDouble(4, transportation.getFreight());
        preparedStatement.setFloat(5, transportation.getItem().getCost());
        preparedStatement.execute();
        return transportation;
    }

    //Remove
    @Override
    public Transportation remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM TRANSPORTATION WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<Transportation> findAll() throws Exception {
        List<Transportation> transportationList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM TRANSPORTATION ORDER BY ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Transportation transportation = Transportation
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .direction(resultSet.getString("DIRECTION"))
                    .origin(resultSet.getString("ORIGIN"))
                    .freight(resultSet.getFloat("FREIGHT"))
                    .item(resultSet.getObject("ITEM", Item.class))
                    .build();

            transportationList.add(transportation);
        }

        return transportationList;
    }

    //FindById
    @Override
    public Transportation findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM TRANSPORTATION WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Transportation transportation = null;
        if (resultSet.next()) {
            transportation = Transportation
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .direction(resultSet.getString("DIRECTION"))
                    .origin(resultSet.getString("ORIGIN"))
                    .freight(resultSet.getFloat("FREIGHT"))
                    .item(resultSet.getObject("ITEM", Item.class))
                    .build();
        }
        return transportation;
    }

    //Close
    @Override
    public void close() throws Exception {

    }
}
