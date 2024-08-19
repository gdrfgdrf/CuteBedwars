package io.github.gdrfgdrf.cutebedwars.database.impl.beans.typehandler;

import io.github.gdrfgdrf.cuteframework.utils.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author gdrfgdrf
 */
@MappedTypes(value = {
        UUID.class
})
public class UUIDTypeHandler extends BaseTypeHandler<UUID> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UUID parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.toString());
    }

    @Override
    public UUID getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String string = rs.getString(columnName);
        if (StringUtils.isBlank(string)) {
            return null;
        }
        return UUID.fromString(string);
    }

    @Override
    public UUID getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String string = rs.getString(columnIndex);
        if (StringUtils.isBlank(string)) {
            return null;
        }
        return UUID.fromString(string);
    }

    @Override
    public UUID getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String string = cs.getString(columnIndex);
        if (StringUtils.isBlank(string)) {
            return null;
        }
        return UUID.fromString(string);
    }
}
