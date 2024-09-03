package codegym.service.impl;

import codegym.model.Category;
import codegym.model.Post;
import codegym.repository.IPostRepository;
import codegym.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService implements IPostService {

    @Autowired
    private IPostRepository iPostRepository;

    @Override
    public Iterable<Post> findAllByCategory(Category category) {
        return iPostRepository.findAllByCategory(category);
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return iPostRepository.findAll(pageable);
    }

    @Override
    public Iterable<Post> findAll() {
        return iPostRepository.findAll();
    }

    @Override
    public void save(Post post) {
        iPostRepository.save(post);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return iPostRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iPostRepository.deleteById(id);
    }
}
