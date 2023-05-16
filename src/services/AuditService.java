package services;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static constants.Constante.FISIER_AUDIT;

public class AuditService implements IAuditService {
    private static AuditService instance;

    public AuditService() {
    }

    public static AuditService getInstance() {
        if (instance == null)
            instance = new AuditService();

        return instance;
    }

    @Override
    public void scrieMesaj(String mesaj) {
        try {
            // Creeaza fisierul daca nu exista deja.
            File file = new File(FISIER_AUDIT);
            if (!file.exists() || file.isDirectory()) {
                if (file.createNewFile()) {
                    System.out.println("Fisierul CSV pentru serviciul audit a fost creat.");
                }
            }

            // Scrie in fisier
            BufferedWriter writer = new BufferedWriter(new FileWriter(FISIER_AUDIT, true));

            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTime = currentTime.format(timeFormat);

            writer.write(mesaj + ", " + formattedTime + "\n");
            writer.close();
        }
        catch (IOException e) {
            System.out.println("EROARE AUDIT");
        }
    }
}
