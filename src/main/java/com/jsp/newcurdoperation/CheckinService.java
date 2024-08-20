package com.jsp.newcurdoperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CheckinService {
@Autowired
private CheckInRepository cr;
public Page<CheckIn> getAllCheckIns(Pageable pagable){
	return cr.findAll(pagable);
}
public CheckIn createCheckIn(CheckIn checkin) {
	return cr.save(checkin);
	
}
public CheckIn getCheckInById(long id) {
	return cr.findById(id).orElse(null);
	
}
public CheckIn updateCheckIn(Long id, CheckIn checkIn) {
	CheckIn existingCheckIn=getCheckInById(id);
	existingCheckIn.setCheckinDate(checkIn.getCheckinDate());
	existingCheckIn.setCheckoutDate(checkIn.getCheckoutDate());
	return cr.save(existingCheckIn);
	}


public CheckIn
}
