package exportation.model.da;

import exportation.model.entity.ExportTracing;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.sql.Timestamp.valueOf;

public class ExportTracingDa implements AutoCloseable, CRUD<ExportTracing> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public ExportTracingDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    @Override
    public ExportTracing save(ExportTracing exportTracing) throws Exception {
        exportTracing.setExportId(ConnectionProvider.getConnectionProvider().getNextId("EXPORT_TRACING_SEQ"));
        preparedStatement = connection.prepareStatement("INSERT INTO EXPORTTRACING (EXPORT_ID, TRANSFER_TIME, RECEIVE_TIME, LOADING_STATUS, INVOICE, WAYBILL, PREPAYMENT, CHECKOUT, TRANSFEREE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, exportTracing.getExportId());
        preparedStatement.setTimestamp(2, valueOf(exportTracing.getTransferTime()));
        preparedStatement.setTimestamp(3, valueOf(exportTracing.getReceiveTime()));
        preparedStatement.setBoolean(4,exportTracing.isLoadingStatus());
        preparedStatement.setString(5, exportTracing.getInvoice());
        preparedStatement.setString(6, exportTracing.getWaybill());
        preparedStatement.setBoolean(7,exportTracing.isPrePayment());
        preparedStatement.setBoolean(8,exportTracing.isCheckout());
        preparedStatement.setString(9, String.valueOf(exportTracing.getTransferee()));
        preparedStatement.execute();
        return exportTracing;
    }

    @Override
    public ExportTracing edit(ExportTracing exportTracing) throws Exception {
        preparedStatement = connection.prepareStatement("UPDATE EXPORTTRACING SET TRANSFER_TIME=?, RECEIVE_TIME=?, LOADING_STATUS=?, INVOICE=?, WAYBILL=?, PREPAYMENT=?, CHECKOUT=?, TRANSFEREE=? WHERE EXPORT_ID=? ");
        preparedStatement.setTimestamp(1, valueOf(exportTracing.getTransferTime()));
        preparedStatement.setTimestamp(2, valueOf(exportTracing.getReceiveTime()));
        preparedStatement.setBoolean(3,exportTracing.isLoadingStatus());
        preparedStatement.setString(4, exportTracing.getInvoice());
        preparedStatement.setString(5, exportTracing.getWaybill());
        preparedStatement.setBoolean(6,exportTracing.isPrePayment());
        preparedStatement.setBoolean(7,exportTracing.isCheckout());
        preparedStatement.setString(8, String.valueOf(exportTracing.getTransferee()));
        preparedStatement.setInt(9, exportTracing.getExportId());
        preparedStatement.execute();
        return exportTracing;
    }

    @Override
    public ExportTracing remove(int exportId) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM EXPORTTRACING WHERE EXPORT_ID=?");
        preparedStatement.setInt(1, exportId);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<ExportTracing> findAll() throws Exception {
        List<ExportTracing> exportTracingList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM EXPORTTRACING ORDER BY EXPORT_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            ExportTracing exportTracing = ExportTracing
                    .builder()
                    .exportId(resultSet.getInt("EXPORT-ID"))
                    .transferTime(resultSet.getTimestamp("TRANSFER_TIME"))
                    .receiveTime(resultSet.getTimestamp("RECEIVE_TIME"))
                    .loadingStatus(resultSet.getBoolean("LOADING_STATUS"))
                    .invoice(resultSet.getString("INVOICE"))
                    .waybill(resultSet.getString("WAYBILL"))
                    .prePayment(resultSet.getBoolean("PREPAYMENT"))
                    .checkout(resultSet.getBoolean("CHECKOUT"))
                    .transferee(resultSet.getString("TRANSFEREE"))
                    .build();
            exportTracingList.add(exportTracing);
        }
        return  exportTracingList;
    }

    @Override
    public ExportTracing findById(int exportId) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM EXPORTTRACING WHERE EXPORT_ID=?");
        preparedStatement.setInt(1, exportId);
        ResultSet resultSet = preparedStatement.executeQuery();
        ExportTracing exportTracing= null;

        if (resultSet.next()) {
            exportTracing = ExportTracing
                    .builder()
                    .exportId(resultSet.getInt("EXPORT-ID"))
                    .transferTime(resultSet.getTimestamp("TRANSFER_TIME"))
                    .receiveTime(resultSet.getTimestamp("RECEIVE_TIME"))
                    .loadingStatus(resultSet.getBoolean("LOADING_STATUS"))
                    .invoice(resultSet.getString("INVOICE"))
                    .waybill(resultSet.getString("WAYBILL"))
                    .prePayment(resultSet.getBoolean("PREPAYMENT"))
                    .checkout(resultSet.getBoolean("CHECKOUT"))
                    .transferee(resultSet.getString("TRANSFEREE"))
                    .build();;
        }
        return  exportTracing;
        }



    @Override
    public void close() throws Exception {

    }
}
