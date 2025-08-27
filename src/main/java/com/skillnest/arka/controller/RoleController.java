package com.skillnest.arka.controller;

import com.skillnest.arka.dto.RoleDTO;
import com.skillnest.arka.model.Role;
import com.skillnest.arka.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private  final RoleService roleService;

    @GetMapping()
    public ResponseEntity<List<RoleDTO>> getAllRoles(){
        List<RoleDTO> roles = roleService.getFindAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRolById(@PathVariable Long id){
        RoleDTO roleDTO = roleService.getRoleById(id);
        if (roleDTO ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<RoleDTO> saveRole (@Valid @RequestBody RoleDTO role){
        RoleDTO newRole = roleService.createRole(role);
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole (@PathVariable Long id, @Valid @RequestBody RoleDTO roleDTO){
        RoleDTO updateRole = roleService.updateRole(id, roleDTO);
        if (updateRole == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(updateRole, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id){
        if (roleService.deleteRole(id)){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
