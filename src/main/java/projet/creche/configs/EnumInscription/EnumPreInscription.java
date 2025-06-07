package projet.creche.configs.EnumInscription;

/**
 * Classe contenant toutes les enums utilisées dans le contexte de la pré-inscription.
 * Cela regroupe les choix possibles pour différents aspects liés à la pré-inscription.
 */
public class EnumPreInscription {

    /**
     * Enum représentant la situation familiale.
     */
    public enum SituationFamille {
        MARIE,
        UNION_LIBRE,
        PACSE,
        SEPARE,
        CELIBATAIRE,
        VEUF,
        DIVORCE
    }

    public enum GardeEnfant {
        MONSIEUR,
        MADAME,
        ALTERNEE,
    }

    /**
     * Enum représentant le sexe.
     */
    public enum Sexe {
        MASCULIN,
        FEMININ
    }

    /**
     * Enum représentant la semaine.
     */
    public enum JourSemaine {
        LUNDI,
        MARDI,
        MERCREDI,
        JEUDI,
        VENDREDI
    }

    public enum NombreHeuresParSemaine {
        ZERO_A_DIX_NEUF("0h-19h"),
        VINGT_A_TRENTE("20h-29h"),
        TRENTE_A_QUARANTE("30h-39h"),
        QUARANTE_A_CINQUANTE("40h-49h"),
        CINQUANTE_PLUS("50h +");

        private final String label;

        NombreHeuresParSemaine(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

}
