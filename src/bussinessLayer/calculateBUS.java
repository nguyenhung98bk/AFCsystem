package bussinessLayer;

import dataLayer.stationDAO;

public class calculateBUS implements calculateInterface {
	@Override
	public float getPriceMin() {
		return (float) 1.9;
	}
	@Override
	public float getLandmarkDistance() {
		return (float) 5;
	}
	@Override
	public float getPriceJump() {
		return (float) 0.4;
	}
	@Override
	public float getDistanceJump() {
		return (float) 2;
	}
	public float distance(char startStation, char endStation) {
		float distanceStart;
		float distanceEnd;
		float distance;
		stationDAO st = new stationDAO();
		distanceStart = st.distance(startStation);
		distanceEnd = st.distance(endStation);
		distance = Math.abs(distanceEnd-distanceStart);
		return distance;
	}
	public float pricing(float distance) {
		float price = 0;
		if(distance <= getLandmarkDistance()) {
			price = getPriceMin();
		}
		else {
			if((distance-getLandmarkDistance())%getDistanceJump()==0) {
				price = getPriceMin() + (distance-getLandmarkDistance())*getPriceJump();
			}
			else {
				int a = (int) Math.ceil((distance-getLandmarkDistance())/2);
				price = getPriceMin() + (a+1)*getPriceJump();
			}
		}
		return Math.round(price*100)/100;
	}
}
