package codegym.repository;

import codegym.model.Category;
import codegym.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface IPostRepository extends CrudRepository<Post, Long> {
    Iterable<Post> findAllByCategory(Category category);

    Page<Post> findAll(Pageable pageable);

}
