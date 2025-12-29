package api.github.tests;

import api.github.methods.*;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class GithubApiTests {

    @Test
    public void TC01_getUser_octocat() throws Exception {
        GetUserMethod api = new GetUserMethod();

        Response resp = api.callAPI();
        Assert.assertNotNull(resp, "Response is null");

        Assert.assertEquals(resp.getStatusCode(), 200, "Unexpected status code");

        String contentType = resp.getHeader("Content-Type");
        Assert.assertNotNull(contentType, "Content-Type header missing");
        Assert.assertTrue(contentType.contains("application/json"),
                "Unexpected Content-Type: " + contentType);

        String expected = readResource("api/github/get_user/rs.json");
        String actual = resp.asString();
        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    public void TC02_getRepo_octocat() throws Exception {
        GetRepoMethod api = new GetRepoMethod();

        Response resp = api.callAPI();
        Assert.assertNotNull(resp, "Response is null");

        Assert.assertEquals(resp.getStatusCode(), 200, "Unexpected status code");

        String contentType = resp.getHeader("Content-Type");
        Assert.assertNotNull(contentType, "Content-Type header missing");
        Assert.assertTrue(contentType.contains("application/json"),
                "Unexpected Content-Type: " + contentType);

        String expected = readResource("api/github/get_repo/rs.json");
        String actual = resp.asString();
        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    public void TC03_listCommits_octocatHelloWorld() throws Exception {
        ListCommitsMethod api = new ListCommitsMethod();

        Response resp = api.callAPI();
        Assert.assertNotNull(resp, "Response is null");

        Assert.assertEquals(resp.getStatusCode(), 200, "Unexpected status code");

        String contentType = resp.getHeader("Content-Type");
        Assert.assertNotNull(contentType, "Content-Type header missing");
        Assert.assertTrue(contentType.contains("application/json"),
                "Unexpected Content-Type: " + contentType);

        JSONArray actualArray = new JSONArray(resp.asString());
        Assert.assertTrue(actualArray.length() > 0, "Commits array is empty");

        String sha = actualArray.getJSONObject(0).optString("sha", "");
        Assert.assertFalse(sha.isBlank(), "sha is missing/empty");

        String name = actualArray.getJSONObject(0)
                .getJSONObject("commit")
                .getJSONObject("author")
                .optString("name", "");
        Assert.assertFalse(name.isBlank(), "commit.author.name is missing/empty");

        String date = actualArray.getJSONObject(0)
                .getJSONObject("commit")
                .getJSONObject("author")
                .optString("date", "");
        Assert.assertFalse(date.isBlank(), "commit.author.date is missing/empty");

    }

    @Test
    public void TC04_listIssues_octocatHelloWorld() throws Exception {
        ListIssuesMethod api = new ListIssuesMethod();

        Response resp = api.callAPI();
        Assert.assertNotNull(resp, "Response is null");
        Assert.assertEquals(resp.getStatusCode(), 200, "Unexpected status code");

        String contentType = resp.getHeader("Content-Type");
        Assert.assertNotNull(contentType, "Content-Type header missing");
        Assert.assertTrue(contentType.contains("application/json"),
                "Unexpected Content-Type: " + contentType);

        JSONArray arr = new JSONArray(resp.asString());

        if (arr.length() > 0) {
            JSONObject issue = arr.getJSONObject(0);

            Assert.assertTrue(issue.has("id") && !issue.isNull("id"), "id missing");
            Assert.assertFalse(issue.optString("title", "").isBlank(), "title missing/empty");
            Assert.assertFalse(issue.optString("state", "").isBlank(), "state missing/empty");
            Assert.assertFalse(issue.optString("html_url", "").isBlank(), "html_url missing/empty");
        }
    }

    @Test
    public void TC05_getRateLimit() {
        GetRateLimitMethod api = new GetRateLimitMethod();
        Response resp = api.callAPI();
        Assert.assertNotNull(resp, "Response is null");
        Assert.assertEquals(resp.getStatusCode(), 200, "Unexpected status code");

        String contentType = resp.getHeader("Content-Type");
        Assert.assertNotNull(contentType, "Content-Type header missing");
        Assert.assertTrue(contentType.contains("application/json"),
                "Unexpected Content-Type: " + contentType);

        JSONObject obj = new JSONObject(resp.asString());

        Assert.assertTrue(obj.has("resources") && !obj.isNull("resources"), "resources missing");
        Assert.assertTrue(obj.has("rate") && !obj.isNull("rate"), "rate missing");

        JSONObject rate = obj.getJSONObject("rate");
        Assert.assertTrue(rate.has("limit") && rate.getInt("limit") >= 0, "rate.limit missing/bad");
        Assert.assertTrue(rate.has("remaining") && rate.getInt("remaining") >= 0, "rate.remaining missing/bad");
        Assert.assertTrue(rate.has("reset") && rate.getLong("reset") > 0, "rate.reset missing/bad");
    }

    private static String readResource(String path) throws Exception {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        try (InputStream is = cl.getResourceAsStream(path)) {
            if (is == null) throw new IllegalStateException("Resource not found: " + path);
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
