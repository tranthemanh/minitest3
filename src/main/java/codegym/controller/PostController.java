package codegym.controller;

import codegym.model.Category;
import codegym.model.Post;
import codegym.model.PostForm;
import codegym.service.ICategoryService;
import codegym.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private IPostService postService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("category")
    public Iterable<Category> listCategory() {
        return categoryService.findAll();
    }

    @GetMapping
    public ModelAndView listPost(@PageableDefault(value = 5) Pageable pageable) {
        Page<Post> posts = postService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/post/list");
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/post/create");
        modelAndView.addObject("post", new Post());
        return modelAndView;
    }

    @Value("${file-upload}")
    private String upload;

    @PostMapping("/create")
    public String create(PostForm postForm, RedirectAttributes redirectAttributes) {
        MultipartFile multipartFile = postForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(upload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Post post = new Post();
        post.setTitle(postForm.getTitle());
        post.setContent(postForm.getContent());
        post.setDescription(postForm.getDescription());
        post.setImage(fileName);
        post.setCategory(postForm.getCategory());
        postService.save(post);
        redirectAttributes.addFlashAttribute("success", "Post created successfully");
        return "redirect:/posts";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Post> post = postService.findById(id);
        if (post.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/post/update");
            modelAndView.addObject("post", post.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public String update(PostForm postForm,
                         RedirectAttributes redirect) {
        MultipartFile multipartFile = postForm.getImage();
        String fileName = multipartFile.getOriginalFilename();

        Post post = postService.findById(postForm.getId()).get();
        post.setTitle(postForm.getTitle());
        post.setDescription(postForm.getDescription());
        post.setContent(postForm.getContent());
        post.setCategory(postForm.getCategory());
        if (multipartFile.isEmpty()) {
            post.setImage(post.getImage());
        } else {
            post.setImage(fileName);
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), new File(upload + fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        postService.save(post);
        redirect.addFlashAttribute("message", "Update post successfully");
        return "redirect:/posts";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirect) {

        postService.remove(id);
        redirect.addFlashAttribute("message", "Delete post successfully");
        return "redirect:/posts";
    }

    @Controller
    public class HomeController {

        @GetMapping("/")
        public String home() {
            return "redirect:/posts";
        }
    }
}
