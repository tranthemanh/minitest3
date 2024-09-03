package codegym.controller;

import codegym.model.Category;
import codegym.model.Post;
import codegym.service.ICategoryService;
import codegym.service.IPostService;
import codegym.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/categorys")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private IPostService postService;

    @GetMapping("")
    public ModelAndView listCategory() {
        ModelAndView modelAndView = new ModelAndView("category/list");
        Iterable<Category> categories = categoryService.findAll();
        modelAndView.addObject("categorys", categories);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("category") Category category,
                         RedirectAttributes redirectAttributes) {
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("message", "Create new category successfully");
        return "redirect:/categorys";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/category/update");
            modelAndView.addObject("category", category.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("category") Category category,
                         RedirectAttributes redirect) {
        categoryService.save(category);
        redirect.addFlashAttribute("message", "Update category successfully");
        return "redirect:/categorys";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categorys";
    }

//    @GetMapping("/view-category/{id}")
//    public ModelAndView viewProvince(@PathVariable("id") Long id){
//        Optional<Category> categoryOptional = categoryService.findById(id);
//        if(!categoryOptional.isPresent()){
//            return new ModelAndView("/error_404");
//        }
//
//        Iterable<Post> posts = postService.findAllByCategory(categoryOptional.get());
//
//        ModelAndView modelAndView = new ModelAndView("/post/list");
//        modelAndView.addObject("posts", posts);
//        return modelAndView;
//    }
}
