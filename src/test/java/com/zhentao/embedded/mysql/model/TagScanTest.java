package com.zhentao.embedded.mysql.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.zhentao.embedded.mysql.model.Tag;
import com.zhentao.embedded.mysql.model.TagScan;

public class TagScanTest {
    @Test
    public void testHashCode() {
        new TagScan().hashCode();
        new TagScan(1L, new Tag()).hashCode();
    }

    @Test
    public void testEquals() {
        TagScan tagScan = new TagScan();
        assertFalse(tagScan.equals(null));
        assertFalse(tagScan.equals(new Object()));
        assertFalse(tagScan.equals(new TagScan(1L, null)));
        TagScan other = new TagScan();
        other.setPriority(1);
        assertFalse(tagScan.equals(other));
        other = new TagScan();
        other.setRandom(1);
        assertFalse(tagScan.equals(other));
        other = new TagScan(0L, new Tag(1L));
        assertFalse(tagScan.equals(other));

        assertEquals(tagScan, new TagScan());
    }


}
