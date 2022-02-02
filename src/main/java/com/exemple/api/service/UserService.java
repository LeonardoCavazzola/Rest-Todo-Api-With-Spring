package com.exemple.api.service;

import com.exemple.api.entity.User;

public interface UserService {
    User findById(Long id);
    User create(User user);
    User update(Long id, User right);
}
