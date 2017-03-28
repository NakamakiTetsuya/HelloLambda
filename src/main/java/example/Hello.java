package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class Hello implements RequestHandler<String, String> {
    public String handleRequest(String input, Context context){

    	Twitter twitter = new TwitterFactory().getInstance();
    	User user = null;
        try {
			user = twitter.verifyCredentials();
		} catch (TwitterException e) {
			return null;
		}

        System.out.println("なまえ　　　：" + user.getName());
        System.out.println("ひょうじ名　：" + user.getScreenName());
        System.err.println("ふぉろー数　：" + user.getFriendsCount());
        System.out.println("ふぉろわー数：" + user.getFollowersCount());


    	String output = "Hello, " + input + "!?";
    	output += "\r\nなまえ　　　：" + user.getName();
    	output += "\r\nひょうじ名　：" + user.getScreenName();
    	output += "\r\nふぉろー数　：" + user.getFriendsCount();
    	output += "\r\nふぉろわー数：" + user.getFollowersCount();

    	return output;
    }
}