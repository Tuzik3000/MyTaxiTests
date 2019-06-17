package dataStructures;

import urlHelper.UrlHelper;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Class used for storing relevant user data and all relevant Arrays of info
 * Constructor takes Json object and fills relevant fields
 */
public class UserData {

    private String username;
    private String email;
    private String id;
    private String phone;
    private String name;
    private JSONArray commentsArray;
    private JSONArray albumsArray;
    private JSONArray photosArray;
    private JSONArray todosArray;
    private JSONArray postsArray;

    public UserData(JSONObject sync) {
        this.username = sync.getString(UrlHelper.USERNAME);
        this.email = sync.getString(UrlHelper.EMAIL);
        this.id = String.valueOf(sync.getInt(UrlHelper.ID));
        this.phone = sync.getString(UrlHelper.PHONE);
        this.name = sync.getString(UrlHelper.NAME);
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public JSONArray getCommentsArray() {
        return commentsArray;
    }

    public void setCommentsArray(JSONArray commentsArray) {
        this.commentsArray = commentsArray;
    }

    public JSONArray getAlbumsArray() {
        return albumsArray;
    }

    public void setAlbumsArray(JSONArray albumsArray) {
        this.albumsArray = albumsArray;
    }

    public JSONArray getPhotosArray() {
        return photosArray;
    }

    public void setPhotosArray(JSONArray photosArray) {
        this.photosArray = photosArray;
    }

    public JSONArray getTodosArray() {
        return todosArray;
    }

    public void setTodosArray(JSONArray todosArray) {
        this.todosArray = todosArray;
    }

    public JSONArray getPostsArray() {
        return postsArray;
    }

    public void setPostsArray(JSONArray postsArray) {
        this.postsArray = postsArray;
    }

}
