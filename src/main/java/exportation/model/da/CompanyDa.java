package exportation.model.da;

import exportation.model.entity.Company;
import exportation.model.entity.enums.RoleAccess;
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

    @Override
    public Company save(Company company) throws SQLException {
    company.setCompanyId(ConnectionProvider.getConnectionProvider().getNextId("COMPANY_SEQ"));

    preparedStatement = connection.prepareStatement("INSERT INTO COMPANY (COMPANY_ID, COMPANY_NAME, COMPANY_TYPE, COMPANY_MANAGER, COMPANY_PRODUCT, COMPANY_ADDRESS, COMPANY_EMAIL, COMPANY_PHONE, COUNTRY_NAME) VALUES (?, ?,?,?,?,?,?,?)");
    preparedStatement.setInt(1, company.getCompanyId());
    preparedStatement.setString(2, company.getCompanyName());
    preparedStatement.setString(3, String.valueOf(company.getCompanyType()));
    preparedStatement.setString(4, String.valueOf(company.getCompanyManager()));
    preparedStatement.setString(5, company.getCompanyProduct());
    preparedStatement.setString(6, company.getCompanyAddress());
    preparedStatement.setString(7, company.getCompanyEmail());
    preparedStatement.setString(8, company.getCompanyPhone());
    preparedStatement.setString(9,String.valueOf(company.getCountryName()));
    preparedStatement.execute();
    return company;
    }

    @Override
    public Company edit(Company company) throws Exception {
            preparedStatement = connection.prepareStatement("UPDATE COMPANY SET COMPANY_NAME=?, COMPANY_TYPE=?, COMPANY_MANAGER=?, COMPANY_PRODUCT=?, COMPANY_ADDRESS=?, COMPANY_EMAIL=?, COMPANY_PHONE=?, COUNTRY_NAME=? WHERE COMPANY_ID=? ");

            preparedStatement.setString(1, company.getCompanyName());
            preparedStatement.setString(2, String.valueOf(company.getCompanyType()));
            preparedStatement.setString(3, String.valueOf(company.getCompanyManager()));
            preparedStatement.setString(4, company.getCompanyProduct());
            preparedStatement.setString(5, company.getCompanyAddress());
            preparedStatement.setString(6, company.getCompanyEmail());
            preparedStatement.setString(7, company.getCompanyPhone());
            preparedStatement.setString(8,String.valueOf(company.getCountryName()));
            preparedStatement.setInt(9, company.getCompanyId());
            preparedStatement.execute();
            return company;
    }

    @Override
    public Company remove(int companyId) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM COMPANY WHERE COMPANY_ID=?");
        preparedStatement.setInt(1, companyId);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Company> findAll() throws Exception {
        List<Company> companyList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM COMPANY ORDER BY COMPANY_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            Company company = Company
                    .builder()
                    .companyId(resultSet.getInt("COMPANY_ID"))
                    .companyName(resultSet.getString("COMPANY_NAME"))
                    .companyType(RoleAccess.valueOf(resultSet.getString("COMPANY_TYPE")))
                    .companyManager(resultSet.getString("COMPANY_MANAGER"))
                    .companyProduct(resultSet.getString("COMPANY_PRODUCT"))
                    .companyAddress(resultSet.getString("COMPANY_ADDRESS"))
                    .companyEmail(resultSet.getString("COMPANY_EMAIL"))
                    .companyPhone(resultSet.getString("COMPANY_PHONE"))
                    .countryName(resultSet.getString("COUNTRY_NAME"))
                    .build();

                    companyList.add(company);
        }
        return companyList;
    }

    @Override
    public Company findById(int companyId) throws Exception {
       preparedStatement=connection.prepareStatement("SELECT * FROM COMPANY WHERE COMPANY_ID=?");
       preparedStatement.setInt(1, companyId);
       ResultSet resultSet = preparedStatement.executeQuery();
       Company company = null;

       if(resultSet.next()) {
         company = Company
                 .builder()
                 .companyId(resultSet.getInt("COMPANY_ID"))
                 .companyName(resultSet.getString("COMPANY_NAME"))
                 .companyType(RoleAccess.valueOf(resultSet.getString("COMPANY_TYPE")))
                 .companyManager(resultSet.getString("COMPANY_MANAGER"))
                 .companyProduct(resultSet.getString("COMPANY_PRODUCT"))
                 .companyAddress(resultSet.getString("COMPANY_ADDRESS"))
                 .companyEmail(resultSet.getString("COMPANY_EMAIL"))
                 .companyPhone(resultSet.getString("COMPANY_PHONE"))
                 .countryName(resultSet.getString("COUNTRY_NAME"))
                 .build();
       }
        return company;
    }


    public List<Company> findByName (String companyName) throws Exception{
        List<Company> companyList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM COMPANY WHERE COMPANY_NAME LIKE? ORDER BY COMPANY_ID");
        preparedStatement.setString(1, companyName + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            Company company = Company
                    .builder()
                    .companyId(resultSet.getInt("COMPANY_ID"))
                    .companyName(resultSet.getString("COMPANY_NAME"))
                    .companyType(RoleAccess.valueOf(resultSet.getString("COMPANY_TYPE")))
                    .companyManager(resultSet.getString("COMPANY_MANAGER"))
                    .companyProduct(resultSet.getString("COMPANY_PRODUCT"))
                    .companyAddress(resultSet.getString("COMPANY_ADDRESS"))
                    .companyEmail(resultSet.getString("COMPANY_EMAIL"))
                    .companyPhone(resultSet.getString("COMPANY_PHONE"))
                    .countryName(resultSet.getString("COUNTRY_NAME"))
                    .build();
            companyList.add(company);
        }
        return companyList;
    }


    @Override
    public void close() throws Exception {

    }
}
