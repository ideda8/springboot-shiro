package com.da.service;

import com.da.po.User;

public interface UserService {
    public User findByName(String name);

    public User findById(Integer id);

}
