package com.example.songmanagerment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Song {
private String id;
private String songName;
private String author;
private String description;
private String imageUrl;
private String videoUrl;
private Integer duration;
private Boolean stauts;

}
