package com.fsm.backend.Objects.User;

import com.fsm.backend.Interfaces.ListRepository;

public class UserRepository extends ListRepository<User> {

    private static final UserRepository repository = new UserRepository();

    private UserRepository() { }

    public static UserRepository getInstance() {
        return repository;
    }

}
