package codegym.service;

import codegym.model.Category;

public interface ICategoryService extends IGenerateService<Category> {
    void deleteCategory(Long id);
}
