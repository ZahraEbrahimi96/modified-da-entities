package exportation.model.da;

import exportation.model.entity.ExportTracing;
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
                "INSERT INTO EXPORTTRACING (TRACING_ID,TRACING_LODINGSTATUS,TRACING_PREPAYMENT,TRACING_CHECKOUT,TRACING_WAYBILL,TRACING_INVOICE) VALUES (?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, exportTracing.getId());
        preparedStatement.setBoolean(2, exportTracing.isLoadingStatus());
        preparedStatement.setBoolean(3, exportTracing.isPrePayment());
        preparedStatement.setBoolean(4, exportTracing.isCheckout());
        preparedStatement.setString(5, exportTracing.getWaybill());
        preparedStatement.setString(6, exportTracing.getInvoice());

        preparedStatement.execute();
        return exportTracing;
    }

    //Edit
    @Override
    public ExportTracing edit(ExportTracing exportTracing) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE EXPORTTRACING SET TRACING_LODINGSTATUS=?, TRACING_PREPAYMENT=?, TRACING_CHECKOUT=?, TRACING_WAYBILL=?,TRACING_INVOICE=?, WHERE TRACING_ID=?"
        );
        preparedStatement.setInt(1, exportTracing.getId());
        preparedStatement.setBoolean(2, exportTracing.isLoadingStatus());
        preparedStatement.setBoolean(3, exportTracing.isPrePayment());
        preparedStatement.setBoolean(4, exportTracing.isCheckout());
        preparedStatement.setString(5, exportTracing.getWaybill());
        preparedStatement.setString(6, exportTracing.getInvoice());

        preparedStatement.execute();
        return exportTracing;
    }

    //Remove
    @Override
    public ExportTracing remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM EXPORTTRACING WHERE TRACING_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<ExportTracing> findAll() throws Exception {
        List<ExportTracing> exportTracingList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM EXPORTTRACING ORDER BY TRACING_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            ExportTracing exportTracing = ExportTracing
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .loadingStatus(resultSet.getBoolean("LODINGSTATUS"))
                    .prePayment(resultSet.getBoolean("PREPAYMENT"))
                    .checkout(resultSet.getBoolean("CHECKOUT"))
                    .waybill(resultSet.getString("WAYBILL"))
                    .invoice(resultSet.getString("INVOICE"))
                    .build();

            exportTracingList.add(exportTracing);
        }

        return exportTracingList;
    }

    //FindById
    @Override
    public ExportTracing findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM EXPORTTRACING WHERE TRACING_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        ExportTracing exportTracing = null;
        if (resultSet.next()) {
            exportTracing = ExportTracing
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .loadingStatus(resultSet.getBoolean("LODINGSTATUS"))
                    .prePayment(resultSet.getBoolean("PREPAYMENT"))
                    .checkout(resultSet.getBoolean("CHECKOUT"))
                    .waybill(resultSet.getString("WAYBILL"))
                    .invoice(resultSet.getString("INVOICE"))
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
