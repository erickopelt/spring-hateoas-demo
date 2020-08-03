package io.opelt.hateoas.controller;

import io.opelt.hateoas.domain.User;
import io.opelt.hateoas.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
class UserController {

    private final UserRepository repository;

    private final PagedResourcesAssembler<User> userPagedResourcesAssembler;

    private final UserModelAssembler assembler;

    public UserController(UserRepository repository, PagedResourcesAssembler<User> userPagedResourcesAssembler,
                          UserModelAssembler assembler) {
        this.repository = repository;
        this.userPagedResourcesAssembler = userPagedResourcesAssembler;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<PagedModel<UserModel>> findUsers(Pageable pageable) {
        var page = userPagedResourcesAssembler.toModel(repository.findAll(pageable), assembler);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> findUser(@PathVariable("id") String id) {
        return repository.findById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
