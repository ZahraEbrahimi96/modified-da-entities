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
                "INSERT INTO PAYMENT (ID,TOTALCOST,TAX,INSURANCE,COST,FREIGHT,TARIF) VALUES (?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setLong(2, payment.getTotalCost());
        preparedStatement.setFloat(3, payment.getTax());
        preparedStatement.setFloat(4, payment.getInsurance());
        //      5,6,7 write
        preparedStatement.execute();
        return payment;
    }

    //Edit
    @Override
    public Payment edit(Payment payment) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE PAYMENT SET TOTALCOST=?, TAX=?, INSURANCE=?, COST=?, FREIGHT=?,TARIF=?, WHERE ID=?"
        );
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setLong(2, payment.getTotalCost());
        preparedStatement.setFloat(3, payment.getTax());
        preparedStatement.setFloat(4, payment.getInsurance());
        //      5,6,7 write
        preparedStatement.execute();
        return payment;
    }

    //Remove
    @Override
    public Payment remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM PAYMENT WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<Payment> findAll() throws Exception {
        List<Payment> paymentList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM PAYMENT ORDER BY ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Payment payment = Payment
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .totalCost(resultSet.getLong("TOTALCOST"))
                    .tax(resultSet.getFloat("TAX"))
                    .insurance(resultSet.getFloat("INSURANCE"))
                    //      5,6,7 write
                    .build();

            paymentList.add(payment);
        }

        return paymentList;
    }

    //FindById
    @Override
    public Payment findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM PAYMENT WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Payment payment = null;
        if (resultSet.next()) {
            payment = Payment
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .totalCost(resultSet.getLong("TOTALCOST"))
                    .tax(resultSet.getFloat("TAX"))
                    .insurance(resultSet.getFloat("INSURANCE"))
                    //      5,6,7 write
                    .build();
        }
        return payment;
    }

    //Close
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}