package com.da.mapper;

import com.da.po.User;

public interface UserMapper {
    public User findByName(String name);

    public User findById(Integer id);


}
