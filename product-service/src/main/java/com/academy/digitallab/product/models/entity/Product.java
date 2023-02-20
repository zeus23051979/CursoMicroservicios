package com.academy.digitallab.product.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode
//@ToString
@Entity
@Table(name="tbl_products")
@Data
@Builder
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="El nombre del campo no debe ser vacio")
	private String name;
	
	private String description;
	
	@Positive(message="El stock no puede ser menor a 0")
	private Double stock;
	
	private Double price;
	
	private String status;
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@NotNull(message="La categoria no puede ser vacia")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="category_id")
	@JsonIgnoreProperties({"dataSourceScriptDatabaseInitializer", "handler"})
	private Category category;



}