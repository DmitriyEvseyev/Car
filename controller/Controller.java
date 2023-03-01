package controller;

import exceptions.NotFoundException;
import model.Car;
import DAO.DAOCar;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static controller.database.Connection.connection;

public class Controller {
    // part of singleton pattern. read more: https://habr.com/ru/post/129494/
    private static Controller instance;
    private DAOCar daoCar;

    // singleton pattern
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    // constructor is private because singleton pattern

    private Controller() {
        this.daoCar = DAOCar.getInstance(connection);
    }

    public List<Car> getAllCars() throws SQLException {
        // dao
        // return unmodifiable list of cars because we can change some car only via addCar method or getCar and set required fields
        return Collections.unmodifiableList(new ArrayList<>(daoCar.getAll()));
    }

    public void addCar(Car car) throws SQLException {
        // dao
       daoCar.CreateCar(
               car.getId(),
               car.getName(),
               car.getDate(),
               car.getColor(),
               car.isAfterCrash());
    }

    public void removeCar(Integer id) throws NotFoundException, SQLException {
        //dao
        daoCar.delete(daoCar.read(id));
    }

    public void updateCar(Integer id, String Name, Date date, String color, boolean isAfterCrash) throws SQLException {
        // dao
        Car UpdateCar;
        UpdateCar = Car.builder()
                        .id(id)
                        .name(Name)
                        .date(date)
                        .color(color)
                        .isAfterCrash(isAfterCrash)
                        .build();
        daoCar.update(UpdateCar);
    }
}
