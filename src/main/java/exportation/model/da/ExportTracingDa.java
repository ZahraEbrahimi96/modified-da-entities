package exportation.model.da;

import exportation.model.entity.ExportTracing;
import exportation.model.entity.Trade;
import exportation.model.entity.Transportation;
import exportation.model.entity.enums.Status;
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
        exportTracing.setId(ConnectionProvider.getConnectionProvider().getNextId("EXPORT_TRACING_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO EXPORT_TRACING_TABLE (EXPORT_TRACING_ID, EXPORT_TRACING_LOADING_STATUS, EXPORT_TRACING_PREPAYMENT, EXPORT_TRACING_CHECKOUT,TRANSPORTATION_ID,TRADE_ID,EXPORT_DATE_TIME) VALUES (?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, exportTracing.getId());
        preparedStatement.setString(2, exportTracing.getLoadingStatus().toString());
        preparedStatement.setString(3, exportTracing.getPrePayment().toString());
        preparedStatement.setString(4, exportTracing.getCheckout().toString());
        preparedStatement.setInt(5, exportTracing.getTransportation().getId());
        preparedStatement.setInt(6, exportTracing.getTrade().getId());
        preparedStatement.setDate(7, Date.valueOf(exportTracing.getDate()));
        preparedStatement.execute();
        return exportTracing;
    }

    //Edit
    @Override
    public ExportTracing edit(ExportTracing exportTracing) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE EXPORT_TRACING_TABLE SET EXPORT_TRACING_LOADING_STATUS=?, EXPORT_TRACING_PREPAYMENT=?, EXPORT_TRACING_CHECKOUT=?,TRANSPORTATION_ID=?, TRADE_ID=?, EXPORT_DATE_TIME=? WHERE EXPORT_TRACING_ID=?"
        );

        preparedStatement.setString(1, exportTracing.getLoadingStatus().toString());
        preparedStatement.setString(2, exportTracing.getPrePayment().toString());
        preparedStatement.setString(3, exportTracing.getCheckout().toString());
        preparedStatement.setInt(4, exportTracing.getTransportation().getId());
        preparedStatement.setInt(5, exportTracing.getTrade().getId());
        preparedStatement.setDate(6, Date.valueOf(exportTracing.getDate()));
        preparedStatement.setInt(7, exportTracing.getId());
        preparedStatement.execute();
        return exportTracing;
    }

    //Remove
    @Override
    public ExportTracing remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM EXPORT_TRACING_TABLE WHERE EXPORT_TRACING_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<ExportTracing> findAll() throws Exception {
        List<ExportTracing> exportTracingList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM EXPORT_TRACING_TABLE ORDER BY EXPORT_TRACING_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            ExportTracing exportTracing = ExportTracing
                    .builder()
                    .id(resultSet.getInt("EXPORT_TRACING_ID"))
                    .loadingStatus(Status.valueOf(resultSet.getString("EXPORT_TRACING_LOADING_STATUS")))
                    .prePayment(Status.valueOf(resultSet.getString("EXPORT_TRACING_PREPAYMENT")))
                    .checkout(Status.valueOf(resultSet.getString("EXPORT_TRACING_CHECKOUT")))
                    .transportation(Transportation.builder().id(resultSet.getInt("TRANSPORTATION_ID")).build())
                    .trade(Trade.builder().id(resultSet.getInt("TRADE_ID")).build())
                    .date(resultSet.getDate("EXPORT_DATE_TIME").toLocalDate())
                    .build();

            exportTracingList.add(exportTracing);
        }

        return exportTracingList;
    }

    //FindById
    @Override
    public ExportTracing findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM EXPORT_TRACING_TABLE WHERE EXPORT_TRACING_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        ExportTracing exportTracing = null;
        if (resultSet.next()) {
            exportTracing = ExportTracing
                    .builder()
                    .id(resultSet.getInt("EXPORT_TRACING_ID"))
                    .loadingStatus(Status.valueOf(resultSet.getString("EXPORT_TRACING_LOADING_STATUS")))
                    .prePayment(Status.valueOf(resultSet.getString("EXPORT_TRACING_PREPAYMENT")))
                    .checkout(Status.valueOf(resultSet.getString("EXPORT_TRACING_CHECKOUT")))
                    .transportation(Transportation.builder().id(resultSet.getInt("TRANSPORTATION_ID")).build())
                    .trade(Trade.builder().id(resultSet.getInt("TRADE_ID")).build())
                    .date(resultSet.getDate("EXPORT_DATE_TIME").toLocalDate())
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
