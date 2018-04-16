package com.pengda.repository;

import com.pengda.domain.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/11/2.
 */
@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin,Long>{
    UserLogin findUserNameById(int id) ;
}
