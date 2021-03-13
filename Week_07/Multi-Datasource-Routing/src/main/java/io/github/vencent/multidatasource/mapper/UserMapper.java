package io.github.vencent.multidatasource.mapper;

import io.github.vencent.multidatasource.entity.User;
import org.apache.ibatis.annotations.Insert;
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
public interface UserMapper {

    @Select("select * from USER")
    List<User> findAllUser();

    @Insert("insert into USER values (#{id},#{userName})")
    void addUser( User user);
}
