package exportation.model.da;
import exportation.model.entity.AccessPath;
import exportation.model.entity.Country;
import exportation.model.entity.Item;
import exportation.model.entity.Transportation;
import exportation.model.entity.enums.PathType;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Timestamp.valueOf;

public class TransportationDa implements AutoCloseable, CRUD<Transportation> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public TransportationDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    //save
    @Override
    public Transportation save(Transportation transportation) throws Exception {
        transportation.setId(ConnectionProvider.getConnectionProvider().getNextId("TRANSPORTATION_SEQ"));
        preparedStatement = connection.prepareStatement("INSERT INTO TRANSPORTATION (TRANSPORTATION_ID, T_PATH_TYPE, T_ACCESS_PATH, T_COUNTRY, T_ORIGIN, T_FREIGHT, T_ITEM) VALUES (?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, transportation.getId());
        preparedStatement.setString(2,(String.valueOf(transportation.getPathType())));
        preparedStatement.setString(2,(String.valueOf(transportation.getAccessPath())));
        preparedStatement.setString(4,(String.valueOf(transportation.getCountry())));
        preparedStatement.setString(5, transportation.getOrigin());
        preparedStatement.setFloat(6, transportation.getFreight());
        preparedStatement.setString(7,(String.valueOf(transportation.getItem())));
        preparedStatement.execute();
        return transportation;
    }

    //edit
    @Override
    public Transportation edit(Transportation transportation) throws Exception {
        preparedStatement = connection.prepareStatement("UPDATE TRANSPORTATION SET  T_PATH_TYPE=?, T_ACCESS_PATH=?, T_COUNTRY=?, T_ORIGIN=?, T_FREIGHT=?, T_ITEM=? WHERE TRANSPORTATION_ID=? ");
        preparedStatement.setString(1,(String.valueOf(transportation.getPathType())));
        preparedStatement.setString(2,(String.valueOf(transportation.getAccessPath())));
        preparedStatement.setString(3,(String.valueOf(transportation.getCountry())));
        preparedStatement.setString(4, transportation.getOrigin());
        preparedStatement.setFloat(5, transportation.getFreight());
        preparedStatement.setString(6,(String.valueOf(transportation.getItem())));
        preparedStatement.setInt(7, transportation.getId());
        preparedStatement.execute();
        return transportation;
    }

    //remove
    @Override
    public Transportation remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM EXPORTTRACING WHERE TRANSPORTATION_ID=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //findAll
    @Override
    public List<Transportation> findAll() throws Exception {
        List<Transportation> transportationList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM TRANSPORTATION ORDER BY TRANSPORTATION_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Transportation transportation = Transportation
                    .builder()
                    .id(resultSet.getInt("TRANSPORTATION_ID"))
                    .pathType((PathType)resultSet.getObject("T_PATH_TYPE"))
                    .accessPath((AccessPath)resultSet.getObject("T_ACCESS_PATH"))
                    .country((Country)resultSet.getObject("T_COUNTRY"))
                    .origin(resultSet.getString("T_ORIGIN"))
                    .freight(resultSet.getFloat("T_FREIGHT"))
                    .item((Item)resultSet.getObject("T_ITEM"))
                    .build();
            transportationList.add(transportation);
        }
        return  transportationList;
    }

    //findById
    @Override
    public Transportation findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM transportation WHERE TRANSPORTATION_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Transportation transportation= null;

        if (resultSet.next()) {
            transportation = Transportation
                    .builder()
                    .id(resultSet.getInt("TRANSPORTATION_ID"))
                    .pathType((PathType)resultSet.getObject("T_PATH_TYPE"))
                    .accessPath((AccessPath)resultSet.getObject("T_ACCESS_PATH"))
                    .country((Country)resultSet.getObject("T_COUNTRY"))
                    .origin(resultSet.getString("T_ORIGIN"))
                    .freight(resultSet.getFloat("T_FREIGHT"))
                    .item((Item)resultSet.getObject("T_ITEM"))
                    .build();
        }
        return  transportation;
    }



    @Override
    public void close() throws Exception {

    }
}


