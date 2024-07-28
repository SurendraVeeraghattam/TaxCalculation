package com.tax.calculation.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hpsf.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tax.calculation.excel.ExcelGenerator;
import com.tax.calculation.model.IncomeDetModel;
import com.tax.calculation.model.UserDetModel;
import com.tax.calculation.service.IUserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@PostMapping("/Details")
	public UserDetModel saveUserDetModel(@RequestBody UserDetModel userDetModel) {
		return userService.saveUserDetModel(userDetModel);
	}
	
	@PostMapping("/Incomedetails")
	public IncomeDetModel saveIncomeDetModel(@RequestBody IncomeDetModel incomeDetModel) throws ApiException {
		userService.TotalTax(incomeDetModel);
		return userService.saveIncomeDetModel(incomeDetModel);
	}
	
	@GetMapping("/Getdetails")
	public List<UserDetModel> getAllUserDetModels(){
		return userService.getAllUserDetModels();
	}
	
	@GetMapping("/Getdetails/{id}")
	public UserDetModel getAllUserDetModels(@PathVariable("id") long userId){
		return (userService.getUserDetModelsById(userId));
	}
	
	@PutMapping("/Update/{id}")
	public UserDetModel updateUserDetModel(@PathVariable("id") long userId,@RequestBody UserDetModel userDetModel) {
		return (userService.updateUserDetModelById(userId, userDetModel));
	}
	
	@DeleteMapping("/Delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") long userId) {
		userService.deleteById(userId);
		return new ResponseEntity<String>("Deleted Successfully",HttpStatus.OK);
	}

	
	@GetMapping(path="/ExcelData")
	public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
	response.setContentType("application/octet-stream");
//	DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//	String currentDateTime = dateFormatter.format(new Date());
	String headerKey = "Content-Disposition";
	String headerValue = "attachment; filename=User TaxCalculation Data"  + ".xlsx";
	response.setHeader(headerKey, headerValue);
	List <UserDetModel> userDetModel = userService.getAllUserDetModels();
//	List <UserDetModel> userDetModel = null;
//	 if(userId.equals("ALL")) {
//		 userDetModel = userService.getAllUserDetModels();
//		}
//		else {
//			userDetModel = userService.findById(userId);
//		}
	ExcelGenerator generator = new ExcelGenerator(userDetModel);
	generator.generateExcelFile(response);
	}
	
	@GetMapping(path="/ExcelData/{id}")
	public void exportIntoExcelFile(@PathVariable("id") long userId, HttpServletResponse response) throws IOException{
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=User TaxCalculation Data"  + ".xlsx";
		response.setHeader(headerKey, headerValue);
		List <UserDetModel> userDetModel = userService.getAllUserDetModelsById(userId);
		ExcelGenerator generator = new ExcelGenerator(userDetModel);
		generator.generateExcelFile(response);
	}
	
	
	@GetMapping("/pageData")
	public Page<UserDetModel> getAllUserDetModel(
			@RequestParam(name = "page",defaultValue = "0") int page,
			@RequestParam(name = "pageSize",defaultValue = "4") int pageSize,
			@RequestParam(required = false) String userName
			){
		Pageable pageable  = PageRequest.of(page, pageSize);
		Page<UserDetModel> records = userService.getAllUserDetModel(userName, pageable);
		return records;
		}

	
}
