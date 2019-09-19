
package tacos.web;
import javax.validation.Valid;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import tacos.Order;
import tacos.User;
import tacos.data.OrderRepository;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@ConfigurationProperties(prefix="taco.orders") /*This means that when setting the pageSize property, you need to use a 
											     configuration property named taco.orders.pageSize
 											   
 											     For example, you could set this property in application.yml like this:
													taco:
														orders:
															pageSize: 10
 											    */
public class OrderController {
  
  private int pageSize = 20;
  
  public void setPageSize(int pageSize) {
	  this.pageSize = pageSize;
  }
	
  private OrderRepository orderRepo;
  
  private OrderProps props;

  public OrderController(OrderRepository orderRepo, OrderProps props) {
    this.orderRepo = orderRepo;
    this.props = props;
  }
  
  @GetMapping("/current")
  public String orderForm(@AuthenticationPrincipal User user, @ModelAttribute Order order) {
    if (order.getDeliveryName() == null) {
      order.setDeliveryName(user.getFullname());
    }
    if (order.getDeliveryStreet() == null) {
      order.setDeliveryStreet(user.getStreet());
    }
    if (order.getDeliveryCity() == null) {
      order.setDeliveryCity(user.getCity());
    }
    if (order.getDeliveryState() == null) {
      order.setDeliveryState(user.getState());
    }
    if (order.getDeliveryZip() == null) {
      order.setDeliveryZip(user.getZip());
    }
    
    return "orderForm";
  }
  
  @GetMapping
  public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
	  Pageable pageable = PageRequest.of(0, props.getPageSize());
	  model.addAttribute("orders", orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
	  return "orderList";
  }

  @PostMapping
  public String processOrder(@Valid Order order, Errors errors, 
      SessionStatus sessionStatus, 
      @AuthenticationPrincipal User user) {
    
    if (errors.hasErrors()) {
      return "orderForm";
    }
    
    order.setUser(user);
    
    orderRepo.save(order);
    sessionStatus.setComplete();
    
    return "redirect:/";
  }
  // end::processOrderWithAuthenticationPrincipal[]

}