/**
 * Created by harrison on 8/18/15.
 */
public class Test {

    public static void main(String[] args) {
        SimplyWallStApi api = new SimplyWallStApi("US", "AAPL");
        try {
            System.out.println(api.getCompanyName());
            System.out.println(api.getIncome());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
