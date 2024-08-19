package io.github.gdrfgdrf.cutebedwars.database.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.gdrfgdrf.cutebedwars.database.impl.beans.game.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author gdrfgdrf
 */
@Mapper
public interface TeamMapper extends BaseMapper<Team>, CreatableMapper {
    @Override
    @Update("CREATE TABLE IF NOT EXISTS game_teams" +
            "        (" +
            "            id   BIGINT(0)    NOT NULL," +
            "            game_id BIGINT(0) NOT NULL," +
            "            PRIMARY KEY (id)" +
            "        )")
    void createTable();
}
