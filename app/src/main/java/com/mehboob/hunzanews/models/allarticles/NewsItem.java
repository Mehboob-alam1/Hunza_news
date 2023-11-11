package com.mehboob.hunzanews.models.allarticles;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "allarticle")
public class NewsItem {
    @SerializedName("ID")
    @PrimaryKey(autoGenerate = false)
    private int id;

    @SerializedName("post_author")
    @ColumnInfo(name = "post_author")
    private String postAuthor;

    @SerializedName("post_date")
    @ColumnInfo(name = "post_date")

    private String postDate;

    @SerializedName("post_content")
    @ColumnInfo(name = "post_content")

    private String postContent;

    @SerializedName("post_title")
    @ColumnInfo(name = "post_title")

    private String postTitle;

    @SerializedName("post_excerpt")
    @ColumnInfo(name = "post_excerpt")

    private String postExcerpt;

    @SerializedName("post_status")
    @ColumnInfo(name = "post_status")

    private String postStatus;

    @SerializedName("guid")
    @ColumnInfo(name = "guid")

    private String guid;

    @SerializedName("post_type")
    @ColumnInfo(name = "post_type")

    private String postType;

    @TypeConverters(CommentConverter.class)
    @SerializedName("comment_count")
    private CommentCount commentCount;


    @SerializedName("thumbnail_url")
    @ColumnInfo(name = "thumbnail_url")

    private String thumbnailUrl;

    // Add getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(String postAuthor) {
        this.postAuthor = postAuthor;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostExcerpt() {
        return postExcerpt;
    }

    public void setPostExcerpt(String postExcerpt) {
        this.postExcerpt = postExcerpt;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public CommentCount getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(CommentCount commentCount) {
        this.commentCount = commentCount;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }


    @Override
    public String toString() {
        return "NewsItem{" +
                "id=" + id +
                ", postAuthor='" + postAuthor + '\'' +
                ", postDate='" + postDate + '\'' +
                ", postContent='" + postContent + '\'' +
                ", postTitle='" + postTitle + '\'' +
                ", postExcerpt='" + postExcerpt + '\'' +
                ", postStatus='" + postStatus + '\'' +
                ", guid='" + guid + '\'' +
                ", postType='" + postType + '\'' +
                ", commentCount=" + commentCount +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
