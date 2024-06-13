package exportation.model.da;

import exportation.model.entity.*;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;

import java.sql.*;
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
        transportation.setId(ConnectionProvider.getConnectionProvider().getNextId("Transportation_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO TRANSPORTATION_TABLE (TRANSPORTATION_ID,TRANSPORTATION_DIRECTION,TRANSPORTATION_FREIGHT, ITEM_ID, COMPANY_ID, EXPORT_ID,COUNTRY_ID,TRANSPORTATION_DATE_TIME ) VALUES (?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, transportation.getId());
        preparedStatement.setString(2, transportation.getDirection());
        preparedStatement.setDouble(3, transportation.getFreight());
        preparedStatement.setInt(4, transportation.getItem().getId());
        preparedStatement.setInt(5, transportation.getCompany().getId());
        preparedStatement.setInt(6, transportation.getExportTracing().getId());
        preparedStatement.setInt(7, transportation.getCountry().getId());
        preparedStatement.setTimestamp(8, Timestamp.valueOf(transportation.getDateTime()));
        preparedStatement.execute();
        return transportation;
    }

    //Edit
    @Override
    public Transportation edit(Transportation transportation) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE TRANSPORTATION_TABLE SET TRANSPORTATION_DIRECTION=?, TRANSPORTATION_FREIGHT=?,ITEM_ID=?, COMPANY_ID=?,EXPORT_ID=?,COUNTRY_ID=?, TRANSPORTATION_DATE_TIME=?  WHERE TRANSPORTATION_ID=?"
        );
        preparedStatement.setString(1, transportation.getDirection());
        preparedStatement.setDouble(2, transportation.getFreight());
        preparedStatement.setInt(3, transportation.getItem().getId());
        preparedStatement.setInt(4, transportation.getItem().getId());
        preparedStatement.setInt(5, transportation.getExportTracing().getId());
        preparedStatement.setInt(6, transportation.getCountry().getId());
        preparedStatement.setTimestamp(7, Timestamp.valueOf(transportation.getDateTime()));
        preparedStatement.setInt(8, transportation.getId());
        preparedStatement.execute();
        return transportation;
    }

    //Remove
    @Override
    public Transportation remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM TRANSPORTATION_TABLE WHERE TRANSPORTATION_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<Transportation> findAll() throws Exception {
        List<Transportation> transportationList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM TRANSPORTATION_TABLE ORDER BY TRANSPORTATION_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Transportation transportation = Transportation
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .direction(resultSet.getString("DIRECTION"))
                    .freight(resultSet.getFloat("FREIGHT"))
                    .item(Item.builder().id(resultSet.getInt("ITEM_ID")).build())
                    .company(Company.builder().name(resultSet.getString("COMPANY_ID")).build())
                    .exportTracing(ExportTracing.builder().id(resultSet.getInt("EXPORT_ID")).build())
                    .country(Country.builder().id(resultSet.getInt("COUNTRY_ID")).build())
                    .dateTime(resultSet.getTimestamp("DATE_TIME").toLocalDateTime())
                    .build();

            transportationList.add(transportation);
        }

        return transportationList;
    }

    //FindById
    @Override
    public Transportation findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM TRANSPORTATION_TABLE WHERE TRANSPORTATION_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Transportation transportation = null;
        if (resultSet.next()) {
            transportation = Transportation
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .direction(resultSet.getString("DIRECTION"))
                    .freight(resultSet.getFloat("FREIGHT"))
                    .item(Item.builder().id(resultSet.getInt("ITEM_ID")).build())
                    .company(Company.builder().name(resultSet.getString("COMPANY_ID")).build())
                    .exportTracing(ExportTracing.builder().id(resultSet.getInt("EXPORT_ID")).build())
                    .country(Country.builder().id(resultSet.getInt("COUNTRY_ID")).build())
                    .dateTime(resultSet.getTimestamp("DATE_TIME").toLocalDateTime())
                    .build();
        }
        return transportation;
    }

    //Close
    @Override
    public void close() throws Exception {

    }
}
