package projet.creche.tools;

import java.text.SimpleDateFormat;

public class Config {
    /**
     * permet de returner le format de la date attendu
     * @return le format de la date attendu
     */
    public static SimpleDateFormat getFormatDate() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }
}
