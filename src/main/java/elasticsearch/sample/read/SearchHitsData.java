package elasticsearch.sample.read;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class SearchHitsData {

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

        // To get access to the returned documents, we need to first get the SearchHits
        // contained in the response:
        SearchHits hits = searchResponse.getHits();
        // Nested inside the SearchHits are the individual search results that can be
        // iterated over:
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            // do something with the SearchHit
            // The SearchHit provides access to basic information like index, document ID
            // and score of each search hit:
            String index = hit.getIndex();
            String id = hit.getId();
            float score = hit.getScore();

            System.out.println("index: " + index);
            System.out.println("id: " + id);
            System.out.println("score: " + score);
        }

        // クライアントを閉じる
        client.close();
    }
}
