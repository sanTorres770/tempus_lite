package com.santorres.tempus_lite.user.infrastructure.persistence;

import com.santorres.tempus_lite.user.domain.User;
import com.santorres.tempus_lite.user.domain.UserRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserPgsql implements UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserPgsql(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean saveNewUser(User user) {


        try {

            MapSqlParameterSource map = new MapSqlParameterSource();

            map.addValue("id",user.getId());
            map.addValue("name",user.getName());
            map.addValue("last_name",user.getLastName());
            map.addValue("user_name",user.getUsername());
            map.addValue("password",user.getPassword());
            map.addValue("enabled",user.isEnabled());

            String sql = "insert into bd_1.users values (" +
                    ":id, " +
                    ":name, " +
                    ":last_name, " +
                    ":user_name, " +
                    ":password, " +
                    ":enabled)";

            return jdbcTemplate.update(sql,map) > 0;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;

    }

    @Override
    public User getUserByUserName(String username) {

        try {

            MapSqlParameterSource map = new MapSqlParameterSource();

            map.addValue("username",username);

            String sql = "select * from bd_1.users where user_name = :username";

            return jdbcTemplate.queryForObject(sql,map,new UserRowMapper());


        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }
}
