package org.launchcode.blogz.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.launchcode.blogz.models.Post;
import org.launchcode.blogz.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostController extends AbstractController {

	@RequestMapping(value = "/blog/newpost", method = RequestMethod.GET)
	public String newPostForm() {
		return "newpost";
	}
	
	@RequestMapping(value = "/blog/newpost", method = RequestMethod.POST)
	public String newPost(HttpServletRequest request, Model model) {
		
		// TODO - implement newPost
		//get request parameters
		String title = request.getParameter("title");
		String body = request.getParameter("body");
	
		//validate parameters
		//if valid, create new Post
		//if not valid, send back to form with error message
		if (title == "" || body == "" ){
			//need to addAttribute for the error too model.addAttribute
			String error = "You did not fill out the title or body";
			model.addAttribute("error", error);
			model.addAttribute("value", title);
			model.addAttribute("body", body);
			return "newpost";
		}
		
		
		
	else {
		HttpSession thisSession = request.getSession();
		User author = getUserFromSession(thisSession);
		
		Post newPost = new Post(title, body, author);
		postDao.save(newPost);	
		//need to add attributes to show post on new page
		
		//get info for username, uid from User author line 34
		int uid = newPost.getUid();
		String username = author.getUsername();
		//line 57 is not going to work as written
		//the uid is for the post!
		
		return "redirect:" + username + "/" + uid; // TODO - this redirect should go to the new post's page  		
		
		}
		
		}
	
	
	//handles request like /blog/chris/5
	@RequestMapping(value = "/blog/{username}/{uid}", method = RequestMethod.GET)
	public String singlePost(@PathVariable String username, @PathVariable int uid, Model model) {
		
		// TODO - implement singlePost
		
		//first thing I need to do is get a POST compare to getting user
		//CHarlie go read code to find out how to do this.
		//*GET requests just for displaying!
		//get the post you want
		//model.addattribute pass in the POST (which contains title, body, author)
		//return post template
		//*get the given post
		
		//Post onePost = new Post (title, body, author);
		Post onePost = postDao.findByUid (uid);
		model.addAttribute("posts", onePost);
		//*pass the post into the template
		
		return "post";
	}
	
	@RequestMapping(value = "/blog/{username}", method = RequestMethod.GET)
	public String userPosts(@PathVariable String username, Model model) {
		
		// TODO - implement userPosts
		//get all the users posts
	
		User allPosts = userDao.findByUsername(username);
		List<Post> userPosts = postDao.findByAuthor(allPosts);
		model.addAttribute("posts", userPosts);
				
		return "blog";
	}
	
}
