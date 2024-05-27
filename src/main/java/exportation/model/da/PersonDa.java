package exportation.model.da;

import exportation.model.entity.Person;
import exportation.model.entity.enums.Gender;
import lombok.extern.log4j.Log4j;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class PersonDa implements AutoCloseable, CRUD<Person> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public PersonDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    //save
    @Override
    public Person save(Person person) throws Exception {
        person.setId(ConnectionProvider.getConnectionProvider().getNextId("PERSON_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO PERSON (ID,NAME,FAMILY,EMAIL,PHONENUMBER,NATIONALID,POSITION,ADDRESS,GENDER) VALUES (?,?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, person.getId());
        preparedStatement.setString(2, person.getName());
        preparedStatement.setString(3, person.getFamily());
        preparedStatement.setString(4, person.getEmail());
        preparedStatement.setString(5, person.getPhoneNumber());
        preparedStatement.setString(6, person.getNationalId());
        preparedStatement.setString(7, person.getPosition());
        preparedStatement.setString(8, person.getAddress());
        preparedStatement.setString(9, person.getGender().toString());
        preparedStatement.execute();
        return person;
    }

    //Edit
    @Override
    public Person edit(Person person) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE PERSON SET NAME=?, FAMILY=?, EMAIL=?, PHONENUMBER=?, NATIONALID=?,POSITION=?,ADDRESS=?,GENDER=?, WHERE ID=?"
        );
        preparedStatement.setInt(1, person.getId());
        preparedStatement.setString(2, person.getName());
        preparedStatement.setString(3, person.getFamily());
        preparedStatement.setString(4, person.getEmail());
        preparedStatement.setString(5, person.getPhoneNumber());
        preparedStatement.setString(6, person.getNationalId());
        preparedStatement.setString(7, person.getPosition());
        preparedStatement.setString(8, person.getAddress());
        preparedStatement.setString(9, person.getGender().toString());
        preparedStatement.execute();
        return person;
    }

    //Remove
    @Override
    public Person remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM PERSON WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //FindALl
    @Override
    public List<Person> findAll() throws Exception {
        List<Person> personList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM PERSON ORDER BY ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Person person = Person
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .email(resultSet.getString("EMAIL"))
                    .phoneNumber(resultSet.getString("PHONENUMBER"))
                    .nationalId(resultSet.getString("NATIONALID"))
                    .position(resultSet.getString("POSITION"))
                    .address(resultSet.getString("ADDRESS"))
                    .gender(resultSet.getObject("GENDER", Gender.class))
                    .build();

            personList.add(person);
        }

        return personList;
    }

    //FindById
    @Override
    public Person findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM PERSON WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Person person = null;
        if (resultSet.next()) {
            person = Person
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .email(resultSet.getString("EMAIL"))
                    .phoneNumber(resultSet.getString("PHONENUMBER"))
                    .nationalId(resultSet.getString("NATIONALID"))
                    .position(resultSet.getString("POSITION"))
                    .address(resultSet.getString("ADDRESS"))
                    .gender(resultSet.getObject("GENDER", Gender.class))
                    .build();
        }
        return person;
    }

    //FindByFamily
    public List<Person> findByFamily(String family) throws Exception {
        List<Person> personList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM PERSON WHERE FAMILY LIKE? ORDER BY ID");
        preparedStatement.setString(1, family + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Person person = Person
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .email(resultSet.getString("EMAIL"))
                    .phoneNumber(resultSet.getString("PHONENUMBER"))
                    .nationalId(resultSet.getString("NATIONALID"))
                    .position(resultSet.getString("POSITION"))
                    .address(resultSet.getString("ADDRESS"))
                    .gender(resultSet.getObject("GENDER", Gender.class))
                    .build();
            personList.add(person);
        }
        return personList;
    }

    //Close
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
