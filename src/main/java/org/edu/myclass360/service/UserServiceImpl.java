package org.edu.myclass360.service;

import org.edu.myclass360.dto.CreateUserDTO;
import org.edu.myclass360.dto.UpdateUserDto;
import org.edu.myclass360.dto.UserDTO;
import org.edu.myclass360.exceptions.UsuarioNoEncontradoException;
import org.edu.myclass360.model.Role;
import org.edu.myclass360.model.User;
import org.edu.myclass360.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .filter(User::isActive)
                .map(this::toDTO).toList();
    }

    @Override
    public UserDTO findByDni(String dni) {
       User user = userRepository.findByDni(dni)
               .orElseThrow(()-> new UsuarioNoEncontradoException("Usuario con DNI " + dni + " no encontrado"));
       return toDTO(user);
    }

    @Override
    public UserDTO create(CreateUserDTO dto) {

        if (userRepository.existsByEmail(dto.email()) || userRepository.existsByDni(dto.dni())){
            throw new IllegalArgumentException("Email o DNI ya registrados en la base de datos");
        }

        User user = new User();
        user.setName(dto.name());
        user.setLastName(dto.lastName());
        user.setSecondLastName(dto.secondLastName());
        user.setDni(dto.dni());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRole(Role.valueOf(dto.role()));
        user.setActive(true);

        return toDTO(userRepository.save(user));

    }

    @Override
    public UserDTO update(Long id, UpdateUserDto dto) {

        User user = getActiveUserById(id);
        user.setName(dto.name());
        user.setLastName(dto.lastName());
        user.setSecondLastName(dto.secondLastName());
        user.setEmail(dto.email());
        user.setRole(Role.valueOf(dto.role()));
        user.setActive(dto.active());

        return toDTO(userRepository.save(user));
    }

    @Override
    public void deactive(Long id) {
        User user = getActiveUserById(id);
        user.setActive(false);
        userRepository.save(user);

    }

    private User getActiveUserById(Long id) {
        return userRepository.findById(id)
                .filter(User::isActive)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
    }


    private UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getSecondLastName(),
                user.getDni(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}
