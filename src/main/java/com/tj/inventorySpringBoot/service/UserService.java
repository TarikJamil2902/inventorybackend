package com.tj.inventorySpringBoot.service;


import com.tj.inventorySpringBoot.dto.UserDTO;
import com.tj.inventorySpringBoot.entity.Role;
import com.tj.inventorySpringBoot.entity.User;
import com.tj.inventorySpringBoot.enums.ERole;
import com.tj.inventorySpringBoot.repository.RoleRepository;
import com.tj.inventorySpringBoot.repository.UserRepository;
import com.tj.inventorySpringBoot.util.NotFoundException;
import jakarta.transaction.Transactional;

// import java.awt.print.Pageable;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public UserService(final UserRepository userRepository, final RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }




    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName(ERole.ADMIN.toString());
        adminRole.setRoleDescription("Admin role");
        adminRole.setDateCreated(OffsetDateTime.now());
        adminRole.setLastUpdated(OffsetDateTime.now());
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName(ERole.STAFF.toString());
        userRole.setRoleDescription("Default role for newly created record");
        userRole.setDateCreated(OffsetDateTime.now());
        userRole.setLastUpdated(OffsetDateTime.now());
        roleRepository.save(userRole);

        Role roleMODERATOR = new Role();
        roleMODERATOR.setRoleName("MANAGER");
        roleMODERATOR.setRoleDescription("Default role for newly ROLE_MODERATOR record");
        roleMODERATOR.setDateCreated(OffsetDateTime.now());
        roleMODERATOR.setLastUpdated(OffsetDateTime.now());
        roleRepository.save(roleMODERATOR);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setEmail("admin@gmail.com");
        adminUser.setDateCreated(OffsetDateTime.now());
        adminUser.setLastUpdated(OffsetDateTime.now());
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
//        adminUser.setRoles(adminRoles);
        userRepository.save(adminUser);

    }


    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("userName"));
        return users.stream()
                .map((user01) -> mapToDTO(user01, new UserDTO()))
                .toList();
    }

    public UserDTO get(final String userName) {
        return userRepository.findById(userName)
                .map(user01 -> mapToDTO(user01, new UserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        user.setUserName(userDTO.getUserName());
        return userRepository.save(user).getUserName();
    }

    public void update(final String userName, final UserDTO userDTO) {
        final User user = userRepository.findById(userName)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final String userName) {
        userRepository.deleteById(userName);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setUserName(user.getUserName());
        userDTO.setUserFirstName(user.getUserFirstName());
        userDTO.setUserLastName(user.getUserLastName());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setEnabled(user.getEnabled());
        userDTO.setCredentialsNonExpired(user.getCredentialsNonExpired());
        userDTO.setAccountNonExpired(user.getAccountNonExpired());
        userDTO.setAccountNonLocked(user.getAccountNonLocked());
        userDTO.setRoles(user.getRoles() == null ? null : user.getRoles().stream()
                .map(role01 -> role01.getRoleName())
                .toList());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setUserFirstName(userDTO.getUserFirstName());
        user.setUserLastName(userDTO.getUserLastName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setEnabled(userDTO.getEnabled());
        user.setCredentialsNonExpired(userDTO.getCredentialsNonExpired());
        user.setAccountNonExpired(userDTO.getAccountNonExpired());
        user.setAccountNonLocked(userDTO.getAccountNonLocked());
        final List<Role> roles = roleRepository.findAllById(
                userDTO.getRoles() == null ? Collections.emptyList() : userDTO.getRoles());
        if (roles.size() != (userDTO.getRoles() == null ? 0 : userDTO.getRoles().size())) {
            throw new NotFoundException("one of roles not found");
        }
        user.setRoles(roles.stream().collect(Collectors.toSet()));
        return user;
    }

    public boolean userNameExists(final String userName) {
        return userRepository.existsByUserNameIgnoreCase(userName);
    }

    public boolean emailExists(final String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public Page<UserDTO> getAllUsers(int page, int size, String sortBy, String sortDir) {
        // Create sorting direction based on provided sort direction (asc or desc)
        Sort sort = Sort.by(Sort.Order.asc(sortBy));  // Default sorting direction is ascending
        if ("desc".equalsIgnoreCase(sortDir)) {
            sort = Sort.by(Sort.Order.desc(sortBy));  // If descending, change sorting direction
        }

        // Create Pageable object with page number, size, and sorting
        Pageable pageable = (Pageable) PageRequest.of(page, size, sort);

        // Fetch paginated results from the userRepository and map them to UserDTO
        Page<UserDTO> userDTOPage = userRepository.findAll(pageable).map(user -> mapToDTO(user, new UserDTO()));

        // Return the Page of UserDTO objects
        return userDTOPage;
    }
//
//    public Page<UserDTO> searchUsers(int page, int size, String sortBy, String sortDir, String searchTerm) {
//        Sort sort = Sort.by(Sort.Order.asc(sortBy));
//        if ("desc".equalsIgnoreCase(sortDir)) {
//            sort = Sort.by(Sort.Order.desc(sortBy));
//        }
//
//        Pageable pageable = (Pageable) PageRequest.of(page, size, sort);
//
//
//        Page<UserDTO> userDTOPage;
//        if (searchTerm == null || searchTerm.isEmpty()) {
//            userDTOPage = userRepository.findAll(pageable).map(user -> mapToDTO(user, new UserDTO()));
//        } else {
//
//            userDTOPage = userRepository.findByUserNameContainingIgnoreCaseOrUserFirstNameContainingIgnoreCaseOrUserLastNameContainingIgnoreCaseOrUserEmailContainingIgnoreCase(searchTerm, searchTerm, pageable)
//                    .map(user -> mapToDTO(user, new UserDTO()));
//        }
//
//        return userDTOPage;
//    }

}
