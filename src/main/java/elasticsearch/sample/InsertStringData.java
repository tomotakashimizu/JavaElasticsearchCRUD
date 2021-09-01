package elasticsearch.sample;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

// String型のデータを Elasticsearch に送る class
public class InsertStringData {

    public static void main(String[] args) throws IOException {
        // Elasticsearch に接続
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // String型のデータを Elasticsearch に送る
        IndexRequest indexRequest = new IndexRequest("sampleindex");
        indexRequest.id("001");
        indexRequest.source("SampleKey", "SampleValue");
        IndexResponse indexResponse = client.index(indexRequest,
                RequestOptions.DEFAULT);
        System.out.println("response id: " + indexResponse.getId());
        System.out.println("response name: " + indexResponse.getResult().name());
    }
}
