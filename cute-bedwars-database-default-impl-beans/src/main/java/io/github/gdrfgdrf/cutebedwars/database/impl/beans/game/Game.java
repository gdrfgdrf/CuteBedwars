package io.github.gdrfgdrf.cutebedwars.database.impl.beans.game;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.dreamyoung.mprelation.JoinColumn;
import com.github.dreamyoung.mprelation.OneToMany;
import io.github.gdrfgdrf.cutebedwars.beans.game.AbstractGame;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gdrfgdrf
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("games")
public class Game extends AbstractGame implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("start_time")
    private Timestamp startTime;

    @TableField("end_time")
    private Timestamp endTime;

    @TableField("winner_team_id")
    private Long winnerTeamId;

    @TableField(exist = false)
    @OneToMany
    @JoinColumn(name = "id", referencedColumnName = "game_id")
    private List<Team> teams = new ArrayList<>();
}
