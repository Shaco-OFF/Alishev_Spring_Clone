package ru.alishev.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Neil Alishev
 */
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    //URL и пароль от базы данных должны храниться в отдельном файле .properties (но для урока используются поля)
    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    //создание соединения с БД
    private static Connection connection;

    static {
        //необязательный блок в новых версиях (делается автоматически). Подгрузка класса-драйвера для postgreSQL
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        try {
            //создание объекта с SQL-запросом к БД (подключение к БД)
            Statement statement = connection.createStatement();
            //необязательный блок
            String SQL = "SELECT * FROM Person";
            //передача SQL-запроса и получение инкапсулированных строк из БД в переменную ResultSet
            //метод executeQuery - получение данных по запросу
            ResultSet resultSet = statement.executeQuery(SQL);
            //получение данных из каждой строки контейнера ResultSet
            while (resultSet.next()) {

                Person person = new Person();
                //получение значений полей (колонки/столбцы)по их именам  для текущей строки (объект)
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                //добавление человека в список
                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public Person show(int id) {
        /*return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);*/
        return null;
    }

    public void save(Person person) {
        /*person.setId(++PEOPLE_COUNT);
        people.add(person);*/
        try {
            //создание объекта с SQL-запросом к БД (подключение к БД)
            //для исключения SQL-инъекций необходимо использовать PreparedStatement
            Statement statement = connection.createStatement();
            //создание SQL-команды (ручное построение команды дано для примера
            String SQL = "INSERT INTO Person VALUES(" + 1 + ",'" + person.getName() + "'," + person.getAge() +
                    ",'" + person.getEmail() + "')";
            //INSERT INTO Person VALUES(1, 'name', 18, 'email')
            //метод executeUpdate - изменение данных по запросу
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Person updatedPerson) {
        /*Person personToBeUpdated = show(id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());*/
    }

    public void delete(int id) {
        /*people.removeIf(p -> p.getId() == id);*/
    }
}
