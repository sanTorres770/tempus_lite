package com.santorres.tempus_lite.employee.infrastructure.persistence;

import com.santorres.tempus_lite.employee.domain.Employee;
import com.santorres.tempus_lite.employee.domain.EmployeeData;
import com.santorres.tempus_lite.employee.domain.EmployeeRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeePgsql implements EmployeeRepository {


    private final NamedParameterJdbcTemplate jdbcTemplate;

    public EmployeePgsql(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<EmployeeData> getAll() {


        String sql = " select employees.*, r.name as role_name " +
                " from bd_1.employees " +
                " join bd_1.employee_roles r on r.id = employees.fk_role order by document_id asc";

        return jdbcTemplate.query(sql, new EmployeeDataRowMapper());
    }

    @Override
    public boolean saveNewEmployee(Employee employee) {

        try {

            MapSqlParameterSource map = new MapSqlParameterSource();

            map.addValue("documentId",employee.getDocumentId());
            map.addValue("name",employee.getName());
            map.addValue("last_name",employee.getLastName());
            map.addValue("telephone",employee.getTelephone());
            map.addValue("email",employee.getEmail());
            map.addValue("fkRole",employee.getFkRole());

            String sql = "insert into bd_1.employees values (" +
                    ":documentId, " +
                    ":name, " +
                    ":last_name, " +
                    ":telephone, " +
                    ":email, " +
                    ":fkRole)";

            return jdbcTemplate.update(sql,map) > 0;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public EmployeeData getEmployeeById(String documentId) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("documentId",documentId);

        String sql = " select employees.*, r.name as role_name " +
                " from bd_1.employees " +
                " join bd_1.employee_roles r on r.id = employees.fk_role " +
                "where document_id = :documentId";

        return jdbcTemplate.queryForObject(sql, map, new EmployeeDataRowMapper());
    }

    @Override
    public boolean updateEmployee(Employee employee, String documentId) {

        try {

            MapSqlParameterSource map = new MapSqlParameterSource();

            map.addValue("documentId",documentId);
            map.addValue("name",employee.getName());
            map.addValue("last_name",employee.getLastName());
            map.addValue("telephone",employee.getTelephone());
            map.addValue("email",employee.getEmail());
            map.addValue("fkRole",employee.getFkRole());

            String sql = "update bd_1.employees set " +
                    "name=:name, " +
                    "last_name=:last_name, " +
                    "telephone=:telephone, " +
                    "email=:email, " +
                    "fk_role=:fkRole " +
                    " where document_id = :documentId";

            return jdbcTemplate.update(sql,map) > 0;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }
}
