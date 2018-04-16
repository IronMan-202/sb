package com.pengda.service.impl;

import com.pengda.domain.UserLogin;
import com.pengda.repository.UserLoginRepository;
import com.pengda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/11/2.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserLoginRepository userLoginRepository ;

    //查询姓名
    @Override
    public UserLogin getUserName(int id) {
        return this.userLoginRepository.findUserNameById(id) ;
    }
}
