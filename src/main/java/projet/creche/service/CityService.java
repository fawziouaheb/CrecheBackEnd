package projet.creche.service;

import projet.creche.model.City;

import java.util.List;

public interface CityService {
    City findById(Long id);
    Boolean cityExistsByName(String name);
    List<City> getCities();
    City saveCity(City city);
    void deleteCity(City city);
    public void deleteCity(Long id) ;

}
