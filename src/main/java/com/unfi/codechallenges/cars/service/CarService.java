package com.unfi.codechallenges.cars.service;

import com.unfi.codechallenges.cars.dto.CarDto;
import com.unfi.codechallenges.cars.entity.Car;
import com.unfi.codechallenges.cars.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CarService {

   private static final Logger log = LoggerFactory.getLogger(CarService.class);
	 private final CarRepository carRepository;

	    public CarService(CarRepository carRepository) {
	        this.carRepository = carRepository;
	    }

	    public CarDto createCar(CarDto car) {
	    	var newCar = new Car(car.getMake(), car.getModel(), car.getYear());
	    	newCar.setVin(car.getVin());
	        log.info("Creating car");
	        var createdCar = carRepository.save(newCar);
	        log.info("Created car with id: {}", createdCar.getId());
			 
			 CarDto dto = new CarDto();
			 dto.setId(createdCar.getId());
			 dto.setMake(createdCar.getMake());
			 dto.setModel(createdCar.getModel());
			 dto.setYear(createdCar.getYear());
			 dto.setVin(createdCar.getVin());
			 return dto;
			
	    }

	    @Transactional
	    public CarDto update(CarDto car) {
	        Optional<Car> optionalCar = carRepository.findById(car.getId());
	        if (optionalCar.isPresent()) {
	            var foundCar = optionalCar.get();
	            foundCar.setMake(car.getMake());
	            foundCar.setModel(car.getModel());
	            foundCar.setYear(car.getYear());
	            foundCar.setVin(car.getVin());
	            foundCar.setIsActive(true);
	            var updatedCar = carRepository.save(foundCar);
				  CarDto dto = new CarDto();
					 dto.setId(updatedCar.getId());
					 dto.setMake(updatedCar.getMake());
					 dto.setModel(updatedCar.getModel());
					 dto.setYear(updatedCar.getYear());
					 dto.setVin(updatedCar.getVin());
					 return dto;
				 
	        } else {
	            throw new RuntimeException("Car not found");
	        }
		
	    }

	    @Transactional
	    public void delete(Long id) {
	        Optional<Car> optionalCar = carRepository.findById(id);
	        if (optionalCar.isPresent()) {
	            var foundCar = optionalCar.get();
	            log.info("Soft deleting car with id: {}", foundCar.getId());
	            foundCar.setIsActive(false);
	            carRepository.save(foundCar);
	        } else {
	            throw new RuntimeException("Car not found");
	        }
	    }

	    public List<CarDto> getAll() {
	        var allCars = carRepository.findAllByIsActiveTrue();
	        List<CarDto> cars = new ArrayList<>();
	        for (Car car : allCars) {
				  
				    CarDto dto = new CarDto();
					 dto.setId(car.getId());
					 dto.setMake(car.getMake());
					 dto.setModel(car.getModel());
					 dto.setYear(car.getYear());
					 dto.setVin(car.getVin());
					 cars.add(dto);
					
				 
	        }
	        return cars;
	    }
}
