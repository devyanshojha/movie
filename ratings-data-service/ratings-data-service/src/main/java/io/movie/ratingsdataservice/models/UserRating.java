package io.movie.ratingsdataservice.models;

import java.util.Arrays;
import java.util.List;

public class UserRating {
	private String userId;
	private List<Rating> userRating;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<Rating> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<Rating> userRating) {
		this.userRating = userRating;
	}
	public void initData(String userId)
	{
		this.setUserId(userId);
		this.setUserRating(Arrays.asList(
				new Rating("123", 6),
				new Rating("124", 8)));
	}
}
