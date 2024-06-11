package exportation.model.da;

import exportation.model.entity.ExportTracing;
import exportation.model.entity.Trade;
import exportation.model.entity.Transportation;
import lombok.extern.log4j.Log4j;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class ExportTracingDa implements AutoCloseable, CRUD<ExportTracing> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public ExportTracingDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    //save
    @Override
    public ExportTracing save(ExportTracing exportTracing) throws Exception {
        exportTracing.setId(ConnectionProvider.getConnectionProvider().getNextId("EXPORTTRACING_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO EXPORT_TRACING_TABLE (EXPORT_ID, EXPORT_LOADINGSTATUS, EXPORT_PREPAYMENT, EXPORT_CHECKOUT, EXPORT_TRANSPORT_ID, EXPORT_TRADE_ID) VALUES (?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, exportTracing.getId());
        preparedStatement.setBoolean(2, exportTracing.isLoadingStatus());
        preparedStatement.setBoolean(3, exportTracing.isPrePayment());
        preparedStatement.setBoolean(4, exportTracing.isCheckout());
        preparedStatement.setInt(5, exportTracing.getTransportation().getId());
        preparedStatement.setInt(6, exportTracing.getTrade().getId());

        preparedStatement.execute();
        return exportTracing;
    }

    //Edit
    @Override
    public ExportTracing edit(ExportTracing exportTracing) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE EXPORT_TRACING_TABLE SET EXPORT_LOADINGSTATUS=?, EXPORT_PREPAYMENT=?, EXPORT_CHECKOUT=?, EXPORT_TRANSPORT_ID=?, EXPORT_TRADE_ID=?, WHERE EXPORT_ID=?"
        );
        preparedStatement.setBoolean(1, exportTracing.isLoadingStatus());
        preparedStatement.setBoolean(2, exportTracing.isPrePayment());
        preparedStatement.setBoolean(3, exportTracing.isCheckout());
        preparedStatement.setInt(4, exportTracing.getTransportation().getId());
        preparedStatement.setInt(5, exportTracing.getTrade().getId());
        preparedStatement.setInt(6, exportTracing.getId());

        preparedStatement.execute();
        return exportTracing;
    }

    //Remove
    @Override
    public ExportTracing remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM EXPORT_TRACING_TABLE WHERE EXPORT_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<ExportTracing> findAll() throws Exception {
        List<ExportTracing> exportTracingList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM EXPORT_TRACING_TABLE ORDER BY EXPORT_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            ExportTracing exportTracing = ExportTracing
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .loadingStatus(resultSet.getBoolean("LOADINGSTATUS"))
                    .prePayment(resultSet.getBoolean("PREPAYMENT"))
                    .checkout(resultSet.getBoolean("CHECKOUT"))
                    .transportation(Transportation.builder().id(resultSet.getInt("EXPORT_TRANSPORT_ID")).build())
                    .trade(Trade.builder().id(resultSet.getInt("EXPORT_TRADE_ID")).build())
                    .build();

            exportTracingList.add(exportTracing);
        }

        return exportTracingList;
    }

    //FindById
    @Override
    public ExportTracing findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM EXPORT_TRACING_TABLE WHERE EXPORT_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        ExportTracing exportTracing = null;
        if (resultSet.next()) {
            exportTracing = ExportTracing
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .loadingStatus(resultSet.getBoolean("LOADINGSTATUS"))
                    .prePayment(resultSet.getBoolean("PREPAYMENT"))
                    .checkout(resultSet.getBoolean("CHECKOUT"))
                    .transportation(Transportation.builder().id(resultSet.getInt("EXPORT_TRANSPORT_ID")).build())
                    .trade(Trade.builder().id(resultSet.getInt("EXPORT_TRADE_ID")).build())
                    .build();
        }
        return exportTracing;
    }

    //Close
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
