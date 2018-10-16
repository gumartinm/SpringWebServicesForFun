package de.spring.example.persistence.domain;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Ad {

	private String id;

	@Max(60)
	private Long companyId;

	@Max(40)
	private Long companyCategId;

	@Size(min = 2, max = 255)
	private String adMobileImage;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ssZ")
	// IT DOES NOT WORK. I THINK THERE IS A BUG BECAUSE
	// MappingMongoConverter.this.conversionService never includes my custom
	// converters!!!!
	// ALL THIS STUFF SUCKS A LOT!!!
	// private OffsetDateTime createdAt;
	private Date createdAt;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ssZ")
	// IT DOES NOT WORK. I THINK THERE IS A BUG BECAUSE
	// MappingMongoConverter.this.conversionService never includes my custom
	// converters!!!!
	// ALL THIS STUFF SUCKS A LOT!!!
	// private OffsetDateTime updatedAt;
	private Date updatedAt;

	protected Ad() {

	}

	public Ad(String id, Long companyId, Long companyCategId, String adMobileImage,
	        Date createdAt, Date updatedAt) {
		this.id = id;
		this.companyId = companyId;
		this.companyCategId = companyCategId;
		this.adMobileImage = adMobileImage;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	/**
	 * WARNING: JPA REQUIRES GETTERS!!!
	 */

	public String getId() {
		return id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public Long getCompanyCategId() {
		return companyCategId;
	}

	public String getAdMobileImage() {
		return adMobileImage;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
}
