package bussinessLayer;

public interface calculateInterface {
	public float getPriceMin();
	public float getLandmarkDistance();
	public float getPriceJump();
	public float getDistanceJump();
	public float distance(char startStation, char endStation);
	public float pricing(float distance);
}
