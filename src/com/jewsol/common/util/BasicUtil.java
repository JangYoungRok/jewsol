package com.jewsol.common.util;

import org.springframework.stereotype.Service;

@Service
public class BasicUtil {

	public Double doubleCut(Double value) {
		return (Math.round(value*100)/100.0);
	}

	public int intCut(int value) {
		return (Math.round(value/100)*100);
	}

}
