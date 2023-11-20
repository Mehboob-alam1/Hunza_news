package com.mehboob.hunzanews.models.allarticles;

public class CategoryItem {





        private int id;
        private String postAuthor;
        private String postDate;
        private String postContent;
        private String postTitle;
        private String postExcerpt;
        private String postStatus;
        private String thumbnailUrl;

        private String guid;

    private String postType;
        // Add constructors, getters, and setters here

    public String getGuid() {
        return guid;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

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

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }

        // Example constructor:

    public CategoryItem(int id, String postAuthor, String postDate, String postContent, String postTitle, String postExcerpt, String postStatus, String thumbnailUrl, String guid, String postType) {
        this.id = id;
        this.postAuthor = postAuthor;
        this.postDate = postDate;
        this.postContent = postContent;
        this.postTitle = postTitle;
        this.postExcerpt = postExcerpt;
        this.postStatus = postStatus;
        this.thumbnailUrl = thumbnailUrl;
        this.guid = guid;
        this.postType = postType;
    }

    @Override
    public String toString() {
        return "CategoryItem{" +
                "id=" + id +
                ", postAuthor='" + postAuthor + '\'' +
                ", postDate='" + postDate + '\'' +
                ", postContent='" + postContent + '\'' +
                ", postTitle='" + postTitle + '\'' +
                ", postExcerpt='" + postExcerpt + '\'' +
                ", postStatus='" + postStatus + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", guid='" + guid + '\'' +
                ", postType='" + postType + '\'' +
                '}';
    }
}


