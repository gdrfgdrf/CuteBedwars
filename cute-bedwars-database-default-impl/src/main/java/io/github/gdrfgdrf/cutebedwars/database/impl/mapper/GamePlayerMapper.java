package io.github.gdrfgdrf.cutebedwars.database.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.gdrfgdrf.cutebedwars.database.impl.beans.game.GamePlayer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author gdrfgdrf
 */
@Mapper
public interface GamePlayerMapper extends BaseMapper<GamePlayer>, CreatableMapper {
    @Override
    @Update("CREATE TABLE IF NOT EXISTS game_players" +
            "        (" +
            "            id   BIGINT(0)    NOT NULL," +
            "            game_id BIGINT(0) NOT NULL," +
            "            team_id BIGINT(0) NOT NULL," +
            "            PRIMARY KEY (id)" +
            "        )")
    void createTable();
}
