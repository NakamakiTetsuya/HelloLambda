package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class Hello implements RequestHandler<String, String> {
	public String handleRequest(String input, Context context) {

		Twitter twitter = new TwitterFactory().getInstance();
		Query query = new Query();
		query.setLang("ja");
		query.setCount(1);
		query.setQuery("from:njslyr");

		QueryResult result = null;
		ObjectMapper mapper = new ObjectMapper();

		try {
			result = twitter.search(query);
			return mapper.writeValueAsString(result.getTweets());
		} catch (TwitterException e1) {
			return null;
		}  catch (JsonProcessingException e) {
			return null;
		}
	}
}