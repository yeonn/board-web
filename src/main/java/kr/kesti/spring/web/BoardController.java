package kr.kesti.spring.web;

import java.util.Date;
import java.util.List;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@EnableAutoConfiguration 
@RequestMapping("/bb") 
public class BoardController {

	@Autowired
	private PostDao postDao;
	
    @RequestMapping("/list") 
//    @ResponseBody
    public String list(Model model) {
    	List<Post> postList = postDao.findAll();
		model.addAttribute("postList", postList);
		return "list";
	}
    
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String form(Post post) {
    	return "write";
    }
    
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(Post post) {
		post.setRegDate(new Date());
		postDao.save(post).getId();
		return "redirect:/bb/list";
	}
	
	@RequestMapping("/{id}")
	public String view(Model model, @PathVariable int id) {
		Post post = postDao.findOne(id);
		model.addAttribute("post", post);
		return "content";
	}
    
    
    
    
//    String home() {
//        return "Hello Worlds!";
//    }

    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        
        return registrationBean;
    } 
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(BoardController.class, args);
    }
}