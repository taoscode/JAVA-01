package io.github.vencent.multidatasource.mapper;

import io.github.vencent.multidatasource.entity.User;
import io.github.vencent.multidatasource.tag.MasterDb;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * UserMapper
 *
 * @author vencent
 * @create 2021-03-07 22:18
 **/
@Repository
@Mapper
@MasterDb
public interface UserWriteMapper {

    @Insert("insert into USER values (#{id},#{userName})")
    void addUser( User user);
}
