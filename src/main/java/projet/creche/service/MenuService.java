package projet.creche.service;

import projet.creche.model.Menu;

import java.util.List;

public interface MenuService {
    Menu persist(Menu menu);
    Menu findLastMenu();
    List<Menu> findAll();
    Boolean deleteMenu(Long id);
}


