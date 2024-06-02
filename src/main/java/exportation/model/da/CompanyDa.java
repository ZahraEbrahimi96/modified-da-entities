package exportation.model.da;

import exportation.model.entity.Company;
import lombok.extern.log4j.Log4j;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;

import java.sql.*;
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
    public Company save(Company company) throws Exception {
        company.setId(ConnectionProvider.getConnectionProvider().getNextId("COMPANY_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO COMPANY (COMPANY_ID,COMPANY_NAME,COMPANY_PRODUCT,COMPANY_ADDRESS,COMPANY_EMAIL,COMPANY_PHONE_NUMBER) VALUES (?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, company.getId());
        preparedStatement.setString(2, company.getName());
        preparedStatement.setString(3, company.getProduct());
        preparedStatement.setString(4, company.getAddress());
        preparedStatement.setString(5, company.getEmail());
        preparedStatement.setString(6, company.getPhoneNumber());
        preparedStatement.execute();
        return company;
    }

    //Edit
    @Override
    public Company edit(Company company) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE COMPANY SET COMPANY_NAME=?, COMPANY_PRODUCT=?, COMPANY_ADDRESS=?,COMPANY_EMAIL=?,COMPANY_PHONE_NUMBER, WHERE COMPANY_ID=?"
        );
        preparedStatement.setInt(1, company.getId());
        preparedStatement.setString(2, company.getName());
        preparedStatement.setString(3, company.getProduct());
        preparedStatement.setString(4, company.getAddress());
        preparedStatement.setString(5, company.getEmail());
        preparedStatement.setString(6, company.getPhoneNumber());
        preparedStatement.execute();
        return company;
    }

    //Remove
    @Override
    public Company remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM COMPANY WHERE COMPANY_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<Company> findAll() throws Exception {
        List<Company> companyList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM COMPANY ORDER BY COMPANY_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Company company = Company
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .product(resultSet.getString("PRODUCT"))
                    .address(resultSet.getString("ADDRESS"))
                    .email(resultSet.getString("EMAIL"))
                    .phoneNumber(resultSet.getString("PHONENUMBER"))
                    .build();

            companyList.add(company);
        }
        return companyList;
    }

    //FindById
    @Override
    public Company findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM COMPANY WHERE COMPANY_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Company company = null;
        if (resultSet.next()) {
            company = Company
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .product(resultSet.getString("PRODUCT"))
                    .address(resultSet.getString("ADDRESS"))
                    .email(resultSet.getString("EMAIL"))
                    .phoneNumber(resultSet.getString("PHONENUMBER"))
                    .build();
        }
        return company;
    }

    //Close
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
