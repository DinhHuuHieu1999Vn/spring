package com.vn.spring.service;

import com.vn.spring.enums.SortEnums;
import com.vn.spring.model.Users;
import com.vn.spring.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersService {
    public static UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public void addUsers(Users users) {
        Optional<Users> findUsersByEmail = usersRepository.findUsersByEmail(users.getEmail());
        Optional<Users> findUsersByUsername = usersRepository.findUsersByUsername(users.getEmail());

        if (findUsersByEmail.isPresent() || findUsersByUsername.isPresent()) {
            throw new IllegalStateException("Email or Username taken");
        }

        usersRepository.save(users);
    }

    @Transactional
    public void updateUsers(Long usersId,
                            String firstName,
                            String lastName,
                            String gender,
                            String email,
                            String password,
                            LocalDate birthDate,
                            String image) {
        Users users = usersRepository
            .findById(usersId)
            .orElseThrow(() -> new IllegalStateException("Users with id:" + usersId + " not exist"));

        if (firstName != null && firstName.length() > 0 && !Objects.equals(users.getFirstName(), firstName)) {
            users.setFirstName(firstName);
        }

        if (lastName != null && lastName.length() > 0 && !Objects.equals(users.getLastName(), lastName)) {
            users.setLastName(lastName);
        }

        if (gender != null && gender.length() > 0 && !Objects.equals(users.getGender(), gender)) {
            users.setGender(gender);
        }

        if (email != null && email.length() > 0 && !Objects.equals(users.getEmail(), email)) {
            Optional<Users> updateEmail = usersRepository.findUsersByEmail(email);
            if (updateEmail.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            users.setEmail(email);
        }

        if (password != null && password.length() > 0 && !Objects.equals(users.getPassword(), password)) {
            users.setPassword(password);
        }

        if (birthDate != null && !Objects.equals(users.getBirthDate(), birthDate)) {
            users.setBirthDate(birthDate);
        }

        if (image != null && image.length() > 0 && !Objects.equals(users.getImage(), image)) {
            users.setImage(image);
        }

        usersRepository.save(users);
    }

    public void deleteUsers(Long usersId) {
        Boolean usersExists = usersRepository.existsById(usersId);
        if (!usersExists) {
            throw new IllegalStateException(
                "Users with id" + usersId + " does not exists"
            );
        }
        usersRepository.deleteById(usersId);
    }

    public Page<Users> getUsersS(String keyword,
                                 Integer pageNumber,
                                 Integer pageSize) {
        return usersRepository.findAll(PageRequest.of(
            pageNumber,
            pageSize,
            Sort.by(keyword).descending()));
    }

    public List<Users> getAllUsersSorted(String keyword, String sort) {
        if (sort.toString().trim().toLowerCase().equals(SortEnums.DESC.toString().trim().toLowerCase())) {
            return usersRepository.findAll(Sort.by(keyword).descending());
        }
        return usersRepository.findAll(Sort.by(keyword).ascending());
    }
}
