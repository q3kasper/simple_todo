package net.guides.springboot.todomanagement.controller;

import net.guides.springboot.todomanagement.model.Todo;
import net.guides.springboot.todomanagement.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TodoController {

	private final String REDIRECT_LIST_TODOS = "redirect:/";
	private final String UPDATE_TODO = "/update-todo";
	private final String ADD_TODO = "/add-todo";
	private final String TODO = "todo";
	private final String DATE_FORMAT = "dd/MM/yyyy";
	private final String TODOS = "todos";
	private final String LIST_TODOS = "list-todos";
	private final String DELETE_TODO = "/delete-todo";

	@Autowired
	private ITodoService todoService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		model.put(TODOS, todoService.getAllTodos());
		return LIST_TODOS;
	}

	@RequestMapping(value = ADD_TODO, method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		model.addAttribute(TODO, new Todo());

		return TODO;
	}

	@RequestMapping(value = DELETE_TODO, method = RequestMethod.GET)
	public String deleteTodo(@RequestParam long id) {
		todoService.deleteTodo(id);

		return REDIRECT_LIST_TODOS;
	}

	@RequestMapping(value = UPDATE_TODO, method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam long id, ModelMap model) {
		Todo todo = todoService.getTodoById(id).get();
		model.put(TODO, todo);

		return TODO;
	}

	@RequestMapping(value = UPDATE_TODO, method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return TODO;
		}
		todoService.updateTodo(todo);

		return REDIRECT_LIST_TODOS;
	}

	@RequestMapping(value = ADD_TODO, method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return TODO;
		}
		todoService.saveTodo(todo);

		return REDIRECT_LIST_TODOS;
	}
}