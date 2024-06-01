package exportation.model.da;

import exportation.model.entity.Country;
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
                "INSERT INTO COUNTRY (ID,NAME,PHONECODE,RELATEDMARKET) VALUES (?,?,?,?)"
        );
        preparedStatement.setInt(1, country.getId());
        preparedStatement.setString(2, country.getName());
        preparedStatement.setString(3, country.getPhoneCode());
        preparedStatement.setString(4, country.getRelatedMarket());
        preparedStatement.execute();
        return country;
    }

    //Edit
    @Override
    public Country edit(Country country) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE COUNTRY SET NAME=?, PHONECODE=?, RELATEDMARKET=?, WHERE ID=?"
        );
        preparedStatement.setInt(1, country.getId());
        preparedStatement.setString(2, country.getName());
        preparedStatement.setString(3, country.getPhoneCode());
        preparedStatement.setString(4, country.getRelatedMarket());
        preparedStatement.execute();
        return country;
    }

    //Remove
    @Override
    public Country remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM COUNTRY WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<Country> findAll() throws Exception {
        List<Country> countryList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM COUNTRY ORDER BY ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Country country = Country
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .phoneCode(resultSet.getString("PHONECODE"))
                    .relatedMarket(resultSet.getString("RELATEDMARKET"))
                    .build();

            countryList.add(country);
        }

        return countryList;
    }

    //FindById
    @Override
    public Country findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM COUNTRY WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Country country = null;
        if (resultSet.next()) {
            country = Country
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .phoneCode(resultSet.getString("PHONECODE"))
                    .relatedMarket(resultSet.getString("RELATEDMARKET"))
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
