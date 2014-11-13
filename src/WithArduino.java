import java.util.List;
import java.util.ArrayList;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class WithArduino{
	Credentials credentials = new Credentials(); //This is to prevent me from sharing my own keys
	
	public void moodStart() throws Exception{
		ArduinoConnection arduino = new ArduinoConnection();
    	arduino.initialize();
		int r = 0;
		while (r < 100){
			List<String> tweets = find("#happy OR #sad OR #mad OR #glad");
			int numOfTweets = tweets.size();
			int happy = 15;
			int x = 0;
			while (x < numOfTweets){
				//System.out.println(tweets.get(x));
				String str1 = tweets.get(x);
				String str2 = "#happy";
				String str3 = "#sad";
				if(str1.toLowerCase().contains(str2.toLowerCase()) == true){
					happy+=1;
				}
				if(str1.toLowerCase().contains(str3.toLowerCase()) == true){
					happy-=1;
				}
				x+=1;
			}
			
			// Begin Arduino Light Switch
			if (happy >= 0 && happy < 4){
				arduino.sendData("10000000");
			}
			if (happy >= 4 && happy < 8){
				arduino.sendData("01000000");
			}
			if (happy >= 8 && happy < 12){
				arduino.sendData("00100000");
			}
			if (happy >= 12 && happy < 16){
				arduino.sendData("00010000");
			}
			if (happy >= 16 && happy < 20){
				arduino.sendData("00001000");
			}
			if (happy >= 20 && happy < 24){
				arduino.sendData("00000100");
			}
			if (happy >= 24 && happy < 28){
				arduino.sendData("00000010");
			}
			if (happy >= 28 && happy < 31){
				arduino.sendData("00000001");
			}
			// End Arduino Light Switch

			// Begin visual graph
			x = 0;
			String before = "";
			while(x < happy){
				before += "-";
				x+=1;
			}
			String after = "";
			while(x < 30){
				after += "-";
				x+=1;
			}
			System.out.println("Sad |" + before + "X" + after + "| Happy / (Value = " + String.valueOf(happy) + ")");
			// End visual graph

			Thread.sleep(10000);
		}
		
		arduino.close(); // This never seems to work
	}
	


	public List<String> find(String searchQuery) {
		String searchResults = "";
	    ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(true);
	    cb.setOAuthConsumerKey(credentials.twitterConsumerKey);
	    cb.setOAuthConsumerSecret(credentials.twitterConsumerSecret);
	    cb.setOAuthAccessToken(credentials.twitterAccessToken);
	    cb.setOAuthAccessTokenSecret(credentials.twitterAccessSecret);
	    TwitterFactory tf = new TwitterFactory(cb.build());
	    Twitter twitter = tf.getInstance();
	    List<String> tweetList = new ArrayList<String>();
	        try {
	            Query query = new Query(searchQuery);
	            QueryResult result;
	            result = twitter.search(query);
	            List<Status> tweets = result.getTweets();
	            for (Status tweet : tweets) {
	            	tweetList.add(tweet.getText()); //Collect each tweet one by one
	            }

	        } catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Failed to search tweets: " + te.getMessage());
	        }
	        return tweetList; //Return the collected list of tweets
	}
	
}