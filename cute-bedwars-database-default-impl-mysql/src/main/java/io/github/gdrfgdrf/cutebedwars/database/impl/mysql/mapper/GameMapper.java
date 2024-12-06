package io.github.gdrfgdrf.cutebedwars.database.impl.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.beans.game.Game;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author gdrfgdrf
 */
@Mapper
public interface GameMapper extends BaseMapper<Game>, CreatableMapper {
    @Override
    @Update("CREATE TABLE IF NOT EXISTS games" +
            "        (" +
            "            id   BIGINT(0)    NOT NULL," +
            "            start_time TIMESTAMP NOT NULL," +
            "            end_time TIMESTAMP NOT NULL," +
            "            winner_team_id BIGINT(0) NOT NULL," +
            "            PRIMARY KEY (id)" +
            "        )")
    void createTable();
}
