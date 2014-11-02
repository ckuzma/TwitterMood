import java.util.List;
import java.util.ArrayList;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class MoodRunnableNoDuino{
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
			
			// Anything more than 15 means more happy than not
			/*
			// Begin switch
			if (happy >= 0 && happy < 4){
				System.out.println("Twitter is super sad." + "(" + String.valueOf(happy) + ")");
			}
			if (happy >= 4 && happy < 8){
				System.out.println("Twitter is very sad." + "(" + String.valueOf(happy) + ")");
			}
			if (happy >= 8 && happy < 12){
				System.out.println("Twitter is sad happy." + "(" + String.valueOf(happy) + ")");
			}
			if (happy >= 12 && happy < 16){
				System.out.println("Twitter is alight." + "(" + String.valueOf(happy) + ")");
			}
			if (happy >= 16 && happy < 20){
				System.out.println("Twitter is happy." + "(" + String.valueOf(happy) + ")");
			}
			if (happy >= 20 && happy < 24){
				System.out.println("Twitter is quite happy." + "(" + String.valueOf(happy) + ")");
			}
			if (happy >= 24 && happy < 28){
				System.out.println("Twitter is very happy." + "(" + String.valueOf(happy) + ")");
			}
			if (happy >= 28 && happy < 31){
				System.out.println("Twitter is really happy." + "(" + String.valueOf(happy) + ")");
			}
			*/
			
			// generate before part
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

			
			Thread.sleep(4000);
		}

	}
	
	// repeat twitter class



	public List<String> find(String searchQuery) {
		String searchResults = "";
	    ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(true)
	          .setOAuthConsumerKey("")
	          .setOAuthConsumerSecret("")
	          .setOAuthAccessToken("")
	          .setOAuthAccessTokenSecret("");
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