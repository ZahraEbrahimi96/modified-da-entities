package exportation.model.da;

import exportation.model.entity.Item;
import lombok.extern.log4j.Log4j;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class ItemDa implements AutoCloseable, CRUD<Item> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public ItemDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    //save
    @Override
    public Item save(Item item) throws Exception {
        item.setId(ConnectionProvider.getConnectionProvider().getNextId("ITEM_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO ITEM (ITEM_ID,ITEM_NAME,ITEM_MODEL,ITEM_DIMENSIONOFUNITE,ITEM_DIMENSIONOFPALLET,ITEM_PALLETCAPACITY,ITEM_COST,ITEM_HS_CODE,ITEM_WEIGHTOFUNIT,ITEM_WEIGHTOFPALLET) VALUES (?,?,?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, item.getId());
        preparedStatement.setString(2, item.getName());
        preparedStatement.setString(3, item.getModel());
        preparedStatement.setString(4, item.getDimensionOfUnite());
        preparedStatement.setString(5, item.getDimensionOfPallet());
        preparedStatement.setInt(6, item.getPalletCapacity());
        preparedStatement.setDouble(7, item.getCost());
        preparedStatement.setLong(8, item.getHs_Code());
        preparedStatement.setFloat(9, item.getWeightOfUnit());
        preparedStatement.setFloat(10, item.getWeightOfPallet());
        preparedStatement.execute();
        return item;
    }

    //Edit
    @Override
    public Item edit(Item item) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE ITEM SET ITEM_NAME=?, ITEM_MODEL=?, ITEM_DIMENSIONOFUNITE=?,ITEM_DIMENSIONOFPALLET=?,ITEM_PALLETCAPACITY=?,ITEM_COST=? ,ITEM_HS_CODE=?,ITEM_WEIGHTOFUNIT=?,ITEM_WEIGHTOFPALLET=?, WHERE ITEM_ID=?"
        );
        preparedStatement.setInt(1, item.getId());
        preparedStatement.setString(2, item.getName());
        preparedStatement.setString(3, item.getModel());
        preparedStatement.setString(4, item.getDimensionOfUnite());
        preparedStatement.setString(5, item.getDimensionOfPallet());
        preparedStatement.setInt(6, item.getPalletCapacity());
        preparedStatement.setDouble(7, item.getCost());
        preparedStatement.setLong(8, item.getHs_Code());
        preparedStatement.setFloat(9, item.getWeightOfUnit());
        preparedStatement.setFloat(10, item.getWeightOfPallet());
        preparedStatement.execute();
        return item;
    }

    //Remove
    @Override
    public Item remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM ITEM WHERE ITEM_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<Item> findAll() throws Exception {
        List<Item> itemList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM ITEM ORDER BY ITEM_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Item item = Item
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .model(resultSet.getString("MODEL"))
                    .dimensionOfUnite(resultSet.getString("DOU"))
                    .dimensionOfPallet(resultSet.getString("DOP"))
                    .palletCapacity(resultSet.getInt("PALLETCAPACITY"))
                    .cost(resultSet.getFloat("COST"))
                    .weightOfUnit(resultSet.getFloat("WOU"))
                    .weightOfPallet(resultSet.getFloat("WOP"))
                    .build();

            itemList.add(item);
        }

        return itemList;
    }

    //FindById
    @Override
    public Item findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM ITEM WHERE ITEM_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Item item = null;
        if (resultSet.next()) {
            item = Item
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .model(resultSet.getString("MODEL"))
                    .dimensionOfUnite(resultSet.getString("DOU"))
                    .dimensionOfPallet(resultSet.getString("DOP"))
                    .palletCapacity(resultSet.getInt("PALLETCAPACITY"))
                    .cost(resultSet.getFloat("COST"))
                    .weightOfUnit(resultSet.getFloat("WOU"))
                    .weightOfPallet(resultSet.getFloat("WOP"))
                    .build();
        }
        return item;
    }

    //Close
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
