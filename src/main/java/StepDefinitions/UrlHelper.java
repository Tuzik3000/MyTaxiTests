package StepDefinitions;

/**
 * Base class for working for Providing any Url and Fields Strings
 */
public class UrlHelper {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    public static final String POSTS = "posts";
    public static final String COMMENTS = "comments";
    public static final String PHOTOS = "photos";
    public static final String TODOS = "todos";
    public static final String USERS = "users";
    public static final String ALBUMS = "albums";
    public static final String USERID = "userId";
    public static final String USERNAME = "username";
    public static final String POSTID = "postId";
    public static final String ALBUMID = "albumId";
    public static final String EMAIL = "email";
    public static final String ID = "id";
    public static final String PHONE = "phone";
    public static final String NAME = "name";

    public static String getUrl() {
            return BASE_URL;
    }

    private static String searchForBy(String valueFromSearch, String valueBySearch, String mainValue) {
        return BASE_URL + "/" + valueFromSearch + "?" + valueBySearch + "=" + mainValue;
    }

    private static String searchFor(String valueFromSearch) {
        return BASE_URL + "/" + valueFromSearch;
    }

    public static String findAllComments() {
        return searchFor(COMMENTS);
    }

    public static String searchPostsByUserId(String userId) {
        return searchForBy(POSTS, USERID, userId);
    }

    public static String searchUsersByUsername(String username) {
        return searchForBy(USERS, USERNAME, username);
    }

    public static String searchCommentsByPostId(String postId) {
        return searchForBy(COMMENTS, POSTID, postId);
    }

    public static String searchAlbumsByUserId(String userId) {
        return searchForBy(ALBUMS, USERID, userId);
    }

    public static String searchTodosByUserId(String userId) {
        return searchForBy(TODOS, USERID, userId);
    }

    public static String searchPhotosByAlbumId(String albumId) {
        return searchForBy(PHOTOS, ALBUMID, albumId);
    }

}
