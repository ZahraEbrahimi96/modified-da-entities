package exportation.model.da;
import exportation.model.entity.enums.Brand;
import exportation.model.entity.enums.Type;
import lombok.extern.log4j.Log4j;
import exportation.model.entity.Item;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Log4j
public class ItemDa implements AutoCloseable, CRUD<Item> {
    private final Connection connection = null;
    private PreparedStatement preparedStatement;

    public ItemDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    //SAVE
    @Override
    public Item save(Item item) throws Exception {
        Item.setItemId(ConnectionProvider.getConnectionProvider().getNextId("ITEM_SEQ"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO ITEM (ITEM_ID, NAME, MODEL, TYPE ,BRAND ,DIMENSION_OF_UNIT ,DIMENSION_OF_PALLET ,WEIGHT_OF_UNIT ,WEIGHT_OF_PALLET, PALLET_CAPACITY, COST) VALUES (?,?,?,?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, item.getItemId());
        preparedStatement.setString(2, item.getName());
        preparedStatement.setString(3, item.getModel());
        preparedStatement.setString(5, item.getType().name());
        preparedStatement.setString(5, item.getBrand().name());
        preparedStatement.setString(6, item.getDimensionOfUnite());
        preparedStatement.setString(7, item.getDimensionOfPallet());
        preparedStatement.setFloat(8, item.getWeightOfUnit());
        preparedStatement.setFloat(9, item.getWeightOfPallet());
        preparedStatement.setInt(10,item.getPalletCapacity());
        preparedStatement.setFloat(11, item.getCost());
        preparedStatement.execute();
        return item;
    }

    //EDIT
    @Override
    public Item edit(Item item) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE ITEM SET NAME=?, MODEL=?, TYPE=? ,BRAND=? ,DIMENSION_OF_UNIT=? ,DIMENSION_OF_PALLET=? ,WEIGHT_OF_UNIT=? ,WEIGHT_OF_PALLET=?, PALLET_CAPACITY=?, COST=? WHERE ITEM_ID=?"
        );
        preparedStatement.setString(1, item.getName());
        preparedStatement.setString(2, item.getModel());
        preparedStatement.setString(3, item.getType().name());
        preparedStatement.setString(4, item.getBrand().name());
        preparedStatement.setString(5, item.getDimensionOfUnite());
        preparedStatement.setString(6, item.getDimensionOfPallet());
        preparedStatement.setFloat(7, item.getWeightOfUnit());
        preparedStatement.setFloat(8, item.getWeightOfPallet());
        preparedStatement.setInt(9,item.getPalletCapacity());
        preparedStatement.setFloat(10, item.getCost());
        preparedStatement.setInt(11, item.getItemId());
        preparedStatement.execute();
        return item;
    }

    //REMOVE
    @Override
    public Item remove(int itemId) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM ITEM WHERE ITEM_ID=?"
        );
        preparedStatement.setInt(1, itemId);
        preparedStatement.execute();
        return null;
    }

    //FINDALL
    @Override
    public List<Item> findAll() throws Exception {
        List<Item> itemList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM ITEM ORDER BY ITEM_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Item item = Item
                    .builder()
                    .itemId(resultSet.getInt("item_ID"))
                    .name(resultSet.getString("NAME"))
                    .model(resultSet.getString("MODEL"))
                    .type(Type.valueOf(resultSet.getString("TYPE")))
                    .brand(Brand.valueOf(resultSet.getString("BRAND")))
                    .dimensionOfUnite(resultSet.getString("DIMENSION_OF_UNIT"))
                    .dimensionOfPallet(resultSet.getString("DIMENSION_OF_PALLET"))
                    .weightOfUnit(Float.parseFloat(String.valueOf(resultSet.getFloat("WEIGHT_OF_UNIT"))))
                    .weightOfPallet(Float.parseFloat(String.valueOf(resultSet.getFloat("DIMENSION_OF_PALLET"))))
                    .palletCapacity(resultSet.getInt("PALLET_CAPACITY"))
                    .cost(Float.parseFloat(String.valueOf(resultSet.getFloat("COST"))))
                    .build();

            itemList.add(item);
        }

        return itemList;
    }


    //FIND BY ID
    @Override
    public Item findById(int itemId) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM ITEM WHERE ITEM_ID=?");
        preparedStatement.setInt(1, itemId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Item item = null;
        if (resultSet.next()) {
            item = Item
                    .builder()
                    .itemId(resultSet.getInt("item_ID"))
                    .name(resultSet.getString("NAME"))
                    .model(resultSet.getString("MODEL"))
                    .type(Type.valueOf(resultSet.getString("TYPE")))
                    .brand(Brand.valueOf(resultSet.getString("BRAND")))
                    .dimensionOfUnite(resultSet.getString("DIMENSION_OF_UNIT"))
                    .dimensionOfPallet(resultSet.getString("DIMENSION_OF_PALLET"))
                    .weightOfUnit(Float.parseFloat(String.valueOf(resultSet.getFloat("WEIGHT_OF_UNIT"))))
                    .weightOfPallet(Float.parseFloat(String.valueOf(resultSet.getFloat("DIMENSION_OF_PALLET"))))
                    .palletCapacity(resultSet.getInt("PALLET_CAPACITY"))
                    .cost(Float.parseFloat(String.valueOf(resultSet.getFloat("COST"))))
                    .build();
        }
        return item;
    }

    //FIND BY NAME
    public List<Item> findByName(String name) throws Exception {
        List<Item> itemList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM ITEM WHERE NAME LIKE? ORDER BY ITEM_ID");
        preparedStatement.setString(1, name + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Item item = Item
                    .builder()
                    .itemId(resultSet.getInt("item_ID"))
                    .name(resultSet.getString("NAME"))
                    .model(resultSet.getString("MODEL"))
                    .type(Type.valueOf(resultSet.getString("TYPE")))
                    .brand(Brand.valueOf(resultSet.getString("BRAND")))
                    .dimensionOfUnite(resultSet.getString("DIMENSION_OF_UNIT"))
                    .dimensionOfPallet(resultSet.getString("DIMENSION_OF_PALLET"))
                    .weightOfUnit(Float.parseFloat(String.valueOf(resultSet.getFloat("WEIGHT_OF_UNIT"))))
                    .weightOfPallet(Float.parseFloat(String.valueOf(resultSet.getFloat("DIMENSION_OF_PALLET"))))
                    .palletCapacity(resultSet.getInt("PALLET_CAPACITY"))
                    .cost(Float.parseFloat(String.valueOf(resultSet.getFloat("COST"))))
                    .build();

            itemList.add(item);
        }

        return itemList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}


