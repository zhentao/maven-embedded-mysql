package com.zhentao.embedded.mysql.dao;

import java.util.List;

import com.zhentao.embedded.mysql.model.TagScan;

public interface TagScanDao {
    TagScan getNextAvailable();

    List<TagScan> getAvailable(int limit);

    int deleteTagScan(TagScan tagScan);

    int deleteTagScans(List<TagScan> tagScans);

    int findNumberOfTagScans();

    int insert(TagScan tagScan);
}
