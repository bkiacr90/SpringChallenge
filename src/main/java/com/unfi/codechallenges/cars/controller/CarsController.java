package com.unfi.codechallenges.cars.controller;

import com.unfi.codechallenges.cars.dto.CarDto;
import com.unfi.codechallenges.cars.service.CarService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cars")
public class CarsController {

     private static final Logger log = LoggerFactory.getLogger(CarsController.class);
	 private final CarService carService;

	    public CarsController(CarService carService) {
	        this.carService = carService;
	    }

	    @GetMapping("/getAll")
	    public ResponseEntity<List<CarDto>> getAllCars() {
	        log.info("Getting all active cars");
	        return ResponseEntity.ok(carService.getAll());
	    }

	    @PostMapping("/createcar")
	    public ResponseEntity<CarDto> createCar(@RequestBody CarDto car) {
	    	log.info("Creating a new car");
	        return ResponseEntity.ok(carService.createCar(car));
	    }

		@PutMapping("/update/{id}")
	    public ResponseEntity<CarDto> updateCar(@PathVariable Long id,@RequestBody CarDto car) {
			log.info("Updating car with id: {}");
			car.setId(id);
	        return ResponseEntity.ok(carService.update(car));
	    }

	    @DeleteMapping("/{id}")
		 public ResponseEntity<String> deleteCar(@PathVariable("id") Long id){
	    	 carService.delete(id);
	    	 return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
	    }
	    

	    // Handle constraint violations
	    @ExceptionHandler(ConstraintViolationException.class)
	    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException e) {
	        log.error("Constraint violation occurred", e);
	        return ResponseEntity.badRequest().body("Constraint violation: " + e.getMessage());
	    }

	    // Handle method argument validation issues
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<String> handleValidationError(MethodArgumentNotValidException e) {
	        log.error("Validation failed", e);
	        return ResponseEntity.badRequest().body("Validation error: " + e.getMessage());
	    }


}