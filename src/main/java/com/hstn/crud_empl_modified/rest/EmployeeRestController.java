package com.hstn.crud_empl_modified.rest;

import com.hstn.crud_empl_modified.entity.Employee;
import com.hstn.crud_empl_modified.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    //    private EmployeeDAO employeeDAO;
    private EmployeeService employeeService;

    @Autowired
    // В этом случае аннотация эта не обязательна, т.к. в
    // этом классе только один конструктор
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if (employee == null) {
            throw new RuntimeException("Employee with id = " + employeeId + " not found.");
        }
        else {
            return employee;
        }
    }

    @PostMapping("/employees")
    // Для добавления сотрудника мы будем использовать метод post()
    // Метод post() мы не можем использовать в браузере, т.к.
    // в браузере мы можем использовать только метод get()
    // но мы будем использовать метод post() в приложении Postman.
    // Через метод post() мы отправляем данные в БД, а не получаем как через метод get()
    // Так мы добавим в БД нового сотрудника, выбрав raw  и JSON в приложении Postman
    // на вкладке Body, и далее в формате JSON там напишем все
    // данные нового сотрудника кроме id
    // {"firstName":"Tania","lastName":"Gimalayeva","email":"gimalayeva@gmail.com"}
    // после выполнения метода post() через приложение Postman добавится в нашу БД такой сотрудник
    // {"id":6,"firstName":"Tania","lastName":"Gimalayeva","email":"gimalayeva@gmail.com"}

    public Employee addEmployee(@RequestBody Employee employee) {
        // Аннотация @RequestBody нужна чтобы передать параметры добавляемого сотрудника
        employee.setId(0);
        return employeeService.save(employee);
    }

    @PutMapping("/employees")
    // Эта аннотация служит для обновления уже существующих записей
    // Для изменения сотрудника мы будем использовать метод put()
    // Метод put() мы не можем использовать в браузере, т.к.
    // в браузере мы можем использовать только метод get()
    // но мы будем использовать метод put() в приложении Postman.
    // Через метод put() мы отправляем на изменение
    // данные в БД, а не получаем как через метод get()
    // Так мы изменяем в БД данные уже имеющегося сотрудника,
    // выбрав raw  и JSON в приложении Postman на вкладке Body,
    // и далее в формате JSON там напишем все
    // данные изменённого сотрудника вместе с id
    // {"id":6,'firstName":"Tania","lastName":"Gimalayeva", "email":"gimalayeva@gmail.com"}
    // после выполнения метода put() через приложение Postman
    // изменятся данные в нашей БД указанного сотрудника:
    // {"id":6,"firstName":"Tania","lastName":"Vetrova","email":"vetrova@gmail.com"}
    public Employee updateEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.save(employee);
        return updatedEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    // Эта аннотация служит для удаления записей
    // Для удаления сотрудника мы будем использовать метод delete()
    // Метод delete() мы не можем использовать в браузере, т.к.
    // в браузере мы можем использовать только метод get()
    // но мы будем использовать метод delete() в приложении Postman.
    // Через метод delete() мы отправляем на удаление
    // данные в БД, а не получаем как через метод get()
    // Мы удаляем из БД сотрудника, сначала через метод get()
    // получив всех сотрудников, далее в конце адресной строки добавив /6,
    // и выбрав метод delete() нажав Send получаем сообщение:
    // Deleted employee - Employee{id=6, firstName='Tania',
    // lastName='Vetrova', email='vetrova@gmail.com'}
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if (employee == null) {
            throw new RuntimeException("Employee with id = " + employeeId + " not found.");
        }
        else {
            employeeService.deleteEmployeeById(employeeId);
        }
        return "Deleted employee - " + employee.toString();
        // т.к. информация без использования метода toString()
        // будет точно такой же как и без его использования, то
        // он горит серым цветом
    }
}