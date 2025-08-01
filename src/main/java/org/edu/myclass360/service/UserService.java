package org.edu.myclass360.service;

import org.edu.myclass360.dto.CreateUserDTO;
import org.edu.myclass360.dto.UpdateUserDto;
import org.edu.myclass360.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();
    UserDTO findByDni(String id);
    UserDTO create (CreateUserDTO dto);
    UserDTO update (Long id, UpdateUserDto dto);
    void deactive(Long id);

}
