package exportation.model.da;

import exportation.model.entity.Info;
import lombok.extern.log4j.Log4j;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class InfoDa implements AutoCloseable, CRUD<Info> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public InfoDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }


    //save
    @Override
    public Info save(Info info) throws Exception {
        info.setId(ConnectionProvider.getConnectionProvider().getNextId("INFO_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO INFO (ID,POPULATION,CARRATE,CLIMATE,ACCESSPASS,lIFEEXPECTANSY,DEMAND,TARIFF) VALUES (?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, info.getId());
        preparedStatement.setLong(2, info.getPopulation());
        preparedStatement.setLong(3, info.getCarRate());
        preparedStatement.setString(4, info.getClimate());
        preparedStatement.setString(5, info.getAccessPath());
        preparedStatement.setString(6, info.getLifeExpectancy());
        preparedStatement.setString(7, info.getDemand());
        preparedStatement.setString(8, info.getTariff());
        preparedStatement.execute();
        return info;
    }

    //Edit
    @Override
    public Info edit(Info info) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE INFO SET POPULATION=?, CARRATE=?, CLIMATE=?,ACCESSPASS=?,lIFEEXPECTANSY=?,DEMAND=?,TARIFF=?, WHERE ID=?"
        );
        preparedStatement.setInt(1, info.getId());
        preparedStatement.setLong(2, info.getPopulation());
        preparedStatement.setLong(3, info.getCarRate());
        preparedStatement.setString(4, info.getClimate());
        preparedStatement.setString(5, info.getAccessPath());
        preparedStatement.setString(6, info.getLifeExpectancy());
        preparedStatement.setString(7, info.getDemand());
        preparedStatement.setString(8, info.getTariff());
        preparedStatement.execute();
        return info;
    }

    //Remove
    @Override
    public Info remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM INFO WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<Info> findAll() throws Exception {
        List<Info> infoList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM INFO ORDER BY ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Info info = Info
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .population(resultSet.getLong("POPULATION"))
                    .carRate(resultSet.getLong("CARRATE"))
                    .climate(resultSet.getString("CLIMATE"))
                    .accessPath(resultSet.getString("ACCESSPATH"))
                    .lifeExpectancy(resultSet.getString("LIFEEXPECTANSY"))
                    .demand(resultSet.getString("DEMAND"))
                    .tariff(resultSet.getString("TARIFF"))
                    .build();

            infoList.add(info);
        }

        return infoList;
    }

    //FindById
    @Override
    public Info findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM INFO WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Info info = null;
        if (resultSet.next()) {
            info = Info
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .population(resultSet.getLong("POPULATION"))
                    .carRate(resultSet.getLong("CARRATE"))
                    .climate(resultSet.getString("CLIMATE"))
                    .accessPath(resultSet.getString("ACCESSPATH"))
                    .lifeExpectancy(resultSet.getString("LIFEEXPECTANSY"))
                    .demand(resultSet.getString("DEMAND"))
                    .tariff(resultSet.getString("TARIFF"))
                    .build();
        }
        return info;
    }

    //Close
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
