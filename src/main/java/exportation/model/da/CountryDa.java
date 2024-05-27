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

   //save
    @Override
    public Country save(Country country) throws Exception {
       country.setId(ConnectionProvider.getConnectionProvider().getNextId("COUNTRY_SEQ"));

       preparedStatement = connection.prepareStatement("INSERT INTO COUNTRY (COUNTRY_ID, COUNTRY_NAME, COUNTRY_PHONE_CODE) VALUES(?,?,?)");
       preparedStatement.setInt(1, country.getId());
       preparedStatement.setString(2, country.getName());
       preparedStatement.setString(3, country.getPhoneCode());
       preparedStatement.execute();
       return country;
    }

    //edit
    @Override
    public Country edit(Country country) throws Exception {
       preparedStatement = connection.prepareStatement("UPDATE COUNTRY SET COUNTRY_NAME=?, COUNTRY_PHONE_CODE=?,WHERE COUNTRY_ID=? ");

       preparedStatement.setString(1, country.getName());
       preparedStatement.setString(2, country.getPhoneCode());
       preparedStatement.setInt(3, country.getId());
       preparedStatement.execute();
       return country;
    }

    //remove
    @Override
    public Country remove(int id) throws Exception {
       preparedStatement = connection.prepareStatement("DELETE FROM COUNTRY WHERE COUNTRY_ID=?");
       preparedStatement.setInt(1, id);
       preparedStatement.execute();
        return null;
    }

    //findAll
    @Override
    public List<Country> findAll() throws Exception {
        List<Country> countryList= new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM COUNTRY ORDER BY COUNTRY_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            Country country = Country
                    .builder()
                    .id(resultSet.getInt("COUNTRY_ID"))
                    .name(resultSet.getString("COUNTRY_NAME"))
                    .phoneCode(resultSet.getString("COUNTRY_PHONE_CODE"))
                    .build();
            countryList.add(country);
        }
        return countryList;
    }

//    BL-findAll()

    //findById
    @Override
    public Country findById(int id) throws Exception {
       preparedStatement = connection.prepareStatement("SELECT * FROM COUNTRY WHERE COUNTRY_ID=?");
       preparedStatement.setInt(1, id);
       ResultSet resultSet = preparedStatement.executeQuery();
       Country country=null;

       if (resultSet.next()){
           country = Country
                   .builder()
                   .id(resultSet.getInt("COUNTRY_ID"))
                   .name(resultSet.getString("COUNTRY_NAME"))
                   .phoneCode(resultSet.getString("COUNTRY_PHONE_CODE"))
                   .build();
       }
        return country;
    }


    //findByName
    public List<Country> findByName(String name) throws Exception {
       List<Country> countryList= new ArrayList<>();
       preparedStatement = connection.prepareStatement("SELECT * FROM COUNTRY WHERE COUNTRY_NAME LIKE? ORDER BY COUNTRY_ID");
       preparedStatement.setString(1, name+"%");
       ResultSet resultSet = preparedStatement.executeQuery();

       while(resultSet.next()){
           Country country= Country
                   .builder()
                   .id(resultSet.getInt("COUNTRY_ID"))
                   .name(resultSet.getString("COUNTRY_NAME"))
                   .phoneCode(resultSet.getString("COUNTRY_PHONE_CODE"))
                   .build();
           countryList.add(country);
       }
        return countryList;
   }


    @Override
    public void close() throws Exception {

    }
}
//...