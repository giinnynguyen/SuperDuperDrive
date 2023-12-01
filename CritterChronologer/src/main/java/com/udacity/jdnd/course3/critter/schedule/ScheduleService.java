package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        List<Employee> employees = this.employeeRepository.findAllById(scheduleDTO.getEmployeeIds());
        List<Pet> pets = this.petRepository.findAllById(scheduleDTO.getPetIds());

        if (employees.size() == 0 || pets.size() == 0) return null;
        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivities(scheduleDTO.getActivities());

        schedule.setEmployees(employees);
        schedule.setPets(pets);
        Schedule result = this.scheduleRepository.save(schedule);
        List<Long> petIds = result.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList());
        List<Long> employeeIds = result.getEmployees().stream().map(e -> e.getId()).collect(Collectors.toList());
        return new ScheduleDTO(result.getId(), employeeIds, petIds, result.getDate(), result.getActivities());
    }

    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> list = this.scheduleRepository.findAll();
        return getScheduleDTOS(list);
    }

    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        Pet pet = this.petRepository.getOne(petId);
        List<Schedule> list = this.scheduleRepository.findAllByPetsContaining(pet);
        return getScheduleDTOS(list);
    }

    private List<ScheduleDTO> getScheduleDTOS(List<Schedule> list) {
        return list.stream().map(s -> new ScheduleDTO(
                s.getId(),
                s.getEmployees().stream().map(e -> e.getId()).collect(Collectors.toList()),
                s.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()),
                s.getDate(),
                s.getActivities()
        )).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        Employee employee = this.employeeRepository.getOne(employeeId);
        List<Schedule> list = this.scheduleRepository.findAllByEmployeesContaining(employee);
        return getScheduleDTOS(list);
    }

    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Pet> pets = this.petRepository.findByOwerId(customerId);
        List<Schedule> list = this.scheduleRepository.findAllByPetsIn(pets);
        return getScheduleDTOS(list);
    }
}
