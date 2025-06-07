package projet.creche.service;

import projet.creche.model.Structure;

import java.sql.Struct;
import java.util.List;
import java.util.Optional;

public interface StructureService {
    public  Structure updateStructure(Structure structure);
    public Structure persistStructure(Structure structure);
    public Structure findById(Long id);
    public List<Structure> getAllStructure();
    public Structure update(Structure structure);
    void deleteStructureById(Long id);
    List<Structure> findByStructureName(String structureName);
    List<Structure> getStructureAbbeville(Long idCity );
    public List<String> getAllStructureNames();


}
