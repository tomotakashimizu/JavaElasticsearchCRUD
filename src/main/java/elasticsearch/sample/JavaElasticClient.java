package elasticsearch.sample;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class JavaElasticClient {

    public static void main(String[] args) throws IOException {
        // Elasticsearch に接続
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // Elasticsearch に新しい index を作成
        // CreateIndexRequest request = new CreateIndexRequest("sampleindex");
        // request.settings(Settings.builder()
        // .put("index.number_of_shards", 1)
        // .put("index.number_of_replicas", 2));
        // // index が作成されたか確認
        // CreateIndexResponse createIndexResponse = client.indices().create(request,
        // RequestOptions.DEFAULT);
        // System.out.println("response id: " + createIndexResponse.index());

        // String型のデータを Elasticsearch に送る
        // IndexRequest indexRequest = new IndexRequest("sampleindex");
        // indexRequest.id("001");
        // indexRequest.source("SampleKey", "SampleValue");
        // IndexResponse indexResponse = client.index(indexRequest,
        // RequestOptions.DEFAULT);
        // System.out.println("response id: " + indexResponse.getId());
        // System.out.println("response name: " + indexResponse.getResult().name());

        // Map 型のサンプルデータを作成
        // HashMap<String, Integer> map = new HashMap<String, Integer>();
        // map.put("keyOne", 10);
        // map.put("keyTwo", 30);
        // map.put("KeyThree", 20);

        // Map 型のデータを Elasticsearch に送る
        // IndexRequest indexRequest = new IndexRequest("sampleindex");
        // indexRequest.id("002");
        // indexRequest.source(map);
        // 正常に処理されたか確認
        // IndexResponse indexResponse = client.index(indexRequest,
        // RequestOptions.DEFAULT);
        // System.out.println("response id: " + indexResponse.getId());
        // System.out.println("response name: " + indexResponse.getResult().name());

        // POJO クラスをインスタンス化
        EmployeePojo emp = new EmployeePojo("Elon", "Musk", LocalDate.now());

        // POJO を JSON 形式にして データを Elasticsearch に送る
        IndexRequest indexRequest = new IndexRequest("sampleindex");
        indexRequest.id("003");
        indexRequest.source(new ObjectMapper().writeValueAsString(emp), XContentType.JSON);
        System.out.println("JSONデータ" + new ObjectMapper().writeValueAsString(emp));
        // 正常に処理されたか確認
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("response id: " + indexResponse.getId());
        System.out.println("response name: " + indexResponse.getResult().name());
    }
}
