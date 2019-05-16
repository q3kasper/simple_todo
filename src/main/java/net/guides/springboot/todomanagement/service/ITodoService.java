package net.guides.springboot.todomanagement.service;

import net.guides.springboot.todomanagement.model.Todo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ITodoService {

	List<Todo> getAllTodos();

	Optional<Todo> getTodoById(long id);

	void updateTodo(Todo todo);

	void addTodo(String desc, Date targetDate);

	void deleteTodo(long id);
	
	void saveTodo(Todo todo);

}