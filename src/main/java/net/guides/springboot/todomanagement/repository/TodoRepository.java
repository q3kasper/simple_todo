package net.guides.springboot.todomanagement.repository;

import net.guides.springboot.todomanagement.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
}
