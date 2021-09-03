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

public class JavaElasticUpdateDelete {

    public static void main(String[] args) throws IOException {
        // Elasticsearch に接続
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // // Elasticsearch の String 型でデータ更新, 追加
        // UpdateRequest updateRequest = new UpdateRequest("employeeindex", "002");
        // updateRequest.doc("department", "Bussiness");
        // // 更新されたか id の確認
        // UpdateResponse updateResponse = client.update(updateRequest,
        // RequestOptions.DEFAULT);
        // System.out.println("updated response id: " + updateResponse.getId());

        // // Map を作成
        // Map<String, Object> updateMap = new HashMap<String, Object>();
        // updateMap.put("firstname", "Sundar");
        // updateMap.put("lastname", "Pichai");
        // updateMap.put("company", "Google");
        // updateMap.put("sector", "IT");
        // // Elasticsearch を Map 型でデータ更新, 追加
        // UpdateRequest request = new UpdateRequest("employeeindex",
        // "002").doc(updateMap);
        // UpdateResponse updateResponse = client.update(request,
        // RequestOptions.DEFAULT);
        // System.out.println("updated response id: " + updateResponse.getId());

        // // update way2 Elasticsearch の String 型でデータ更新（元からデータがあるidの場合は一新される）
        // IndexRequest request = new IndexRequest("employeeindex");
        // request.id("001");
        // request.source("company", "SpaceX");
        // IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        // System.out.println("response id: " + indexResponse.getId());
        // System.out.println(indexResponse.getResult().name());

        // // update way2
        // // Elasticsearch を Map 型でデータ更新（元からデータがあるidの場合は一新される）
        // Map<String, Object> updateMap = new HashMap<String, Object>();
        // updateMap.put("firstname", "Sundar");
        // updateMap.put("lastname", "Pichai");
        // updateMap.put("company", "Google");
        // updateMap.put("sector", "IT");
        // IndexRequest request2 = new IndexRequest("employeeindex");
        // request2.id("002");
        // request2.source(updateMap);
        // IndexResponse indexResponseUpdate = client.index(request2,
        // RequestOptions.DEFAULT);
        // System.out.println("response id: " + indexResponseUpdate.getId());
        // System.out.println(indexResponse.getResult().name());

        EmployeePojo emp = new EmployeePojo("Elon01", "Musk01", LocalDate.now());
        // POJO を JSON 形式にして Elasticsearch のデータを更新
        IndexRequest request = new IndexRequest("employeeindex");
        request.id("001");
        request.source(new ObjectMapper().writeValueAsString(emp), XContentType.JSON);
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        System.out.println("response id:" + indexResponse.getId());
        System.out.println(indexResponse.getResult().name());
    }
}
