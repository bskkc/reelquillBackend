package com.example.reelquill.service;

import com.example.reelquill.dto.AlbumDTO;
import com.example.reelquill.dto.ArtistsDTO;
import com.example.reelquill.dto.DownloadUrlDTO;
import com.example.reelquill.dto.SongResponseDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public SongService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<SongResponseDTO> searchSongsWithArtist(String query) {
        String url = "https://saavn.dev/api/search/artists?query=" + query;
        String response = restTemplate.getForObject(url, String.class);

        List<SongResponseDTO> songDTOList = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode items = root.path("data").path("results");

            for (JsonNode item : items) {
                SongResponseDTO songDTO = new SongResponseDTO();
                songDTO.setId(item.path("id").asText());
                songDTO.setName(item.path("name").asText());
                songDTO.setUrl(item.path("url").asText(null));  // Eğer null ise varsayılan değer verilebilir
                songDTO.setCopyright(item.path("copyright").asText(null));

                JsonNode albumNode = item.path("album");
                if (!albumNode.isMissingNode()) {
                    AlbumDTO albumDTO = new AlbumDTO();
                    albumDTO.setId(albumNode.path("id").asText());
                    albumDTO.setName(albumNode.path("name").asText());
                    albumDTO.setUrl(albumNode.path("url").asText());
                    songDTO.setAlbum(albumDTO);
                }

                JsonNode artistsNode = item.path("artists");
                if (!artistsNode.isMissingNode()) {
                    ArtistsDTO artistsDTO = new ArtistsDTO();
                    artistsDTO.setPrimary(artistsNode.path("primary").findValuesAsText("name"));
                    artistsDTO.setFeatured(artistsNode.path("featured").findValuesAsText("name"));
                    artistsDTO.setAll(artistsNode.path("all").findValuesAsText("name"));
                    songDTO.setArtists(artistsDTO);
                }

                JsonNode downloadUrlsNode = item.path("downloadUrl");
                if (!downloadUrlsNode.isMissingNode()) {
                    List<DownloadUrlDTO> downloadUrlList = new ArrayList<>();
                    downloadUrlsNode.forEach(urlNode -> {
                        DownloadUrlDTO downloadUrlDTO = new DownloadUrlDTO();
                        downloadUrlDTO.setUrl(urlNode.path("url").asText());
                        downloadUrlList.add(downloadUrlDTO);
                    });
                    songDTO.setDownloadUrls(downloadUrlList);
                }

                songDTO.setDuration(item.path("duration").asInt());
                songDTO.setExplicitContent(item.path("explicitContent").asBoolean());
                songDTO.setHasLyrics(item.path("hasLyrics").asBoolean());
                songDTO.setImage(item.path("image").findValuesAsText("url"));
                songDTO.setLabel(item.path("label").asText());
                songDTO.setLanguage(item.path("language").asText());
                songDTO.setYear(item.path("year").asText());
                songDTO.setPlayCount(item.path("playCount").asInt());
                songDTO.setType(item.path("type").asText());

                songDTOList.add(songDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return songDTOList;
    }

    public List<SongResponseDTO> searchSongsWithName(String query) {
        String url = "https://saavn.dev/api/search/songs?query=" + query;
        String response = restTemplate.getForObject(url, String.class);

        List<SongResponseDTO> songDTOList = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode items = root.path("data").path("results");

            for (JsonNode item : items) {
                SongResponseDTO songDTO = new SongResponseDTO();
                songDTO.setId(item.path("id").asText());
                songDTO.setName(item.path("name").asText());
                songDTO.setUrl(item.path("url").asText(null));  // Eğer null ise varsayılan değer verilebilir
                songDTO.setCopyright(item.path("copyright").asText(null));

                JsonNode albumNode = item.path("album");
                if (!albumNode.isMissingNode()) {
                    AlbumDTO albumDTO = new AlbumDTO();
                    albumDTO.setId(albumNode.path("id").asText());
                    albumDTO.setName(albumNode.path("name").asText());
                    albumDTO.setUrl(albumNode.path("url").asText());
                    songDTO.setAlbum(albumDTO);
                }

                JsonNode artistsNode = item.path("artists");
                if (!artistsNode.isMissingNode()) {
                    ArtistsDTO artistsDTO = new ArtistsDTO();
                    artistsDTO.setPrimary(artistsNode.path("primary").findValuesAsText("name"));
                    artistsDTO.setFeatured(artistsNode.path("featured").findValuesAsText("name"));
                    artistsDTO.setAll(artistsNode.path("all").findValuesAsText("name"));
                    songDTO.setArtists(artistsDTO);
                }

                JsonNode downloadUrlsNode = item.path("downloadUrl");
                if (!downloadUrlsNode.isMissingNode()) {
                    List<DownloadUrlDTO> downloadUrlList = new ArrayList<>();
                    downloadUrlsNode.forEach(urlNode -> {
                        DownloadUrlDTO downloadUrlDTO = new DownloadUrlDTO();
                        downloadUrlDTO.setUrl(urlNode.path("url").asText());
                        downloadUrlList.add(downloadUrlDTO);
                    });
                    songDTO.setDownloadUrls(downloadUrlList);
                }

                songDTO.setDuration(item.path("duration").asInt());
                songDTO.setExplicitContent(item.path("explicitContent").asBoolean());
                songDTO.setHasLyrics(item.path("hasLyrics").asBoolean());
                songDTO.setImage(item.path("image").findValuesAsText("url"));
                songDTO.setLabel(item.path("label").asText());
                songDTO.setLanguage(item.path("language").asText());
                songDTO.setYear(item.path("year").asText());
                songDTO.setPlayCount(item.path("playCount").asInt());
                songDTO.setType(item.path("type").asText());

                songDTOList.add(songDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return songDTOList;
    }
}
