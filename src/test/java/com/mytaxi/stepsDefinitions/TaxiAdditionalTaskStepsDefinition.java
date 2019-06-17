package com.mytaxi.stepsDefinitions;

import urlHelper.UrlHelper;
import com.jayway.restassured.response.Response;
import com.mytaxi.storageData.UsersMap;
import cucumber.api.java.en.And;
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
 * Additional task StepDefinition
 */
public class TaxiAdditionalTaskStepsDefinition {

    @Autowired
    UsersMap userMap;

    /**
     * Extracting Albums collection by UserId and storing it in UserDataMap
     * @param user username for User
     */
    @When("album collection for username (.*?) is extracted")
    public void getPostCollection(String user) {
        Response response = given().get(UrlHelper.searchAlbumsByUserId(((UserData)userMap.getUserMap().get(user)).getId()));
        ((UserData)userMap.getUserMap().get(user)).setAlbumsArray(new JSONArray(response.body().asString()));
    }

    /**
     * Extracting all photos and finding comments for our user by albumId parameter
     * from posts collection and storing them UserDataMap
     * @param user username for User
     */
    @And("photos collection for username (.*?) is extracted")
    public void getCommentsCollection(String user) {
        Response response = given().get(UrlHelper.findAllPhotos());
        JSONArray photosArray = new JSONArray(response.body().asString());
        JSONArray commentsSortedPhotosArray = new JSONArray();
        for (Object object : ((UserData)userMap.getUserMap().get(user)).getAlbumsArray()) {
            StreamSupport.stream(photosArray.spliterator(), false)
                    .map(JSONObject.class::cast)
                    .filter(o -> o.getLong(UrlHelper.ALBUMID) == ((JSONObject)object).getLong(UrlHelper.ID)).
                    forEach(o -> commentsSortedPhotosArray.put(o));
        }
        ((UserData)userMap.getUserMap().get(user)).setPhotosArray(commentsSortedPhotosArray);
    }

    /**
     * Check that current user have enough photos for its albums
     * @param user username for User
     */
    @Then("photos collection for username (.*?) should have (\\d+) photos")
    public void post(String user, int photosAmount) {
        Assert.assertTrue("Username " + user + " doesn't have enough photos for its albums",  ((UserData)userMap.getUserMap().get(user)).getPhotosArray().length() == photosAmount);
    }
}
