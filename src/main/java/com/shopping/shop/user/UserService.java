package com.shopping.shop.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements IUserService {

    private final UserRepository repository;

    @Override
    public void save(User user) {

         repository.save(user);
    }

    @Override
    public User getById(Long id) {
        Optional<User> optionalUser = repository.findById(id);
        return optionalUser.get();
    }

    @Override
    public List<User> getAll() {
        List<User> list = (List<User>) repository.findAll();
        return list;
    }

    @Override
    public User update(Long id ,User user) {
        User lastSavedUser;
        lastSavedUser = getById(id);
        lastSavedUser.setFirstName(user.getFirstName());
        lastSavedUser.setLastName(user.getLastName());
        lastSavedUser.setUsername(user.getUsername());
        lastSavedUser.setPassword(user.getPassword());
        lastSavedUser.setBirthDay(user.getBirthDay());
        if(user.getNationalCode() != lastSavedUser.getNationalCode()){
            lastSavedUser.setNationalCode(user.getNationalCode());
        }
        save(lastSavedUser);
        return lastSavedUser;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


}
