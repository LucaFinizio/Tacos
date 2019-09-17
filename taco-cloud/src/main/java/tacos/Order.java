package tacos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;



import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
@Entity
@Table(name="Taco_order")
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Date placedAt;
	
	@ManyToOne
	private User user;
	 
	@NotBlank(message="Delivery name is required")
	private String deliveryName;
	
	@NotBlank(message="Street is required")
	private String deliveryStreet;
	
	@NotBlank(message="City is required")
	private String deliveryCity;
	  
	@NotBlank(message="State is required")
	private String deliveryState;
	  
	@NotBlank(message="Zip code is required")
	private String deliveryZip;

	@CreditCardNumber(message="Not a valid credit card number")
	private String ccNumber;
	  
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
	private String ccExpiration;

	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;
	
	@ManyToMany(targetEntity=Taco.class)
	  private List<Taco> tacos = new ArrayList<>();
	  
	  public void addDesign(Taco design) {
	    this.tacos.add(design);
	  }
	  
	  @PrePersist
	  void placedAt() {
	    this.placedAt = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	  }

	public Long getId() {
		return id;
	}

	public Date getPlacedAt() {
		return placedAt;
	}

	public User getUser() {
		return user;
	}

	public String getDeliveryName() {
		return deliveryName;
	}

	public String getDeliveryStreet() {
		return deliveryStreet;
	}

	public String getDeliveryCity() {
		return deliveryCity;
	}

	public String getDeliveryState() {
		return deliveryState;
	}

	public String getDeliveryZip() {
		return deliveryZip;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public String getCcExpiration() {
		return ccExpiration;
	}

	public String getCcCVV() {
		return ccCVV;
	}

	public List<Taco> getTacos() {
		return tacos;
	}

	public void setDeliveryName(String fullname) {
		this.deliveryName = fullname;
	}

	public void setDeliveryStreet(String street) {
		this.deliveryStreet = street;		
	}

	public void setDeliveryCity(String city) {
		this.deliveryCity = city;
	}

	public void setDeliveryState(String state) {
		this.deliveryState = state;
	}

	public void setDeliveryZip(String zip) {
		this.deliveryZip = zip;
	}

	public void setUser(User user) {
		this.user = user;
	}
	  
	  
}
