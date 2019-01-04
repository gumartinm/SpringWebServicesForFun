package de.example.spring.pact.provider.domain.entity;

public class Car {
	private final String brand;
	private final String engine;
	
	private Car(String brand, String engine) {
		this.brand = brand;
		this.engine = engine;
	}
	
	public String getBrand() {
		return brand;
	}

	public String getEngine() {
		return engine;
	}

	public static class CarBuilder {
		private String brand;
		private String engine;
		
		public CarBuilder withBrand(String brand) {
			this.brand = brand;
			return this;
		}
		
		public CarBuilder withEngine(String engine) {
			this.engine = engine;
			return this;
		}
		
		public Car build() {
			return new Car(this.brand, this.engine);
		}
	}

}
