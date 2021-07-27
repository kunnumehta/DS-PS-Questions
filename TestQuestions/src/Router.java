package Router;//interface Router.Router {
//
//    fun withRoute(path: String, result: String) : Unit;
//
//    fun route(path: String) : String;
//
//}
//Usage:
//        Router.Router.withRoute("/bar/*", "result")
//        Router.Router.route("/bar") -> "result"
//        Usage:
//        Router.Router.withRoute("/bar", "result")
//        Router.Router.route("/bar") -> "result"


public interface Router {

    void withRoute(String path, String result);
    String route(String path);
}


