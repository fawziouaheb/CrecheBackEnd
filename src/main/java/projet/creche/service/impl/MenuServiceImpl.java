package projet.creche.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.creche.model.Menu;
import projet.creche.repository.MenuRepository;
import projet.creche.service.MenuService;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    private MenuRepository menuRepository;
    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public Menu persist(Menu menu) {
        return this.menuRepository.save(menu);
    }

    @Override
    public Menu findLastMenu() {
        return this.menuRepository.findTopByOrderByIdDesc();
    }

    @Override
    public List<Menu> findAll() {
        return this.menuRepository.findAll();
    }

    @Override
    public Boolean deleteMenu(Long id) {
        Optional<Menu> menuOptional = menuRepository.findById(id);
        if (menuOptional.isPresent()) {
            menuRepository.delete(menuOptional.get());  // Supprime le menu de la base de données
            return true;
        }
        return false;
    }
}
