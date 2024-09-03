package codegym.service;

import codegym.model.Category;
import codegym.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPostService extends IGenerateService<Post> {

    Iterable<Post> findAllByCategory(Category category);

    Page<Post> findAll(Pageable pageable);
}
