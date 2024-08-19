package io.github.gdrfgdrf.cutebedwars.database.impl.beans.game;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.gdrfgdrf.cutebedwars.beans.game.AbstractGamePlayer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author gdrfgdrf
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("game_players")
public class GamePlayer extends AbstractGamePlayer implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @TableField(value = "game_id")
    private Long gameId;
    @TableField(value = "team_id")
    private Long teamId;
}
