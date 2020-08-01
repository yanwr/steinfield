package com.my.steinfield.Steinfield.models;

import com.my.steinfield.Steinfield.models.pk.OrderItemPk;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem {
	
//	cria uma abstração dentro da entidade que não existe na tabela do banco de dados.
	@EmbeddedId
	private OrderItemPk id = new OrderItemPk();
	
	private Integer quantity;
	
	private Double price;
	
	public OrderItem() { }
	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		super();
		this.id.setOrder(order);
		this.id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}
	
	@JsonIgnore
	public Order getOrder (){
		return this.id.getOrder();
	}
	
	public void SetOrder(Order order) {
		this.id.setOrder(order);
	}
	public Product getProduct() {
		return this.id.getProduct();
	}
	
	public void setProduct(Product product) {
		this.id.setProduct(product);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}