package com.dailymeows.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dailymeows.models.LoginUser;
import com.dailymeows.models.Meow;
import com.dailymeows.models.User;
import com.dailymeows.services.MeowService;
import com.dailymeows.services.UserService;

@Controller
public class MainController {
	@Autowired
	UserService userService;
	@Autowired
	MeowService meowService;
	
	private static String imgageFolder = "src/main/webapp/images/";
	
	@RequestMapping("/")
	public String reroute() {
		return "redirect:/dailymeows/signup";
	}
	
	@RequestMapping("/dailymeows/signup")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		return "index.jsp";
	}
	
	@PostMapping("/dailymeows/signup")
	public String registerUser(
			@Valid @ModelAttribute("newUser") User newUser, 
			BindingResult result, 
			Model model, 
			HttpSession session
			) {
		User user = userService.register(newUser, result);
		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		} else {
			session.setAttribute("userId", user.getId());
			return "redirect:/dailymeows/home";
		}
	}
	
	@RequestMapping("/dailymeows/login")
	public String login(Model model) {
		model.addAttribute("newLogin", new LoginUser());
		return "login.jsp";
	}

	@PostMapping("/dailymeows/login")
	public String loginUser(
			@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
			BindingResult result, 
			Model model, 
			HttpSession session
			) {
		User user = userService.login(newLogin, result);
		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "login.jsp";
		} else {
			session.setAttribute("userId", user.getId());
			return "redirect:/dailymeows/home";
		}
	}
	
	@RequestMapping("/dailymeows/home")
	public String home(
			Model model, 
			HttpSession session
			) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		} else {
			model.addAttribute("user", userService.getById((Long)session.getAttribute("userId")));
			model.addAttribute("meows", meowService.getAll());
			return "home.jsp";
		}
	}
	
	@RequestMapping("/dailymeows/add")
	public String addMeow(
			@ModelAttribute("meow") Meow meow, 
			Model model, 
			HttpSession session
			) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		} else {
			Long userId = (Long) session.getAttribute("userId");
			model.addAttribute("user", userService.findById(userId));
			return "addMeow.jsp";
		}
	}
	
	@PostMapping("/dailymeows/add")
	public String postAddMeow(
			@RequestParam("headline") String headline,
			@RequestParam("location") String location,
			@RequestParam("image") MultipartFile file,
			@RequestParam("details") String details,
			HttpSession session,
			RedirectAttributes redirectAttributes			
			) {
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Image file required.");
			return "redirect:/dailymeows/add";
		}
		try {
			User user = userService.getById((Long)session.getAttribute("userId"));
				
			byte[] bytes = file.getBytes();
			Path path = Paths.get(imgageFolder + file.getOriginalFilename());
			Files.write(path, bytes);
			
			String imageURL = "/images/" + file.getOriginalFilename();
			
			meowService.createMeow(headline, location, imageURL, details, user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/dailymeows/home";
		
	}

	@RequestMapping("/dailymeows/update/{id}")
	public String updateMeow(
			@PathVariable("id") Long id, 
			Model model, 
			HttpSession session
			) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		} else {
			Long userId = (Long) session.getAttribute("userId");
			model.addAttribute("user", userService.findById(userId));
			Meow meow = meowService.findMeow(id);
			model.addAttribute("meow", meow);
			return "updateMeow.jsp";
		}
	}
	
	@PutMapping("/dailymeows/update/{id}")
	public String postUpdateMeow(
			@Valid @ModelAttribute("meow") Meow meow, 
			BindingResult result, 
			Model model
			) {
		if (result.hasErrors()) {
			return "updateMeow.jsp";
		} else {
			meow = meowService.updateMeow(meow);
			return "redirect:/dailymeows/home";
		}
	}
	
	@RequestMapping("/dailymeows/{id}")
	public String showMeow(
			@PathVariable("id") Long id, 
			Model model, 
			HttpSession session
			) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		} else {
			Meow meow = meowService.findMeow(id);
			model.addAttribute("meow", meow);
			return "showMeow.jsp";			
		}
	}

	@DeleteMapping("/dailymeows/delete/{id}")
	public String deleteMeow(@PathVariable("id") Long id) {
		meowService.deleteMeow(id);
		return "redirect:/dailymeows/home";
	}
	
	@RequestMapping("/logout")
	public String logoutUser(HttpSession session) {
		session.setAttribute("userId", null);
		return "redirect:/";
	}
	
	@RequestMapping("/dailymeows/account/{id}")
	public String account(
			@PathVariable("id") Long id,
			@ModelAttribute("user") User user,
			Model model, 
			HttpSession session
			) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		} else {
			Long userId = (Long) session.getAttribute("userId");
			model.addAttribute("user", userService.findById(userId));
			return "account.jsp";
		}
	}
	
	
}