
public class Solution {

	public Solution() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[] ip = new String[5];
		ip[0] = "asf";
		ip[1] = "121.18.19.20";
		ip[2] = "2001:0db8:0000:0000:0000:ff00:0042:8329";
		ip[3] = "3:0db8:0:01:F:ff0:0042:8329";
		ip[4] = "3::0:01:F:ff0:0042:8329";
		
		String result[] = checkIP(ip);
		
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}

	}

	static String[] checkIP(String[] ip) {
		// TODO Auto-generated method stub
		String[] result = new String[ip.length];
		String IPv6_pattern = "([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]){1,4}";
		String IPv4_pattern = "(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}"
				+ "(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]))";
		
		for (int i = 0; i < ip.length; i++) {
			if(ip[i].matches(IPv4_pattern) == true) {
				result[i] = "IPv4";
			}
			else if(ip[i].matches(IPv6_pattern) == true) {
				result[i] = "IPv6";
			}
			else {
				result[i] = "Neither";
			}
		}
		
		return result;
	}

}
