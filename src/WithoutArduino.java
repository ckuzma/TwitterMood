import java.util.List;
import java.util.ArrayList;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class WithoutArduino{
	Credentials credentials = new Credentials(); //This is to prevent me from sharing my own keys
	
	public void moodStart() throws Exception{
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
			String below = "";
			
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
	            	// These are just some examples of what can be done:
	            	/*
	                System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
	                System.out.println(tweet.getCreatedAt());
	                */

	            	tweetList.add(tweet.getText());
	            	
	            	
	            }

	            

	            //System.exit(-1);
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Failed to search tweets: " + te.getMessage());
	            //System.exit(-1);
	        }
	        return tweetList;
	}
	
}