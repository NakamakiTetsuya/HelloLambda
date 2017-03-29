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
import twitter4j.conf.ConfigurationBuilder;

public class Hello implements RequestHandler<String, String> {
	public String handleRequest(String input, Context context) {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(System.getenv("twitter4j_debug").equals(true))
				.setOAuthConsumerKey(System.getenv("twitter4j_oauth_consumerKey"))
				.setOAuthConsumerSecret(System.getenv("twitter4j_oauth_consumerSecret"))
				.setOAuthAccessToken(System.getenv("twitter4j_oauth_accessToken"))
				.setOAuthAccessTokenSecret(System.getenv("twitter4j_oauth_accessTokenSecret"));
		TwitterFactory tf = new TwitterFactory(cb.build());

		Twitter twitter = tf.getInstance();
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
		} catch (JsonProcessingException e) {
			return null;
		}
	}
}