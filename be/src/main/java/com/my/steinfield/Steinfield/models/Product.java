package com.my.steinfield.Steinfield.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_products")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;
    //	Anotação JPA indicando que se trata de um relacionamento muitos para muitos
    @ManyToMany
//	Anotação JPA para representar Tabela de relacionamento entre as classes Product e Category,
	@JoinTable(name = "tb_product_category",
//	Indica qual nome do campo na tabela tb_product_category que representará o id do Product
            joinColumns= @JoinColumn(name = "product_id"),
//	Indica qual nome do campo na tabela tb_product_category que representará o id do Category	
            inverseJoinColumns = @JoinColumn(name = "category_id"))
//	Ultilizamos um HashSet para representar as categorias, para garantir que não teremos duas categorias iguais no mesmo produto
//	Note que declaramos um Set<Category> (Interface)  e instanciamos um new HashSet<>() (implementação) ==  List<AlgumaCoisa> = new ArrayList<>()
	private Set<Category> categories = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.product")
	private Set<OrderItem> items = new HashSet<>();
	
	public Product() { }
	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategories() {
		return categories;
	}
	
//	obter os Pedidos através dos itens
	@JsonIgnore
	public Set<Order> getOrder(){
		Set<Order> set = new HashSet<>();
		for (OrderItem x : this.items) {
			set.add(x.getOrder());
		}
		return set;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}