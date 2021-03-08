package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ToDoHardcodedService {

	private static Long idCounter =0L;
	private static ArrayList<Todo> todoList = new ArrayList<Todo>();
	
	static {
		todoList.add(new Todo((++idCounter), "in28minutes", "Learn Spring",
				new Date(), false));
		todoList.add(new Todo((++idCounter), "in28minutes", "Learn Java",
				new Date(), false));
		todoList.add(new Todo((++idCounter), "in28minutes", "Learn Maths",
				new Date(), false));
	}
	
	public Todo save(Todo todo) {
		if(todo.getId()==-1 || todo.getId()==-0) {
			todo.setId(++idCounter);
			todoList.add(todo);
		}
		else {
			deleteTodo(todo.getId());
			todoList.add(todo);
		}
		return todo;
	}
	
	public List<Todo> findAll(){
		return todoList;
	}
	
	public Todo deleteTodo(long id) {
		
		Todo todo = findById(id);
		
		if(todo==null) return null;
		
		if(todoList.remove(todo)) {
			return todo;
		}
		return null;
	}

	public Todo findById(long id) {

		for(Todo todo: todoList) {
			if(todo.getId()==id)
				return todo;
		}
		return null;
	}
	
}
