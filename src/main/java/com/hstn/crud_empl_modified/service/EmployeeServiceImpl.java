package com.hstn.crud_empl_modified.service;

import com.hstn.crud_empl_modified.dao.EmployeeRepository;
import com.hstn.crud_empl_modified.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
        // Это мы добавили самостоятельно вместо того что было
    }

    @Override
    public Employee findById(int id) {
        // После изменения названия этого метода  везде  где  светится красным в
        // связи с этим изменением также изменим
        Optional<Employee> employee = employeeRepository.findById(id);
        // Здесь мы хотим вернуть работника, ное го может и не быть,
        // поэтому мы используем Optional
        Employee newEmployee = null;
        if (employee.isPresent()) {
            newEmployee = employee.get();
        }
        else {
            throw new RuntimeException("No employee with id = " + id);
        }
        return newEmployee;
    }

    @Override
    // @Transactional
    // Эта аннотация здесь нужна потому что происходит изменение данных
    // Т.к. интерфейс в пакете dao наследуется от JpaRepository<Employee, Integer>,
    // то эту аннотацию можно не указывать
    public Employee save(Employee employees) {
        return employeeRepository.save(employees);
    }

    @Override
    // @Transactional
    // Эта аннотация здесь нужна потому что происходит изменение данных
    // Т.к. интерфейс в пакете dao наследуется от JpaRepository<Employee, Integer>,
    // то эту аннотацию можно не указывать
    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }
}
