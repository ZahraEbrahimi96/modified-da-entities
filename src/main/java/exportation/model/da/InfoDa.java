package exportation.model.da;
import exportation.model.entity.*;
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
                "INSERT INTO INFO (INFO_ID, POPULATION, CAR_RATE, CLIMATE, ACCESS_PATH, SUPPLIER, MANUFACTURER, IMPORTING, EXPORTING, DEMAND, TARIFF) VALUES (?,?,?,?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, info.getId());
        preparedStatement.setLong(2, info.getPopulation());
        preparedStatement.setLong(3, info.getCarRate());
        preparedStatement.setString(4, info.getClimate());
        preparedStatement.setString(5, String.valueOf(info.getAccessPath()));
        preparedStatement.setString(6, String.valueOf(info.getSupplier()));
        preparedStatement.setString(7, String.valueOf(info.getManufacturer()));
        preparedStatement.setString(8, String.valueOf(info.getImporting()));
        preparedStatement.setString(9, String.valueOf(info.getExporting()));
        preparedStatement.setString(10, info.getDemand());
        preparedStatement.setString(11, info.getTariff());
        preparedStatement.execute();
        return info;
    }

    //edit
    @Override
    public Info edit(Info info) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE INFO SET  POPULATION=?, CAR_RATE=?, CLIMATE=?, ACCESS_PATH=?, SUPPLIER=?, MANUFACTURER=?, IMPORTING=?, EXPORTING=?, DEMAND=?, TARIFF=? WHERE INFO_ID=?"
        );
        preparedStatement.setLong(1, info.getPopulation());
        preparedStatement.setLong(2, info.getCarRate());
        preparedStatement.setString(3, info.getClimate());
        preparedStatement.setString(4, String.valueOf(info.getAccessPath()));
        preparedStatement.setString(5, String.valueOf(info.getSupplier()));
        preparedStatement.setString(6, String.valueOf(info.getManufacturer()));
        preparedStatement.setString(7, String.valueOf(info.getImporting()));
        preparedStatement.setString(8, String.valueOf(info.getExporting()));
        preparedStatement.setString(9, info.getDemand());
        preparedStatement.setString(10, info.getTariff());
        preparedStatement.setInt(11, info.getId());
        preparedStatement.execute();
        return info;
    }

    //remove
    @Override
    public Info remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM INFO WHERE INFO_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //findAll
    @Override
    public List<Info> findAll() throws Exception {
        List<Info> infoList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM INFO ORDER BY INFO_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Info info = Info
                    .builder()
                    .id(resultSet.getInt("INFO_ID"))
                    .population(resultSet.getLong("POPULATION"))
                    .carRate(resultSet.getLong("CAR_RATE"))
                    .climate(resultSet.getString("CLIMATE")))
                    .accessPath((AccessPath)resultSet.getObject("ACCESS_PATH"))
                    .supplier((Supplier)resultSet.getObject("SUPPLIER"))
                    .manufacturer((Manufacturer)resultSet.getObject("MANUFACTURER"))
                    .importing((Imports)resultSet.getObject("IMPORTING"))
                    .exporting((Exports)resultSet.getObject("EXPORTING"))
                    .demand(resultSet.getString("DEMAND"))
                    .tariff(resultSet.getString("TARIFF"))
                    .build();

            infoList.add(info);
        }

        return infoList;
    }

    //findById
    @Override
    public Info findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM INFO WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Info info = null;
        if (resultSet.next()) {
            info = Info
                    .builder()
                    .id(resultSet.getInt("INFO_ID"))
                    .population(resultSet.getLong("POPULATION"))
                    .carRate(resultSet.getLong("CAR_RATE"))
                    .climate(resultSet.getString("CLIMATE")))
                    .accessPath((AccessPath)resultSet.getObject("ACCESS_PATH"))
                    .supplier((Supplier)resultSet.getObject("SUPPLIER"))
                    .manufacturer((Manufacturer)resultSet.getObject("MANUFACTURER"))
                    .importing((Imports)resultSet.getObject("IMPORTING"))
                    .exporting((Exports)resultSet.getObject("EXPORTING"))
                    .demand(resultSet.getString("DEMAND"))
                    .tariff(resultSet.getString("TARIFF"))
                    .build();
        }
        return info;
    }



    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}


