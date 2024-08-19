package io.github.gdrfgdrf.cutebedwars.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.gdrfgdrf.cutebedwars.beans.typehandler.UUIDTypeHandler;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author gdrfgdrf
 */
@Data
@TableName("users")
public class PlayerData implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @TableField(typeHandler = UUIDTypeHandler.class)
    private UUID uuid;
}
