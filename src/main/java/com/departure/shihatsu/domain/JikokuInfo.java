package com.departure.shihatsu.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JikokuInfo {
  private String stationName;
  private String lineName;
  private String lineDir;
  private String dateGroup;
}