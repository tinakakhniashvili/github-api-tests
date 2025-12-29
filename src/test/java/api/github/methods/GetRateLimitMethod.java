package api.github.methods;

import com.zebrunner.carina.api.AbstractApiMethodV2;

public class GetRateLimitMethod extends AbstractApiMethodV2 {
    public GetRateLimitMethod() {
        super(null, "api/github/rate_limit/rs.json");
    }
}
