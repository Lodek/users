package com.wipro.bartenders.users.user.repository;

import com.wipro.bartenders.users.user.entity.User;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class UserRepository {

    List<User> users;

    public UserRepository(){
        this.users = new ArrayList<User>();
        User user1 = new User(1, "trillian", "Tricia", "McMillan", "1994-01-01", "tricia42@dolphins.com");
        User user2 = new User(2, "trillian2", "Tricia2", "McMillan2", "1994-01-01", "tricia42_2@dolphins.com");
        users.add(user1);
        users.add(user2);
    }

}
