package com.mehboob.hunzanews.models.allarticles;

import com.google.gson.annotations.SerializedName;

public class CommentCount {
    @SerializedName("approved")
    private int approved;

    @SerializedName("awaiting_moderation")
    private int awaitingModeration;

    @SerializedName("spam")
    private int spam;

    @SerializedName("trash")
    private int trash;

    @SerializedName("post-trashed")
    private int postTrashed;

    @SerializedName("all")
    private int all;

    @SerializedName("total_comments")
    private int totalComments;

    // Add getters and setters


    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public int getAwaitingModeration() {
        return awaitingModeration;
    }

    public void setAwaitingModeration(int awaitingModeration) {
        this.awaitingModeration = awaitingModeration;
    }

    public int getSpam() {
        return spam;
    }

    public void setSpam(int spam) {
        this.spam = spam;
    }

    public int getTrash() {
        return trash;
    }

    public void setTrash(int trash) {
        this.trash = trash;
    }

    public int getPostTrashed() {
        return postTrashed;
    }

    public void setPostTrashed(int postTrashed) {
        this.postTrashed = postTrashed;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public int getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(int totalComments) {
        this.totalComments = totalComments;
    }
}
