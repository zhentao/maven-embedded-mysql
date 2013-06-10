package com.zhentao.embedded.mysql.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.zhentao.embedded.mysql.model.Tag;
import com.zhentao.embedded.mysql.model.TagScan;

public class TagScanDaoJdbcImpl extends JdbcDaoSupport implements TagScanDao {
    private static final String FIND_NEXT_TAGS_SQL = "select s.id, s.tag_seq_id, t.ad_source from tag_scan s inner join tag t on s.tag_seq_id = t.seq_id order by priority, random limit ?";
    private static final String DELETE_TAG_SCAN_SQL = "delete from tag_scan where id = ?";
    private static final String DELETE_IN_SQL = "delete from tag_scan where id in ( %s )";
    private static final String COUNT_SQL = "select count(*) from tag_scan";
    private static final String INSERT_TAG_SCAN_SQL = "insert into tag_scan (tag_seq_id, priority, random) values (?, ?, ?)";

    @Override
    public TagScan getNextAvailable() {
       List<TagScan> list = getAvailable(1);
       return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<TagScan> getAvailable(int limit) {
        return getJdbcTemplate().query(FIND_NEXT_TAGS_SQL, new Object[] {limit}, ROW_MAPPER);
    }

    @Override
    public int deleteTagScan(TagScan tagScan) {
        return getJdbcTemplate().update(DELETE_TAG_SCAN_SQL, tagScan.getId());
    }

    @Override
    public int deleteTagScans(List<TagScan> tagScans) {
        StringBuilder ids = new StringBuilder(tagScans.size() * 5);
        for (TagScan scan : tagScans) {
            ids.append(scan.getId());
            ids.append(",");
        }
        ids.deleteCharAt(ids.lastIndexOf(","));
        String delete = DELETE_IN_SQL.replace("%s", ids.toString());
        return getJdbcTemplate().update(delete);
    }

    @Override
    public int findNumberOfTagScans() {
        return getJdbcTemplate().queryForObject(COUNT_SQL, Integer.class);
    }

    @Override
    public int insert(TagScan tagScan) {
        return getJdbcTemplate().update(INSERT_TAG_SCAN_SQL, tagScan.getTag().getId(), tagScan.getPriority(), tagScan.getRandom());
    }

    private static final RowMapper<TagScan> ROW_MAPPER = new RowMapper<TagScan>() {
        @Override
        public TagScan mapRow(ResultSet rs, int rowNum) throws SQLException {
            Tag tag = new Tag(rs.getLong("tag_seq_id"));
            tag.setAdSource(rs.getString("ad_source"));
            TagScan scan = new TagScan(rs.getLong("id"), tag);
            return scan;
        }
    };
}
