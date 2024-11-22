package com.example.reelquill.dto;

import java.util.List;

public class SongResponseDTO {
    private String id;
    private String name;
    private String url;
    private AlbumDTO album;
    private ArtistsDTO artists;
    private String copyright;
    private List<DownloadUrlDTO> downloadUrls;
    private int duration;
    private boolean explicitContent;
    private boolean hasLyrics;
    private List<String> image;
    private String label;
    private String language;
    private String year;
    private String type;
    private int playCount;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AlbumDTO getAlbum() {
        return album;
    }

    public void setAlbum(AlbumDTO album) {
        this.album = album;
    }

    public ArtistsDTO getArtists() {
        return artists;
    }

    public void setArtists(ArtistsDTO artists) {
        this.artists = artists;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<DownloadUrlDTO> getDownloadUrls() {
        return downloadUrls;
    }

    public void setDownloadUrls(List<DownloadUrlDTO> downloadUrls) {
        this.downloadUrls = downloadUrls;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isExplicitContent() {
        return explicitContent;
    }

    public void setExplicitContent(boolean explicitContent) {
        this.explicitContent = explicitContent;
    }

    public boolean isHasLyrics() {
        return hasLyrics;
    }

    public void setHasLyrics(boolean hasLyrics) {
        this.hasLyrics = hasLyrics;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }
}
