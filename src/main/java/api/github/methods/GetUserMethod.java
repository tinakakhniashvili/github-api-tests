package api.github.methods;

import com.zebrunner.carina.api.AbstractApiMethodV2;

public class GetUserMethod extends AbstractApiMethodV2 {
    public GetUserMethod() {
        super(null, "api/github/get_user/rs.json");
    }
}
