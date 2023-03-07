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

    // fixme main class should not throw exceptions - all of them should be catched
    public static void main(String[] args) throws NotFoundException, ParseException, SQLException {
        // fixme this logic should be moved to dao manager class initialisation
        Connection.connection();
        // fixme similar to previous 
        CreateTable.createTable(connection);

        // init some test data

        Controller controller = Controller.getInstance();
        
        
        // fixme what is maxid - why it places here?
        CLIView cliView = CLIView.getInstance(MaxId.createMaxId(connection));
        cliView.run();
    }
}
