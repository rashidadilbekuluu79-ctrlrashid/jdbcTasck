package org.example.dao.daoImpl;

import org.example.config.JdbcConfig;
import org.example.dao.PersonDao;
import org.example.enums.Gender;
import org.example.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonImpl implements PersonDao {
    private final Connection connection = JdbcConfig.getConnection();

    @Override
    public void creteTablePerson() {
        String sql = """
                CREATE TABLE IF NOT EXISTS persons (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(45) NOT NULL,
                    age INT,
                    gender VARCHAR(13) NOT NULL,
                    email VARCHAR(50) UNIQUE
                )
                """;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица persons создана.");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String insertPerson(Person person) {
        String sql = "INSERT INTO persons(name,age,gender,email) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,person.getName());
            preparedStatement.setInt(2,person.getAge());
            preparedStatement.setString(3, person.getGender().name());
            preparedStatement.setString(4,person.getEmail());
            preparedStatement.executeUpdate();
            return "Успешно добавлено";
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Person findPersonById(Long id) {
        String sql = "SELECT * FROM persons p WHERE p.id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Person person = new Person();
            while (resultSet.next()) {
                person.setId(resultSet.getLong("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                String genderStr = resultSet.getString("gender");
                if (genderStr != null) {
                    person.setGender(Gender.valueOf(genderStr));
                }

                person.setEmail(resultSet.getString("email"));
            }
            return person;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Person> findAllPerson() {
        String sql = "SELECT * FROM persons";
        List<Person> people = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                String genderStr = resultSet.getString("gender");
                if (genderStr != null) {
                    person.setGender(Gender.valueOf(genderStr));
                }

                person.setEmail(resultSet.getString("email"));
                people.add(person);
            }
            return people;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String updatePerson(Long id, Person person) {
        String sql = "UPDATE persons SET name=?, age=?, gender=?, email=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,person.getName());
            preparedStatement.setInt(2,person.getAge());
            preparedStatement.setString(3, person.getGender().name());
            preparedStatement.setString(4,person.getEmail());
            preparedStatement.setLong(5,id);
            preparedStatement.executeUpdate();
            return "Успешно обновлено";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deletePersonById(Long id) {
        String sql = "DELETE FROM persons WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            return "Успешно удалено";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}