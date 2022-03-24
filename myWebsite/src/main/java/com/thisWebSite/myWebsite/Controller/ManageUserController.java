package com.thisWebSite.myWebsite.Controller;

import com.thisWebSite.myWebsite.DTO.PostDTO;
import com.thisWebSite.myWebsite.DTO.UserDTO;
import com.thisWebSite.myWebsite.Exeption.StatusP;
import com.thisWebSite.myWebsite.Service.ManageService;
import com.thisWebSite.myWebsite.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manage")
public class ManageUserController {
    @Autowired
    private ManageService manageService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> showAllUser() {
        return new ResponseEntity<>(manageService.showAllUser(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO> showUserInfor(@PathVariable @RequestBody Long id) {
        return new ResponseEntity<>(manageService.showUserInfor(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<StatusP> UpdateUserByRole(@RequestBody UserDTO userDTOS) {
        return manageService.UpdateUserRole(userDTOS);
    }
}
