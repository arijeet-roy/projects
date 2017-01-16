
public class Dest {

	private String address;
	private int dist;
	private int duration;
	private int durTraffic;
	
	public Dest(String address) {
		this.address = address;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

	public int getDuration() {
		return duration;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getDurTraffic() {
		return durTraffic;
	}

	public void setDurTraffic(int durTraffic) {
		this.durTraffic = durTraffic;
	}
	
}
