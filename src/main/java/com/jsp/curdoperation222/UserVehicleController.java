package com.jsp.curdoperation222;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users-vehicles")
public class UserVehicleController {

    @Autowired
    private UserService userService;
@Autowired
    private  VehicleService vr;


    @GetMapping("/users")
    public Page<User> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return userService.getAllUsers(pageable);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setName(userDetails.getName());
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/vehicles")
    public Page<Vehicle> getVehicles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return vr.getAllVehicles(pageable);
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        Vehicle vehicle = vr.getVehicleById(id);
        return ResponseEntity.ok(vehicle);
    }

    @PostMapping("/vehicles")
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vr.saveVehicle(vehicle);
    }

    @PutMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicleDetails) {
        Vehicle vehicle = vr.getVehicleById(id);
        if (vehicle == null) {
            return ResponseEntity.notFound().build();
        }
        vehicle.setVehicleNumber(vehicleDetails.getVehicleType());
        return ResponseEntity.ok(vr.saveVehicle(vehicle));
    }

    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vr.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/users/{id}/vehicle")
    public ResponseEntity<Vehicle> getUserVehicleDetails(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null || user.getVehicle() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.getVehicle());
    }
}
