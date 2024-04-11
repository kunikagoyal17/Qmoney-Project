package com.crio.jukebox.service;


import com.crio.jukebox.dtos.UserInfoDto;

public interface IUserService {

    public UserInfoDto create(String name);

}