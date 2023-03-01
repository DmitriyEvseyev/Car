package DAO;

import model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public  class DAOCar implements DAOInterface {
    private static DAOCar instance;
    private  Connection connection;

    // singleton pattern
    public static DAOCar getInstance(Connection connection) {
        if (instance == null) {
            instance = new DAOCar(connection);
        }
        return instance;
    }

    private DAOCar(Connection connection) {
        this.connection = connection;
    }

    @Override
        public void  CreateCar(Integer id, String Name, Date date, String color, boolean isAfterCrash) throws SQLException {
            String sql = "INSERT INTO CAR (ID, NAME, DATE, COLOR, ISAFTERCRASH)  VALUES (?, ?, ?, ?, ?)";
            Car Car;
            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                PreparedStatement stmt = null;
                stmt = connection.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.setString(2, Name);
                stmt.setDate(3, (new java.sql.Date(date.getTime())));
                stmt.setString(4, color);
                stmt.setBoolean(5, isAfterCrash);
                stmt.executeUpdate();
        }
    }

   @Override
        public Car read(Integer id) throws SQLException {
            String sql = "SELECT * FROM CAR WHERE id = ?";
            Car Car;
            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setInt(1, id);
                Car = createCarByResultSet(stm.executeQuery());
            }
            return Car;
        }

        @Override
        public void update(Car car) throws SQLException {
            String sql = "UPDATE CAR SET NAME = ?, DATE = ?, COLOR = ?, ISAFTERCRASH = ?  WHERE ID = ?";
            try (PreparedStatement stm = connection.prepareStatement(sql);) {
                stm.setString(1, car.getName());
                stm.setDate(2, (new java.sql.Date(car.getDate().getTime())));
                stm.setString(3, car.getColor());
                stm.setBoolean(4, car.isAfterCrash());
                stm.setInt(5, car.getId());
                stm.executeUpdate();
            }
        }

        @Override
        public void delete(Car Car) throws SQLException {
            String sql = "DELETE FROM \"CAR\" WHERE \"Id\" = ?";
            try (PreparedStatement stm = connection.prepareStatement(sql);) {
                stm.setInt(1, Car.getId());
                stm.executeUpdate();
            }
        }

        @Override
        public List<Car> getAll() throws SQLException {
            List<Car> list;
            String sql = "SELECT * FROM CAR";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                list = createListByResultSet(statement.executeQuery());
            }
            return Collections.unmodifiableList(list);
        }

        @Override
        public List<Car> getSortedByCriteria(Integer Id, String column, String criteria) throws SQLException {
            List<Car> list;
            String sql = "SELECT * FROM \"CAR\" WHERE \"Id\" = ? ORDER BY \"%s\" %s";
            sql = String.format(sql, column, criteria);
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Id);
                list = createListByResultSet(statement.executeQuery());
            }
            return Collections.unmodifiableList(list);
        }


        private Car createCarByResultSet(ResultSet rs) throws SQLException {
            rs.next();
            return Car.builder().id(rs.getInt("Id"))
                                .name(rs.getString("Name"))
                                .color(rs.getString("Color"))
                                .date(rs.getDate("Date"))
                                .isAfterCrash(rs.getBoolean("isAfterCrash"))
                                .build();
        }

        private List<Car> createListByResultSet(ResultSet rs) throws SQLException {
            List<Car> list = new LinkedList<>();
            while (rs.next()) {
                list.add(Car.builder().id(rs.getInt("Id"))
                        .name(rs.getString("Name"))
                        .color(rs.getString("Color"))
                        .date(rs.getDate("Date"))
                        .isAfterCrash(rs.getBoolean("isAfterCrash"))
                        .build());
            }
            return list;
       }
}



