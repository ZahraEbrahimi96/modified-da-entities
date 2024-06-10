package exportation.model.da;

import exportation.model.entity.*;
import lombok.extern.log4j.Log4j;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class CountryDa implements AutoCloseable, CRUD<Country> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public CountryDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    //save
    @Override
    public Country save(Country country) throws Exception {
        country.setId(ConnectionProvider.getConnectionProvider().getNextId("COUNTRY_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO COUNTRY (COUNTRY_ID, COUNTRY_NAME, COUNTRY_PHONE_CODE, COUNTRY_SUPPLIER, COUNTRY_MANUFACTURER, COUNTRY_POPULATION, COUNTRY_CAR_RATE, COUNTRY_TARIFF, COUNTRY_NEIGHBORS) VALUES (?,?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, country.getId());
        preparedStatement.setString(2, country.getName());
        preparedStatement.setString(3, country.getPhoneCode());
        preparedStatement.setString(4, String.valueOf(country.getSupplier()));
        preparedStatement.setString(5, String.valueOf(country.getManufacturer()));
        preparedStatement.setLong(6,country.getPopulation());
        preparedStatement.setLong(7,country.getCarRate());
        preparedStatement.setInt(8,country.getTariff());
        preparedStatement.setArray(9, (Array) country.getNeighbors());
        preparedStatement.execute();
        return country;
    }

    //Edit
    @Override
    public Country edit(Country country) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE COUNTRY SET COUNTRY_NAME=?, COUNTRY_PHONE_CODE=?, COUNTRY_SUPPLIER=?,COUNTRY_MANUFACTURER=?,COUNTRY_POPULATION=?, COUNTRY_CAR_RATE=?,  COUNTRY_TARIFF=?, COUNTRY_NEIGHBORS=? WHERE COUNTRY_ID=?"
        );

        preparedStatement.setString(1, country.getName());
        preparedStatement.setString(2, country.getPhoneCode());
        preparedStatement.setString(3, String.valueOf(country.getSupplier()));
        preparedStatement.setString(4, String.valueOf(country.getManufacturer()));
        preparedStatement.setLong(5,country.getPopulation());
        preparedStatement.setLong(6,country.getCarRate());
        preparedStatement.setInt(7,country.getTariff());
        preparedStatement.setArray(8, (Array) country.getNeighbors());
        preparedStatement.setInt(9, country.getId());
        preparedStatement.execute();
        return country;
    }

    //Remove
    @Override
    public Country remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM COUNTRY WHERE COUNTRY_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<Country> findAll() throws Exception {
        List<Country> countryList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM COUNTRY ORDER BY COUNTRY_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Country country = Country
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .phoneCode(resultSet.getString("PHONECODE"))
                    .supplier(Supplier.builder().id(resultSet.getInt("SUPPLIER_ID")).build())
                    .manufacturer(Manufacturer.builder().id(resultSet.getInt("MANUFACTURER_ID")).build())
                    .population(resultSet.getLong("COUNTRY_POPULATION"))
                    .carRate(resultSet.getLong("COUNTRY_CAR_RATE"))
                    .tariff(resultSet.getInt("COUNTRY_TARIFF"))
                    .neighbors((ArrayList<String>) resultSet.getArray("COUNTRY_NEIGHBORS"))
                    .build();

            countryList.add(country);
        }

        return countryList;
    }

    //FindById
    @Override
    public Country findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM COUNTRY WHERE COUNTRY_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Country country = null;
        if (resultSet.next()) {
            country = Country
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .phoneCode(resultSet.getString("PHONECODE"))
                    .supplier(Supplier.builder().id(resultSet.getInt("SUPPLIER_ID")).build())
                    .manufacturer(Manufacturer.builder().id(resultSet.getInt("MANUFACTURER_ID")).build())
                    .population(resultSet.getLong("COUNTRY_POPULATION"))
                    .carRate(resultSet.getLong("COUNTRY_CAR_RATE"))
                    .tariff(resultSet.getInt("COUNTRY_TARIFF"))
                    .neighbors((ArrayList<String>) resultSet.getArray("COUNTRY_NEIGHBORS"))
                    .build();
        }
        return country;
    }

    //Close
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
