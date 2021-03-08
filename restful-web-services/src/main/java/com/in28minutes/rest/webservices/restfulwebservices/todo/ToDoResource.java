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
public class ToDoResource {
	
	@Autowired
	ToDoHardcodedService tdhs;
	
	@GetMapping("/user/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username){
		return tdhs.findAll();
	}
	
	@GetMapping("/user/{username}/todos/{id}")
	public Todo getATodo(@PathVariable String username, @PathVariable long id){
		return tdhs.findById(id);
	}
	
	@PostMapping("/user/{username}/todos")
	public ResponseEntity<Void> getATodo(@PathVariable String username, @RequestBody Todo todo){
		Todo createdTodo = tdhs.save(todo);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdTodo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/user/{username}/todos/{id}")
	public ResponseEntity<Todo> getATodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo){
		Todo updatedTodo = tdhs.save(todo);
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{username}/todos/{id}")
	public ResponseEntity<Void> deleteATodo(@PathVariable String username, @PathVariable long id){
		Todo todo = tdhs.deleteTodo(id);
		if(todo!=null) {
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}
