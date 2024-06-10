package exportation.model.da;

import exportation.model.entity.*;
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
        payment.setId(ConnectionProvider.getConnectionProvider().getNextId("TRADE_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO PAYMENT (PAYMENT_ID, PAYMENT_TAX, PAYMENT_INSURANCE, PAYMENT_ITEM, PAYMENT_FREIGHT, PAYMENT_TARIFF) VALUES (?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setFloat(2, payment.getTax());
        preparedStatement.setFloat(3, payment.getInsurance());
        preparedStatement.setInt(4, payment.getItem().getId());
        preparedStatement.setFloat(5, payment.getTransportation().getFreight());
        preparedStatement.setInt(6, payment.getCountry().getTariff());
        preparedStatement.execute();
        return payment;
    }

    //Edit
    @Override
    public Payment edit(Payment payment) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE PAYMENT SET  PAYMENT_TAX=?, PAYMENT_INSURANCE=?, PAYMENT_ITEM=?, PAYMENT_FREIGHT=?,PAYMENT_TARIF=?, WHERE PAYMENT_ID=?"
        );
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setFloat(3, payment.getTax());
        preparedStatement.setFloat(4, payment.getInsurance());
        preparedStatement.setInt(4, payment.getItem().getId());
        preparedStatement.setFloat(6, payment.getTransportation().getFreight());
        preparedStatement.setInt(6, payment.getCountry().getTariff());
        preparedStatement.execute();
        return payment;
    }

    //Remove
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
                    .id(resultSet.getInt("ID"))
                    .tax(resultSet.getFloat("TAX"))
                    .insurance(resultSet.getFloat("INSURANCE"))
                    .item(Item.builder().id(resultSet.getInt("ITEM")).build())
                    .country(Country.builder().tariff(resultSet.getInt("TARIFF")).build())
                    .transportation(Transportation.builder().freight(resultSet.getInt("FREIGHT")).build())
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
                    .id(resultSet.getInt("ID"))
                    .tax(resultSet.getFloat("TAX"))
                    .insurance(resultSet.getFloat("INSURANCE"))
                    .item(Item.builder().id(resultSet.getInt("ITEM")).build())
                    .country(Country.builder().tariff(resultSet.getInt("TARIFF")).build())
                    .transportation(Transportation.builder().freight(resultSet.getInt("FREIGHT")).build())
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
