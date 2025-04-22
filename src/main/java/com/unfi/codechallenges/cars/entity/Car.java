package com.unfi.codechallenges.cars.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "CAR")
public class Car {

   @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAR_SEQ")
    @SequenceGenerator(sequenceName = "CAR_SEQ", allocationSize = 1, name = "CAR_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "MAKE")
    private String make;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "YEAR_BUILT")
    private String year;

    @Column(name = "VIN")
    private String vin;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @Column(updatable = false, name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "LAST_UPDATED")
    private LocalDateTime lastUpdated;

    // Constructors
    public Car() {}

    public Car(String make, String model, String year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }
    
    public Long getId() { return id; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public String getYear() { return year; }
    public String getVin() { return vin; }
    
    

    public void setId(Long id) {
		this.id = id;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}
	
	

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, make, model, vin, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(id, other.id) && Objects.equals(make, other.make) && Objects.equals(model, other.model)
				&& Objects.equals(vin, other.vin) && Objects.equals(year, other.year);
	}
	
	

	@Override
	public String toString() {
		return "Car [id=" + id + ", make=" + make + ", model=" + model + ", year=" + year + ", vin=" + vin + "]";
	}

	@PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        lastUpdated = LocalDateTime.now();
        isActive = true;
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDateTime.now();
        isActive = true;
    }
}