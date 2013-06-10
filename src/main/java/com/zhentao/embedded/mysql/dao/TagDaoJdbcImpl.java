package com.zhentao.embedded.mysql.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.zhentao.embedded.mysql.model.Tag;

public class TagDaoJdbcImpl extends JdbcDaoSupport implements TagDao {
    private static final String SELECT_TAG_BY_ID = "SELECT seq_id, tag_id, advertiser_account_id, tag_type, ad_source FROM tag WHERE seq_id = ?";
    private static final String SELECT_TAG_BY_KEY = "SELECT seq_id, tag_id, advertiser_account_id, tag_type, ad_source FROM tag WHERE tag_id = ? AND advertiser_account_id = ? AND tag_type = ?";
    private static final String UPDATE_TAG = "UPDATE tag SET ad_source = ? WHERE tag_id = ? AND advertiser_account_id = ? AND tag_type = ?";

    private SimpleJdbcInsert jdbcInsert;

    @Override
    public Tag findById(long id) {
        return getJdbcTemplate().queryForObject(SELECT_TAG_BY_ID, ROW_MAPPER, id);
    }

    @Override
    public Tag findByKey(long tagId, long advertiserAccountId, String type) {
        List<Tag> tags = getJdbcTemplate().query(SELECT_TAG_BY_KEY, ROW_MAPPER, tagId, advertiserAccountId, type);
        if (tags.isEmpty()) {
            return null;
        } else {
            return tags.get(0);
        }
    }

    @Override
    public Tag insert(Tag tag) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("tag_id", tag.getTagId());
        map.put("advertiser_account_id", tag.getAdvertiserAccountId());
        map.put("tag_type", tag.getType());
        map.put("ad_source", tag.getAdSource());
        long id = jdbcInsert.executeAndReturnKey(map).longValue();
        tag.setId(id);
        return tag;
    }

    @Override
    public void update(Tag tag) {
        getJdbcTemplate().update(UPDATE_TAG, tag.getAdSource(), tag.getTagId(), tag.getAdvertiserAccountId(),
                                        tag.getType());
    }

    private static RowMapper<Tag> ROW_MAPPER = new RowMapper<Tag>() {
        @Override
        public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
            Tag tag = new Tag(rs.getLong("tag_id"), rs.getLong("advertiser_account_id"), rs.getString("tag_type"),
                                            rs.getString("ad_source"));
            tag.setId(rs.getLong("seq_id"));
            return tag;
        }
    };

    public void setJdbcInsert(SimpleJdbcInsert jdbcInsert) {
        this.jdbcInsert = jdbcInsert;
    }
}
