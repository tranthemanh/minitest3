package codegym.formatter;

import codegym.model.Category;
import codegym.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

@Component
public class CategoryFormatter implements Formatter<Category> {
    private final ICategoryService categoryService;

    @Autowired
    public CategoryFormatter(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Category parse(String text, Locale locale) {
        return categoryService.findById(Long.parseLong(text))
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + text));
    }


    @Override
    public String print(Category category, Locale locale){
        return "[" + category.getId() + ", " + category.getName() + "]";
    }
}
