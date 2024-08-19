package io.github.gdrfgdrf.cutebedwars.database.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.gdrfgdrf.cutebedwars.database.impl.beans.PlayerData;
import io.github.gdrfgdrf.cutebedwars.database.impl.beans.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.UUID;

/**
 * @author gdrfgdrf
 */
@Mapper
public interface PlayerDataMapper extends BaseMapper<PlayerData>, CreatableMapper {
    @Override
    @Update("CREATE TABLE IF NOT EXISTS users" +
            "        (" +
            "            id   BIGINT(0)    NOT NULL," +
            "            uuid VARCHAR(255) NOT NULL," +
            "            PRIMARY KEY (id)" +
            "        )")
    void createTable();

    @Results(id = "baseResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "uuid", column = "uuid", typeHandler = UUIDTypeHandler.class)
    })
    @Select("select * from users where uuid = #{uuid}")
    PlayerData selectByUuid(@Param("uuid") UUID uuid);
}
