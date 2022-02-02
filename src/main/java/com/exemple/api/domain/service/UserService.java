package com.exemple.api.domain.service;

import com.exemple.api.domain.entity.User;

public interface UserService {
    User findById(Long id);
    User create(User user);
    User update(Long id, User right);
}
