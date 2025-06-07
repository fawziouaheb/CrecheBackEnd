package projet.creche.tools;

import java.security.SecureRandom;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
/**
 * cette class permet de proposer de méthode pour generer des choses
 */
public class Generate {

    // une méthode pour générer des mots de passes
    public static String password() {

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=<>?";
        SecureRandom random = new SecureRandom();
        int length = 8;
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }

    // Méthode pour récupérer la date d'aujourd'hui (au format YYYY-MM-DD)
    public static String getCurrentDate() {
        LocalDate today = LocalDate.now();
        return today.toString();
    }

    // Méthode pour récupérer l'heure actuelle (au format HH:mm:ss)
    public static String getCurrentTime() {
        LocalTime currentTime = LocalTime.now();
        return currentTime.toString();
    }

    // Méthode pour récupérer la date et l'heure actuelles (au format YYYY-MM-DD HH:mm:ss)
    public static String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * permet de tranformer une chaine de caractère en format  Date
     *
     * @param date la date tranformé
     * @return la date
     */
    public static Date StringToDate(String date) {
        try {
            java.util.Date utilDate = Config.getFormatDate().parse(date);
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();  // Affiche l'erreur de parsing
        }
        return null;  // Retourner null en cas d'erreur
    }

    /**
     * Permet de transformer une date en chaîne de caractères dans un format spécifique.
     *
     * @param date la date à transformer en chaîne de caractères
     * @return la date sous forme de chaîne de caractères
     */
    public static String DateToString(Date date) {
        try {
            // Vérifier si la date n'est pas nulle
            if (date == null) {
                return null;
            }
            SimpleDateFormat sdf = Config.getFormatDate();
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * cette méthode permet de sauvegarder un fichier récupéré en base64
     * @param base64Content le contenu du fichier en base 64
     * @param fileName le nom du fichier
     * @return le nom du fichier
     */
    public static String saveToFile(String base64Content, String fileName, String savePath) {
        try {
            byte[] fileBytes = Base64.getDecoder().decode(base64Content.split(",")[1]);
            java.io.File dir = new java.io.File(savePath);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            String filePath = savePath + fileName;
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(fileBytes);
            }
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de l'enregistrement du fichier.");
        }
    }


}
