package exportation.model.da;

import lombok.extern.log4j.Log4j;
import exportation.model.entity.Person;
import exportation.model.entity.enums.Gender;
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

    @Override
    public Person save(Person person) throws Exception {
        person.setpersonId(ConnectionProvider.getConnectionProvider().getNextId("PERSON_SEQ"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO PERSON (PERSON_ID, NAME, FAMILY, NATIONAL_ID ,GENDER ,POSITION ,PHONE ,EMAIL ,ADDRESS) VALUES (?,?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, person.getPersonId());
        preparedStatement.setString(2, person.getName());
        preparedStatement.setString(3, person.getFamily());
        preparedStatement.setString(4, person.getNationalId());
        preparedStatement.setString(5, person.getGender().name());
        preparedStatement.setString(6, person.getPosition());
        preparedStatement.setString(7, person.getPhone());
        preparedStatement.setString(8, person.getEmail());
        preparedStatement.setString(9, person.getAddress());
        preparedStatement.execute();
        return person;
    }

    @Override
    public Person edit(Person person) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE PERSON SET NAME=?, FAMILY=?, GENDER=?, NATIONAL_ID=?, POSITION=?, PHONE=?, EMAIL=?, ADDRESS=? WHERE PERSON_ID=?"
        );
        preparedStatement.setString(1, person.getName());
        preparedStatement.setString(2, person.getFamily());
        preparedStatement.setString(3, person.getNationalId());
        preparedStatement.setString(4, person.getGender().name());
        preparedStatement.setString(5, person.getPosition());
        preparedStatement.setString(6, person.getPhone());
        preparedStatement.setString(7, person.getEmail());
        preparedStatement.setString(8, person.getAddress());
        preparedStatement.setInt(9, person.getPersonId());
        preparedStatement.execute();
        return person;
    }

    @Override
    public Person remove(int personId) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM PERSON WHERE PERSON_ID=?"
        );
        preparedStatement.setInt(1, personId);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Person> findAll() throws Exception {
        List<Person> personList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM PERSON ORDER BY PERSON_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Person person = Person
                    .builder()
                    .personId(resultSet.getInt("PERSON_ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .nationalId(resultSet.getString("NATIONAL_ID"))
                    .gender(Gender.valueOf(resultSet.getString("GENDER")))
                    .position(resultSet.getString("POSITION"))
                    .phone(resultSet.getString("PHONE"))
                    .email(resultSet.getString("EMAIL"))
                    .address(resultSet.getString("ADDRESS"))
                    .build();

            personList.add(person);
        }

        return personList;
    }

    @Override
    public Person findById(int personId) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM PERSON WHERE PERSON_ID=?");
        preparedStatement.setInt(1, personId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Person person = null;
        if (resultSet.next()) {
            person = Person
                    .builder()
                    .personId(resultSet.getInt("PERSON_ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .nationalId(resultSet.getString("NATIONAL_ID"))
                    .gender(Gender.valueOf(resultSet.getString("GENDER")))
                    .position(resultSet.getString("POSITION"))
                    .phone(resultSet.getString("PHONE"))
                    .email(resultSet.getString("EMAIL"))
                    .address(resultSet.getString("ADDRESS"))
                    .build();
        }
        return person;
    }

    public List<Person> findByFamily(String family) throws Exception {
        List<Person> personList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM PERSON WHERE FAMILY LIKE? ORDER BY PERSON_ID");
        preparedStatement.setString(1, family + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Person person = Person
                    .builder()
                    .personId(resultSet.getInt("PERSON_ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .nationalId(resultSet.getString("NATIONAL_ID"))
                    .gender(Gender.valueOf(resultSet.getString("GENDER")))
                    .position(resultSet.getString("POSITION"))
                    .phone(resultSet.getString("PHONE"))
                    .email(resultSet.getString("EMAIL"))
                    .address(resultSet.getString("ADDRESS"))
                    .build();

            personList.add(person);
        }

        return personList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
