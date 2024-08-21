package io.github.gdrfgdrf.cutebedwars.database.impl.beans.game;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.gdrfgdrf.cutebedwars.beans.game.AbstractPlayer;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author gdrfgdrf
 */
@EqualsAndHashCode(callSuper = true)
@TableName("game_players")
public class Player extends AbstractPlayer implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @TableField(value = "game_id")
    private Long gameId;
    @TableField(value = "team_id")
    private Long teamId;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getGameId() {
        return gameId;
    }

    @Override
    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    @Override
    public Long getTeamId() {
        return teamId;
    }

    @Override
    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
