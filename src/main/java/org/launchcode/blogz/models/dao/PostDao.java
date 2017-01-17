package org.launchcode.blogz.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.blogz.models.Post;
import org.launchcode.blogz.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface PostDao extends CrudRepository<Post, Integer> {
    
   List<Post> findByAuthor(int authorId);
   List<Post> findByAuthor(User author);
    
    Post findByAuthor(String author);
    
    //string title body
    Post findByUid (int uid);
    
    List<Post> findAll();
    
    Post findByTitle(String title);
    
    // TODO - add method signatures as needed
    
    //such as "Find all of the posts!"
    
    //find posts by title, if you wanted
	
}
