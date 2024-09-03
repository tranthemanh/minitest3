package codegym.repository;

import codegym.model.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ICategoryRepository extends CrudRepository<Category, Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "call deleteCategory(:id)")
    void deleteCategory(@Param("id") Long id);
}
