import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by harrison on 8/7/15.
 */
public class SimplyWallStApi {

    private static final String ENDPOINT = "https://simplywall.st/api/snowflake/%s:%s";
    private final OkHttpClient client = new OkHttpClient();

    private JSONObject mJson;
    private int[] mScores;

    public SimplyWallStApi(String exchange, String ticker) {
        getData(exchange, ticker);
    }

    private void getData(String exchange, String ticker) {
        Request request = new Request.Builder()
                .url(String.format(ENDPOINT, exchange, ticker))
                .build();

        try {
            Response response = client.newCall(request).execute();

            mJson = new JSONObject(response.body().string());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public String getCompanyName() throws JSONException {
        return mJson.getString("companyName");
    }

    public String getUniqueSymbol() throws JSONException {
        return mJson.getString("uniqueSymbol");
    }

    public String getExchangeSymbol() throws JSONException {
        return mJson.getString("primaryExchangeSymbol");
    }

    public String getTickerSymbol() throws JSONException {
        return mJson.getString("primaryTickerSymbol");
    }

    // Value, Future, Past, Health, Income
    public int[] getSnowflakeScores() throws JSONException {
        JSONArray array = mJson.getJSONArray("snowflakeScores");
        int[] values = new int[array.length()];
        for (int i = 0; i < values.length; i++) {
            values[i] = array.getInt(i);
        }

        if (mScores == null) {
            mScores = Arrays.copyOf(values, values.length);
        }
        return values;
    }

    public int getValue() throws JSONException {
        getSnowflakeScores();
        return mScores[0];
    }

    public int getFuture() throws JSONException {
        getSnowflakeScores();
        return mScores[1];
    }

    public int getPast() throws JSONException {
        getSnowflakeScores();
        return mScores[2];
    }

    public int getHealth() throws JSONException {
        getSnowflakeScores();
        return mScores[3];
    }

    public int getIncome() throws JSONException {
        getSnowflakeScores();
        return mScores[4];
    }

    public double getSnowflakeColor() throws JSONException {
        return mJson.getDouble("snowflakeColour");
    }


}
