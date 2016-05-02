package kr.kesti.spring.web;

import kr.kesti.spring.web.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDao extends JpaRepository<Post, Integer>{

}
