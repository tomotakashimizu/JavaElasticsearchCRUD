package elasticsearch.sample.update;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;

// Using API- UpdateByQueryRequest
public class UpdateQueryRequest {

    public static void main(String[] args) throws IOException {
        // Elasticsearch に接続
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // Using API- UpdateByQueryRequest
        // Map を作成
        Map<String, Object> updateMap2 = new HashMap<String, Object>();
        updateMap2.put("firstname", "Sundar01");
        updateMap2.put("lastname", "Pichai01");

        UpdateByQueryRequest updateByQueryRequest = new UpdateByQueryRequest("employeeindex");
        updateByQueryRequest.setConflicts("proceed");
        updateByQueryRequest.setQuery(new TermQueryBuilder("_id", "001"));
        Script script = new Script(ScriptType.INLINE, "painless", "ctx._source = params", updateMap2);
        updateByQueryRequest.setScript(script);

        try {
            BulkByScrollResponse bulkResponse = client.updateByQuery(updateByQueryRequest, RequestOptions.DEFAULT);
            long totalDocs = bulkResponse.getTotal();
            // 更新されたか id の確認
            System.out.println("updated response id: " + totalDocs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
