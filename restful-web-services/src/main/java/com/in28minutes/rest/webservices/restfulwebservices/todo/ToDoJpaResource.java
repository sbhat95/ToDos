package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ToDoJpaResource {
	
	@Autowired
	TodoJpaRepository todoRepo;
	
	@GetMapping("/jpa/user/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username){
		return todoRepo.findByUsername(username);
	}
	
	@GetMapping("/jpa/user/{username}/todos/{id}")
	public Todo getATodo(@PathVariable String username, @PathVariable long id){
		return todoRepo.findById(id).get();
	}
	
	@PostMapping("/jpa/user/{username}/todos")
	public ResponseEntity<Void> createATodo(@PathVariable String username, @RequestBody Todo todo){
		todo.setUsername(username);
		Todo createdTodo = todoRepo.save(todo);
		
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdTodo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/jpa/user/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo){
		todo.setUsername(username);
		Todo updatedTodo = todoRepo.save(todo);
		
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/jpa/user/{username}/todos/{id}")
	public ResponseEntity<Void> deleteATodo(@PathVariable String username, @PathVariable long id) {
		todoRepo.deleteById(id);
			return ResponseEntity.noContent().build();
	}
	 

}
