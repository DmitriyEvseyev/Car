package view.cli;

import controller.Controller;
import controller.IDGenerator;
import exceptions.NotFoundException;
import model.Car;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class CLIView {
    private static CLIView instance;

    private Scanner scanner;
    private Controller controller;
    private IDGenerator idGenerator;

    public static CLIView getInstance(Integer MaxIDDAO) throws SQLException {
        if (instance == null) {
            instance = new CLIView(MaxIDDAO);
        }
        return instance;
    }

    // todo create custom exception ApplicationViewStartupFailedException
    private CLIView(Integer MaxIDDAO) throws SQLException {
        this.scanner = new Scanner(System.in);
        this.controller = Controller.getInstance();
        this.idGenerator = IDGenerator.getInstance(MaxIDDAO);
    }

    public void run() throws NotFoundException, ParseException, SQLException {
        int menuAction = -1;

        System.out.println("***** Car Manager *****");
        while (menuAction != 5) {
            menuAction = runMenu();

            switch (menuAction) {
                case 1:
                    showCars(); // dao
                    break;
                case 2:
                    createCar(); // dao
                    break;
                case 3:
                    deleteCar(); // dao
                    break;
                case 4:
                    modifyCar();  // dao
                    break;
                case 5:
                    System.out.println("\n Good bye! See you soon :)");
                    break;
                default:
                    System.out.println("Error: Incorrect value, please choose correct");
                    break;
            }
        }
    }

    private int runMenu() {
        System.out.println();
        System.out.println("Choose action:");
        System.out.println("1. Display all cars");
        System.out.println("2. Create car");
        System.out.println("3. Delete car");
        System.out.println("4. Modify car");
        System.out.println("5. Exit");

        System.out.print("\nAction: ");
        String input = scanner.nextLine();
        return Integer.valueOf(input);
    }

    private void showCars() throws SQLException {
        System.out.println("Cars:\n");
        // some magic via lambda
        for (Car i : controller.getAllCars()) {
            System.out.println(i);
        }

        controller.getAllCars();

        System.out.print("Press \"Enter\" for return to the main menu...\n");
        scanner.nextLine();
    }

    // todo work with exceptions
    private void createCar() throws ParseException, SQLException {
        System.out.println("\nNew car:");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        Date date;
        while (true) {
            System.out.print("Enter date (yyyy-mm-dd): ");
            String productionDate = scanner.nextLine();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
            try {
                date = formatter.parse(productionDate);
                System.out.println("Correct date");
                break;
            } catch (ParseException e) {
                System.out.println("Input is not a date, continue");
            }
        }
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        System.out.print("Does car have crashes (yes/no): ");
        String crashes = scanner.nextLine();
        boolean isAfterCrash = false;
        if ("yes".equalsIgnoreCase(crashes)) {
            isAfterCrash = true;
        }
        controller.addCar(
                Car.builder()
                        .id(idGenerator.getId())
                        .name(name)
                        .date(date)
                        .color(color)
                        .isAfterCrash(isAfterCrash)
                        .build()
        );
        System.out.println("\nInfo: Car has been added!");
    }

    // todo similar with exceptions
    public void deleteCar() throws NotFoundException, SQLException {
        System.out.println("Which car to sell?");
        System.out.print("Enter id: ");
        String ID = scanner.nextLine();
        Integer id = Integer.valueOf(ID);
        Integer IdForPrint = id;
        controller.removeCar(id);
        System.out.println("the car has been deleted:  " + IdForPrint);
    }

    // todo similar with exceptions
    public void modifyCar() throws SQLException {
        System.out.print("Choose a car. Enter id: ");
        String ID = scanner.nextLine();
        Integer id = Integer.valueOf(ID);

        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        Date date;
        while (true) {
            System.out.print("Enter date (yyyy-mm-dd): ");
            String productionDate = scanner.nextLine();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
            try {
                date = formatter.parse(productionDate);
                System.out.println("Correct date");
                break;
            } catch (ParseException e) {
                System.out.println("Input is not a date, continue");
            }
        }

        System.out.print("Change the color:");
        String color = scanner.nextLine();

        System.out.println("Does car have crashes (yes/no)?");
        String crashes = scanner.nextLine();
        boolean isAfterCrash = false;
        if ("yes".equalsIgnoreCase(crashes)) {
            isAfterCrash = true;
        }
        controller.updateCar(id, name, date, color, isAfterCrash);

    }
}
