package exportation.model.da;

import exportation.model.entity.Country;
import exportation.model.entity.Person;
import exportation.model.entity.Supplier;
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
public class SupplierDa implements AutoCloseable, CRUD<Supplier> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public SupplierDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    //save
    @Override
    public Supplier save(Supplier supplier) throws SQLException {
        supplier.setId(ConnectionProvider.getConnectionProvider().getNextId("SUPPLIER_SEQ"));

        preparedStatement = connection.prepareStatement("INSERT INTO SUPPLIER (SUPPLIER_ID, SUPPLIER_NAME, SUPPLIER_PRODUCT, SUPPLIER_ADDRESS,  SUPPLIER_PHONE,SUPPLIER_EMAIL, SUPPLIER_COUNTRY, ONLINE_SALE, SUPPLIER_MANAGER) VALUES (?,?,?,?,?,?,?,?,?)");
        preparedStatement.setInt(1, supplier.getId());
        preparedStatement.setString(2, supplier.getName());
        preparedStatement.setString(3, supplier.getProduct());
        preparedStatement.setString(4, supplier.getAddress());
        preparedStatement.setString(5, supplier.getPhoneNumber());
        preparedStatement.setString(6, supplier.getEmail());
        preparedStatement.setString(7, supplier.getCountry().getName());
        preparedStatement.setBoolean(8, supplier.isOnlineSale());
        preparedStatement.setString(9, String.valueOf(supplier.getPerson().getId()));
        preparedStatement.execute();
        return supplier;
    }

    //edit
    @Override
    public Supplier edit(Supplier supplier) throws Exception {
        preparedStatement = connection.prepareStatement("UPDATE SUPPLIER SET SUPPLIER_NAME=?, SUPPLIER_PRODUCT=?, SUPPLIER_ADDRESS=?,  SUPPLIER_PHONE=?,SUPPLIER_EMAIL=?, SUPPLIER_COUNTRY=, ONLINE_SALE=?, SUPPLIER_MANAGER=? WHERE SUPPLIER_ID=? ");
        preparedStatement.setString(1, supplier.getName());
        preparedStatement.setString(2, supplier.getProduct());
        preparedStatement.setString(3, supplier.getAddress());
        preparedStatement.setString(4, supplier.getPhoneNumber());
        preparedStatement.setString(5, supplier.getEmail());
        preparedStatement.setString(6, supplier.getCountry().getName());
        preparedStatement.setBoolean(7, supplier.isOnlineSale());
        preparedStatement.setString(8, String.valueOf(supplier.getPerson().getId()));
        preparedStatement.setInt(9, supplier.getId());
        preparedStatement.execute();
        return supplier;
    }

    //remove
    @Override
    public Supplier remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM SUPPLIER WHERE SUPPLIER_ID=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //findAll
    @Override
    public List<Supplier> findAll() throws Exception {
        List<Supplier> supplierList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM SUPPLIER ORDER BY SUPPLIER_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            Supplier supplier = Supplier
                    .builder()
                    .id(resultSet.getInt("SUPPLIER_ID"))
                    .name(resultSet.getString("SUPPLIER_NAME"))
                    .product(resultSet.getString("SUPPLIER_PRODUCT"))
                    .address(resultSet.getString("SUPPLIER_ADDRESS"))
                    .phoneNumber(resultSet.getString("SUPPLIER_PHONE"))
                    .email(resultSet.getString("SUPPLIER_EMAIL"))
                    .country(Country.builder().name(resultSet.getString("SUPPLIER_COUNTRY")).build())
                    .onlineSale(resultSet.getBoolean("ONLINE_SALE"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .build();

            supplierList.add(supplier);
        }
        return supplierList;
    }

    //findById
    @Override
    public Supplier findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM SUPPLIER WHERE SUPPLIER_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Supplier supplier = null;

        if (resultSet.next()) {
            supplier = Supplier
                    .builder()
                    .id(resultSet.getInt("SUPPLIER_ID"))
                    .name(resultSet.getString("SUPPLIER_NAME"))
                    .product(resultSet.getString("SUPPLIER_PRODUCT"))
                    .address(resultSet.getString("SUPPLIER_ADDRESS"))
                    .phoneNumber(resultSet.getString("SUPPLIER_PHONE"))
                    .email(resultSet.getString("SUPPLIER_EMAIL"))
                    .country(Country.builder().name(resultSet.getString("SUPPLIER_COUNTRY")).build())
                    .onlineSale(resultSet.getBoolean("ONLINE_SALE"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .build();
        }
        return supplier;
    }

    //findByName
    public List<Supplier> findByName(String name) throws Exception {
        List<Supplier> supplierList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM SUPPLIER WHERE SUPPLIER_NAME LIKE? ORDER BY SUPPLIER_ID");
        preparedStatement.setString(1, name + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Supplier supplier = Supplier
                    .builder()
                    .id(resultSet.getInt("SUPPLIER_ID"))
                    .name(resultSet.getString("SUPPLIER_NAME"))
                    .product(resultSet.getString("SUPPLIER_PRODUCT"))
                    .address(resultSet.getString("SUPPLIER_ADDRESS"))
                    .phoneNumber(resultSet.getString("SUPPLIER_PHONE"))
                    .email(resultSet.getString("SUPPLIER_EMAIL"))
                    .country(Country.builder().name(resultSet.getString("SUPPLIER_COUNTRY")).build())
                    .onlineSale(resultSet.getBoolean("ONLINE_SALE"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .build();
            supplierList.add(supplier);
        }
        return supplierList;
    }

    //close
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}

