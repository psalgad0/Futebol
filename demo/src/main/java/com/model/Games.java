package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Games {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
	public String match_hometeam_name;
	public String match_awayteam_name;
	public String match_hometeam_score;
	public String match_awayteam_score;
	public String match_status;
	public String league_name;
	public String country_name;
	public String match_time;
	public String match_id;
	public String match_date;
	public String match_live;
	public String getMatch_hometeam_score() {
		return match_hometeam_score;
	}
	public void setMatch_hometeam_score(String match_hometeam_score) {
		this.match_hometeam_score = match_hometeam_score;
	}
	public String getMatch_awayteam_score() {
		return match_awayteam_score;
	}
	public void setMatch_awayteam_score(String match_awayteam_score) {
		this.match_awayteam_score = match_awayteam_score;
	}
	public String getMatch_status() {
		return match_status;
	}
	public void setMatch_status(String match_status) {
		this.match_status = match_status;
	}
	public String getMatch_live() {
		return match_live;
	}
	public void setMatch_live(String match_live) {
		this.match_live = match_live;
	}

	
}
