package com.mk.SpringBootProject_1.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SentimentData {

  private String email;
  private String sentiment;

}
