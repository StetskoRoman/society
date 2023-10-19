package com.rv.society.service;

import com.rv.society.domain.Role;
import com.rv.society.domain.User;
import com.rv.society.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
//@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private  UserRepo userRepo;

    @Autowired
    private SmtpMailSender smtpMailSender;

    // этот был изначально
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
// if found someone in DB
        if (userFromDb != null) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());



        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
//                http://localhost:8080/activate/ лучше в проперти вынести
                            "Welcome to community. Visit next link for continue registration:  http://localhost:8080/activate/%s",
                    user.getUsername(), user.getActivationCode()
            );

            smtpMailSender.send(user.getEmail(), "Activation code ", message);
            userRepo.save(user);

        }
        return true;
    }

    public boolean activateUser(String code) {

        User user = userRepo.findByActivationCode(code);
//        Активация зафэйлена, код не найден
        if (user == null) {
            return false;
        }

        user.setActivationCode(null);
        userRepo.save(user);
        return true;
    }
}

//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
//                String.format("Пользователь '%s' не найден", username)
//        ));
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
//        );
//    }
//
//    public User createNewUser(RegistrationUserDto registrationUserDto) {
//        User user = new User();
//        user.setUsername(registrationUserDto.getUsername());
//        user.setEmail(registrationUserDto.getEmail());
//        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
//        user.setRoles(List.of(roleService.getUserRole()));
//        return userRepository.save(user);
//    }

