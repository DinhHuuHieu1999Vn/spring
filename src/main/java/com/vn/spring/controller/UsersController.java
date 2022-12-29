package com.vn.spring.controller;

import com.vn.spring.model.Users;
import com.vn.spring.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = new UsersService(UsersService.usersRepository);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Users> getUsers() {
        return usersService.getAllUsers();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void CreateUsers(@RequestBody Users users) {
        usersService.addUsers(users);
    }

    @PatchMapping(path = "{usersId}", produces = {"application/json"})
    public void updateUsers(
        @PathVariable("usersId") Long usersId,
        @RequestParam(required = false, value = "firstName") String firstName,
        @RequestParam(required = false) String lastName,
        @RequestParam(required = false) String gender,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String password,
        @RequestParam(required = false) LocalDate birthDate,
        @RequestParam(required = false) String image) {
        usersService.updateUsers(usersId, firstName, lastName, gender, email, password, birthDate, image);
    }

    @DeleteMapping(path = "{usersId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsers(@PathVariable("usersId") Long usersId) {
        usersService.deleteUsers(usersId);
    }

//    @GetMapping(path = "/pagination")
//    public Page<Users> getUsersPagination(@RequestParam String keyword,
//                                          @RequestParam Integer pageNumber,
//                                          @RequestParam Integer pageSize) {
//        return usersService.getUsersPagination(keyword, pageNumber, pageSize);
//    }

    @GetMapping(path = "/sorted")
    public List<Users> getAllUsersSorted(@RequestParam String keyword,
                                         @RequestParam String sort) {
        return usersService.getAllUsersSorted(keyword, sort);
    }
}
