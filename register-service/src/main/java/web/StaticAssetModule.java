package web;

import io.jooby.Jooby;
import io.jooby.Route;

import java.nio.file.Paths;

public class StaticAssetModule extends Jooby {

    public StaticAssetModule() {

        //handle favicon
        get("favicon.ico", Route.FAVICON);

        //Serve everything FOR NOw, in the static file folder
        assets("/*", Paths.get("static"));
    }
}
