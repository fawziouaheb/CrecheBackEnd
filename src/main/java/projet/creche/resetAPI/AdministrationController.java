package projet.creche.resetAPI;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/administration")
public class AdministrationController {

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMINISTRATEUR')")
    public String getDashboard() {
        return "Page d'administration (accessible uniquement aux Admin)";
    }

    @GetMapping("/dashboardParent")
    @PreAuthorize("hasRole('PARENT')")
    public String getDashboardParent() {
        return "Page d'administration (accessible uniquement aux PARENTs)";
    }
}

