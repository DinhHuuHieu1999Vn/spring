package com.vn.spring.controller;

import com.vn.spring.model.Users;
import com.vn.spring.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/users")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = new UsersService(UsersService.usersRepository);
    }

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping(path = "/get/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping(path = "/get/all/fetch")
    @ResponseStatus(HttpStatus.OK)
    public String getAllUsersFetch() {
        String stringUrl = "https://dummyjson.com/users";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(
            stringUrl, HttpMethod.GET, httpEntity, String.class).getBody();
    }

    @GetMapping(path = "/get/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Users> getUserById(@PathVariable("userId") Long id) {
        return usersService.getUserById(id);
    }

    @GetMapping(path = "/get/user/fetch/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Users getUserByIdFetch(@PathVariable("userId") Long id) {
        String stringUrl = "https://dummyjson.com/users";
        Users response = restTemplate.getForObject(stringUrl + "/" + id, Users.class);
        return response;
    }

    @PutMapping(path = "/put")
    @ResponseStatus(HttpStatus.CREATED)
    public void CreateUsers(@RequestBody Users users) {
        usersService.addUsers(users);
    }

    @PostMapping(path = "/put/fetch")
    @ResponseStatus(HttpStatus.OK)
    public String CreateUsersFetch(@RequestBody(required = false) Object object) {
        String stringUrl = "https://dummyjson.com/users/add";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(object, headers);

        return restTemplate.exchange(stringUrl, HttpMethod.POST, entity, String.class).getBody();
    }

    @PatchMapping(path = "/patch/{usersId}", produces = {"application/json"})
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

    @PutMapping(path = "/patch/{usersId}/fetch", produces = {"application/json"})
    public String updateUsersFetch(@PathVariable("usersId") Long usersId,
                                   @RequestBody(required = false) Object object) {
        String stringUrl = "https://dummyjson.com/users";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(object, headers);

        return restTemplate.exchange(stringUrl + "/" + usersId, HttpMethod.PUT, entity, String.class).getBody();
    }

    @DeleteMapping(path = "/delete/{usersId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsers(@PathVariable("usersId") Long usersId) {
        usersService.deleteUsers(usersId);
    }

    @DeleteMapping(path = "/delete/{usersId}/fetch")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteUsersFetch(@PathVariable("usersId") Long usersId) {
        String stringUrl = "https://dummyjson.com/users";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(stringUrl + "/" + usersId, HttpMethod.DELETE, entity, String.class).getBody();
    }

    @GetMapping(path = "/get/all/sorted")
    @ResponseStatus(HttpStatus.OK)
    public List<Users> getAllUsersSorted(@RequestParam String sort) {
        return usersService.getAllUsersSorted(sort);
    }

    @GetMapping(path = "/get/all/sorted/keyword")
    @ResponseStatus(HttpStatus.OK)
    public List<Users> getAllUsersSortedByKeyword(@RequestParam String keyword,
                                                  @RequestParam String sort) {
        return usersService.getAllUsersSorted(keyword, sort);
    }

    @GetMapping(path = "/get/all/pagination")
    @ResponseStatus(HttpStatus.OK)
    public Page<Users> getAllUsersPagination(@RequestParam Integer pageNumber,
                                             @RequestParam Integer pageSize) {
        return usersService.getAllUsersPagination(pageNumber, pageSize);
    }

    @GetMapping(path = "/get/all/pagination/sorted/keyword")
    @ResponseStatus(HttpStatus.OK)
    public Page<Users> getAllUsersPaginationSortedByKeyword(@RequestParam String keyword,
                                                            @RequestParam String sort,
                                                            @RequestParam Integer pageNumber,
                                                            @RequestParam Integer pageSize) {
        return usersService.getAllUsersPaginationSortedByKeyword(keyword, sort, pageNumber, pageSize);
    }
}
