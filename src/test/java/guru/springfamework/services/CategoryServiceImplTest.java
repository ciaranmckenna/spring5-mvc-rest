package guru.springfamework.services;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CategoryServiceImplTest {

  public static final Long ID = 2L;
  public static final String NAME = "Jimmy";

  @Mock CategoryRepository categoryRepository;

  @Mock CategoryMapper categoryMapper;

  @InjectMocks CategoryServiceImpl categoryService;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
  }

  @Test
  public void getAllCategories() {

    // given
    List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

    when(categoryRepository.findAll()).thenReturn(categories);

    // when
    List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

    // then
    assertEquals(3, categoryDTOS.size());
  }

  @Test
  public void getCategoryByName() {

    // given
    Category category = new Category();
    category.setId(ID);
    category.setName(NAME);

    when(categoryRepository.findByName(anyString())).thenReturn(category);

    // when
    CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

    // then
    assertEquals(ID, categoryDTO.getId());
    assertEquals(NAME, categoryDTO.getName());
  }
}
