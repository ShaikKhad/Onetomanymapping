package com.jsp.newcurdoperation;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class CheckIn {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String checkinDate;
private String checkoutDate;

@OneToMany(mappedBy = "checkin",cascade = CascadeType.ALL, orphanRemoval = true)
private List<Guest> guest;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getCheckinDate() {
	return checkinDate;
}

public void setCheckinDate(String checkinDate) {
	this.checkinDate = checkinDate;
}

public String getCheckoutDate() {
	return checkoutDate;
}

public void setCheckoutDate(String checkoutDate) {
	this.checkoutDate = checkoutDate;
}

public List<Guest> getGuest() {
	return guest;
}

public void setGuest(List<Guest> guest) {
	this.guest = guest;
}
}
