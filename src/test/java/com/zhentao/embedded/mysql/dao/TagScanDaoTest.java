package com.zhentao.embedded.mysql.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhentao.embedded.mysql.config.EmbeddedDataSourceConfig;
import com.zhentao.embedded.mysql.dao.TagScanDaoJdbcImpl;
import com.zhentao.embedded.mysql.model.Tag;
import com.zhentao.embedded.mysql.model.TagScan;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=EmbeddedDataSourceConfig.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class TagScanDaoTest {
    @Autowired
    private DataSource database;
    private TagScanDaoJdbcImpl tagScanDao;

    @Before
    public void setUp() {
        tagScanDao = new TagScanDaoJdbcImpl();
        tagScanDao.setDataSource(database);
    }

    @Test
    public void testGetNextAvailabeTagScan() {
        TagScan scan = tagScanDao.getNextAvailable();
        assertEquals(1, scan.getId());
    }

    @Test
    public void testGetNextAvailabeTagScanReturnNull() {
        List<TagScan> list = tagScanDao.getAvailable(100);
        tagScanDao.deleteTagScans(list);

        TagScan scan = tagScanDao.getNextAvailable();
        assertNull(scan);
    }

    @Test
    public void testGetAvailabe() {
        int limit = 5;
        List<TagScan> list = tagScanDao.getAvailable(limit);
        assertEquals(limit, list.size());
    }

    @Test
    public void testDeleteTagScans() {
        int limit = 5;
        List<TagScan> list = tagScanDao.getAvailable(limit);
        int deleted = tagScanDao.deleteTagScans(list);
        assertEquals(limit, deleted);

        TagScan scan = tagScanDao.getNextAvailable();
        assertEquals(5, scan.getId());
    }

    @Test
    public void testDeleteTagScan() {
        int initialTotal = tagScanDao.findNumberOfTagScans();

        TagScan scan = tagScanDao.getNextAvailable();
        tagScanDao.deleteTagScan(scan);

        int total = tagScanDao.findNumberOfTagScans();
        assertEquals(initialTotal - 1, total);
    }

    @Test
    public void testInsert() {
        int countBeforeInsert = tagScanDao.findNumberOfTagScans();
        TagScan tagScan = new TagScan();
        tagScan.setTag(new Tag(1L));
        tagScan.setPriority(1);
        tagScan.setRandom(0.0);
        tagScanDao.insert(tagScan);
        int count = tagScanDao.findNumberOfTagScans();
        assertThat(count, is(countBeforeInsert + 1));
    }
}
