package com.training.pms.galaxe.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Entity
@Table(name="freshproducts")
@Getter
@Setter
@ToString
public class Product {
	
	@Id
	private int productId;
	private String productName;
	private int quantityOnHand;
	private int price;

}
