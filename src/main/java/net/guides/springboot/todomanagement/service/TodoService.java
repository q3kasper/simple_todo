package net.guides.springboot.todomanagement.service;

import net.guides.springboot.todomanagement.model.Todo;
import net.guides.springboot.todomanagement.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TodoService implements ITodoService {

	@Autowired
	private TodoRepository todoRepository;

	@Override
	public List<Todo> getAllTodos() {
		List<Todo> todoList = new ArrayList<>();
		Iterator<Todo> iterator = todoRepository.findAll().iterator();
		iterator.forEachRemaining(todoList::add);
		return todoList;
	}

	@Override
	public Optional<Todo> getTodoById(long id) {
		return todoRepository.findById(id);
	}

	@Override
	public void updateTodo(Todo todo) {
		todoRepository.save(todo);
	}

	@Override
	public void addTodo(String desc, Date targetDate) {
		todoRepository.save(new Todo(desc, targetDate));
	}

	@Override
	public void deleteTodo(long id) {
		Optional<Todo> todo = todoRepository.findById(id);
		if (todo.isPresent()) {
			todoRepository.delete(todo.get());
		}
	}

	@Override
	public void saveTodo(Todo todo) {
		todoRepository.save(todo);
	}
}