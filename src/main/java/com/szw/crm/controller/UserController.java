package com.szw.crm.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.szw.crm.bean.AddCustomerRequest;
import com.szw.crm.bean.AddDishRequest;
import com.szw.crm.bean.CustomerExpenditureBean;
import com.szw.crm.bean.ShowCustomerComplaintBean;
import com.szw.crm.bean.ShowOrderRecordBean;
import com.szw.crm.entity.Complaint;
import com.szw.crm.entity.Customer;
import com.szw.crm.entity.DishMenu;
import com.szw.crm.entity.Order;
import com.szw.crm.entity.User;
import com.szw.crm.repository.ComplaintRepository;
import com.szw.crm.repository.CustomerRepository;
import com.szw.crm.repository.DishMenuRepository;
import com.szw.crm.repository.OrderRepository;
import com.szw.crm.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private CustomerRepository customerRepository ;
	
	@Autowired
	private DishMenuRepository dishMenuRepository ;
	
	@Autowired
	private OrderRepository orderRepository ;
	
	@Autowired
	private ComplaintRepository complaintRepository ;
	
	@Autowired
	private UserRepository userRepository ;
	
	@RequestMapping("/customerFilePage")
	public String gotoCustomerFilePage(HttpServletRequest request, ModelMap map) {
		User user = (User)request.getSession().getAttribute("user") ;
		if (user == null) return "redirect:/" ;
			else {
			
			int pageNo = 1 ;
			int pageSize = 12 ;
			
			if (request.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt(request.getParameter("pageNo")) ;
			}
			
			Page<Customer> page = this.customerRepository.findAll(new PageRequest(pageNo-1, pageSize)) ;
			
			List<Customer> allCustomers = page.getContent() ;
			
			map.put("allCustomers", allCustomers) ;
			map.put("pageNo", pageNo) ;
			
			return "/views/customerFile/customerFile" ;
		}
	}
	
	@RequestMapping("/addCustomer")
	public String gotoAddCustomer(HttpServletRequest request, ModelMap map) {
		User user = (User)request.getSession().getAttribute("user") ;
		if (user == null) return "redirect:/" ;
			else {
			
			return "/views/customerFile/addCustomer" ;	
		}
		
	}
	
	@RequestMapping("/addCustomer.do")
	public String AddCustomer(ModelMap map, AddCustomerRequest request) {
		
		Customer customer = new Customer () ;
		try {
			customer.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse(request.getBirth()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		customer.setEmail(request.getEmail());
		customer.setMobile(request.getMobile());
		customer.setName(request.getName());
		if (request.getSex().equals("MAN")) customer.setSex(Customer.SEX.MAN);
		else customer.setSex(Customer.SEX.WOMAN);
		
		this.customerRepository.save(customer) ;
		
		map.addAttribute("addCustomerResult", "SUCCESS") ;
		
		return "forward:/customerFilePage" ;
	}
	
	@RequestMapping("/businessInfoPage")
	public String gotoBusinessInfoPage(HttpServletRequest request, ModelMap map) {
		User user = (User)request.getSession().getAttribute("user") ;
		if (user == null) return "redirect:/" ;
			else {
			
			int pageNo = 1 ;
			int pageSize = 12 ;
			
			if (request.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt(request.getParameter("pageNo")) ;
			}
			
			Page<DishMenu> page = this.dishMenuRepository.findAll(new PageRequest(pageNo-1, pageSize)) ;
			
			List<DishMenu> allDishes = page.getContent() ;
			
			map.put("allDishes", allDishes) ;
			map.put("pageNo", pageNo) ;
			
			return "/views/businessInfo/dishMenu" ;
		}
	}
	
	@RequestMapping("/addDish")
	public String gotoAddDish(HttpServletRequest request, ModelMap map) {
		User user = (User)request.getSession().getAttribute("user") ;
		if (user == null) return "redirect:/" ;
			else {
			
			return "/views/businessInfo/addDish" ;	
		}
	}
	
	@RequestMapping("/addDish.do")
	public String AddDish(ModelMap map, AddDishRequest request) {
		
		DishMenu dish = new DishMenu() ;
		
		dish.setDishId(request.getDishId());
		dish.setDishName(request.getDishName());
		
		if (request.getDishType().equals("中餐")) dish.setDishType(DishMenu.DISHTYPE.中餐);
		else dish.setDishType(DishMenu.DISHTYPE.西餐);

		dish.setPrice(request.getPrice());
		
		this.dishMenuRepository.save(dish) ;
		
		map.addAttribute("addDishResult", "SUCCESS") ;
		
		return "forward:/businessInfoPage" ;
	}
	
	@RequestMapping("/customerTrackPage")
	public String gotoCustomerServicePage(HttpServletRequest request, ModelMap map) {
		User user = (User)request.getSession().getAttribute("user") ;
		if (user == null) return "redirect:/" ;
			else {
			
			return "/views/customerTrack/customerTrack" ;
		}
	}
	
	@RequestMapping("/showOrderRecord")
	public String gotoShowOrderRecord(HttpServletRequest request, ModelMap map) {
		User user = (User)request.getSession().getAttribute("user") ;
		if (user == null) return "redirect:/" ;
			else {
			
			int pageNo = 1 ;
			int pageSize = 12 ;
			
			if (request.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt(request.getParameter("pageNo")) ;
			}
			
			Page<Order> page = this.orderRepository.findAll(new PageRequest(pageNo-1, pageSize)) ;
			
			List<Order> allOrders = page.getContent() ;
			List<ShowOrderRecordBean> allOrdersBean = new ArrayList<ShowOrderRecordBean>() ;
			
			for (Order order : allOrders) {
				ShowOrderRecordBean bean = new ShowOrderRecordBean() ;
				bean.setCustomerId(order.getCustomerId());
				bean.setCustomerName(this.customerRepository.findById(order.getCustomerId()).getName());
				bean.setExpenditure(order.getExpenditure());
				bean.setId(order.getId());
				bean.setOrderList(order.getOrderList());
				bean.setOrderTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getOrderTime()));
				
				allOrdersBean.add(bean) ;
			}
			
			
			map.put("allOrders", allOrdersBean) ;
			map.put("pageNo", pageNo) ;
			
			return "/views/customerTrack/orderRecord" ;
		}
	}
	
	@RequestMapping("/showCustomerExpenditure")
	public String gotoShowCustomerExpenditure(HttpServletRequest request, ModelMap map) {
		User user = (User)request.getSession().getAttribute("user") ;
		if (user == null) return "redirect:/" ;
			else {
			
			int pageNo = 1 ;
			int pageSize = 12 ;
			
			if (request.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt(request.getParameter("pageNo")) ;
			}
			
			List<Customer> allCustomers = this.customerRepository.findAll(new PageRequest(pageNo-1, pageSize)).getContent() ;
			List<CustomerExpenditureBean> allBeans = new ArrayList<CustomerExpenditureBean>() ;
			
			for (Customer customer : allCustomers) {
				List<Order> allOrdersOfThisCustomer = this.orderRepository.findByCustomerId(customer.getId()) ;
				BigDecimal expenditure = new BigDecimal(0) ;
				for (Order order : allOrdersOfThisCustomer) {
					expenditure = expenditure.add(order.getExpenditure()) ;
				}
				
				CustomerExpenditureBean bean = new CustomerExpenditureBean() ;
				bean.setCustomerId(customer.getId());
				bean.setCustomerMobile(customer.getMobile());
				bean.setCustomerName(customer.getName());
				bean.setExpenditure(expenditure);
				
				allBeans.add(bean) ;
			}
			
			map.put("allCustomerExpenditure", allBeans) ;
			map.put("pageNo", pageNo) ;
			
			return "/views/customerTrack/customerExpenditure" ;
		}
	}
	
	@RequestMapping("/customerComplaintPage")
	public String gotoCustomerComplaintPage(HttpServletRequest request, ModelMap map) {
		User user = (User)request.getSession().getAttribute("user") ;
		if (user == null) return "redirect:/" ;
			else {
			
			int pageNo = 1 ;
			int pageSize = 12 ;
			
			if (request.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt(request.getParameter("pageNo")) ;
			}
			
			Page<Complaint> page = this.complaintRepository.findAll(new PageRequest(pageNo-1, pageSize)) ;
			
			List<Complaint> allComplaints = page.getContent() ;
			List<ShowCustomerComplaintBean> allBeans = new ArrayList<ShowCustomerComplaintBean>() ;
			
			for (Complaint c : allComplaints) {
				Customer customerOfThisComplaint = this.customerRepository.findById(c.getCustomerId()) ;
				
				ShowCustomerComplaintBean bean = new ShowCustomerComplaintBean() ;
				bean.setContent(c.getContent());
				bean.setCustomerId(c.getCustomerId());
				bean.setCustomerName(customerOfThisComplaint.getName());
				bean.setEmail(customerOfThisComplaint.getEmail());
				bean.setMobile(customerOfThisComplaint.getMobile());
				bean.setSex(customerOfThisComplaint.getSex().toString());
				
				allBeans.add(bean) ;
			}
			
			map.put("allComplaints", allBeans) ;
			map.put("pageNo", pageNo) ;
			
			return "/views/customerComplaint/customerComplaint" ;
		}
	}
	
	@RequestMapping("/userFilePage")
	public String gotoUserFilePage (HttpServletRequest request, ModelMap map) {
		User user = (User)request.getSession().getAttribute("user") ;
		if (user == null) return "redirect:/" ;
		else if (!user.getRole().equals(User.ROLE.admin)) return "redirect:/main" ;
		else {
			
			int pageNo = 1 ;
			int pageSize = 12 ;
			
			if (request.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt(request.getParameter("pageNo")) ;
			}
			
			Page<User> page = this.userRepository.findAll(new PageRequest(pageNo-1, pageSize)) ;
			
			List<User> allUsers = page.getContent() ;
			
			map.put("allUsers", allUsers) ;
			map.put("pageNo", pageNo) ;
			
			return "/systemSettings/userFile" ;
		}
	}
}
