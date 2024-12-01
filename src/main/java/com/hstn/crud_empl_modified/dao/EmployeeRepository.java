package com.hstn.crud_empl_modified.dao;

import com.hstn.crud_empl_modified.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // В дженериках указывается класс сущности (из пакета entity)
    // и тот тип который является первичным ключом, т.е. Integer (т.к. int)

}
