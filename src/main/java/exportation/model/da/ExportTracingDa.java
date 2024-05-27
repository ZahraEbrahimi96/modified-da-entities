package exportation.model.da;
import exportation.model.entity.ExportTracing;
import exportation.model.entity.Person;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Timestamp.valueOf;

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
        preparedStatement = connection.prepareStatement("INSERT INTO EXPORTTRACING (EXPORT_T_ID, LOADING_STATUS, INVOICE, WAYBILL, PREPAYMENT, CHECKOUT) VALUES (?, ?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, exportTracing.getId());
        preparedStatement.setBoolean(2,exportTracing.isLoadingStatus());
        preparedStatement.setString(3, exportTracing.getInvoice());
        preparedStatement.setString(4, exportTracing.getWaybill());
        preparedStatement.setBoolean(5,exportTracing.isPrePayment());
        preparedStatement.setBoolean(6,exportTracing.isCheckout());
        preparedStatement.execute();
        return exportTracing;
    }

    //edit
    @Override
    public ExportTracing edit(ExportTracing exportTracing) throws Exception {
        preparedStatement = connection.prepareStatement("UPDATE EXPORTTRACING SET LOADING_STATUS=?, INVOICE=?, WAYBILL=?, PREPAYMENT=?, CHECKOUT=? WHERE EXPORT_T_ID=? ");
        preparedStatement.setBoolean(1,exportTracing.isLoadingStatus());
        preparedStatement.setString(2, exportTracing.getInvoice());
        preparedStatement.setString(3, exportTracing.getWaybill());
        preparedStatement.setBoolean(4,exportTracing.isPrePayment());
        preparedStatement.setBoolean(5,exportTracing.isCheckout());
        preparedStatement.setInt(6, exportTracing.getId());
        preparedStatement.execute();
        return exportTracing;
    }

    //remove
    @Override
    public ExportTracing remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM EXPORTTRACING WHERE EXPORT_T_ID=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //findAll
    @Override
    public List<ExportTracing> findAll() throws Exception {
        List<ExportTracing> exportTracingList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM EXPORTTRACING ORDER BY EXPORT_T_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            ExportTracing exportTracing = ExportTracing
                    .builder()
                    .id(resultSet.getInt("EXPORT_T-ID"))
                    .loadingStatus(resultSet.getBoolean("LOADING_STATUS"))
                    .invoice(resultSet.getString("INVOICE"))
                    .waybill(resultSet.getString("WAYBILL"))
                    .prePayment(resultSet.getBoolean("PREPAYMENT"))
                    .checkout(resultSet.getBoolean("CHECKOUT"))
                    .build();
            exportTracingList.add(exportTracing);
        }
        return  exportTracingList;
    }

    //findById
    @Override
    public ExportTracing findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM EXPORTTRACING WHERE EXPORT_T_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        ExportTracing exportTracing= null;

        if (resultSet.next()) {
            exportTracing = ExportTracing
                    .builder()
                    .id(resultSet.getInt("EXPORT_T-ID"))
                    .loadingStatus(resultSet.getBoolean("LOADING_STATUS"))
                    .invoice(resultSet.getString("INVOICE"))
                    .waybill(resultSet.getString("WAYBILL"))
                    .prePayment(resultSet.getBoolean("PREPAYMENT"))
                    .checkout(resultSet.getBoolean("CHECKOUT"))
                    .build();
        }
        return  exportTracing;
        }



    @Override
    public void close() throws Exception {

    }
}
//...