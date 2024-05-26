package exportation.model.da;
import exportation.model.entity.Transportation;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.sql.Timestamp.valueOf;

public class TransportationDa implements AutoCloseable, CRUD<Transportation> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public TransportationDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    @Override
    public Transportation save(Transportation transportation) throws Exception {
        transportation.setTransportationId(ConnectionProvider.getConnectionProvider().getNextId("TRANSPORTATION_SEQ"));
        preparedStatement = connection.prepareStatement("INSERT INTO TRANSPORTATION (TRANSPORTATION_ID, TRANSPORTATION_TYPE, DIRECTION, COUNTRY, ORIGIN, FREIGHT, ITEM) VALUES (?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, transportation.getTransportationId());
        preparedStatement.setString(2,(String.valueOf(transportation.getTransportType())));
        preparedStatement.setString(3, transportation.getDirection());
        preparedStatement.setString(4,(String.valueOf(transportation.getCountry())));
        preparedStatement.setString(5, transportation.getOrigin());
        preparedStatement.setFloat(6, transportation.getFreight());
        preparedStatement.setString(7,(String.valueOf(transportation.getItem())));
        preparedStatement.execute();
        return transportation;
    }

    @Override
    public Transportation edit(Transportation transportation) throws Exception {
        preparedStatement = connection.prepareStatement("UPDATE TRANSPORTATION SET TRANSFER_TIME=?, RECEIVE_TIME=?, LOADING_STATUS=?, INVOICE=?, WAYBILL=?, PREPAYMENT=?, CHECKOUT=?, TRANSFEREE=? WHERE EXPORT_ID=? ");
        preparedStatement.setTimestamp(1, valueOf(transportation.getTransferTime()));
        preparedStatement.setTimestamp(2, valueOf(transportation.getReceiveTime()));
        preparedStatement.setBoolean(3,transportation.isLoadingStatus());
        preparedStatement.setString(4, transportation.getInvoice());
        preparedStatement.setString(5, transportation.getWaybill());
        preparedStatement.setBoolean(6,transportation.isPrePayment());
        preparedStatement.setBoolean(7,transportation.isCheckout());
        preparedStatement.setString(8, String.valueOf(transportation.getTransferee()));
        preparedStatement.setInt(9, transportation.getExportId());
        preparedStatement.execute();
        return transportation;
    }

    @Override
    public Transportation remove(int exportId) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM EXPORTTRACING WHERE EXPORT_ID=?");
        preparedStatement.setInt(1, exportId);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Transportation> findAll() throws Exception {
        List<Transportation> transportationList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM EXPORTTRACING ORDER BY EXPORT_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Transportation transportation = Transportation
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
            transportationList.add(transportation);
        }
        return  transportationList;
    }

    @Override
    public Transportation findById(int exportId) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM transportation WHERE EXPORT_ID=?");
        preparedStatement.setInt(1, exportId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Transportation transportation= null;

        if (resultSet.next()) {
            transportation = Transportation
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
        return  transportation;
    }



    @Override
    public void close() throws Exception {

    }
}


