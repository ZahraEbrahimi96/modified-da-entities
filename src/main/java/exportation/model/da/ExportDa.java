package exportation.model.da;
import exportation.model.entity.Country;
import exportation.model.entity.Exports;
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
public class ExportDa implements AutoCloseable, CRUD<Exports> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public ExportDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }
    //save
    @Override
    public Exports save(Exports exports) throws SQLException {
        exports.setId(ConnectionProvider.getConnectionProvider().getNextId("EXPORT_SEQ"));

        preparedStatement = connection.prepareStatement("INSERT INTO EXPORTS (EXPORTS_ID, EXPORTS_HS_CODE, EXPORTS_COUNTRY, EXPORTS_QUANTITY, EXPORTS_USD_VALUE) VALUES (?,?,?,?,?)");
        preparedStatement.setInt(1, exports.getId());
        preparedStatement.setLong(2, exports.getHsCode());
        preparedStatement.setString(3, String.valueOf(exports.getCountry()));
        preparedStatement.setLong(4, exports.getQuantity());
        preparedStatement.setLong(5, exports.getUsdValue());
        preparedStatement.execute();
        return exports;
    }

    //edit
    @Override
    public Exports edit(Exports exports) throws Exception {
        preparedStatement = connection.prepareStatement("UPDATE EXPORTS SET EXPORTS_HS_CODE=?, EXPORTS_COUNTRY=?, EXPORTS_QUANTITY=?, EXPORTS_USD_VALUE=? WHERE EXPORTS_ID=? ");
        preparedStatement.setLong(1, exports.getHsCode());
        preparedStatement.setString(2, String.valueOf(exports.getCountry()));
        preparedStatement.setLong(3, exports.getQuantity());
        preparedStatement.setLong(4, exports.getUsdValue());
        preparedStatement.setInt(5, exports.getId());
        preparedStatement.execute();
        return exports;
    }

    //remove
    @Override
    public Exports remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM EXPORTS WHERE EXPORTS_ID=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //findAll
    @Override
    public List<Exports> findAll() throws Exception {
        List<Exports> exportsList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM EXPORTS ORDER BY EXPORTS_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            Exports exports = Exports
                    .builder()
                    .id(resultSet.getInt("EXPORT_ID"))
                    .hsCode(resultSet.getLong("EXPORT_HS_CODE"))
                    .country((Country) resultSet.getObject("EXPORT_COUNTRY"))
                    .quantity(resultSet.getLong("EXPORT_QUANTITY"))
                    .usdValue(resultSet.getLong("EXPORT_USD_VALUE"))
                    .build();

            exportsList.add(exports);
        }
        return exportsList;
    }

    //findById
    @Override
    public Exports findById(int id) throws Exception {
        preparedStatement=connection.prepareStatement("SELECT * FROM EXPORTS WHERE EXPORTS_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Exports exports = null;

        if(resultSet.next()) {
            exports = Exports
                    .builder()
                    .id(resultSet.getInt("EXPORT_ID"))
                    .hsCode(resultSet.getLong("EXPORT_HS_CODE"))
                    .country((Country) resultSet.getObject("EXPORT_COUNTRY"))
                    .quantity(resultSet.getLong("EXPORT_QUANTITY"))
                    .usdValue(resultSet.getLong("EXPORT_USD_VALUE"))
                    .build();
        }
        return exports;
    }


    //findByCountry
    public List<Exports> findByCountry (Country country) throws Exception{
        List<Exports> exportsList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM EXPORTS WHERE EXPORTS_COUNTRY LIKE? ORDER BY EXPORTS_ID");
        preparedStatement.setString(1, country + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){

            Exports exports = Exports
                    .builder()
                    .id(resultSet.getInt("EXPORT_ID"))
                    .hsCode(resultSet.getLong("EXPORT_HS_CODE"))
                    .country((Country) resultSet.getObject("EXPORT_COUNTRY"))
                    .quantity(resultSet.getLong("EXPORT_QUANTITY"))
                    .usdValue(resultSet.getLong("EXPORT_USD_VALUE"))
                    .build();
            exportsList.add(exports);
        }
        return exportsList;
    }


    @Override
    public void close() throws Exception {

    }
}

//...