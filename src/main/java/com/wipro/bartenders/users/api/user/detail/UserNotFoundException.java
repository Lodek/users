package com.wipro.bartenders.users.api.user.detail;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super(String.format("User with ID %s does not exist", id));
    }
}
