package com.zhentao.embedded.mysql.model;

import java.util.Objects;

public class TagScan {
    private long id;
    private Tag tag;
    private int priority;
    private double random;

    public TagScan() {
        super();
    }

    public TagScan(long id, Tag tag) {
        super();
        this.id = id;
        this.tag = tag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public double getRandom() {
        return random;
    }

    public void setRandom(double random) {
        this.random = random;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + priority;
        long temp = Double.doubleToLongBits(random);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((tag == null) ? 0 : tag.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TagScan other = (TagScan) obj;
        if (id != other.id)
            return false;
        if (priority != other.priority)
            return false;
        if (Double.doubleToLongBits(random) != Double.doubleToLongBits(other.random))
            return false;
        if (!Objects.equals(tag, other.tag)) {
            return false;
        }
        return true;
    }
}
