package io.github.vencent.multidatasource.mapper;

import io.github.vencent.multidatasource.entity.User;
import io.github.vencent.multidatasource.tag.SlaveDb;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserMapper
 *
 * @author vencent
 * @create 2021-03-07 22:18
 **/
@Repository
@Mapper
@SlaveDb
public interface UserReadMapper {
    @Select("select * from USER")
    List<User> findAllUser();

}
