package elasticsearch.sample.read;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class ReadIndexData {

    public static void main(String[] args) throws IOException {
        // Elasticsearch に接続
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // sampleindex のデータを取得_Query
        SearchRequest searchRequest = new SearchRequest("sampleindex");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        // sampleindex のデータを取得_結果を取得
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        // JSON を整形
        String searchResponseString = searchResponse.toString();
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        JsonReader reader = new JsonReader(new StringReader(searchResponseString));
        Map<String, Object> map = gson.fromJson(reader, new TypeToken<Map<String, Object>>() {
        }.getType());

        // sampleindex のデータを取得_結果を表示
        System.out.println("searchResponse: \n" + gson.toJson(map));

        // クライアントを閉じる
        client.close();
    }
}
