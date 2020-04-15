package com.octo.legacy.tripservice.tripservice;

import com.octo.legacy.tripservice.tripservice.exception.UserNotLoggedInException;
import com.octo.legacy.tripservice.tripservice.trip.Trip;
import com.octo.legacy.tripservice.tripservice.trip.TripDAO;
import com.octo.legacy.tripservice.tripservice.user.User;
import com.octo.legacy.tripservice.tripservice.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService_Original {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<Trip>();
		User loggedUser = UserSession.getInstance().getLoggedUser();
		boolean isFriend = false;
		if (loggedUser != null) {
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}
			if (isFriend) {
				tripList = TripDAO.findTripsByUser(user);
			}
			return tripList;
		} else {
			throw new UserNotLoggedInException();
		}
	}
	
}