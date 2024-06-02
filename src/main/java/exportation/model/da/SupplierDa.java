package exportation.model.da;

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

        preparedStatement = connection.prepareStatement("INSERT INTO SUPPLIER (SUPPLIER_ID, SUPPLIER_NAME, SUPPLIER_MANAGER, SUPPLIER_PRODUCT, SUPPLIER_ADDRESS, SUPPLIER_EMAIL, SUPPLIER_PHONE, COUNTRY_NAME, ONLINE_SALE) VALUES (?,?,?,?,?,?,?,?,?)");
        preparedStatement.setInt(1, supplier.getId());
        preparedStatement.setString(2, supplier.getName());
        preparedStatement.setBoolean(3, supplier.isOnlineSale());
        preparedStatement.execute();
        return supplier;
    }

    //edit
    @Override
    public Supplier edit(Supplier supplier) throws Exception {
        preparedStatement = connection.prepareStatement("UPDATE SUPPLIER SET SUPPLIER_NAME=?, SUPPLIER_MANAGER=?, SUPPLIER_PRODUCT=?, SUPPLIER_ADDRESS=?, SUPPLIER_EMAIL=?, SUPPLIER_PHONE=?, COUNTRY_NAME=, ONLINE_SALE=? WHERE SUPPLIER_ID=? ");

        preparedStatement.setInt(1, supplier.getId());
        preparedStatement.setString(2, supplier.getName());
        preparedStatement.setBoolean(3, supplier.isOnlineSale());
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
                    .onlineSale(resultSet.getBoolean("ONLINE_SALE"))
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
                    .onlineSale(resultSet.getBoolean("ONLINE_SALE"))
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
                    .onlineSale(resultSet.getBoolean("ONLINE_SALE"))
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
