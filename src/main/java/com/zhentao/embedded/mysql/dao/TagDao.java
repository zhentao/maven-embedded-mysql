package com.zhentao.embedded.mysql.dao;

import com.zhentao.embedded.mysql.model.Tag;

public interface TagDao {

    Tag insert(Tag tag);

    void update(Tag tag);

    Tag findById(long id);

    Tag findByKey(long tagId, long advertiserAccountId, String type);
}
