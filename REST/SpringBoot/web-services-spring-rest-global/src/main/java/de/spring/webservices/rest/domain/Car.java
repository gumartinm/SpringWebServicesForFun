package de.spring.webservices.rest.domain;

import java.util.Objects;

public class Car {
    private Long id;
    private String content;
    
	private Car(Long id, String content) {
        this.id = id;
        this.content = content;
    }
    
	public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
    
	public static Car.Builder builder() {
		return new Car.Builder();
	}

    @Override
    public int hashCode() {
      return Objects.hash(getId(), getContent());
    }

    @Override
    public boolean equals(Object object) {
      if (!(object instanceof Car)) {
        return false;
      }

      final Car other = (Car) object;
      return Objects.equals(getId(), other.getId())
    		  && Objects.equals(getContent(), other.getContent());
    }

	public static class Builder {
		private Long id;
		private String content;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder content(String content) {
			this.content = content;
			return this;
		}

		public Car build() {
			return new Car(id, content);
		}
	}

}
