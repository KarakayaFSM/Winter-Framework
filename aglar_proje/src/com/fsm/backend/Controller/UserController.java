package com.fsm.backend.Controller;

import com.fsm.backend.Annotation.Action;
import com.fsm.backend.Annotation.Controller;
import com.fsm.backend.Annotation.QueryParam;
import com.fsm.backend.Enums.TYPE;
import com.fsm.backend.Interfaces.MyHttpHandler;
import com.fsm.backend.Objects.Auction.Auction;
import com.fsm.backend.Objects.User.Credentials;
import com.fsm.backend.Objects.User.User;
import com.fsm.backend.Utils.ControllerUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller(path = "users")
public class UserController implements MyHttpHandler {

    @Action(path = "createUser", type = TYPE.POST)
    public User create(@QueryParam(type = Credentials.class) Credentials credentials) {
        User u = new User(credentials);
        User.repository.add(u);
        return u;
    }

    @Action(path = "login", type = TYPE.POST)
    public Collection<Auction> login(@QueryParam(type = User.class) User user) {
        if(ControllerUtils.isAuthenticated(user)) {
            return Auction.repository.getAll();
        }
        return Collections.emptyList();
    }

    @Action(path = "getUserById")
    public User getUserById(String id) {
        return User.repository.findById(id);
    }

    @Action(path = "updateUser", type = TYPE.POST)
    public User update(@QueryParam(type = User.class) User user) {
        return User.repository.update(user);
    }

    @Action(path = "getAllUsers")
    public List<User> getAllUsers() {
        return new ArrayList<>(User.repository.getAll());
    }

}
