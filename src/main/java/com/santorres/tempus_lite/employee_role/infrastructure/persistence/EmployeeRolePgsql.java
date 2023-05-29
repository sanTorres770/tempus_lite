package com.santorres.tempus_lite.employee_role.infrastructure.persistence;

import com.santorres.tempus_lite.employee_role.domain.EmployeeRole;
import com.santorres.tempus_lite.employee_role.domain.EmployeeRoleRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRolePgsql implements EmployeeRoleRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public EmployeeRolePgsql(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<EmployeeRole> getAll() {

        String sql = "select * from bd_1.employee_roles";

        return jdbcTemplate.query(sql, new EmployeeRoleRowMapper());
    }

    @Override
    public void saveAssignedRole(String documentId, int roleId) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("documentId",documentId);
        map.addValue("roleId",roleId);


        String sql = "insert into bd_1.users_roles values (:documentId, :roleId)";

        jdbcTemplate.update(sql,map);
    }

    @Override
    public List<EmployeeRole> getRolesByUser(String userId) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("userId",userId);

        String sql = " select er.*, ur.id from bd_1.employee_roles er " +
                " join bd_1.users_roles ur on er.id = ur.role_id " +
                " where ur.user_id = :userId ";

        return jdbcTemplate.query(sql, map, new EmployeeRoleRowMapper());
    }
}
