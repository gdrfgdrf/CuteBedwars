package io.github.gdrfgdrf.cutebedwars.database.impl.beans.game;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.dreamyoung.mprelation.JoinColumn;
import com.github.dreamyoung.mprelation.Lazy;
import com.github.dreamyoung.mprelation.OneToMany;
import io.github.gdrfgdrf.cutebedwars.beans.game.AbstractPlayer;
import io.github.gdrfgdrf.cutebedwars.beans.game.AbstractTeam;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gdrfgdrf
 */
@EqualsAndHashCode(callSuper = true)
@TableName("game_teams")
public class Team extends AbstractTeam implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("game_id")
    private Long gameId;

    @TableField(exist = false)
    @OneToMany
    @JoinColumn(name = "id", referencedColumnName = "team_id")
    @Lazy
    private List<Player> gamePlayers = new ArrayList<>();

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
    public List<AbstractPlayer> getGamePlayers() {
        return new ArrayList<>(gamePlayers);
    }
}
