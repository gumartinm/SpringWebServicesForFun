package de.spring.webservices.rest.infrastructure.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CarDto.Builder.class)
public class CarDto {

	@NotNull
	private final Long id;

	@NotNull
	@Size(max = 1024)
	private final String content;

	private CarDto(Long id, String content) {
		this.id = id;
		this.content = content;
	}

	public static CarDto.Builder builder() {
		return new CarDto.Builder();
	}

	public Long getId() {
		return this.id;
	}

	public String getContent() {
		return this.content;
	}

	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
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

		public CarDto build() {
			return new CarDto(id, content);
		}
	}
}
