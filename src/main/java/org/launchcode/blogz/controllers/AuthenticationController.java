package org.launchcode.blogz.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.launchcode.blogz.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController extends AbstractController {
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupForm() {
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(HttpServletRequest request, Model model) {
		
		// TODO - implement signup
		//get paramenters from request
		//CE code, no idea if this is correct
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verify = request.getParameter("verify");
		
		//validate parameters (username, password, verify password)
		//static method
		
		//if they validate, create new user, and put them in the session
		if (User.isValidUsername(username) == true){
			
		}
		else {
			String error = "You did not use a legit username";
			model.addAttribute("username_error", error);
			return "signup";
		}
			
		if (User.isValidPassword(password) == true){
				
			}	
			
		else {
			String error = "You did not use a legit password";
			model.addAttribute("password_error", error);
			return "signup";
		}
		if (verify.equals(password)){
						
				}
				else {
					String error = "The passwords don't match, bub.";
					model.addAttribute("verify_error", error);
					return "signup";
				}
		//create new user 	
		User user = new User(username, password);
		//save created user to Dao (Is this needed here?)
		userDao.save(user);
		HttpSession thisSession = request.getSession();
		//made the session, need to "use" it
		setUserInSession(thisSession, user);
		return "redirect:blog/newpost";
										
				}
		
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		
		// TODO - implement login
		
		//get parameters from request
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//get user by their username (CE-from where? the DB, right?)
		User checkUser = userDao.findByUsername(username);
		//username and password for checkUser is pulled in above, now access somehow
		
		//check their password against what's in the DB somehow
		//check password is correct
		if (checkUser.isMatchingPassword(password) == true){
			
		}
		
		else {
			//if (checkUser.isMatchingPassword(checkUser.getPwHash()) == false){	
			String error = "You entered the wrong password, buddy.";
			model.addAttribute("error", error);
			return "login";
		}
		//log them in if so (i.e. setting the user in the session)
		HttpSession thisSession = request.getSession();
		//made the session, need to "use" it
		setUserInSession(thisSession, checkUser);	
		return "redirect:blog/newpost";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
        request.getSession().invalidate();
		return "redirect:/";
	}
}
