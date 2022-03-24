package com.thisWebSite.myWebsite.Service;

import com.thisWebSite.myWebsite.DTO.UserDTO;
import com.thisWebSite.myWebsite.Exeption.StatusP;
import com.thisWebSite.myWebsite.model.User;
import com.thisWebSite.myWebsite.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManageService {
    @Autowired
    private AuthService authService;
    @Autowired
    private userRepository UserRepository;

    @Transactional
    public List<UserDTO> showAllUser() {
        List<User> users = UserRepository.findAll();
        return users.stream().map(this::mapFromUserToDto).collect(Collectors.toList());
    }

    private UserDTO mapFromUserToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUserName());
        userDTO.setNonlock(user.isNonlock());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    @Transactional
    public ResponseEntity<StatusP> UpdateUserRole(UserDTO userDTO) {
        org.springframework.security.core.userdetails.User user = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        User ChangeUser = UserRepository.findByUserName(userDTO.getUsername()).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        User InUser = UserRepository.findByUserName(user.getUsername()).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        if ((InUser.getUserName() != userDTO.getUsername()) && (userDTO.getRole().equals("ROLE_USER") || userDTO.getRole().equals("ROLE_ADMIN") || userDTO.getRole().equals("ROLE_VUSER"))) {
            if ((ChangeUser.getRole().equals("ROLE_USER") || ChangeUser.getRole().equals("ROLE_VUSER")) && (userDTO.getRole().equals("ROLE_USER") || userDTO.getRole().equals("ROLE_VUSER"))) {
                UserRepository.findByUserName(userDTO.getUsername())
                        .map(data -> {
                            data.setRole(userDTO.getRole());
                            return UserRepository.save(data);
                        }).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
            } else if (!ChangeUser.getRole().equals("ROLE_SAD") && !userDTO.getRole().equals("ROLE_SAD") && InUser.getRole().equals("ROLE_SAD")) {
                UserRepository.findByUserName(userDTO.getUsername())
                        .map(data -> {
                            data.setRole(userDTO.getRole());
                            return UserRepository.save(data);
                        }).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusP("false", "Không thể gửi"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusP("false", "Không thể gửi"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new StatusP("oke", "thành công"));
    }

    public UserDTO showUserInfor(Long id) {
        User user = UserRepository.findById(id).orElseThrow();
        return mapFromUserToDto(user);
    }
}
