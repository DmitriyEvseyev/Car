import controller.Controller;
import controller.IDGenerator;
import controller.database.Connection;
import controller.database.CreateTable;
import controller.database.MaxId;
import exceptions.NotFoundException;
import view.cli.CLIView;

import java.sql.SQLException;
import java.text.ParseException;

import static controller.database.Connection.connection;

public class Main {

    public static void main(String[] args) throws NotFoundException, ParseException, SQLException {
        Connection.connection();
        CreateTable.createTable(connection);

        // init some test data

        Controller controller = Controller.getInstance();

        CLIView cliView = CLIView.getInstance(MaxId.createMaxId(connection));
        cliView.run();
    }
}
