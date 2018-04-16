package com.pengda.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/11/2.
 */

@Data
@Entity
public class UserLogin {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    private int id ;
    @Column
    private String loginName ;
    @Column
    private String loginPassword ;
}
