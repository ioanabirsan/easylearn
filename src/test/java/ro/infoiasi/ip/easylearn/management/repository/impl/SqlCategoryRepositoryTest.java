package ro.infoiasi.ip.easylearn.management.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ro.infoiasi.ip.easylearn.management.model.Category;
import ro.infoiasi.ip.easylearn.management.repository.api.CategoryRepository;

import javax.transaction.Transactional;

import java.sql.Types;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)

public class SqlCategoryRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findById() {
        Category myCategory = new Category(10, "mediu");
        String query = "INSERT INTO categorii(categoryID, nume) values(?,?);";
        Object[] params = new Object[]{myCategory.getCategoryId(),myCategory.getNume()};
        int[] types = new int[]{Types.INTEGER, Types.VARCHAR};
        int row = jdbcTemplate.update(query, params, types);


        Category fingCategory = categoryRepository.findById(myCategory.getCategoryId());

        Assert.assertEquals("Fields don't match",
                myCategory.getCategoryId() + myCategory.getNume(),
                fingCategory.getCategoryId() + fingCategory.getNume());

    }

}