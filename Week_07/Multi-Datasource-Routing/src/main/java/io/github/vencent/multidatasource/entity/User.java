package io.github.vencent.multidatasource.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * User
 *
 * @author vencent
 * @create 2021-03-07 22:20
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Long id;
    private String userName;
}
