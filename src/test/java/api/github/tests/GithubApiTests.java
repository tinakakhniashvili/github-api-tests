package api.github.tests;

import api.github.methods.GetRateLimitMethod;
import api.github.methods.GetRepoMethod;
import api.github.methods.GetUserMethod;
import api.github.methods.ListCommitsMethod;
import api.github.methods.ListIssuesMethod;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class GithubApiTests {

    @Test
    public void verifyGetOctocatUserTest() {
        GetUserMethod api = new GetUserMethod();

        String login = System.getProperty("github.login", "octocat");
        api.addProperty("login", login);

        Response resp = api.callAPI();
        Assert.assertEquals(resp.getStatusCode(), 200);

       api.validateResponse(JSONCompareMode.LENIENT);
    }

    @Test
    public void verifyGetRepositoryTest() {
        GetRepoMethod api = new GetRepoMethod();

        String owner = System.getProperty("github.owner", "octocat");
        String repo = System.getProperty("github.repo", "Hello-World");
        api.addProperty("owner", owner);
        api.addProperty("repo", repo);

        Response resp = api.callAPI();
        Assert.assertEquals(resp.getStatusCode(), 200);

       api.validateResponse(JSONCompareMode.LENIENT);
    }

    @Test
    public void verifyListCommitsTest() {
        ListCommitsMethod api = new ListCommitsMethod();

        Response resp = api.callAPI();
        Assert.assertEquals(resp.getStatusCode(), 200);

        JSONArray arr = new JSONArray(resp.asString());
        Assert.assertTrue(arr.length() > 0);

        JSONObject commit = arr.getJSONObject(0);
        Assert.assertFalse(commit.optString("sha").isBlank());
    }

    @Test
    public void verifyListIssuesTest() {
        ListIssuesMethod api = new ListIssuesMethod();

        Response resp = api.callAPI();
        Assert.assertEquals(resp.getStatusCode(), 200);

        JSONArray arr = new JSONArray(resp.asString());
        Assert.assertNotNull(arr);
    }

    @Test
    public void verifyGetRateLimitTest() {
        GetRateLimitMethod api = new GetRateLimitMethod();

        Response resp = api.callAPI();
        Assert.assertEquals(resp.getStatusCode(), 200);

       api.validateResponse(JSONCompareMode.LENIENT);
    }
}
