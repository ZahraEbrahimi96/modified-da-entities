package exportation.model.da;
import exportation.model.entity.Country;
import exportation.model.entity.Imports;
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
public class ImportDa implements AutoCloseable, CRUD<Imports> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public ImportDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    //save
    @Override
    public Imports save(Imports imports) throws SQLException {
        imports.setId(ConnectionProvider.getConnectionProvider().getNextId("IMPORTS_SEQ"));

        preparedStatement = connection.prepareStatement("INSERT INTO IMPORTS (IMPORTS_ID, IMPORTS_HS_CODE, IMPORTS_COUNTRY, IMPORTS_QUANTITY, IMPORTS_USD_VALUE) VALUES (?,?,?,?,?)");
        preparedStatement.setInt(1, imports.getId());
        preparedStatement.setLong(2, imports.getHsCode());
        preparedStatement.setString(3, String.valueOf(imports.getCountry()));
        preparedStatement.setLong(4, imports.getQuantity());
        preparedStatement.setLong(5, imports.getUsdValue());
        preparedStatement.execute();
        return imports;
    }

    //edit
    @Override
    public Imports edit(Imports imports) throws Exception {
        preparedStatement = connection.prepareStatement("UPDATE IMPORTS SET IMPORTS_HS_CODE=?, IMPORTS_COUNTRY=?, IMPORTS_QUANTITY=?, IMPORTS_USD_VALUE=? WHERE IMPORTS_ID=? ");
        preparedStatement.setLong(1, imports.getHsCode());
        preparedStatement.setString(2, String.valueOf(imports.getCountry()));
        preparedStatement.setLong(3, imports.getQuantity());
        preparedStatement.setLong(4, imports.getUsdValue());
        preparedStatement.setInt(5, imports.getId());
        preparedStatement.execute();
        return imports;
    }

    //remove
    @Override
    public Imports remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM IMPORTS WHERE IMPORTS_ID=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //findAll
    @Override
    public List<Imports> findAll() throws Exception {
        List<Imports> importsList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM IMPORTS ORDER BY IMPORTS_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            Imports imports = Imports
                    .builder()
                    .id(resultSet.getInt("IMPORT_ID"))
                    .hsCode(resultSet.getLong("IMPORT_HS_CODE"))
                    .country((Country) resultSet.getObject("IMPORT_COUNTRY"))
                    .quantity(resultSet.getLong("IMPORT_QUANTITY"))
                    .usdValue(resultSet.getLong("IMPORT_USD_VALUE"))
                    .build();

            importsList.add(imports);
        }
        return importsList;
    }

    //findById
    @Override
    public Imports findById(int id) throws Exception {
        preparedStatement=connection.prepareStatement("SELECT * FROM IMPORTS WHERE IMPORTS_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Imports imports = null;

        if(resultSet.next()) {
            imports = Imports
                    .builder()
                    .id(resultSet.getInt("IMPORT_ID"))
                    .hsCode(resultSet.getLong("IMPORT_HS_CODE"))
                    .country((Country) resultSet.getObject("IMPORT_COUNTRY"))
                    .quantity(resultSet.getLong("IMPORT_QUANTITY"))
                    .usdValue(resultSet.getLong("IMPORT_USD_VALUE"))
                    .build();
        }
        return imports;
    }


    //findByCountry
    public List<Imports> findByCountry (Country country) throws Exception{
        List<Imports> importsList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM IMPORTS WHERE IMPORTS_COUNTRY LIKE? ORDER BY IMPORTS_ID");
        preparedStatement.setString(1, country + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){

            Imports imports = Imports
                    .builder()
                    .id(resultSet.getInt("IMPORT_ID"))
                    .hsCode(resultSet.getLong("IMPORT_HS_CODE"))
                    .country((Country) resultSet.getObject("IMPORT_COUNTRY"))
                    .quantity(resultSet.getLong("IMPORT_QUANTITY"))
                    .usdValue(resultSet.getLong("IMPORT_USD_VALUE"))
                    .build();
            importsList.add(imports);
        }
        return importsList;
    }


    @Override
    public void close() throws Exception {

    }
}