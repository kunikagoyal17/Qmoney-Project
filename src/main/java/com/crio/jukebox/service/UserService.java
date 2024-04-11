package com.crio.jukebox.service;
import java.util.*;

import com.crio.jukebox.entities.User;
    


    
    
import com.crio.jukebox.repository.IUserRepository;
import com.crio.jukebox.dtos.UserInfoDto;
//import com.crio.jukebox.entities.User;

public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create a new user in the system
    @Override
    public UserInfoDto create(String name) {
        User u = userRepository.save(new User(name));
        UserInfoDto uDto = new UserInfoDto(u.getId(), u.getName());
        return uDto;
    }

}
 99 changes: 99 additions & 0 deletions99  


    
    
}
