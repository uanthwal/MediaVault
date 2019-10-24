package com.roger.mediavault;

public class AlbumListTO {
    private String albumName;
    private String imageCount;

    public AlbumListTO(String albumName, String imageCount) {
        this.albumName = albumName;
        this.imageCount = imageCount;
    }

    public String getDescription() {
        return albumName;
    }

    public void setDescription(String albumName) {
        this.albumName = albumName;
    }

    public String getImgId() {
        return imageCount;
    }

    public void setImgId(String imageCount) {
        this.imageCount = imageCount;
    }
}