package com.jewsol.orangebox.godoOrder.controller;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jewsol.orangebox.godoOrder.service.GodoOrderService;

@Controller
public class GodoOrderController{

	@Autowired
	private GodoOrderService godoOrderService;
	
	@RequestMapping("/orangebox/godoOrder/sendGodoOrder.do")
	public void sendGodoOrder(@RequestParam("params") String params) throws ParseException{
		//System.out.println(params);
		godoOrderService.insertParams(params);
		
	
	}
}
