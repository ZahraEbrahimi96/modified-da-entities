package exportation.model.da;
import exportation.model.entity.enums.Brand;
import exportation.model.entity.enums.ItemType;
import lombok.extern.log4j.Log4j;
import exportation.model.entity.Item;
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
                "INSERT INTO ITEM (ITEM_ID, HS_CODE, ITEM_NAME, ITEM_MODEL, ITEM_TYPE ,ITEM_BRAND ,DIMENSION_OF_UNIT ,DIMENSION_OF_PALLET ,WEIGHT_OF_UNIT ,WEIGHT_OF_PALLET, PALLET_CAPACITY, ITEM_COST) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, item.getId());
        preparedStatement.setLong(2,item.getHsCode());
        preparedStatement.setString(3, item.getName());
        preparedStatement.setString(4, item.getModel());
        preparedStatement.setString(5, item.getType().name());
        preparedStatement.setString(6, item.getBrand().name());
        preparedStatement.setString(7, item.getDimensionOfUnite());
        preparedStatement.setString(8, item.getDimensionOfPallet());
        preparedStatement.setFloat(9, item.getWeightOfUnit());
        preparedStatement.setFloat(10, item.getWeightOfPallet());
        preparedStatement.setInt(11,item.getPalletCapacity());
        preparedStatement.setFloat(12, item.getCost());
        preparedStatement.execute();
        return item;
    }

    //edit
    @Override
    public Item edit(Item item) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE ITEM SET HS_CODE=?,ITEM_NAME, ITEM_MODEL, ITEM_TYPE ,ITEM_BRAND ,DIMENSION_OF_UNIT=? ,DIMENSION_OF_PALLET=? ,WEIGHT_OF_UNIT=? ,WEIGHT_OF_PALLET=?, PALLET_CAPACITY=?, ITEM_COST WHERE ITEM_ID=?"
        );
        preparedStatement.setLong(1,item.getHsCode());
        preparedStatement.setString(2, item.getName());
        preparedStatement.setString(3, item.getModel());
        preparedStatement.setString(4, item.getType().name());
        preparedStatement.setString(5, item.getBrand().name());
        preparedStatement.setString(6, item.getDimensionOfUnite());
        preparedStatement.setString(7, item.getDimensionOfPallet());
        preparedStatement.setFloat(8, item.getWeightOfUnit());
        preparedStatement.setFloat(9, item.getWeightOfPallet());
        preparedStatement.setInt(10,item.getPalletCapacity());
        preparedStatement.setFloat(11, item.getCost());
        preparedStatement.setInt(12, item.getId());
        preparedStatement.execute();
        return item;
    }

    //remove
    @Override
    public Item remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM ITEM WHERE ITEM_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //findAll
    @Override
    public List<Item> findAll() throws Exception {
        List<Item> itemList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM ITEM ORDER BY ITEM_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Item item = Item
                    .builder()
                    .id(resultSet.getInt("item_ID"))
                    .hsCode(resultSet.getLong("HS_CODE"))
                    .name(resultSet.getString("ITEM_NAME"))
                    .model(resultSet.getString("ITEM_MODEL"))
                    .type(ItemType.valueOf(resultSet.getString("ITEM_TYPE")))
                    .brand(Brand.valueOf(resultSet.getString("ITEM_BRAND")))
                    .dimensionOfUnite(resultSet.getString("DIMENSION_OF_UNIT"))
                    .dimensionOfPallet(resultSet.getString("DIMENSION_OF_PALLET"))
                    .weightOfUnit(Float.parseFloat(String.valueOf(resultSet.getFloat("WEIGHT_OF_UNIT"))))
                    .weightOfPallet(Float.parseFloat(String.valueOf(resultSet.getFloat("DIMENSION_OF_PALLET"))))
                    .palletCapacity(resultSet.getInt("PALLET_CAPACITY"))
                    .cost(Float.parseFloat(String.valueOf(resultSet.getFloat("ITEM_COST"))))
                    .build();

            itemList.add(item);
        }

        return itemList;
    }


    //findById
    @Override
    public Item findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM ITEM WHERE ITEM_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Item item = null;
        if (resultSet.next()) {
            item = Item
                    .builder()
                    .id(resultSet.getInt("item_ID"))
                    .hsCode(resultSet.getLong("HS_CODE"))
                    .name(resultSet.getString("ITEM_NAME"))
                    .model(resultSet.getString("ITEM_MODEL"))
                    .type(ItemType.valueOf(resultSet.getString("ITEM_TYPE")))
                    .brand(Brand.valueOf(resultSet.getString("ITEM_BRAND")))
                    .dimensionOfUnite(resultSet.getString("DIMENSION_OF_UNIT"))
                    .dimensionOfPallet(resultSet.getString("DIMENSION_OF_PALLET"))
                    .weightOfUnit(Float.parseFloat(String.valueOf(resultSet.getFloat("WEIGHT_OF_UNIT"))))
                    .weightOfPallet(Float.parseFloat(String.valueOf(resultSet.getFloat("DIMENSION_OF_PALLET"))))
                    .palletCapacity(resultSet.getInt("PALLET_CAPACITY"))
                    .cost(Float.parseFloat(String.valueOf(resultSet.getFloat("ITEM_COST"))))
                    .build();
        }
        return item;
    }

    //findByName
    public List<Item> findByName(String name) throws Exception {
        List<Item> itemList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM ITEM WHERE ITEM_NAME LIKE? ORDER BY ITEM_ID");
        preparedStatement.setString(1, name + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Item item = Item
                    .builder()
                    .id(resultSet.getInt("ITEM_ID"))
                    .hsCode(resultSet.getLong("HS_CODE"))
                    .name(resultSet.getString("ITEM_NAME"))
                    .model(resultSet.getString("ITEM_MODEL"))
                    .type(ItemType.valueOf(resultSet.getString("ITEM_TYPE")))
                    .brand(Brand.valueOf(resultSet.getString("ITEM_BRAND")))
                    .dimensionOfUnite(resultSet.getString("DIMENSION_OF_UNIT"))
                    .dimensionOfPallet(resultSet.getString("DIMENSION_OF_PALLET"))
                    .weightOfUnit(Float.parseFloat(String.valueOf(resultSet.getFloat("WEIGHT_OF_UNIT"))))
                    .weightOfPallet(Float.parseFloat(String.valueOf(resultSet.getFloat("DIMENSION_OF_PALLET"))))
                    .palletCapacity(resultSet.getInt("PALLET_CAPACITY"))
                    .cost(Float.parseFloat(String.valueOf(resultSet.getFloat("ITEM_COST"))))
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

//....
