package Services;

import java.io.FileWriter;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AuditThread {
    private static AuditThread instance;

    public AuditThread() {

    }

    public static AuditThread getInstance() {
        if (instance == null) {
            instance = new AuditThread();
        }
        return instance;
    }

    public static void printAuditAction(String actionName,String threadName) {
        try {
            FileWriter writer = new FileWriter("data/auditThread.csv", true);

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();

            writer.append(actionName + "," + dateFormat.format(new Date()) + "," + threadName + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println("Probleme cu fisierul");
        }
    }
}
