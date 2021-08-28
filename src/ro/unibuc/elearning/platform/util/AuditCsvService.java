package ro.unibuc.elearning.platform.util;


import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.Date;

public class AuditCsvService {
    private static AuditCsvService instance = null;
    private final PrintStream fout;

    private AuditCsvService() throws FileNotFoundException {
        fout = new PrintStream(new FileOutputStream("audit.csv", true));
    }

    public void writeCsv(@NotNull String actionName) {
        fout.println(actionName + ", " + new Timestamp((new Date()).getTime()));
    }

    public static AuditCsvService getInstance() {
        if (instance == null) {
            try {
                instance = new AuditCsvService();
            } catch (FileNotFoundException e) {
                System.out.println("Exception in AuditCsvService.java: getInstance: " + e);
            }
        }
        return instance;
    }
}
