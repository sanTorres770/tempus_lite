package com.santorres.tempus_lite.employee.infrastructure.persistence;

import com.santorres.tempus_lite.employee.domain.Employee;
import com.santorres.tempus_lite.employee.domain.EmployeeData;
import com.santorres.tempus_lite.employee.domain.EmployeeRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EmployeePgsql implements EmployeeRepository {


    private final NamedParameterJdbcTemplate jdbcTemplate;

    public EmployeePgsql(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<EmployeeData> getAll() {


        String sql = " select employees.*, r.name as role_name, a.name as area_name " +
                " from bd_1.employees " +
                " join bd_1.employee_roles r on r.id = employees.fk_role " +
                " left outer join bd_1.areas a on a.id = employees.fk_area " +
                " where fk_role not in ('1')" +
                " order by employees.name";

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
            map.addValue("fkArea",employee.getFkArea());

            String sql = "insert into bd_1.employees values (" +
                    ":documentId, " +
                    ":name, " +
                    ":last_name, " +
                    ":telephone, " +
                    ":email, " +
                    ":fkRole," +
                    ":fkArea)";

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

        String sql = " select employees.*, r.name as role_name, a.name as area_name" +
                " from bd_1.employees " +
                " join bd_1.employee_roles r on r.id = employees.fk_role " +
                " join bd_1.areas a on a.id = employees.fk_area " +
                " where document_id = :documentId";

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
            map.addValue("fkArea",employee.getFkArea());

            String sql = "update bd_1.employees set " +
                    "name=:name, " +
                    "last_name=:last_name, " +
                    "telephone=:telephone, " +
                    "email=:email, " +
                    "fk_role=:fkRole, " +
                    "fk_area=:fkArea " +
                    " where document_id = :documentId";

            return jdbcTemplate.update(sql,map) > 0;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public List<EmployeeData> getHeadAreaEmployees() {
        String sql = " select employees.*, r.name as role_name, a.name as area_name " +
                " from bd_1.employees " +
                " join bd_1.employee_roles r on r.id = employees.fk_role " +
                " left outer join bd_1.areas a on a.id = employees.fk_area " +
                " where fk_role = 2 and fk_area is null ";

        return jdbcTemplate.query(sql, new EmployeeDataRowMapper());
    }

    @Override
    public List<EmployeeData> getOperatorEmployeesByArea(String fkArea) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("fkArea",fkArea);

        String sql = " select employees.*, r.name as role_name, a.name as area_name " +
                " from bd_1.employees " +
                " join bd_1.employee_roles r on r.id = employees.fk_role " +
                " left outer join bd_1.areas a on a.id = employees.fk_area" +
                " where fk_area = :fkArea and fk_role= 3 ";

        return jdbcTemplate.query(sql, map, new EmployeeDataRowMapper());
    }

    @Override
    public boolean assignEmployeeToArea(String employeeId, String fkArea) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("employeeId",employeeId);
        map.addValue("fkArea",fkArea);

        String sql = "update bd_1.employees set fk_area=:fkArea where document_id=:employeeId";

        return jdbcTemplate.update(sql,map) > 0;
    }

    @Override
    public boolean assignEmployeeToTask(String employeeId, String fkTask) {
        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("employeeId",employeeId);
        map.addValue("fkTask",fkTask);
        map.addValue("assignedAt", LocalDateTime.now());

        String sql = "update bd_1.assigned_tasks set fk_task=:fkTask, assigned_at = :assignedAt " +
                "where fk_employee=:employeeId and fk_task is null and assigned_at is null";

        return jdbcTemplate.update(sql,map) > 0;
    }

    @Override
    public List<EmployeeData> getOperatorEmployeesAvailable() {


        String sql = " select employees.*, r.name as role_name, a.name as area_name, t.fk_task " +
                " from bd_1.employees " +
                " join bd_1.employee_roles r on r.id = employees.fk_role " +
                " left outer join bd_1.areas a on a.id = employees.fk_area" +
                " left outer join bd_1.assigned_tasks t on t.fk_employee = employees.document_id" +
                " where fk_role = 3 and fk_area is null and t.fk_task is null";

        return jdbcTemplate.query(sql, new EmployeeDataRowMapper());
    }

    @Override
    public List<EmployeeData> getOperatorEmployeesAvailableByArea(String fkArea) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("fkArea",fkArea);

        String sql = " select employees.*, r.name as role_name, a.name as area_name " +
                " from bd_1.employees " +
                " join bd_1.employee_roles r on r.id = employees.fk_role " +
                " left outer join bd_1.areas a on a.id = employees.fk_area" +
                " left outer join bd_1.assigned_tasks t on t.fk_employee = employees.document_id" +
                " where fk_role = 3 and fk_area= :fkArea and t.fk_task is null";

        return jdbcTemplate.query(sql, map, new EmployeeDataRowMapper());
    }

    @Override
    public void updateEmployeeArea(String employeeId, String areaId) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("employeeId",employeeId);
        map.addValue("areaId",areaId);

        String sql = "update bd_1.employees set fk_area = :areaId where document_id=:employeeId";

        jdbcTemplate.update(sql,map);

    }

    @Override
    public List<EmployeeData> getOperatorEmployeesByTask(String fkTask) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("fkTask",fkTask);

        String sql = " select employees.*, r.name as role_name, a.name as area_name " +
                " from bd_1.employees " +
                " join bd_1.employee_roles r on r.id = employees.fk_role " +
                " left outer join bd_1.areas a on a.id = employees.fk_area" +
                " join bd_1.assigned_tasks on fk_employee= employees.document_id" +
                " where assigned_tasks.fk_task = :fkTask ";

        return jdbcTemplate.query(sql, map, new EmployeeDataRowMapper());
    }


}
