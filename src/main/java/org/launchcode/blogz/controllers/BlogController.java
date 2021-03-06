package org.launchcode.blogz.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.launchcode.blogz.models.Post;
import org.launchcode.blogz.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController extends AbstractController {

	@RequestMapping(value = "/")
	public String index(Model model){
		
		// TODO - fetch users and pass to template
		
		List <User> allUsers = userDao.findAll();
		model.addAttribute("users", allUsers);
		return "index";
	}
	
	@RequestMapping(value = "/blog")
	public String blogIndex(Model model) {
		
		// TODO - fetch posts and pass to template
		
		List<Post> allPosts = postDao.findAll();
		model.addAttribute("posts", allPosts);
		
		return "blog";
	}
	
}
