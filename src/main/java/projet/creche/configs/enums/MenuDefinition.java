package projet.creche.configs.enums;

import java.util.List;
import java.util.Map;

public enum MenuDefinition {
    DASHBOARD("Tableau de bord", 1, List.of(RoleType.ADMIN, RoleType.DIRECTOR, RoleType.EMPLOYEE, RoleType.PARENT),
            Map.of(
                    RoleType.ADMIN, "/monespace/admin/dashboard",
                    RoleType.DIRECTOR, "/monespace/admin/dashboard",
                    RoleType.EMPLOYEE, "/monespace/employee/dashboard",
                    RoleType.PARENT, "/monespace/parents/dashboard"
            )
    ),
    STRUCTURE("Structures", 2, List.of(RoleType.ADMIN, RoleType.DIRECTOR),
            Map.of(RoleType.ADMIN, "/monespace/admin/structure", RoleType.DIRECTOR, "/monespace/admin/structure")
    ),
    CITY("Villes", 3, List.of(RoleType.ADMIN, RoleType.DIRECTOR),
            Map.of(RoleType.ADMIN, "/monespace/admin/city", RoleType.DIRECTOR, "/monespace/admin/city")
    ),
    PROMO("Promotions", 4, List.of(RoleType.ADMIN, RoleType.DIRECTOR),
            Map.of(RoleType.ADMIN, "/monespace/admin/promo", RoleType.DIRECTOR, "/monespace/admin/promo")
    ),
    ACTIVITE("Activités", 5, List.of(RoleType.ADMIN, RoleType.DIRECTOR),
            Map.of(RoleType.ADMIN, "/monespace/admin/activite", RoleType.DIRECTOR, "/monespace/admin/activite")
    ),
    PROTOCOLE("Protocoles", 6, List.of(RoleType.ADMIN, RoleType.DIRECTOR),
            Map.of(RoleType.ADMIN, "/monespace/admin/protocoles", RoleType.DIRECTOR, "/monespace/admin/protocoles")
    ),
    EMPLOYEE_LIST("Employés", 7, List.of(RoleType.ADMIN, RoleType.DIRECTOR),
            Map.of(RoleType.ADMIN, "/monespace/admin/employee-list", RoleType.DIRECTOR, "/monespace/admin/employee-list")
    ),
    PARENTS_LIST("Parents", 8, List.of(RoleType.ADMIN, RoleType.DIRECTOR),
            Map.of(RoleType.ADMIN, "/monespace/admin/parents-list", RoleType.DIRECTOR, "/monespace/admin/parents-list")
    ),
    PREINSCRITION_LIST("Préinscriptions", 9, List.of(RoleType.ADMIN, RoleType.DIRECTOR),
            Map.of(RoleType.ADMIN, "/monespace/admin/preinscriptions-list", RoleType.DIRECTOR, "/monespace/admin/preinscriptions-list")
    ),
    MENU("Menus des structures", 10, List.of(RoleType.ADMIN, RoleType.DIRECTOR, RoleType.EMPLOYEE),
            Map.of(
                    RoleType.ADMIN, "/monespace/admin/menu",
                    RoleType.DIRECTOR, "/monespace/admin/menu",
                    RoleType.EMPLOYEE, "/monespace/employee/menu"
            )
    ),
    POINTAGE_EMPLOYEE("Mes heures", 11, List.of(RoleType.EMPLOYEE),
            Map.of(RoleType.EMPLOYEE, "/monespace/employee/pointage-employee")
    ),
    PARENTS_PAYEMENT("Mes Paiements", 12, List.of(RoleType.PARENT),
            Map.of(RoleType.PARENT, "/monespace/parents/parents-payement")
    ),
    RAPPORT_REUNION("Rapports des réunions", 9, List.of(RoleType.ADMIN, RoleType.DIRECTOR,RoleType.EMPLOYEE),
            Map.of(
                    RoleType.ADMIN, "/monespace/admin/rapport-reunion",
                    RoleType.DIRECTOR, "/monespace/admin/rapport-reunion",
                    RoleType.EMPLOYEE, "/monespace/employee/rapport-reunion")
    );

    public final String label;
    public final int ordre;
    public final List<RoleType> roles;
    public final Map<RoleType, String> urls;

    MenuDefinition(String label, int ordre, List<RoleType> roles, Map<RoleType, String> urls) {
        this.label = label;
        this.ordre = ordre;
        this.roles = roles;
        this.urls = urls;
    }
}
