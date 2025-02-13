package guru.springfamework.api.v1.mapper;

import static org.junit.Assert.*;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import org.junit.Test;

public class CategoryMapperTest {

  public static final String NAME = "Joe";
  public static final long ID = 1L;
  CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

  @Test
  public void categoryToCategoryDTO() {

    // given
    Category category = new Category();
    category.setName(NAME);
    category.setId(ID);

    // when
    CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

    // then
    assertEquals(Long.valueOf(1L), categoryDTO.getId());
    assertEquals(NAME, categoryDTO.getName());
  }
}
