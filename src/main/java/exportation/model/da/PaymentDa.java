package exportation.model.da;

import exportation.model.entity.Info;
import exportation.model.entity.Item;
import exportation.model.entity.Payment;
import exportation.model.entity.Transportation;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDa implements AutoCloseable, CRUD<Payment> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public PaymentDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    //save
    @Override
    public Payment save(Payment payment) throws Exception {
        payment.setId(ConnectionProvider.getConnectionProvider().getNextId("PAYMENT_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO PAYMENT (PAYMENT_ID,TOTAL_COST,TAX,INSURANCE,PAYMENT_COST,PAYMENT_FREIGHT,PAYMENT_TARIFF) VALUES (?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setLong(2, payment.getTotalCost());
        preparedStatement.setFloat(3, payment.getTax());
        preparedStatement.setFloat(4, payment.getInsurance());
        preparedStatement.setFloat(5, payment.getItem().getCost());
        preparedStatement.setFloat(6, payment.getTransportation().getFreight());
        preparedStatement.setString(7, payment.getInfo().getTariff());
        preparedStatement.execute();
        return payment;
    }

    //edit
    @Override
    public Payment edit(Payment payment) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE PAYMENT SET TOTAL_COST=?, TAX=?, INSURANCE=?, PAYMENT_COST=?, PAYMENT_FREIGHT=?,PAYMENT_TARIFF=?, WHERE PAYMENT_ID=?"
        );
        preparedStatement.setLong(1, payment.getTotalCost());
        preparedStatement.setFloat(2, payment.getTax());
        preparedStatement.setFloat(3, payment.getInsurance());
        preparedStatement.setFloat(4, payment.getItem().getCost());
        preparedStatement.setFloat(5, payment.getTransportation().getFreight());
        preparedStatement.setString(6, payment.getInfo().getTariff());
        preparedStatement.setInt(7, payment.getId());
        preparedStatement.execute();
        return payment;
    }

    //remove
    @Override
    public Payment remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM PAYMENT WHERE PAYMENT_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<Payment> findAll() throws Exception {
        List<Payment> paymentList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM PAYMENT ORDER BY PAYMENT_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Payment payment = Payment
                    .builder()
                    .id(resultSet.getInt("PAYMENT_ID"))
                    .totalCost(resultSet.getLong("TOTALCOST"))
                    .tax(resultSet.getFloat("TAX"))
                    .insurance(resultSet.getFloat("INSURANCE"))
                    .item((Item)resultSet.getObject("PAYMENT_COST"))
                    .transportation((Transportation)resultSet.getObject("PAYMENT_FREIGHT"))
                    .info((Info)resultSet.getObject("PAYMENT_TARIFF"))
                    .build();

            paymentList.add(payment);
        }

        return paymentList;
    }

    //FindById
    @Override
    public Payment findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM PAYMENT WHERE PAYMENT_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Payment payment = null;
        if (resultSet.next()) {
            payment = Payment
                    .builder()
                    .id(resultSet.getInt("PAYMENT_ID"))
                    .totalCost(resultSet.getLong("TOTALCOST"))
                    .tax(resultSet.getFloat("TAX"))
                    .insurance(resultSet.getFloat("INSURANCE"))
                    .item((Item)resultSet.getObject("PAYMENT_COST"))
                    .transportation((Transportation)resultSet.getObject("PAYMENT_FREIGHT"))
                    .info((Info)resultSet.getObject("PAYMENT_TARIFF"))
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
//...