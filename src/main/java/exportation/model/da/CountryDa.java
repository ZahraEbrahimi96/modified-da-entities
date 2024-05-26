package exportation.model.da;

import exportation.model.entity.Country;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Log4j
public class CountryDa implements AutoCloseable, CRUD<Country>{
   private final Connection connection;
   private PreparedStatement preparedStatement;

   public CountryDa() throws SQLException {
       connection = ConnectionProvider.getConnectionProvider().getConnection();
   }

    @Override
    public Country save(Country country) throws Exception {
       country.setCountryId(ConnectionProvider.getConnectionProvider().getNextId("COUNTRY_SEQ"));

       preparedStatement = connection.prepareStatement("INSERT INTO COUNTRY (COUNTRY_ID, COUNTRY_NAME, COUNTRY_PHONE_CODE, RELATED_MARKET, COMPANY, INFO) VALUES(?,?,?,?,?,?)");
       preparedStatement.setInt(1, country.getCountryId());
       preparedStatement.setString(2, country.getCountryName());
       preparedStatement.setString(3, country.getCountryPhoneCode());
       preparedStatement.setString(4, country.getRelatedMarket());
       preparedStatement.execute();
       return country;
    }

    @Override
    public Country edit(Country country) throws Exception {
       preparedStatement = connection.prepareStatement("UPDATE COUNTRY SET COUNTRY_NAME=?, COUNTRY_PHONE_CODE=?, RELATED_MARKET=?, COMPANY=?, INFO=? WHERE COUNTRY_ID=? ");

       preparedStatement.setString(1, country.getCountryName());
       preparedStatement.setString(2, country.getCountryPhoneCode());
       preparedStatement.setString(3, country.getRelatedMarket());
       preparedStatement.setInt(6, country.getCountryId());
       preparedStatement.execute();
       return country;
    }

    @Override
    public Country remove(int countryId) throws Exception {
       preparedStatement = connection.prepareStatement("DELETE FROM COUNTRY WHERE COUNTRY_ID=?");
       preparedStatement.setInt(1, countryId);
       preparedStatement.execute();
        return null;
    }

    @Override
    public List<Country> findAll() throws Exception {
        List<Country> countryList= new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM COUNTRY ORDER BY COUNTRY_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            Country country = Country
                    .builder()
                    .countryId(resultSet.getInt("COUNTRY_ID"))
                    .countryName(resultSet.getString("COUNTRY_NAME"))
                    .countryPhoneCode(resultSet.getString("COUNTRY_PHONE_CODE"))
                    .relatedMarket(resultSet.getString("RELATED_MARKET"))
                    .build();
            countryList.add(country);
        }
        return countryList;
    }

    @Override
    public Country findById(int countryId) throws Exception {
       preparedStatement = connection.prepareStatement("SELECT * FROM COUNTRY WHERE COUNTRY_ID=?");
       preparedStatement.setInt(1, countryId);
       ResultSet resultSet = preparedStatement.executeQuery();
       Country country=null;

       if (resultSet.next()){
           country = Country
                   .builder()
                   .countryId(resultSet.getInt("COUNTRY_ID"))
                   .countryName(resultSet.getString("COUNTRY_NAME"))
                   .countryPhoneCode(resultSet.getString("COUNTRY_PHONE_CODE"))
                   .relatedMarket(resultSet.getString("RELATED_MARKET"))
                   .build();
       }
        return country;
    }

    public List<Country> findByName(String countryName) throws Exception {
       List<Country> countryList= new ArrayList<>();
       preparedStatement = connection.prepareStatement("SELECT * FROM COUNTRY WHERE COUNTRY_NAME LIKE? ORDER BY COUNTRY_ID");
       preparedStatement.setString(1, countryName+"%");
       ResultSet resultSet = preparedStatement.executeQuery();

       while(resultSet.next()){
           Country country= Country
                   .builder()
                   .countryId(resultSet.getInt("COUNTRY_ID"))
                   .countryName(resultSet.getString("COUNTRY_NAME"))
                   .countryPhoneCode(resultSet.getString("COUNTRY_PHONE_CODE"))
                   .relatedMarket(resultSet.getString("RELATED_MARKET"))
                   .build();
           countryList.add(country);
       }
        return countryList;
   }


    @Override
    public void close() throws Exception {

    }
}
