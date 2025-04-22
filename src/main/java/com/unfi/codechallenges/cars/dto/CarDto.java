package com.unfi.codechallenges.cars.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
	
	    private Long id;

	   @NotBlank(message = "Make is mandatory")
	    private String make;

	   @NotBlank(message = "Model is mandatory")
	    private String model;

	    @NotBlank(message = "Year is mandatory")
	    private String year;

	    @NotBlank(message = "VIN is mandatory")
	    private String vin;
	    
	    public Long getId() { return id; }
	    public String getMake() { return make; }
	    public String getModel() { return model; }
	    public String getYear() 
	    {
	    	return year;
	    	}
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
			CarDto other = (CarDto) obj;
			return Objects.equals(id, other.id) && Objects.equals(make, other.make)
					&& Objects.equals(model, other.model) && Objects.equals(vin, other.vin)
					&& Objects.equals(year, other.year);
		}
		@Override
		public String toString() {
			return "CarDto [id=" + id + ", make=" + make + ", model=" + model + ", year=" + year + ", vin=" + vin + "]";
		}
	   
}