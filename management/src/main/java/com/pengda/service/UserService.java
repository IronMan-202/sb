package com.pengda.service;

import com.pengda.domain.UserLogin;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/11/2.
 */
@Service
public interface UserService {

    //查询姓名
    UserLogin getUserName(int id) ;
}
