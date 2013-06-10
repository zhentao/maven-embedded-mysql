package com.zhentao.embedded.mysql.model;

import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tag")
public class Tag {

    private long id;
    private long tagId;
    private long advertiserAccountId;
    private String type;
    private String adSource;

    public Tag() {
        super();
    }

    public Tag(long id) {
        this.id = id;
    }

    public Tag(long tagId, long advertiserAccountId, String type, String adSource) {
        super();
        this.tagId = tagId;
        this.advertiserAccountId = advertiserAccountId;
        this.type = type;
        this.adSource = adSource;
    }

    @XmlElement(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @XmlElement(name = "adSource")
    public String getAdSource() {
        return adSource;
    }

    public void setAdSource(String adSource) {
        this.adSource = adSource;
    }

    @XmlElement(name = "tagId")
    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    @XmlElement(name = "advertiserAccountId")
    public long getAdvertiserAccountId() {
        return advertiserAccountId;
    }

    public void setAdvertiserAccountId(long advertiserAccountId) {
        this.advertiserAccountId = advertiserAccountId;
    }

    @XmlElement(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((adSource == null) ? 0 : adSource.hashCode());
        result = prime * result + (int) (advertiserAccountId ^ (advertiserAccountId >>> 32));
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + (int) (tagId ^ (tagId >>> 32));
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        Tag other = (Tag) obj;
        if (!Objects.equals(adSource, other.adSource))
            return false;
        if (advertiserAccountId != other.advertiserAccountId)
            return false;
        if (id != other.id)
            return false;
        if (tagId != other.tagId)
            return false;
        if (!Objects.equals(type, other.type))
            return false;
        return true;
    }

}
