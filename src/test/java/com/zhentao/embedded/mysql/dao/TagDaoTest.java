package com.zhentao.embedded.mysql.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhentao.embedded.mysql.config.EmbeddedDataSourceConfig;
import com.zhentao.embedded.mysql.dao.TagDaoJdbcImpl;
import com.zhentao.embedded.mysql.model.Tag;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=EmbeddedDataSourceConfig.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class TagDaoTest {
    @Autowired
    private DataSource dataSource;
    private TagDaoJdbcImpl tagDao;

    @Before
    public void setUp() {
        tagDao = new TagDaoJdbcImpl();
        tagDao.setDataSource(dataSource);
        tagDao.setJdbcInsert(new SimpleJdbcInsert(dataSource).withTableName("tag").usingGeneratedKeyColumns("id"));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testFindByIdNotExists() {
        tagDao.findById(10000);
    }
    @Test
    public void testInsert() {
        Tag tag = new Tag(3, 1, "lift", "source");
        tag = tagDao.insert(tag);

        Tag actual = tagDao.findById(tag.getId());
        assertThat(actual, is(tag));
    }

    @Test
    public void testUpdate() {
        String adSource = "2";
        Tag tag = new Tag(2, 2, "lift", adSource);
        tagDao.update(tag);

        Tag actual = tagDao.findByKey(tag.getTagId(), tag.getAdvertiserAccountId(), tag.getType());

        assertThat(actual.getAdSource(), is(adSource));
    }

    @Test
    public void testFindByKeyReturnNull() {
        assertNull(tagDao.findByKey(100l, 100l, "not exist"));
    }
}
