package exportation.model.da;

import exportation.model.entity.Company;
import exportation.model.entity.Country;
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
public class CompanyDa implements AutoCloseable, CRUD<Company> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public CompanyDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }
    //save
    @Override
    public Company save(Company company) throws SQLException {
    company.setId(ConnectionProvider.getConnectionProvider().getNextId("COMPANY_SEQ"));

    preparedStatement = connection.prepareStatement("INSERT INTO COMPANY (COMPANY_ID, COMPANY_NAME, COMPANY_MANAGER, COMPANY_PRODUCT, COMPANY_ADDRESS, COMPANY_EMAIL, COMPANY_PHONE, COUNTRY_NAME) VALUES (?,?,?,?,?,?,?,?)");
    preparedStatement.setInt(1, company.getId());
    preparedStatement.setString(2, company.getName());
    preparedStatement.setString(3, String.valueOf(company.getManager()));
    preparedStatement.setString(4, company.getProduct());
    preparedStatement.setString(5, company.getAddress());
    preparedStatement.setString(6, company.getEmail());
    preparedStatement.setString(7, company.getPhone());
    preparedStatement.setString(8,String.valueOf(company.getCountry()));
    preparedStatement.execute();
    return company;
    }

    //edit
    @Override
    public Company edit(Company company) throws Exception {
            preparedStatement = connection.prepareStatement("UPDATE COMPANY SET COMPANY_NAME=?, COMPANY_MANAGER=?, COMPANY_PRODUCT=?, COMPANY_ADDRESS=?, COMPANY_EMAIL=?, COMPANY_PHONE=?, COUNTRY_NAME=? WHERE COMPANY_ID=? ");

            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, String.valueOf(company.getManager()));
            preparedStatement.setString(3, company.getProduct());
            preparedStatement.setString(4, company.getAddress());
            preparedStatement.setString(5, company.getEmail());
            preparedStatement.setString(6, company.getPhone());
            preparedStatement.setString(7,String.valueOf(company.getCountry()));
            preparedStatement.setInt(8, company.getId());
            preparedStatement.execute();
            return company;
    }

    //remove
    @Override
    public Company remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM COMPANY WHERE COMPANY_ID=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //findAll
    @Override
    public List<Company> findAll() throws Exception {
        List<Company> companyList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM COMPANY ORDER BY COMPANY_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            Company company = Company
                    .builder()
                    .id(resultSet.getInt("COMPANY_ID"))
                    .name(resultSet.getString("COMPANY_NAME"))
                    .manager((Person)resultSet.getObject("COMPANY_MANAGER"))
                    .product(resultSet.getString("COMPANY_PRODUCT"))
                    .address(resultSet.getString("COMPANY_ADDRESS"))
                    .email(resultSet.getString("COMPANY_EMAIL"))
                    .phone(resultSet.getString("COMPANY_PHONE"))
                    .country((Country)resultSet.getObject("COUNTRY_NAME"))
                    .build();
                    companyList.add(company);
        }
        return companyList;
    }

    //findById
    @Override
    public Company findById(int id) throws Exception {
       preparedStatement=connection.prepareStatement("SELECT * FROM COMPANY WHERE COMPANY_ID=?");
       preparedStatement.setInt(1, id);
       ResultSet resultSet = preparedStatement.executeQuery();
       Company company = null;

       if(resultSet.next()) {
         company = Company
                 .builder()
                 .id(resultSet.getInt("COMPANY_ID"))
                 .name(resultSet.getString("COMPANY_NAME"))
                 .manager((Person)resultSet.getObject("COMPANY_MANAGER"))
                 .product(resultSet.getString("COMPANY_PRODUCT"))
                 .address(resultSet.getString("COMPANY_ADDRESS"))
                 .email(resultSet.getString("COMPANY_EMAIL"))
                 .phone(resultSet.getString("COMPANY_PHONE"))
                 .country((Country)resultSet.getObject("COUNTRY_NAME"))
                 .build();

       }
        return company;
    }


    //findByName
    public List<Company> findByName (String name) throws Exception{
        List<Company> companyList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM COMPANY WHERE COMPANY_NAME LIKE? ORDER BY COMPANY_ID");
        preparedStatement.setString(1, name + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            Company company = Company
                    .builder()
                    .id(resultSet.getInt("COMPANY_ID"))
                    .name(resultSet.getString("COMPANY_NAME"))
                    .manager((Person)resultSet.getObject("COMPANY_MANAGER"))
                    .product(resultSet.getString("COMPANY_PRODUCT"))
                    .address(resultSet.getString("COMPANY_ADDRESS"))
                    .email(resultSet.getString("COMPANY_EMAIL"))
                    .phone(resultSet.getString("COMPANY_PHONE"))
                    .country((Country)resultSet.getObject("COUNTRY_NAME"))
                    .build();
            companyList.add(company);
        }
        return companyList;
    }


    @Override
    public void close() throws Exception {

    }
}
