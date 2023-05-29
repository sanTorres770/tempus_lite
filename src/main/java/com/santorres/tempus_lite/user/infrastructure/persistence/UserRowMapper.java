package com.santorres.tempus_lite.user.infrastructure.persistence;

import com.santorres.tempus_lite.user.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        String id = rs.getString("id");
        String name = rs.getString("name");
        String lastName = rs.getString("last_name");
        String username = rs.getString("user_name");
        String password = rs.getString("password");
        boolean enabled = rs.getBoolean("enabled");



        return new User(
                id,
                name,
                lastName,
                username,
                password,
                enabled
        );
    }
}
