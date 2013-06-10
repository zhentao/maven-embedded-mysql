package com.zhentao.embedded.mysql.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zhentao.embedded.mysql.model.Tag;

public class TagTest {
    @Test
    public void testEquals() {
         Tag tag = new Tag(1L);
         assertTrue(tag.equals(tag));
         assertFalse(tag.equals(null));
         assertFalse(tag.equals(new Object()));

         Tag tag2 = new Tag(1l);
         tag2.setAdSource("source1");
         assertFalse(tag.equals(tag2));

         tag2.setAdSource(tag.getAdSource());
         tag2.setAdvertiserAccountId(1);
         assertFalse(tag.equals(tag2));

         assertFalse(tag.equals(new Tag(2l)));

         tag2.setAdvertiserAccountId(tag.getAdvertiserAccountId());
         tag2.setTagId(2);
         assertFalse(tag.equals(tag2));

         tag2.setTagId(tag.getTagId());
         tag2.setType("type");
         assertFalse(tag.equals(tag2));

         assertTrue(tag.equals(new Tag(tag.getId())));
    }

    @Test
    public void testHashCode() {
        new Tag(1, 2, "lift", "source").hashCode();
        new Tag(1l).hashCode();
    }
}
