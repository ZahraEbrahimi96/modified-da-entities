package exportation.model.da;

import exportation.model.entity.*;
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
        payment.setId(ConnectionProvider.getConnectionProvider().getNextId("PAYMENT_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO PAYMENT_TABLE (PAYMENT_ID, PAYMENT_TAX,PAYMENT_INSURANCE,ITEM_COST,ITEM_ID,TRANSPORTATION_FREIGHT,TRANSPORTATION_ID,COUNTRY_TARIFF,COUNTRY_ID) VALUES (?,?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setFloat(2, payment.getTax());
        preparedStatement.setFloat(3, payment.getInsurance());
        preparedStatement.setFloat(4, payment.getItem().getCost());
        preparedStatement.setInt(5, payment.getItem().getId());
        preparedStatement.setFloat(6,payment.getTransportation().getFreight());
        preparedStatement.setInt(7, payment.getTransportation().getId());
        preparedStatement.setInt(8, payment.getCountry().getTariff());
        preparedStatement.setInt(9, payment.getCountry().getId());
        preparedStatement.execute();
        return payment;
    }

    //Edit
    @Override
    public Payment edit(Payment payment) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE PAYMENT_TABLE SET PAYMENT_TAX=? , PAYMENT_INSURANCE=? ,ITEM_COST=?, ITEM_ID=? ,TRANSPORTATION_FREIGHT=?, TRANSPORTATION_ID=? ,COUNTRY_TARIFF=?, COUNTRY_ID=? WHERE PAYMENT_ID=?"
        );
        preparedStatement.setFloat(1, payment.getTax());
        preparedStatement.setFloat(2, payment.getInsurance());
        preparedStatement.setFloat(3, payment.getItem().getCost());
        preparedStatement.setInt(4, payment.getItem().getId());
        preparedStatement.setFloat(5, payment.getTransportation().getFreight());
        preparedStatement.setInt(6, payment.getTransportation().getId());
        preparedStatement.setInt(7, payment.getCountry().getTariff());
        preparedStatement.setInt(8, payment.getCountry().getId());
        preparedStatement.setInt(9, payment.getId());
        preparedStatement.execute();
        return payment;
    }

    //Remove
    @Override
    public Payment remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM PAYMENT_TABLE WHERE PAYMENT_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<Payment> findAll() throws Exception {
        List<Payment> paymentList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM PAYMENT_TABLE ORDER BY PAYMENT_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Payment payment = Payment
                    .builder()
                    .id(resultSet.getInt("PAYMENT_ID"))
                    .tax(resultSet.getFloat("PAYMENT_TAX"))
                    .insurance(resultSet.getFloat("PAYMENT_INSURANCE"))
                    .item(Item.builder().cost(resultSet.getInt("ITEM_COST")).build())
                    .item(Item.builder().id(resultSet.getInt("ITEM_ID")).build())
                    .transportation(Transportation.builder().freight(resultSet.getFloat("TRANSPORTATION_FREIGHT")).build())
                    .transportation(Transportation.builder().id(resultSet.getInt("TRANSPORTATION_ID")).build())
                    .country(Country.builder().tariff(resultSet.getInt("COUNTRY_TARIFF")).build())
                    .country(Country.builder().id(resultSet.getInt("COUNTRY_ID")).build())
                    .build();

            paymentList.add(payment);
        }

        return paymentList;
    }

    //FindById
    @Override
    public Payment findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM PAYMENT_TABLE WHERE PAYMENT_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Payment payment = null;
        if (resultSet.next()) {
            payment = Payment
                    .builder()
                    .id(resultSet.getInt("PAYMENT_ID"))
                    .tax(resultSet.getFloat("PAYMENT_TAX"))
                    .insurance(resultSet.getFloat("PAYMENT_INSURANCE"))
                    .item(Item.builder().cost(resultSet.getInt("ITEM_COST")).build())
                    .item(Item.builder().id(resultSet.getInt("ITEM_ID")).build())
                    .transportation(Transportation.builder().freight(resultSet.getFloat("TRANSPORTATION_FREIGHT")).build())
                    .transportation(Transportation.builder().id(resultSet.getInt("TRANSPORTATION_ID")).build())
                    .country(Country.builder().tariff(resultSet.getInt("COUNTRY_TARIFF")).build())
                    .country(Country.builder().id(resultSet.getInt("COUNTRY_ID")).build())
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
