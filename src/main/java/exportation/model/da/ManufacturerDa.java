package exportation.model.da;

import exportation.model.entity.Country;
import exportation.model.entity.Manufacturer;
import exportation.model.entity.Person;
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
public class ManufacturerDa implements AutoCloseable, CRUD<Manufacturer> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public ManufacturerDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    //save
    @Override
    public Manufacturer save(Manufacturer manufacturer) throws SQLException {
        manufacturer.setId(ConnectionProvider.getConnectionProvider().getNextId("MANUFACTURER_SEQ"));

        preparedStatement = connection.prepareStatement("INSERT INTO MANUFACTURER (MANUFACTURER_ID, MANUFACTURER_NAME, MANUFACTURER_PRODUCT,MANUFACTURER_ADDRESS , MANUFACTURER_PHONE, MANUFACTURER_EMAIL, MANUFACTURER_COUNTRY, PRODUCTION_RATE,MANUFACTURER_MANAGER) VALUES (?,?,?,?,?,?,?,?,?)");
        preparedStatement.setInt(1, manufacturer.getId());
        preparedStatement.setString(2, manufacturer.getName());
        preparedStatement.setString(3, manufacturer.getProduct());
        preparedStatement.setString(4, manufacturer.getAddress());
        preparedStatement.setString(5, manufacturer.getPhoneNumber());
        preparedStatement.setString(6, manufacturer.getEmail());
        preparedStatement.setString(7, manufacturer.getCountry().getName());
        preparedStatement.setLong(8, manufacturer.getProductionRate());
        preparedStatement.setString(9, String.valueOf(manufacturer.getPerson().getId()));
        preparedStatement.execute();
        return manufacturer;
    }

    //edit
    @Override
    public Manufacturer edit(Manufacturer manufacturer) throws Exception {
        preparedStatement = connection.prepareStatement("UPDATE MANUFACTURER SET MANUFACTURER_NAME=?, , MANUFACTURER_PRODUCT=?, MANUFACTURER_ADDRESS=?,MANUFACTURER_PHONE=?, MANUFACTURER_EMAIL=?,  MANUFACTURER_COUNTRY=, PRODUCTION_RATE=?,MANUFACTURER_MANAGER=? WHERE MANUFACTURER_ID=? ");
        preparedStatement.setString(1, manufacturer.getName());
        preparedStatement.setString(2, manufacturer.getProduct());
        preparedStatement.setString(3, manufacturer.getAddress());
        preparedStatement.setString(4, manufacturer.getPhoneNumber());
        preparedStatement.setString(5, manufacturer.getEmail());
        preparedStatement.setString(6, manufacturer.getCountry().getName());
        preparedStatement.setLong(7, manufacturer.getProductionRate());
        preparedStatement.setString(8, String.valueOf(manufacturer.getPerson().getId()));
        preparedStatement.setInt(9, manufacturer.getId());
        preparedStatement.execute();
        return manufacturer;
    }

    //remove
    @Override
    public Manufacturer remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM MANUFACTURER WHERE MANUFACTURER_ID=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //findAll
    @Override
    public List<Manufacturer> findAll() throws Exception {
        List<Manufacturer> manufacturerList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM MANUFACTURER ORDER BY MANUFACTURER_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            Manufacturer manufacturer = Manufacturer
                    .builder()
                    .id(resultSet.getInt("MANUFACTURER_ID"))
                    .name(resultSet.getString("MANUFACTURER_NAME"))
                    .product(resultSet.getString("MANUFACTURER_PRODUCT"))
                    .address(resultSet.getString("MANUFACTURER_ADDRESS"))
                    .phoneNumber(resultSet.getString("MANUFACTURER_PHONE"))
                    .email(resultSet.getString("MANUFACTURER_EMAIL"))
                    .country(Country.builder().name(resultSet.getString("MANUFACTURER_COUNTRY")).build())
                    .productionRate(resultSet.getLong("PRODUCTION_RATE"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .build();

            manufacturerList.add(manufacturer);
        }
        return manufacturerList;
    }

    //findById
    @Override
    public Manufacturer findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM MANUFACTURER WHERE MANUFACTURER_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Manufacturer manufacturer = null;

        if (resultSet.next()) {
            manufacturer = Manufacturer
                    .builder()
                    .id(resultSet.getInt("MANUFACTURER_ID"))
                    .name(resultSet.getString("MANUFACTURER_NAME"))
                    .product(resultSet.getString("MANUFACTURER_PRODUCT"))
                    .address(resultSet.getString("MANUFACTURER_ADDRESS"))
                    .phoneNumber(resultSet.getString("MANUFACTURER_PHONE"))
                    .email(resultSet.getString("MANUFACTURER_EMAIL"))
                    .country(Country.builder().name(resultSet.getString("MANUFACTURER_COUNTRY")).build())
                    .productionRate(resultSet.getLong("PRODUCTION_RATE"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .build();
        }
        return manufacturer;
    }

    //findByName
    public List<Manufacturer> findByName(String name) throws Exception {
        List<Manufacturer> manufacturerList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM MANUFACTURER WHERE MANUFACTURER_NAME LIKE? ORDER BY MANUFACTURER_ID");
        preparedStatement.setString(1, name + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Manufacturer manufacturer = Manufacturer
                    .builder()
                    .id(resultSet.getInt("MANUFACTURER_ID"))
                    .name(resultSet.getString("MANUFACTURER_NAME"))
                    .product(resultSet.getString("MANUFACTURER_PRODUCT"))
                    .address(resultSet.getString("MANUFACTURER_ADDRESS"))
                    .phoneNumber(resultSet.getString("MANUFACTURER_PHONE"))
                    .email(resultSet.getString("MANUFACTURER_EMAIL"))
                    .country(Country.builder().name(resultSet.getString("MANUFACTURER_COUNTRY")).build())
                    .productionRate(resultSet.getLong("PRODUCTION_RATE"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .build();
            manufacturerList.add(manufacturer);
        }
        return manufacturerList;
    }

    @Override
    public void close() throws Exception {

    }
}
