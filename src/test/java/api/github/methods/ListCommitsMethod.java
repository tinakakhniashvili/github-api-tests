package api.github.methods;

import com.zebrunner.carina.api.AbstractApiMethodV2;

public class ListCommitsMethod extends AbstractApiMethodV2 {
    public ListCommitsMethod() {
        super(null, "api/github/list_commits/rs.json");
    }
}
