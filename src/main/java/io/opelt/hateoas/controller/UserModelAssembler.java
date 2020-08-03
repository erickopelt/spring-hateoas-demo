package io.opelt.hateoas.controller;

import io.opelt.hateoas.domain.User;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {

    public UserModelAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(User entity) {
        return createModelWithId(entity.getId(), entity)
                .setId(entity.getId());
    }
}
