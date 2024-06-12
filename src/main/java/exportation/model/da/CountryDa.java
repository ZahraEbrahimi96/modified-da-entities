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
                "INSERT INTO COUNTRY_TABLE (COUNTRY_ID, COUNTRY_NAME, COUNTRY_PHONE_CODE,COUNTRY_POPULATION, COUNTRY_CAR_RATE, COUNTRY_TARIFF, COUNTRY_NEIGHBORS) VALUES (?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, country.getId());
        preparedStatement.setString(2, country.getName());
        preparedStatement.setString(3, country.getPhoneCode());
        preparedStatement.setLong(4, country.getPopulation());
        preparedStatement.setLong(5, country.getCarRate());
        preparedStatement.setInt(6, country.getTariff());
        preparedStatement.setString(7, country.getNeighbors());
        preparedStatement.execute();
        return country;
    }

    //Edit
    @Override
    public Country edit(Country country) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE COUNTRY_TABLE SET COUNTRY_NAME=?, COUNTRY_PHONE_CODE=?,COUNTRY_POPULATION=?, COUNTRY_CAR_RATE=?,  COUNTRY_TARIFF=?, COUNTRY_NEIGHBORS=? WHERE COUNTRY_ID=?"
        );

        preparedStatement.setString(1, country.getName());
        preparedStatement.setString(2, country.getPhoneCode());
        preparedStatement.setLong(3, country.getPopulation());
        preparedStatement.setLong(4, country.getCarRate());
        preparedStatement.setInt(5, country.getTariff());
        preparedStatement.setString(6, country.getNeighbors());
        preparedStatement.setInt(7, country.getId());
        preparedStatement.execute();
        return country;
    }

    //Remove
    @Override
    public Country remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM COUNTRY_TABLE WHERE COUNTRY_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<Country> findAll() throws Exception {
        List<Country> countryList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM COUNTRY_TABLE ORDER BY COUNTRY_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Country country = Country
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .phoneCode(resultSet.getString("PHONECODE"))
                    .population(resultSet.getLong("COUNTRY_POPULATION"))
                    .carRate(resultSet.getLong("COUNTRY_CAR_RATE"))
                    .tariff(resultSet.getInt("COUNTRY_TARIFF"))
                    .neighbors(resultSet.getString("COUNTRY_NEIGHBORS"))
                    .build();

            countryList.add(country);
        }

        return countryList;
    }

    //FindById
    @Override
    public Country findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM COUNTRY_TABLE WHERE COUNTRY_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Country country = null;
        if (resultSet.next()) {
            country = Country
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .phoneCode(resultSet.getString("PHONECODE"))
                    .population(resultSet.getLong("COUNTRY_POPULATION"))
                    .carRate(resultSet.getLong("COUNTRY_CAR_RATE"))
                    .tariff(resultSet.getInt("COUNTRY_TARIFF"))
                    .neighbors(resultSet.getString("COUNTRY_NEIGHBORS"))
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
