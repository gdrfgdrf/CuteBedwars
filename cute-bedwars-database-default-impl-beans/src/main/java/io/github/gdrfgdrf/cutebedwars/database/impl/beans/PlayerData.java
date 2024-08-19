package io.github.gdrfgdrf.cutebedwars.database.impl.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.gdrfgdrf.cutebedwars.beans.AbstractPlayerData;
import io.github.gdrfgdrf.cutebedwars.database.impl.beans.typehandler.UUIDTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author gdrfgdrf
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("player_data")
public class PlayerData extends AbstractPlayerData implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @TableField(typeHandler = UUIDTypeHandler.class)
    private UUID uuid;
}
