package projet.creche.resetAPI;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.creche.dto.RoleDto;
import projet.creche.mapper.RoleMapper;
import projet.creche.model.Role;
import projet.creche.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/administrator/roles")
public class RoleRestController {

    private RoleService roleService;

    @Autowired
    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * récupérer la liste des roles du système .
     * @return arrayList des roles
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        return new ResponseEntity<List<RoleDto>>(RoleMapper.mapperEntityListToDtoList(this.roleService.getAllRoles()), HttpStatus.CREATED);
    }
}
