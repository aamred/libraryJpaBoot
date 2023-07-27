package ua.com.boot.libraryboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Anton Muzhytskyi
 */

@Controller
@RequestMapping("/")
public class EnterController {
	
		@Autowired
	    public EnterController() {}

		@GetMapping() 
		public String index (Model model) {
			return "index";
		}
}
