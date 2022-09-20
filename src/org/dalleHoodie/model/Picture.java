package org.dalleHoodie.model;

public class Picture {
    private int pictureId;
    private int pictureSetId;
    private String link;

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public int getPictureSetId() {
        return pictureSetId;
    }

    public void setPictureSetId(int pictureSetId) {
        this.pictureSetId = pictureSetId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
