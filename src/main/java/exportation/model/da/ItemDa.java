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
                "INSERT INTO ITEM (ID,NAME,MODEL,DOU,DOP,PALLETCAPACITY,COST,WOU,WOP) VALUES (?,?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, item.getId());
        preparedStatement.setString(2, item.getName());
        preparedStatement.setString(3, item.getModel());
        preparedStatement.setString(4, item.getDou());
        preparedStatement.setString(5, item.getDop());
        preparedStatement.setInt(6, item.getPalletCapacity());
        preparedStatement.setDouble(7, item.getCost());
        preparedStatement.setFloat(8, item.getWou());
        preparedStatement.setFloat(9, item.getWop());
        preparedStatement.execute();
        return item;
    }

    //Edit
    @Override
    public Item edit(Item item) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE ITEM SET NAME=?, MODEL=?, DOU=?,DOP=?,PALLETCAPACITY=?,COST=?,WOU=?,WOP=?, WHERE ID=?"
        );
        preparedStatement.setInt(1, item.getId());
        preparedStatement.setString(2, item.getName());
        preparedStatement.setString(3, item.getModel());
        preparedStatement.setString(4, item.getDou());
        preparedStatement.setString(5, item.getDop());
        preparedStatement.setInt(6, item.getPalletCapacity());
        preparedStatement.setDouble(7, item.getCost());
        preparedStatement.setFloat(8, item.getWou());
        preparedStatement.setFloat(9, item.getWop());
        preparedStatement.execute();
        return item;
    }

    //Remove
    @Override
    public Item remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM ITEM WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<Item> findAll() throws Exception {
        List<Item> itemList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM ITEM ORDER BY ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Item item = Item
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .model(resultSet.getString("MODEL"))
                    .dou(resultSet.getString("DOU"))
                    .dop(resultSet.getString("DOP"))
                    .palletCapacity(resultSet.getInt("PALLETCAPACITY"))
                    .cost(resultSet.getFloat("COST"))
                    .wou(resultSet.getFloat("WOU"))
                    .wop(resultSet.getFloat("WOP"))
                    .build();

            itemList.add(item);
        }

        return itemList;
    }

    //FindById
    @Override
    public Item findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM ITEM WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Item item = null;
        if (resultSet.next()) {
            item = Item
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .model(resultSet.getString("MODEL"))
                    .dou(resultSet.getString("DOU"))
                    .dop(resultSet.getString("DOP"))
                    .palletCapacity(resultSet.getInt("PALLETCAPACITY"))
                    .cost(resultSet.getFloat("COST"))
                    .wou(resultSet.getFloat("WOU"))
                    .wop(resultSet.getFloat("WOP"))
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
