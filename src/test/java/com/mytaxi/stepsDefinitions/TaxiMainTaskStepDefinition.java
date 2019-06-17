package com.mytaxi.stepsDefinitions;

import urlHelper.UrlHelper;
import com.jayway.restassured.response.Response;
import com.mytaxi.storageData.UsersMap;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataStructures.UserData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.StreamSupport;

import static com.jayway.restassured.RestAssured.given;

/**
 * Step definition for main Test task
 */
public class TaxiMainTaskStepDefinition {

    @Autowired
    UsersMap userMap;

    /**
     * Extracting user By username from endpoint and storing it in UserDataMap
     * @param user username for User
     */
    @Given("username (.*?) is found")
    public void takeUser(String user) {
        Response response = given().get(UrlHelper.searchUsersByUsername(user));
        JSONArray usersArray = new JSONArray(response.body().asString());
        Assert.assertEquals ("Two users with identical UserNames " + user + " were found",1,usersArray.length());
        UserData userData = new UserData(usersArray.getJSONObject(0));
        if (userMap.getUserMap().get(user) == null)
        userMap.getUserMap().put(user, userData);
    }

    /**
     * Extracting posts collection by UserId and storing it in UserDataMap
     * @param user username for User
     */
    @When("posts collection for username (.*?) is extracted")
    public void getPostCollection(String user) {
        Response response = given().get(UrlHelper.searchPostsByUserId(((UserData)userMap.getUserMap().get(user)).getId()));
        ((UserData)userMap.getUserMap().get(user)).setPostsArray(new JSONArray(response.body().asString()));
    }

    /**
     * Extracting all comments and finding comments for our user by postId parameter
     * from posts collection and storing them UserDataMap
     * @param user username for User
     */
    @And("comments collection for username (.*?) is extracted")
    public void getCommentsCollection(String user) {
        Response response = given().get(UrlHelper.findAllComments());
        JSONArray commentsArray = new JSONArray(response.body().asString());
        JSONArray commentsSortedArray = new JSONArray();
        for (Object object : ((UserData)userMap.getUserMap().get(user)).getPostsArray()) {
            StreamSupport.stream(commentsArray.spliterator(), false)
                        .map(JSONObject.class::cast)
                .filter(o -> o.getLong(UrlHelper.POSTID) == ((JSONObject)object).getLong(UrlHelper.ID)).
                        forEach(o -> commentsSortedArray.put(o));
        }
        ((UserData)userMap.getUserMap().get(user)).setCommentsArray(commentsSortedArray);
    }

    /**
     * Check that email on all comments is the same as it is on this user
     * @param user username for User
     */
    @Then("comments collection for username (.*?) should have correct email")
    public void post(String user) {
        String email = ((UserData)userMap.getUserMap().get(user)).getEmail();
        JSONArray commentsArray = ((UserData)userMap.getUserMap().get(user)).getCommentsArray();
        JSONArray commentsSortedArray = new JSONArray();
        StreamSupport.stream(commentsArray.spliterator(), false)
                .map(JSONObject.class::cast)
                .filter(o -> o.getString(UrlHelper.EMAIL).equals(email)).
                forEach(o -> commentsSortedArray.put(o));
        Assert.assertTrue("Not all comments which were made by Username "
                + user + " and it's email " + email + " comments which were found" + "\n\n\n\n"
                + commentsSortedArray.toString(),  commentsSortedArray.length() == commentsArray.length());
    }
}
