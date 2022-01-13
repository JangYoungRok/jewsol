package com.jewsol.common.date;

import java.util.Calendar;

import org.springframework.stereotype.Service;

@Service
public class DateService {

	public String getToday(){
		Calendar cal = Calendar.getInstance();
		String today = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + (cal.get(Calendar.DATE));
		return today;
	}
}
