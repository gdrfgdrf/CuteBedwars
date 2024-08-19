package io.github.gdrfgdrf.cutebedwars.database.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.gdrfgdrf.cutebedwars.beans.PlayerData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author gdrfgdrf
 */
@Mapper
public interface PlayerDataMapper {
    @Insert("insert into users (id, uuid) values (#{id}, #{uuid})")
    int insert(PlayerData playerData);

    @Select("select * from users where uuid = #{uuid}")
    PlayerData selectByUuid(@Param("uuid") String uuid);
}
