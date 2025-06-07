package projet.creche.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.creche.model.City;
import projet.creche.repository.CityRepository;
import projet.creche.service.CityService;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City findById(Long id) {
        return this.cityRepository.findById(id).get();
    }

    @Override
    public Boolean cityExistsByName(String name) {
        return cityRepository.existsByNameIgnoreCase(name);
    }


    @Override
    public List<City> getCities() {
        return this.cityRepository.findAll();
    }

    @Override
    public City saveCity(City city) {
        return this.cityRepository.save(city);
    }

    @Override
    public void deleteCity(City city) {
         this.cityRepository.delete(city);
    }

    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }

}
