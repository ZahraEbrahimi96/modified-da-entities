package exportation.model.da;

import exportation.model.entity.Item;
import exportation.model.entity.Payment;
import lombok.extern.log4j.Log4j;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class PaymentDa implements AutoCloseable, CRUD<Payment> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public PaymentDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    //save
    @Override
    public Payment save(Payment payment) throws Exception {
        payment.setId(ConnectionProvider.getConnectionProvider().getNextId("TRADE_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO TRADE (ID,STATUS,CORRESPONDENCES,CONTRACT,AGREEMENT,INVOICE) VALUES (?,?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setLong(2, payment.getTotalCost());
        preparedStatement.setFloat(3, payment.getTax());
        preparedStatement.setFloat(4, payment.getInsurance());
//        preparedStatement.setFloat(5, string.valueOf(payment.getCost()));
//        preparedStatement.setFloat(6,payment.);
        preparedStatement.execute();
        return payment;
    }

    //Edit
    @Override
    public Payment edit(Payment payment) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE TRADE SET STATUS=?, CORRESPONDENCES=?, CONTRACT=?, AGREEMENT=?, INVOICE=?, WHERE ID=?"
        );
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setString(2, payment.getStatus());
        preparedStatement.setString(3, payment.getCorrespondences());
        preparedStatement.setString(4, payment.getContract());
        preparedStatement.setString(5, payment.getAgreement());
        preparedStatement.setString(6, payment.getInvoice());
        preparedStatement.execute();
        return payment;
    }

    //Remove
    @Override
    public Payment remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM TRADE WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<Payment> findAll() throws Exception {
        List<Payment> paymentList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM TRADE ORDER BY ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Payment payment = Payment
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .status(resultSet.getString("STATUS"))
                    .correspondences(resultSet.getString("CORRESPONDENCES"))
                    .contract(resultSet.getString("CONTRACT"))
                    .agreement(resultSet.getString("AGREEMENT"))
                    .invoice(resultSet.getString("INVOICE"))
                    .build();

            paymentList.add(payment);
        }

        return paymentList;
    }

    //FindById
    @Override
    public Payment findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM TRADE WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Payment payment = null;
        if (resultSet.next()) {
            payment = Payment
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .status(resultSet.getString("STATUS"))
                    .correspondences(resultSet.getString("CORRESPONDENCES"))
                    .contract(resultSet.getString("CONTRACT"))
                    .agreement(resultSet.getString("AGREEMENT"))
                    .invoice(resultSet.getString("INVOICE"))
                    .build();
        }
        return payment;
    }

    //FindByClient
    public List<Payment> findByClient(String client) throws Exception {
        List<Payment> paymentList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM TRADE WHERE CLIENT LIKE? ORDER BY ID");
        preparedStatement.setString(1, client + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Payment payment = Payment
                    .builder()
                    .id(resultSet.getInt("ID"))

                    .build();

            paymentList.add(payment);
        }
        return paymentList;
    }

    //Close
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
