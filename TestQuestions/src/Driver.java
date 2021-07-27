package Router;

public class Driver {

    public static void main(String[] args) {
        Router router = HashMapImplementation.getInstance();
//        router.withRoute("/bar", "result");
//        System.out.println(router.route("/bar"));
//        System.out.println(router.route("/xyz"));
//        router.withRoute("/xyz", "result1");
//        System.out.println(router.route("/xyz"));
//        router.withRoute("/xyz", "result2");
//        System.out.println(router.route("/xyz"));
        router.withRoute("/bar/*", "result1");
        router.withRoute("/bar/xyz/*", "result2");
        System.out.println(router.route("/bar/xyz"));
    }

}
