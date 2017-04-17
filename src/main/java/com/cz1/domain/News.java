package com.cz1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * Created by wkchen on 2017/4/13.
 */
@Entity
@Table(name = "NEWS")
public class News {

    public static final int STATUS_DEF = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 4, max = 50)
    private String title;

    @NotNull
    @Size(min = 8, max = 100)
    private String content;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String url;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    @JsonIgnore
    private int status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "news", fetch = FetchType.EAGER)
    private Set<Image> images;

    public News() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
