package com.udacity.jdnd.course3.critter.user;

import com.google.common.collect.Sets;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = new Customer(customerDTO.getName(), customerDTO.getPhoneNumber(), customerDTO.getNotes());
        Customer result = this.customerRepository.save(customer);
        return new CustomerDTO(result.getId(), result.getName(), result.getPhoneNumber(), result.getNotes());
    }

    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = this.customerRepository.findAll();
        return customers.stream().map(customer -> new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getNotes(),
                customer.getPhoneNumber()
        )).collect(Collectors.toList());
    }

    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Optional<Pet> pet = this.petRepository.findById(petId);
        if (!pet.isPresent()) return null;
        Customer customer = this.customerRepository.findById(pet.get().getCustomer().getId()).orElse(null);
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getPhoneNumber(), customer.getNotes());
    }

    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee(employeeDTO.getName(), employeeDTO.getSkills(), employeeDTO.getDaysAvailable());
        Employee result = this.employeeRepository.save(employee);
        return new EmployeeDTO(result.getId(), result.getName(), result.getSkills(), result.getDayAvailables());
    }

    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = this.employeeRepository.findById(employeeId).orElse(null);
        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getSkills(), employee.getDayAvailables());
    }

    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId){
        Optional<Employee> e = this.employeeRepository.findById(employeeId);
        if (!e.isPresent()) return;
        Employee employee = e.get();
        employee.setDayAvailables(daysAvailable);
        this.employeeRepository.save(employee);
    }

    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        DayOfWeek dayOfWeek = employeeDTO.getDate().getDayOfWeek();
        List<Employee> result = this.employeeRepository
                .findByDayAvailablesContainingAndSkillsIn(dayOfWeek, employeeDTO.getSkills());

        List<Employee> employeeList = new ArrayList<>();
        result.forEach(e -> {
            if (e.getSkills().containsAll(employeeDTO.getSkills())) {
                employeeList.add(e);
            }
        });


        return employeeList.stream().map(employee -> new EmployeeDTO(
                employee.getId(), employee.getName(), employee.getSkills(), employee.getDayAvailables())
        ).collect(Collectors.toList());
    }

}
