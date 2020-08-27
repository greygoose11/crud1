package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import web.model.Customer;
import web.service.CustomerService;

import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @RequestMapping(value = "/")
    public String home(){
        return "redirect:/customers";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ModelAndView listCustomers() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index1");
        mav.addObject("listCustomer", customerService.listAll());
        return mav;
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }
    @RequestMapping(value = "/delete{id}")
    public String removeCustomer(@ModelAttribute("id") long id) {
        customerService.delete(id);
        return "redirect:/customers";
    }
    @RequestMapping("/edit{id}")
    public ModelAndView editCustomerForm(@RequestParam long id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("edit_customer");
        Customer customer = customerService.get(id);
        mav.addObject("customer", customer);

        return mav;
    }
    @RequestMapping("/edit")
    public String deleteCustomerForm(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }
//    @RequestMapping("/search")
//    public ModelAndView search(@RequestParam String keyword) {
//        List<Customer> result = customerService.search(keyword);
//        ModelAndView mav = new ModelAndView("search");
//        mav.addObject("result", result);
//
//        return mav;
//    }


}
