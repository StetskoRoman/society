package com.rv.society.service;

import com.rv.society.domain.Role;
import com.rv.society.domain.User;
import com.rv.society.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private  UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SmtpMailSender smtpMailSender;

    // этот был изначально
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
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
//        Шифруется пароль при регистрации пользователя
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);
        sendMessage(user);
        return true;
    }

//    ctrl+alt+M - вынести кусок кода в новый метод,  отправка сообщения об авторизации на емэйл
    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
//                http://localhost:8080/activate/ лучше в проперти вынести
                            "Welcome to community. Visit next link for continue registration:  http://localhost:8080/activate/%s",
                    user.getUsername(), user.getActivationCode()
            );

            smtpMailSender.send(user.getEmail(), "Activation code ", message);


        }
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

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
    }

    public void updateProfile(User user, String password, String email) {
//        Берем текущий емэйл,  String email - это будет новый емэйл
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));

        if (isEmailChanged) {
            user.setEmail(email);
//            раз новый мэйл, то надо по новому активацию делать с новым мэйлом
            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());

            }
        }

        if (StringUtils.isEmpty(password)) {
            user.setPassword(password);

        }
        userRepo.save(user);

        if (isEmailChanged) {
            sendMessage(user);
        }
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

