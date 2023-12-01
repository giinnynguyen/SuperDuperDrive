package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.util.CustomResponse;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import com.udacity.jdnd.course3.critter.util.ErrorMessage;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Optional<Customer> customer = customerRepository.findById(petDTO.getOwnerId());
        if (!customer.isPresent()) {
            return null;
        }
        Pet pet = new Pet(petDTO.getType(), petDTO.getName(), petDTO.getBirthDate(), petDTO.getNotes(), customer.get());
        Pet result = this.petRepository.save(pet);
        return new PetDTO(result.getId(), result.getPetType(), result.getName(), result.getCustomer().getId(), result.getBirthDate(), result.getNotes());
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet result = this.petRepository.findById(petId).orElseThrow(null);
        return new PetDTO(result.getId(), result.getPetType(), result.getName(), result.getCustomer().getId(), result.getBirthDate(), result.getNotes());
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = this.petRepository.findAll();
        return pets.stream().map(pet -> new PetDTO(
                pet.getId(), pet.getPetType(), pet.getName(), pet.getCustomer().getId(), pet.getBirthDate(), pet.getNotes()
        )).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = this.petRepository.findByOwerId(ownerId);

        return pets.stream().map(pet -> new PetDTO(
                pet.getId(), pet.getPetType(), pet.getName(), pet.getCustomer().getId(), pet.getBirthDate(), pet.getNotes()
        )).collect(Collectors.toList());

    }
}
